
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class Task5_ChildPlay {

    static int naiveSolution(List<Integer> heights) {
        int rounds = -1;

        for (int startCount = 0; startCount != heights.size(); rounds++) {

            List<Integer> toBeRemoved = new ArrayList<>();

            startCount = heights.size();
            for (int i = 0; i < heights.size() - 1; i++) {
                if (heights.get(i) < heights.get(i + 1)) {
                    // add the indecies of the items to be removed, backwards
                    // because when removing the indexing is changed and the saved
                    // indexes are no longer valid
                    toBeRemoved.add(0, i + 1);
                }
            }

            for (Integer i : toBeRemoved) {
                heights.remove((int) i);
            }

        }

        return rounds;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        List<Integer> heights = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            heights.add(sc.nextInt());
        }

        //System.out.println(naiveSolution(heights));
        System.out.println(dynamicProgrammingSolution(heights));

    }

    private static int dynamicProgrammingSolution(List<Integer> heights) {
        Deque<Integer> neighbours = new ArrayDeque<>();
        int[] rounds = new int[heights.size()];

        neighbours.push(0);

        for (int i = 1; i < heights.size(); i++) {

            int maxRounds = 0;

            while (!neighbours.isEmpty() && heights.get(neighbours.peek()) >= heights.get(i)) {

                int idx = neighbours.pop();
                maxRounds = Math.max(maxRounds, rounds[idx]);
            }

            if (!heights.isEmpty()) {
                rounds[i] = maxRounds + 1;
            }

            neighbours.push(i);
        }

        int max = 0;
        for (int i = 0; i < rounds.length; i++) {
            if (max < rounds[i]) {
                max = rounds[i];
            }
        }
        return max;
    }
}
