
public class Task1_Cards {

    public static void main(String[] args) {

        System.out.println(printEnumValues(CardSuit.class));
        
        System.out.println(printEnumValues(CardRank.class));
    }

    private static String printEnumValues(Class cls) {
        
        StringBuilder sb = new StringBuilder();
        Object[] values = cls.getEnumConstants();
        
        for (int i = 0; i < values.length - 1; i++) {
            sb.append(values[i]).append(", ");
        }
        sb.append(values[values.length - 1]);
        
        return sb.toString();
    }
}
