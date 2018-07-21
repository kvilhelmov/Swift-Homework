
import java.util.Scanner;

public class Task1f_ReadNNumbersOnNewLines {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        String draft = "";

        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();

            //draft += x + " ";
            draft = draft + x + " ";
        }

        System.out.println(draft);
    }
}
