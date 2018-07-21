
import education.Education;
import education.GradedEducation;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

final class Person {

    private String firstName;
    private String middleName;
    private String lastName;

    private char gender;
    private short height;
    private final LocalDate dateOfBirth;
    
    private Address currentAddress;
    private Education education;

    Person(String firstName, String middleName, String lastName, char gender, short height,
            LocalDate dateOfBirth) {

        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
        setGender(gender);
        setHeight(height);

        this.dateOfBirth = dateOfBirth;
    }

    public short getAge() {
        return (short) dateOfBirth.until(LocalDate.now(), ChronoUnit.YEARS);
    }

    @Override
    public String toString() {

        String heOrShe;
        String hisOrHer;

        if (gender == 'M') {
            heOrShe = "He";
            hisOrHer = "His";
        } else {
            heOrShe = "She";
            hisOrHer = "Her";
        }

        String result = String.format("%s %s %s is %d years old.", firstName, middleName, lastName, getAge());
        result += String.format(" %s was born in %d.%n", heOrShe, dateOfBirth.getYear());

        if (isUnderAged()) {
            result += String.format("%s %s %s is under-aged.%n", firstName, middleName, lastName);
        }

        if (this.currentAddress != null) {
            result += String.format("%s lives at:%n%s", heOrShe, currentAddress);
        }

        result += String.format("%n%s started %s degree in %s on %s",
                heOrShe, education.getDegree().toLowerCase(),
                education.getInstitutionName(), education.getEnrollmentDate());

        if (education.isGraduated()) {

            result += String.format(" and finished on %s.", education.getGraduationDate());

            if (education instanceof GradedEducation) {
                result += String.format(" %s grade was %.3f.", hisOrHer, ((GradedEducation) education).getFinalGrade());
            }

        } else {
            result += String.format(" and is supposed to graduate on %s.", education.getGraduationDate());
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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = Character.toUpperCase(gender);
    }

    public Address getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(Address currentAddress) {
        this.currentAddress = currentAddress;
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

    public void setEducation(Education education) {
        this.education = education;
    }
}
