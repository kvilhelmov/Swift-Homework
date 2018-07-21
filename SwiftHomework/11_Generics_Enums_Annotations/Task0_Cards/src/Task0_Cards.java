
import java.util.Arrays;
import java.util.stream.Collectors;

public class Task0_Cards {

    public static void main(String[] args) {

        System.out.println(
                String.join(", ",
                        Arrays.asList(CardSuit.values()).stream()
                                .map(x -> x.toString())
                                .collect(Collectors.toList())));

        System.out.println(
                String.join(", ",
                        Arrays.asList(CardRank.values()).stream()
                                .map(x -> x.toString())
                                .collect(Collectors.toList())));
        
    }
}
