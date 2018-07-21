public class Task7_SwapVariables{

    public static void main(String[] args){
        
        int x = 5;
        int y = 12;
        
        // Solution 1
        int tmp = x;
        x = y;
        y = tmp;
        
        // Solution 2
        x ^= y;
        y ^= x;
        x ^= y;
        
        // Solution 3
        x = x + y;
        y = x - y;
        x = x - y;
        
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        
        
    }
}
