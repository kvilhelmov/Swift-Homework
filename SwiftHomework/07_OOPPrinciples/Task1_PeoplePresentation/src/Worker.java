class Worker extends Person{
    private double _weekSalary;
    private double _workHoursPerDay;

    Worker(String firstName, String lastName, double weekSalary, double workHoursPerDay) {
        super(firstName, lastName);
        _weekSalary = weekSalary;
        _workHoursPerDay = workHoursPerDay;
    }

    @Override
    String getInfo() {
        
        return String.format("%s%n"
                + "Occupation: Worker%n"
                + "Week salary: %.2f%n"
                + "Hours per day: %f%n"
                + "Salary per hour: %.2f",
                getCommonInfo(), _weekSalary, _workHoursPerDay, _weekSalary / (_workHoursPerDay * 5) );
    }
}