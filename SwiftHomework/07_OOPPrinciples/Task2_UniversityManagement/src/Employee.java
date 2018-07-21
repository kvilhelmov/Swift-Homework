abstract class Employee extends Person{
    double _hourlyRate;

    public Employee(String name, String phoneNo, double hourlyRate) {
        super(name, phoneNo);
        _hourlyRate = hourlyRate;
    }
}