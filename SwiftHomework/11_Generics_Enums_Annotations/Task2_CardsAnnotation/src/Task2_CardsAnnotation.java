
public class Task2_CardsAnnotation {

    public static void main(String[] args) {

        printAnnotations(Card.class);
        printAnnotations(CardRank.class);
        printAnnotations(CardSuit.class);
    }

    private static void printAnnotations(Class cls) {
        CardsAnnotation annotation = (CardsAnnotation) cls.getAnnotation(CardsAnnotation.class);

        System.out.println(cls.getName() + " " + annotation.type() + " " + annotation.description());
    }
}
