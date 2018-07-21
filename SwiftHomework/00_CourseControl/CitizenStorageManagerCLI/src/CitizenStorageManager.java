
import educations.MySqlEducationRepository;
import exceptions.DALException;
import address.MySqlAddressStorage;
import education.*;
import personaldetails.*;
import address.Address;
import java.io.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import citizens.*;
import insurance.SocialInsuranceRecord;
import socialinsurance.*;

public class CitizenStorageManager {

    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.M.yyyy");

    static final String DB_CONN_STRING = "jdbc:mysql://192.168.136.133:3306/citizen_registrations";
    static final String DB_USERNAME = "root";
    static final String DB_PASSWORD = "swift12345";

    public static void main(String[] args) throws DALException {

        Scanner sc = getScanner(args);
        int n = sc.nextInt();
        sc.nextLine();

        wipeDatabase();
        System.out.println("All data truncated.");

        CitizenReposiotory citizenStorage = new MySqlCitizenRepository(DB_CONN_STRING, DB_USERNAME, DB_PASSWORD);

        String line = null;
        for (int i = 0; i < n; i++) {
            try {
                line = sc.nextLine();
                Citizen citizen = parseCitizen(line);

                List<SocialInsuranceRecord> records = parseSocInsRecords(line = sc.nextLine());
                records.forEach((record) -> {
                    citizen.addSocialInsuranceRecord(record);
                });

                citizenStorage.insertCitizen(citizen);

                if ((1.0 * i / n) * 100 % 5 == 0) {
                    System.out.printf("Progress %d%% [%d/%d]%n", 100 * i / n, i, n);
                }
            } catch (DALException ex) {
                System.out.println("line number: " + i);
                System.out.println(line);
                throw ex;
            }
        }
    }

    private static void wipeDatabase() throws DALException {
        new MySqlCitizenRepository(DB_CONN_STRING, DB_USERNAME, DB_PASSWORD).deleteAll();
        new MySqlAddressStorage(DB_CONN_STRING, DB_USERNAME, DB_PASSWORD).deleteAll();
        new MySqlEducationRepository(DB_CONN_STRING, DB_USERNAME, DB_PASSWORD).deleteAll();
        new MySqlSocialInsuranceRepository(DB_CONN_STRING, DB_USERNAME, DB_PASSWORD).deleteAll();
    }

    private static Citizen parseCitizen(String data) {

        String[] split = data.split(";", -1);

        Citizen citizen = parsePersonalDetails(split, 0);
        Address address = parseAddress(split, 6);
        citizen.setAddress(address);

        for (Education education : parseEducations(split, 14)) {
            citizen.addEducation(education);
        }

        return citizen;
    }

    private static Citizen parsePersonalDetails(String[] split, int idx) throws IllegalArgumentException {
        String firstName = split[idx++];
        String middleName = split[idx++];
        String lastName = split[idx++];
        char gender = Character.toUpperCase(split[idx++].toCharArray()[0]);
        LocalDate dateOfBirth = LocalDate.parse(split[idx++].trim(), FORMATTER);
        Gender genderEnum;
        switch (gender) {
            case 'm':
            case 'M':
                genderEnum = Gender.Male;
                break;
            case 'f':
            case 'F':
                genderEnum = Gender.Female;
                break;
            default:
                throw new IllegalArgumentException("Gender value inappropriate.");
        }
        short height = Short.parseShort(split[idx++].trim());

        return new Citizen(firstName, middleName, lastName, genderEnum, height, dateOfBirth);
    }

    private static Address parseAddress(String[] split, int idx) {
        String country = split[idx++].trim();
        String city = split[idx++].trim();
        String municipality = split[idx++].trim();
        String postalCode = split[idx++].trim();
        String street = split[idx++].trim();
        String number = split[idx++].trim();
        String floorString = split[idx++].trim();
        String apartmentNoString = split[idx++].trim();

        Integer floor = floorString.isEmpty() ? null : Integer.parseInt(floorString);
        Integer apartmentNo = apartmentNoString.isEmpty() ? null : Integer.parseInt(apartmentNoString);

        return new Address(country, city, municipality, postalCode, street, number, floor, apartmentNo);
    }

    private static List<Education> parseEducations(String[] split, int idx) throws IllegalArgumentException {

        List<Education> educations = new ArrayList<>();

        while (idx < split.length) {
            Education education = null;
            String degree = split[idx++].trim();
            String institutionName = split[idx++].trim();
            LocalDate enrollmentDate = LocalDate.parse(split[idx++].trim(), FORMATTER);
            LocalDate graduationDate = LocalDate.parse(split[idx++].trim(), FORMATTER);

            switch (degree.toUpperCase()) {
                case "P":
                    education = new PrimaryEducation(institutionName, enrollmentDate, graduationDate);
                    break;
                case "S":
                    education = new SecondaryEducation(institutionName, enrollmentDate, graduationDate);
                    break;
                case "B":
                    education = new HigherEducation(institutionName, enrollmentDate, graduationDate, EducationDegree.Bachelor);
                    break;
                case "M":
                    education = new HigherEducation(institutionName, enrollmentDate, graduationDate, EducationDegree.Master);
                    break;
                case "D":
                    education = new HigherEducation(institutionName, enrollmentDate, graduationDate, EducationDegree.Doctorate);
                    break;
                default:
                    throw new IllegalArgumentException("Degree value inappropriate. Expected - P, S, B, M, D.");
            }

            if (education instanceof GradedEducation && graduationDate.isBefore(LocalDate.now())) {
                float grade = Float.parseFloat(split[idx++].trim());
                ((GradedEducation) education).gotGraduated(grade);
            }

            educations.add(education);
        }

        return educations;
    }

    private static Scanner getScanner(String[] args) {
        if (args.length > 0 && !args[0].isEmpty()) {
            File file = new File(args[0]);
            if (file.isFile()) {
                try {
                    return new Scanner(new FileInputStream(args[0]));
                } catch (FileNotFoundException ex) {
                    System.out.println("File " + file.getName() + " was not found. Reading from console.");
                }
            }
        }
        return new Scanner(System.in);
    }

    private static List<SocialInsuranceRecord> parseSocInsRecords(String line) {
        List<SocialInsuranceRecord> list = new ArrayList<>();
        String[] split = line.split(";", -1);

        for (int i = 0; i < split.length; i += 3) {
            int year = Integer.parseInt(split[i].trim());
            int month = Integer.parseInt(split[i + 1].trim());
            BigDecimal amount = new BigDecimal(split[i + 2].trim());

            list.add(new SocialInsuranceRecord(year, month, amount));
        }

        return list;
    }
}
