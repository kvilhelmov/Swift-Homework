package accounts;

import customers.Customer;

public abstract class Account {
    private final String iban;
    private Customer owner;
    private double balance;
    private final double monthlyInterestRate;
    private static int count = 1;
        
    public abstract double getInterestFor(int months);

    Account(Customer owner, double balance, double monthlyInterestRate){
        this.owner = owner;
        this.iban = String.format("%08d", count++);
        this.setBalance(balance);
        this.monthlyInterestRate = monthlyInterestRate / 100;
    }
    
    public void deposit(double amount){
        if(amount < 0){
            return;
        }
        
        balance += amount;
    }
    
    protected double getBalance(){
        return balance;
    }
    
    protected final void setBalance(double balance){
        if(balance < 0) {
            return;
        }
        
        this.balance = balance;
    }
    
    protected double getMonthlyInterestRate(){
        return monthlyInterestRate;
    }
    
    public Customer getOwner(){
        return owner;
    }

    public String getIban() {
        return iban;
    }
}
