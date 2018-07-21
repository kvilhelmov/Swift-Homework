package customers;

public abstract class Customer {
    private String name;
    
    Customer(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
}
