
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Task7_StackMax {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line;

        Deque<Integer> mainStack = new ArrayDeque<>();
        Deque<Integer> maxStack = new ArrayDeque<>();

        while (!(line = sc.nextLine()).equalsIgnoreCase("END")) {
            String[] split = line.split(" ");

            String command = split[0].trim().toUpperCase();

            switch (command) {
                case "PUSH":
                    int number = Integer.parseInt(split[1].trim());
                    mainStack.push(number);
                    int currentMax = maxStack.isEmpty() ? Integer.MIN_VALUE : maxStack.peek();
                    maxStack.push(Math.max(number, currentMax));
                    break;
                case "POP":
                    System.out.println(mainStack.pop());
                    maxStack.pop();
                    break;
                case "MAX":
                    System.out.println(maxStack.peek());
                    break;
                default:
                    break;
            }
        }

        while (!mainStack.isEmpty()) {
            System.out.print(mainStack.pop() + " ");
        }
    }
}
