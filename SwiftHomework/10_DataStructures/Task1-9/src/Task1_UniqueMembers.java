
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class Task1_UniqueMembers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Set<Integer> ints = new HashSet<>();

        for (int i = 0; i < n; i++) {
            ints.add(sc.nextInt());
        }
        
        for(Integer i : ints){
            System.out.print(i + " ");
        }

    }
}
