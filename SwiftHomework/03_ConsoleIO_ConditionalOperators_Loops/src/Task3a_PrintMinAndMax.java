
import java.util.Scanner;

public class Task3a_PrintMinAndMax {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int current = sc.nextInt();
        int min = current;
        int max = current;
        for (int i = 1; i < n; i++) {
            current = sc.nextInt();
            if (current < min) {
                min = current;
            } else if (current > max) {
                max = current;
            }
        }

        System.out.print(min + " " + max);
    }
}
