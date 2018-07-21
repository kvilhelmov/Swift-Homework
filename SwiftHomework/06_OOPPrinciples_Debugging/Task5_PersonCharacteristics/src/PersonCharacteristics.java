
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PersonCharacteristics{

    public static void main(String []args){
        
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        sc.nextLine();
        
        for(int i = 0 ; i < n; i++){
            Person person = readPerson(sc.nextLine());
            
            System.out.println(person.getInfo());
        }
    }

    private static Person readPerson(String data) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
        
        String[] split = data.split(";");
        
        String firstName = split[0];
        String lastName = split[1];
        char gender = Character.toUpperCase(split[2].toCharArray()[0]);
        short height = Short.parseShort(split[3].trim());
        LocalDate dateOfBirth = LocalDate.parse(split[4].trim(), formatter);

        String institutionName = split[5].trim();
        LocalDate enrollmentDate = LocalDate.parse(split[6].trim(), formatter);
        LocalDate graduationDate = LocalDate.parse(split[7].trim(), formatter);
        
        SecondaryEducation education = new SecondaryEducation(institutionName, enrollmentDate, graduationDate);
        
        if(split.length >= 9){
            education.gotGraduated(Float.parseFloat(split[8].trim()));
        }
        
        Person person = new Person(firstName, lastName, gender, height, dateOfBirth);
        person.addEducation(education);
        
        return person;
    }
}
