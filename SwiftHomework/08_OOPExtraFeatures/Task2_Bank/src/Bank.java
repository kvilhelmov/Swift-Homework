
import accounts.Account;
import accounts.DepositAccount;
import accounts.LoanAccount;
import accounts.MortgageAccount;
import customers.CompanyCustomer;
import customers.Customer;
import customers.IndividualCustomer;
import java.util.Scanner;

public class Bank {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Account[] accounts = new Account[100];
        int accountCount = 0;

        String line;
        while (!(line = sc.nextLine()).equalsIgnoreCase("END")) {
            String[] split = line.split(" ");
            String command = split[0].trim();
            String nameOrIban = split[1].trim();
            double balance;
            Account account;

            switch (command.toUpperCase()) {
                case "OPEN":
                    int clientType = Integer.parseInt(split[2].trim());
                    int accountType = Integer.parseInt(split[3].trim());
                    balance = Double.parseDouble(split[4].trim());
                    double monthlyInterestRate = Double.parseDouble(split[5].trim());

                    Customer customer;
                    if (clientType == 1) {
                        customer = new IndividualCustomer(nameOrIban);
                    } else if (clientType == 2) {
                        customer = new CompanyCustomer(nameOrIban);
                    } else {
                        //should not happen
                        return;
                    }

                    if (accountType == 1) {
                        account = new DepositAccount(customer, balance, monthlyInterestRate);
                    } else if (accountType == 2) {
                        account = new LoanAccount(customer, balance, monthlyInterestRate);
                    } else if (accountType == 3) {
                        account = new MortgageAccount(customer, balance, monthlyInterestRate);
                    } else {
                        //should not happen
                        return;
                    }

                    accounts[accountCount++] = account;
                    System.out.println(account.getIban());
                    
                    break;
                case "PUT":
                    balance = Double.parseDouble(split[2].trim());
                    account = findAccount(accounts, nameOrIban);
                    if (account == null) {
                        //should not happen
                        return;
                    } else {
                        account.deposit(balance);
                    }
                    break;
                case "GET":
                    balance = Double.parseDouble(split[2].trim());
                    account = findAccount(accounts, nameOrIban);
                    if (account == null || !(account instanceof DepositAccount)) {
                        //should not happen
                        return;
                    } else {
                        ((DepositAccount) account).withdraw(balance);
                    }
                    break;
                case "INFO":
                    account = findAccount(accounts, nameOrIban);
                    int months = Integer.parseInt(split[2].trim());
                    System.out.println(account.getInterestFor(months));
                    break;
                default:
                    // no such command
                    return;
            }
        }
    }

    private static Account findAccount(Account[] accounts, String nameOrIban) {
        for (Account current : accounts) {
            if (current.getIban().equalsIgnoreCase(nameOrIban)
                    || current.getOwner().getName().equalsIgnoreCase(nameOrIban)) {

                return current;
            }
        }

        return null;
    }
}
