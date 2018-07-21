
public class Card implements Comparable<Card> {

    CardRank rank;
    CardSuit suit;

    Card(CardRank rank, CardSuit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public int compareTo(Card other) {
        return this.rank.compareTo(other.rank);
    }

    @Override
    public String toString() {
        return this.rank + " of " + this.suit;
    }

}
