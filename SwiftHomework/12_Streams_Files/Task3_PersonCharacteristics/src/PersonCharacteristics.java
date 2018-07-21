
import education.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonCharacteristics {

    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.M.yyyy");

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        if (args.length > 0 && !args[0].isEmpty()) {
            File file = new File(args[0]);
            if (file.isFile()) {
                try {
                    sc = new Scanner(new FileInputStream(args[0]));
                } catch (FileNotFoundException ex) {
                    System.out.println("File " + file.getName() + " was not found.");
                    return;
                }
            }
        }

        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            Person person = readPerson(sc.nextLine());

            System.out.println(person);
            System.out.println();
        }
    }

    private static Person readPerson(String data) {

        String[] split = data.split(";");

        Person person = readPersonalDetails(split, 0);
        Address address = readAddress(split, 6);
        person.setCurrentAddress(address);

        for (Education education : readEducations(split, 14)) {
            person.addEducation(education);
        }

        return person;
    }

    private static Person readPersonalDetails(String[] split, int idx) throws IllegalArgumentException {
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

        return new Person(firstName, middleName, lastName, genderEnum, height, dateOfBirth);
    }

    private static Address readAddress(String[] split, int idx) {
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

    private static List<Education> readEducations(String[] split, int idx) throws IllegalArgumentException {

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
}
