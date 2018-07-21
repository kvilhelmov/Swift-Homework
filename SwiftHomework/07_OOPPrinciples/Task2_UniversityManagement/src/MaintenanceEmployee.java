class MaintenanceEmployee extends Employee{

    public MaintenanceEmployee(String name, String phoneNo) {
        super(name, phoneNo, 15);
    }

    @Override
    double work(Person[] people, int peopleCount) {
        
        for(int i = 0; i < peopleCount; i++){
            people[i].increaseTolerance(2);
        }
        
        return -_hourlyRate;
    }
    
}