
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Task3_ParenthesesMatching {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        Deque<Character> stack = new ArrayDeque<>();

        for (Character c : input.toCharArray()) {

            Character popped;

            switch (c) {
                case '(':
                case '[':
                case '{':
                    stack.push(c);
                    continue;
                case ')':
                    popped = stack.pop();
                    if (popped != '(') {
                        System.out.println(false);
                        System.exit(0);
                    }
                    break;
                case ']':
                    popped = stack.pop();
                    if (popped != '[') {
                        System.out.println(false);
                        System.exit(0);
                    }
                    break;
                case '}':
                    popped = stack.pop();
                    if (popped != '{') {
                        System.out.println(false);
                        System.exit(0);
                    }
                    break;
                default:
                    break;
            }
        }

        System.out.println(true);
    }
}
