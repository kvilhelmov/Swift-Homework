
import java.util.ArrayList;
import java.util.List;

public class Task1_Deck {

    public static void main(String[] args) {

        List<Card> deck = new ArrayList<>();

        for (CardSuit suit : CardSuit.values()) {
            for (CardRank rank : CardRank.values()) {
                deck.add(new Card(rank, suit));
            }
        }
        
        for(Card card : deck){
            System.out.println(card);
        }
        
    }
}
