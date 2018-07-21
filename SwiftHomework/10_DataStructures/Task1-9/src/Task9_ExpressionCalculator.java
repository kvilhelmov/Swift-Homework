
import advanced.ReversePolishNotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task9_ExpressionCalculator {

    public static void main(String[] args) {

        Map<String, Integer> operatorPrecedence = new HashMap<>();
        operatorPrecedence.put("(", 0);
        operatorPrecedence.put(")", 0);
        operatorPrecedence.put("^", 3);
        operatorPrecedence.put("*", 2);
        operatorPrecedence.put("/", 2);
        operatorPrecedence.put("+", 1);
        operatorPrecedence.put("-", 1);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        ReversePolishNotation rpn = new ReversePolishNotation(operatorPrecedence);
        double result = rpn.calculateExpression(input);
        System.out.format("%.6f%n", result);
    }
}
