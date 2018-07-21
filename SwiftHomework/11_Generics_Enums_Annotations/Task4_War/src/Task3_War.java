
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class Task3_War {

    static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        Deque<Card> p1 = readDeck(n);
        Deque<Card> p2 = readDeck(n);

        int round = 0;
        while (!p1.isEmpty() && !p2.isEmpty()) {
            round++;

            Card card1 = p1.poll();
            Card card2 = p2.poll();

            int comparisonResult = card1.compareTo(card2);

            if (comparisonResult < 0) {
                p2.add(card2);
                p2.add(card1);
            } else if (comparisonResult > 0) {
                p1.add(card1);
                p1.add(card2);
            } else {
                war(round, p1, p2, card1, card2);
            }
        }

        System.out.printf("Player %d wins on round %d.%n", p1.isEmpty() ? 2 : 1, round);
    }

    private static void war(int round, Deque<Card> p1, Deque<Card> p2, Card card1, Card card2) {
        int power1 = 0;
        int power2 = 0;

        Deque<Card> warCards1 = new ArrayDeque<>();
        Deque<Card> warCards2 = new ArrayDeque<>();

        while (power1 == power2) {
            if (p1.isEmpty() && p2.isEmpty()) {
                System.out.println("Game is draw on round " + round + ".");
                System.exit(0);
            }

            for (int i = 0; i < 3; i++) {
                Card c;
                if (!p1.isEmpty()) {
                    c = p1.poll();
                    power1 += c.getValue();
                    warCards1.add(c);
                }
                if (!p2.isEmpty()) {
                    c = p2.poll();
                    power2 += c.getValue();
                    warCards2.add(c);
                }
            }
        }

        if (power1 < power2) {
            p2.add(card2);
            p2.add(card1);
            while (!warCards2.isEmpty()) {
                p2.add(warCards2.poll());
            }
            while (!warCards1.isEmpty()) {
                p2.add(warCards1.poll());
            }
        } else {
            p1.add(card1);
            p1.add(card2);
            while (!warCards1.isEmpty()) {
                p1.add(warCards1.poll());
            }
            while (!warCards2.isEmpty()) {
                p1.add(warCards2.poll());
            }
        }
    }

    private static Deque<Card> readDeck(int n) {
        Deque<Card> p1 = new ArrayDeque<>();

        String line = sc.nextLine();
        String[] split = line.split(" ");

        for (int i = 0; i < n; i++) {
            p1.add(Card.parseCard(split[i]));
        }

        return p1;
    }
}
