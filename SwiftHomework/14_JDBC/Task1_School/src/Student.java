
import java.time.LocalDate;

public class Student {
    String name;
    LocalDate enrollmentDate;
    Address address;

    Student(String name, LocalDate enrollmentDate, Address address) {
        this.name = name;
        this.enrollmentDate = enrollmentDate;
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("%s %s%n%s", name, enrollmentDate, address);
    }
    
    
}
