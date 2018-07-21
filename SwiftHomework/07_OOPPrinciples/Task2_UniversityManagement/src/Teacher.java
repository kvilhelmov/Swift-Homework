class Teacher extends Employee{
    
    String[] _subjects;

    public Teacher(String name, String phoneNo, String[] subjects) {
        super(name, phoneNo, 25);
        _subjects = subjects;
    }

    @Override
    double work(Person[] people, int peopleCount) {
        
         for(int i = 0; i < peopleCount; i++){
            
            Person other = people[i];
            
            if(other instanceof MaintenanceEmployee){
                other.decreaseTolerance(3);
            } else if(other instanceof Student) {
                other.increaseTolerance(3);
            } else if(other instanceof AdministrationEmployee){
                other.decreaseTolerance(1);
            }
        }
         
        return -_hourlyRate;
    }
}