
import advanced.ReversePolishNotation;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

public class Task9_Test {

    final static double DELTA = 1e-6;
    Map<String, Integer> operatorPrecedence;

    public Task9_Test() {
        operatorPrecedence = new HashMap<>();
        operatorPrecedence.put("(", 0);
        operatorPrecedence.put(")", 0);
        operatorPrecedence.put("^", 3);
        operatorPrecedence.put("*", 2);
        operatorPrecedence.put("/", 2);
        operatorPrecedence.put("+", 1);
        operatorPrecedence.put("-", 1);
    }

    @Test
    public void test1() {
        String input = "(5 + 3) * 2 - 11 / 2^2";

        ReversePolishNotation rpn = new ReversePolishNotation(operatorPrecedence);
        double result = rpn.calculateExpression(input);

        assertEquals(13.25, result, DELTA);
    }

    @Test
    public void test2() {
        String input = "(((15-7*5)/6+3)^2*(65-23)-3)/2*11";

        ReversePolishNotation rpn = new ReversePolishNotation(operatorPrecedence);
        double result = rpn.calculateExpression(input);

        assertEquals(9.166667, result, DELTA);
    }

    @Test
    public void test3() {
        String input = "5-3+2*3/4-11^2/(3+6)";

        ReversePolishNotation rpn = new ReversePolishNotation(operatorPrecedence);
        double result = rpn.calculateExpression(input);

        assertEquals(-9.944444, result, DELTA);
    }

    @Test
    public void test4() {
        String input = "5*3-2*(15/4)^1.25/11+6.3";

        ReversePolishNotation rpn = new ReversePolishNotation(operatorPrecedence);
        double result = rpn.calculateExpression(input);

        assertEquals(20.351196, result, DELTA);
    }

    @Test
    public void test5() {
        String input = "-5.4/9";

        ReversePolishNotation rpn = new ReversePolishNotation(operatorPrecedence);
        double result = rpn.calculateExpression(input);

        assertEquals(-0.6, result, DELTA);
    }

    @Test
    public void test6() {
        String input = "-(3+4)*9^2";

        ReversePolishNotation rpn = new ReversePolishNotation(operatorPrecedence);
        double result = rpn.calculateExpression(input);

        assertEquals(-567, result, DELTA);
    }

    @Test
    public void test7() {
        String input = "3.5*2.16-9.11^(1.3*1.22)";

        ReversePolishNotation rpn = new ReversePolishNotation(operatorPrecedence);
        double result = rpn.calculateExpression(input);

        assertEquals(-25.690344, result, DELTA);
    }

}
