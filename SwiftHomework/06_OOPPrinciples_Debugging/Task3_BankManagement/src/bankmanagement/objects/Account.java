package bankmanagement.objects;

import security.Credentials;

class Account{
    
    private Credentials _credentials;
    private String      _name;
    private String      _govId;
    
    private double      _balance;
    
    protected Account(Credentials credentials, String name, String govId){
        _credentials = credentials;
        _name = name;
        _govId = govId;
        
        _balance = 0;
    }
    
    void deposit(double amount){
        _balance += amount;
    }
    
    void withdraw(double amount){
        _balance -= amount;
    }
   
    boolean hasAccess(String password){
        return _credentials.matchPassword(password);
    }
    
    double getBalance(){
        return _balance;
    }
    
    String getName(){
        return _name;
    }
    
    String getGovId(){
        return _govId;
    }
    
    String getUsername(){
        return _credentials.getUsername();
    }
}