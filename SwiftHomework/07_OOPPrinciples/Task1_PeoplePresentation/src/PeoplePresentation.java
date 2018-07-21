
import java.util.Scanner;

class PeoplePresentation{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String line;
        
        while( ! (line = sc.nextLine()).equalsIgnoreCase("END")){
            String[] split = line.split(" ");
            
            String firstName = split[0].trim();
            String lastName = split[1].trim();
            
            Person person = null;
            
            if(split.length == 5){ // it is a Student
                String facultyNo = split[2].trim();
                int lectureCount = Integer.parseInt(split[3].trim());
                int exerciseCount = Integer.parseInt(split[4].trim());
                
                person = new Student(firstName, lastName, facultyNo, lectureCount, exerciseCount);
                
            } else { // length will be 4 and defining a Worker
                double weekSalary = Double.parseDouble(split[2].trim());
                double workHoursPerDay = Double.parseDouble(split[3].trim());
                
                person = new Worker(firstName, lastName, weekSalary, workHoursPerDay);
            }
            
            System.out.println(person.getInfo());
            System.out.println();
        }
    }
}