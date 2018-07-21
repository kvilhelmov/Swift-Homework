class Student extends Person{
    String _facultyNo;
    String[] _subjects;

    public Student(String name, String phoneNo, String facultyNo, String[] subjects) {
        super(name, phoneNo);
        _facultyNo = facultyNo;
        _subjects = subjects;
    }

    @Override
    double work(Person[] people, int peopleCount) {
        
        _tolerance += 2;
        
        for(int i = 0; i < peopleCount; i++){
            if(people[i] instanceof MaintenanceEmployee){
                people[i].decreaseTolerance(1);
            }
        }
        
        if(_tolerance >= 50){
            return 10;
        }
        
        return 0;
    }
}