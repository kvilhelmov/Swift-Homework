package accounts;

import customers.Customer;

public class DepositAccount extends Account{

    public DepositAccount(Customer owner, double balance, double monthlyInterestRate) {
        super(owner, balance, monthlyInterestRate);
    }
    
    public void withdraw(double amount){
        if(amount < 0){
            return;
        }
        
        setBalance(getBalance() - amount);
    }

    @Override
    public double getInterestFor(int months) {
        return this.getBalance() * months * this.getMonthlyInterestRate();
    }
    
}
