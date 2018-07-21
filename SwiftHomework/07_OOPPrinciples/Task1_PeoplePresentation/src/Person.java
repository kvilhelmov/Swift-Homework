abstract class Person{
    private String _firstName;
    private String _lastName;
    
    Person(String firstName, String lastName){
        _firstName = firstName;
        _lastName = lastName;
    }
    
    abstract String getInfo();
    
    String getCommonInfo(){
        
        return String.format("First name: %s%nLast name: %s", _firstName, _lastName);
    }
}