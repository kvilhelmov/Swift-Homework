import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

final class Person {

    private String              firstName;
    private String              lastName;
    private char                gender;
    private final LocalDate     dateOfBirth;
    private short               height;
    
    private SecondaryEducation  education;

    Person(String firstName, String lastName, char gender, short height, LocalDate dateOfBirth) {
        
        setFirstName(firstName);
        setLastName(lastName);
        setGender(gender);
        setHeight(height);
        
        this.dateOfBirth = dateOfBirth;
    }

    public short getAge() {
        return (short) dateOfBirth.until(LocalDate.now(), ChronoUnit.YEARS);
    }

    public String getInfo() {

        String heOrShe;
        String hisOrHer;

        if (gender == 'M') {
            heOrShe = "He";
            hisOrHer = "His";
        } else {
            heOrShe = "She";
            hisOrHer = "Her";
        }

        String result = String.format("%s %s is %d years old.", firstName, lastName, getAge());
        result += String.format(" %s was born in %d.", heOrShe, dateOfBirth.getYear());
        result += String.format(" %s started %s on %s", heOrShe, education.getInstitutionName(), education.getEnrollmentDate());
        if(education.isGraduated()){
            result += String.format(" and finished on %s with a grade of %.3f.", education.getGraduationDate(), education.getFinalGrade());
        } else{
            result += String.format(" and is supposed to graduate on %s.", education.getGraduationDate());
        }

        if (isUnderAged()) {
            result += String.format(" %s %s is under-aged.", firstName, lastName);
        }

        return result;
    }

    public boolean isUnderAged() {
        return getAge() < 18;
    }
    
    // Accessors

    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
       this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
       this.lastName = lastName;
    }
    
    public char getGender(){
        return gender;
    }
    
    public void setGender(char gender) {
       this.gender = gender;
    }
    
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public short getHeight() {
        return height;
    }

    public void setHeight(short height) {
        this.height = height;
    }
    
    public void addEducation(SecondaryEducation education){
        this.education = education;
    }
}
