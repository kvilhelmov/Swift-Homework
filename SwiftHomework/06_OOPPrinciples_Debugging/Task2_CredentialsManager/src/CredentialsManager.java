import java.util.Scanner;

public class CredentialsManager{
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        String line;
        
        Credentials[] credentials = new Credentials[200];
        int credentialCount = 0;
        
        while( ! (line = sc.nextLine()).equalsIgnoreCase(("END"))){
            
            String[] split = line.split(" ");
            String command = split[0];
            String target = split[1].trim();
            String targetPass = split[2].trim();
            
            switch(command.toUpperCase()){
                case "ENROLL":
                    credentials[credentialCount++] = new Credentials(target, targetPass);
                    System.out.println("ENROLL success");
                    break;
                case "CHPASS":
                    for (int i = 0; i < credentialCount; i++) {
                        String source = credentials[i].getUsername();
                        
                        if( source.equalsIgnoreCase(target) ){
                            
                            boolean changePasswordSuccessful = credentials[i].tryChangePassword(targetPass, split[3].trim());
                            
                            if( changePasswordSuccessful ){
                                System.out.println("CHPASS success");
                            } else{
                                System.out.println("CHPASS fail");
                            }
                            
                            break;
                        }
                    }
                    break;
                case "AUTH":
                    for (int i = 0; i < credentialCount; i++) {
                        String source = credentials[i].getUsername();
                        
                        if( source.equalsIgnoreCase(target) ){
                            if(credentials[i].matchPassword(targetPass)) {
                                System.out.println("AUTH success");
                            } else{
                                System.out.println("AUTH fail");
                            }
                            
                            break;
                        }
                    }
                    break;
                default:
                    // wrong command
                    break;
            }
        }
    }
}