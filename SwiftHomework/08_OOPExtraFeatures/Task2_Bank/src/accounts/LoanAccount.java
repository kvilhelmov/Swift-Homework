package accounts;

import customers.*;

public class LoanAccount extends Account {

    public LoanAccount(Customer owner, double balance, double monthlyInterestRate) {
        super(owner, balance, monthlyInterestRate);
    }

    @Override
    public double getInterestFor(int months) {
        Customer owner = getOwner();

        if (owner instanceof IndividualCustomer) {
            return Math.min(0, (months - 3) * getBalance() * getMonthlyInterestRate());
        } else if (owner instanceof CompanyCustomer) {
            return Math.min(0, (months - 2) * getBalance() * getMonthlyInterestRate());
        } else {
            return -1;
        }
    }

}
