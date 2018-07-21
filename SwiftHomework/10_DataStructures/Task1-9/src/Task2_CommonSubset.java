
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Task2_CommonSubset {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        
        for(int i = 0 ; i < n; i++){
            set1.add(sc.nextInt());
        }
        
        for(int i = 0 ; i < n; i++){
            set2.add(sc.nextInt());
        }
        
        boolean printed = false;
        for(Integer i : set1){
            if(set2.contains(i)){
                System.out.print(i + " ");
                printed = true;
            }
        }
        
        if(!printed){
            System.out.println("NO");
        }
    }
}
