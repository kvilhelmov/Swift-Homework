package bankmanagement;

import bankmanagement.objects.*;
import java.util.Scanner;

public class BankManagement {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        String line;
        
        Bank bank = new Bank();
        
        while( ! (line = sc.nextLine()).equalsIgnoreCase("END") ){
            String[] split = line.split(" ");
            String command = split[0].trim();
            String username = split[1].trim();
            String password;
            double amount;
            
            String result = "";
            
            switch(command.toUpperCase()){
                case "OPEN":
                    result = "OPEN ";
                    password = split[2].trim();
                    String name = split[3].trim();
                    String govId = split[4].trim();
                    
                    result += bank.openAccount(username, password, name, govId)
                            ? "success"
                            : "fail";
                    
                    break;
                case "CLOSE":
                    result = "CLOSE ";
                    password = split[2].trim();
                    
                    result += bank.closeAccount(username, password)
                            ? "success"
                            : "fail";
                    
                    break;
                case "DEPOSIT":
                    result = "DEPOSIT ";
                    amount = Double.parseDouble(split[2].trim());
                    
                    result += bank.deposit(username, amount)
                            ? "success"
                            : "fail";
                    
                    break;
                case "TRANSFER":
                    result = "TRANSFER ";
                    password = split[2].trim();
                    amount = Double.parseDouble(split[3].trim());
                    String recipient = split[4].trim();
                    
                    result += bank.transfer(username, password, amount, recipient)
                            ? "success"
                            : "fail";
                    
                    break;
                case "WITHDRAW":
                    result = "WITHDRAW ";
                    password = split[2].trim();
                    amount = Double.parseDouble(split[3].trim());
                    
                    result += bank.withdraw(username, password, amount)
                            ? "success"
                            : "fail";
                    
                    break;
            }
            
            System.out.println(result);
        }
        
        System.out.println(bank.getAssets());
        bank.printAccounts();
    }
}
