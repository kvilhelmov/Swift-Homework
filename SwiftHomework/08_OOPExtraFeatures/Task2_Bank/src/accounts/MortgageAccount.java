package accounts;

import customers.*;

public class MortgageAccount extends Account {

    public MortgageAccount(Customer owner, double balance, double monthlyInterestRate) {
        super(owner, balance, monthlyInterestRate);
    }

    @Override
    public double getInterestFor(int months) {
        Customer owner = getOwner();

        if (owner instanceof IndividualCustomer) {
            return Math.min(0, (months - 6) * getBalance() * getMonthlyInterestRate());
        } else if (owner instanceof CompanyCustomer) {
            if(months <= 12){
                return months * getBalance() * getMonthlyInterestRate() * 0.5;
            } else{
                return (months - 6) * getBalance() * getMonthlyInterestRate();
            }
        } else {
            return -1;
        }
    }

}
