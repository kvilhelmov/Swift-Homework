package advanced;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReversePolishNotation {

    private static final String NUMBER_PATTERN = "\\d+(\\.\\d+)?";

    private final Map<String, Integer> operatorPrecedence;
    private final Set<String> operators;

    public ReversePolishNotation(Map<String, Integer> operatorPrecedence) {
        this.operatorPrecedence = new HashMap<>(operatorPrecedence);
        this.operators = operatorPrecedence.keySet();
    }

    public Deque<String> getPostfixExpressionStack(String source) {
        Deque<String> rpn = new ArrayDeque<>();
        Deque<String> stack = new ArrayDeque<>();

        List<String> split = tokenSplit(source);

        for (String token : split) {
            if (token.matches(NUMBER_PATTERN)) {

                // if token is a number put on result queue
                rpn.add(token);

            } else if (token.equals("(")) {

                // if it is opening parenthesis put on operator stack (highest priority)
                stack.push(token);

            } else if (token.equals(")")) {

                // if it is closing parenthesis, pop operators until opening parenthesis is found
                while (!stack.peek().equals("(")) {
                    rpn.add(stack.pop());
                }

                stack.pop(); // remove the current token --> "("

            } else if (operatorPrecedence.keySet().contains(token)) {

                //if token is an operator pop all operators with smaller or equal precedence
                while (!stack.isEmpty()
                        && operatorPrecedence.get(token) <= operatorPrecedence.get(stack.peek())) {

                    rpn.add(stack.pop());
                }

                stack.push(token);
            }
        }

        // remaining operators should be appended in the end
        while (!stack.isEmpty()) {
            rpn.add(stack.pop());
        }

        return rpn;
    }

    public double calculateExpression(String source) {

        Deque<String> rpn = getPostfixExpressionStack(source);

        Deque<Double> stack = new ArrayDeque<>();

        while (!rpn.isEmpty()) {
            String element = rpn.pop();

            if (element.matches(NUMBER_PATTERN)) {
                //if it is a number, put in stack
                stack.push(Double.parseDouble(element));
            } else if (operators.contains(element)) {
                //if it is an operator, pull 2 items from stack and do the operation
                //if we had non binary operators (say unary or functions), then we should
                //pop the respective number of items from stack
                double op2 = stack.pop();
                double op1 = stack.pop();
                double result = calculate(op1, element, op2);
                stack.push(result);
            }
        }

        //in the end the remaining number in the stack
        //is the answer of the expression
        return stack.pop();
    }

    public String asString(String source) {

        return String.join(" ", getPostfixExpressionStack(source));
    }

    private List<String> tokenSplit(String input) {
        input = input.replace(" ", "");

        List<String> tokenList = new ArrayList<>();

        Pattern numberRegex = Pattern.compile(NUMBER_PATTERN);
        Matcher numberMatcher = numberRegex.matcher(input);
        boolean moreNumbers = numberMatcher.find();

        if (input.charAt(0) == '-') {
            tokenList.add("0");
        }

        for (int i = 0; i < input.length(); i++) {
            if (moreNumbers && i == numberMatcher.start()) {

                tokenList.add(numberMatcher.group());
                i = numberMatcher.end() - 1;
                moreNumbers = numberMatcher.find();
            } else if (operators.contains(Character.toString(input.charAt(i)))) {
                tokenList.add(Character.toString(input.charAt(i)));
            }
        }

        return tokenList;
    }

    private static double calculate(double op1, String operator, double op2) {
        double result = 0;
        switch (operator) {
            case "+":
                result = op1 + op2;
                break;
            case "-":
                result = op1 - op2;
                break;
            case "*":
                result = op1 * op2;
                break;
            case "/":
                result = op1 / op2;
                break;
            case "^":
                result = Math.pow(op1, op2);
                break;
        }

        return result;
    }

}
