
import java.util.HashMap;
import java.util.Map;


public enum CardRank implements Comparable<CardRank> {
    Two(2),
    Three(3),
    Four(4),
    Five(5),
    Six(6),
    Seven(7),
    Eight(8),
    Nine(9),
    Ten(10),
    Jack(11),
    Queen(12),
    King(13),
    Ace(14);

    private final int value;

    private static final Map<Integer, CardRank> lookup = new HashMap<>();

    static{
        for(CardRank rank : values()){
            lookup.put(rank.getValue(), rank);
        }
    }
    
    private CardRank(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static CardRank valueOf(int value){
        return lookup.get(value);
    }
}
