
public class Card implements Comparable<Card> {

    CardRank rank;
    CardSuit suit;

    Card(CardRank rank, CardSuit suit) {
        this.rank = rank;
        this.suit = suit;
    }
    
    public int getValue(){
        return this.rank.getValue();
    }

    @Override
    public int compareTo(Card other) {
        return this.rank.compareTo(other.rank);
    }

    @Override
    public String toString() {
        return this.rank + " of " + this.suit;
    }

    public static Card parseCard(String input) {
        String failureMessage = "Could not parse input: " + input;

        if (input.length() != 2) {
            throw new IllegalArgumentException(failureMessage);
        }

        char rankChar = input.charAt(0);
        char suitChar = input.charAt(1);

        CardRank rank;
        switch (rankChar) {
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                rank = CardRank.valueOf(rankChar - '0');
                break;
            case 'T':
                rank = CardRank.Ten;
                break;
            case 'J':
                rank = CardRank.Jack;
                break;
            case 'Q':
                rank = CardRank.Queen;
                break;
            case 'K':
                rank = CardRank.King;
                break;
            case 'A':
                rank = CardRank.Ace;
                break;
            default:
                throw new IllegalArgumentException(failureMessage);
        }

        CardSuit suit;
        switch (suitChar) {
            case 'c':
                suit = CardSuit.Clubs;
                break;
            case 'd':
                suit = CardSuit.Diamonds;
                break;
            case 'h':
                suit = CardSuit.Hearts;
                break;
            case 's':
                suit = CardSuit.Spades;
                break;
            default:
                throw new IllegalArgumentException(failureMessage);
        }
        
        return new Card(rank, suit);
    }
}
