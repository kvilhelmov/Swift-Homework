
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task6_WordReplacement {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String text = sc.nextLine().toLowerCase();
        int n = sc.nextInt();
        sc.nextLine();

        Map<String, String> dictionary = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] split = sc.nextLine().split(" ");

            dictionary.put(split[0].trim().toLowerCase(), split[1].trim().toLowerCase());
        }

        String result = "";
        String currentWord = "";

        for (Character c : text.toCharArray()) {
            if ('a' <= c && c <= 'z') {
                currentWord += c;
            } else {

                if (dictionary.containsKey(currentWord)) {
                    result += dictionary.get(currentWord);
                } else {
                    result += currentWord;
                }

                result += c;

                currentWord = "";
            }
        }

        if (!currentWord.isEmpty()) {
            if (dictionary.containsKey(currentWord)) {
                result += dictionary.get(currentWord);
            } else {
                result += currentWord;
            }
        }

        System.out.println(result);
    }
}
