class Student extends Person{
    private String _facultyNumber;
    private int _lectureCount;
    private int _exerciseCount;

    public Student(String firstName, String lastName, String facultyNumber, int lectureCount, int exerciseCount) {
        super(firstName, lastName);
        _facultyNumber = facultyNumber;
        _lectureCount = lectureCount;
        _exerciseCount = exerciseCount;
    }

    @Override
    String getInfo() {
        return String.format("%s%n"
                + "Occupation: Student%n"
                + "Faculty number: %s%n"
                + "Hours per day: %.2f",
                getCommonInfo(), _facultyNumber, (2 * _lectureCount + 1.5 * _exerciseCount) / 5);
    }
}