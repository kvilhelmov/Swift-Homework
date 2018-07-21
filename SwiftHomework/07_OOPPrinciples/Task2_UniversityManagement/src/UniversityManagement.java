
import java.util.Arrays;
import java.util.Scanner;

class UniversityManagement{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String line;
        
        Person[] people = new Person[500];
        int peopleCount = 0;
        double balance = 500;
        
        while( ! (line = sc.nextLine()).equalsIgnoreCase("END")){
            String[] split = line.split(" ");
            String command = split[0].trim();
            
            switch(command.toUpperCase()){
                case "NEW":{
                    String occupation = split[1].trim();
                    String name = split[2].trim();
                    String phoneNo = split[3].trim();
                    
                    switch(occupation.toUpperCase()){
                        case "ADMIN":{
                            String[] subjects = Arrays.copyOfRange(split, 4, split.length - 1);
                            people[peopleCount++] = new AdministrationEmployee(name, phoneNo, subjects);
                            break;
                        }
                        case "TEACH":{
                            String[] subjects = Arrays.copyOfRange(split, 4, split.length - 1);
                            people[peopleCount++] = new Teacher(name, phoneNo, subjects);
                            break;
                        }
                        case "MAINT":{
                            people[peopleCount++] = new MaintenanceEmployee(name, phoneNo);
                            break;
                        }
                        case "STUD":{
                            String facultyNo = split[4].trim();
                            String[] subjects = Arrays.copyOfRange(split, 5, split.length - 1);
                            people[peopleCount++] = new Student(name, phoneNo, facultyNo, subjects);
                            break;
                        }
                    }
                    break;
                }
                case "WORK":{
                    String name = split[1].trim();
                    Person person = findPerson(name, people, peopleCount);
                    balance += person.work(people, peopleCount);
                    
                    if(balance < 0){
                        System.out.println("Bankrupcy");
                        return;
                    }
                    
                    for(int i = 0 ; i < peopleCount; i++){
                        if(people[i].getTolerance() <= 0){
                            System.out.println(people[i].getName() + " is not happy.");
                            return;
                        }
                    }
                    break;
                }
                case "IDLE":{
                    
                    for(int i = 0 ; i < peopleCount; i++){
                        people[i].decreaseTolerance(5);
                        if(people[i].getTolerance() <= 0){
                            System.out.println(people[i].getName() + " is not happy.");
                            return;
                        }
                    }
                    break;
                }                    
            }
        }
        
        System.out.println(balance);
    }

    private static Person findPerson(String name, Person[] people, int peopleCount) {
        for(int i = 0 ; i < peopleCount; i++){
            if(people[i].getName().equals(name))
                return people[i];
        }
        
        return null;
    }
}