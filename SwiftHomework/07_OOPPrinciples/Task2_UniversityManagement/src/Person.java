abstract class Person{
    String _name;
    String _phoneNo;
    int _tolerance;
    
    Person(String name, String phoneNo){
        _name = name;
        _phoneNo = phoneNo;
        _tolerance = 20;
    }
    
    /**
     * Returns the outcome of the work done by an individual.
     * If it is a student, it would result into a positive number that is
     * the donation he or she makes. If it is a worker it will result to a 
     * negative number showing the price for the work done.
     * @param people    affected objects
     * @param peopleCount count of affected objects
     * @return          change in balance
     */
    abstract double work(Person[] people, int peopleCount);
    
    String getName(){
        return _name;
    }
    
    int getTolerance(){
        return _tolerance;
    }
    
    void decreaseTolerance(int value){
        _tolerance -= value;
    }

    void increaseTolerance(int value) {
        _tolerance += value;
    }
}