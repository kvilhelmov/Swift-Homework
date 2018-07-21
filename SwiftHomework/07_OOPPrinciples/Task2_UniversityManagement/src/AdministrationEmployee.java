class AdministrationEmployee extends Employee{
    
    String[] _subjects;

    public AdministrationEmployee(String name, String phoneNo, String[] subjects) {
        super(name, phoneNo, 19);
        _subjects = subjects;
    }
    
    @Override
    double work(Person[] people, int peopleCount) {
        
        for(int i = 0; i < peopleCount; i++){
            
            Person other = people[i];
            
            if(other instanceof MaintenanceEmployee){
                other.increaseTolerance(1);
            } else if(other instanceof Teacher || other instanceof Student) {
                other.increaseTolerance(3);
            }
        }
        
        return -_hourlyRate;
    }
}