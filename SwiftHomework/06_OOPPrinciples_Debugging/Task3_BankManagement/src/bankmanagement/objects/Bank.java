package bankmanagement.objects;

import security.Credentials;

public class Bank {

    private final int MAX_ACCOUNT_COUNT = 5;

    private static int accountCount = 0;

    private double _assets;

    private Account[] _accounts;

    public Bank() {
        _accounts = new Account[MAX_ACCOUNT_COUNT];
    }

    public boolean openAccount(String username, String password, String name, String govId) {
        if (accountCount == MAX_ACCOUNT_COUNT) {
            return false;
        }

        Credentials credentials = new Credentials(username, password);

        Account account = new Account(credentials, name, govId);

        _accounts[accountCount++] = account;
        
        return true;
    }

    public boolean closeAccount(String username, String password) {
        int accountIdx = findAccountIdx(username);

        if ( ! _accounts[accountIdx].hasAccess(password)) {
            return false;
        }
            
        _assets -= _accounts[accountIdx].getBalance();
        accountCount--;
        _accounts[accountIdx] = _accounts[accountCount];
        return true;
    }

    public boolean deposit(String username, double amount) {
        int accountIdx = findAccountIdx(username);
        
         if(amount < 0) 
            return false;
        
        _assets += amount;
        _accounts[accountIdx].deposit(amount);
        
        return true;
    }

    public boolean withdraw(String username, String password, double amount) {
        int accountIdx = findAccountIdx(username);

        if ( ! _accounts[accountIdx].hasAccess(password)) {
            return false;
        }
        
        if(amount < 0) 
            return false;
        if(amount > _accounts[accountIdx].getBalance()) 
            return false;
        
        _assets -= amount;
        _accounts[accountIdx].withdraw(amount);
        
        return true;
    }

    public boolean transfer(String username, String password, double amount, String recipientUsername) {
        int accountIdx = findAccountIdx(username);
        int recipientAccountIdx = findAccountIdx(recipientUsername);

        if ( ! _accounts[accountIdx].hasAccess(password)) {
            return false;
        }
        
        if(amount < 0) 
            return false;
        if(amount > _accounts[accountIdx].getBalance()) 
            return false;
        
        _accounts[accountIdx].withdraw(amount);
        _accounts[recipientAccountIdx].deposit(amount);
        
        return true;
    }

    private int findAccountIdx(String username) {
        for (int i = 0; i < accountCount; i++) {
            if (_accounts[i].getUsername().equalsIgnoreCase(username)) {
                return i;
            }
        }

        System.out.println("Account not found.");
        return -1;
    }
    
    public double getAssets(){
        return _assets;
    }
    
    public void printAccounts(){
        for(int i = 0 ; i < accountCount ; i++ ){
            Account account = _accounts[i];
            
            System.out.printf("%s, %s, %.2f%n", 
                    account.getName(), account.getGovId(), account.getBalance());
        }
    }
}
