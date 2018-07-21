import java.util.Scanner;

public class Task2e_PrintFirstDigit{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        
        while(n >= 10){
            n /= 10;
        }
        
        System.out.println(n);
    }
}