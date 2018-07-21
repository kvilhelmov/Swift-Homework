
import java.util.Scanner;

public class Task0_NumberNotANumber {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line;

        while (!(line = sc.nextLine()).equalsIgnoreCase("END")) {
            try {
                Integer.parseInt(line);
                System.out.println("Number");
            } catch (NumberFormatException ex) {
                System.out.println("Not a number");
            }
        }
    }
}
