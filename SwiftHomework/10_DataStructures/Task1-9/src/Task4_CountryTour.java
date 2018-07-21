
import java.util.Scanner;

public class Task4_CountryTour {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] distances = new int[n];
        int[] foods = new int[n];

        for (int i = 0; i < n; i++) {
            distances[i] = sc.nextInt();
            foods[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {

            int food = foods[i];

            for (int j = 1; j <= n; j++) {
                int distance = distances[(i + j) % n];

                food -= distance;

                if (food < 0) {
                    break;
                }

                food += foods[(i + j) % n];
            }

            if (food >= 0) {
                System.out.println(i + 1);
                System.exit(0);
            }
        }

        System.out.println("NO");
    }
}
