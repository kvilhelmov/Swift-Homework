
public class Teacher {

    String name;
    String email;
    double salary;

    Teacher(String name, String email, double salary) {
        this.name = name;
        this.email = email;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("%s %s %f", this.name, this.email, this.salary);
    }
    
    
}
