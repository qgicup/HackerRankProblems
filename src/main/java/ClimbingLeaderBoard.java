import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Alice is playing an arcade game and wants to climb to the top of the leaderboard. Can you help her track her ranking as she plays? The game uses Dense Ranking, so its leaderboard works like this:
 *
 * The player with the highest score is ranked number  on the leaderboard.
 * Players who have equal scores receive the same ranking number, and the next player(s) receive the immediately following ranking number.
 * We want to determine Alice's rank as she progresses up the leaderboard. For example, the four players on the leaderboard have high scores of , , , and . Those players will have ranks , , , and , respectively. If Alice's scores are , and , her rankings after each game are ,  and .
 *
 * You are given an array, , of monotonically decreasing leaderboard scores, and another array, , of Alice's scores for the game. You must print  integers. The  integer should indicate the current rank of alice after her  game.
 *
 * Input Format
 *
 * The first line contains an integer , the number of players on the leaderboard.
 * The next line contains  space-separated integers , the leaderboard scores in decreasing order.
 * The next line contains an integer, , denoting the number games Alice plays.
 * The last line contains  space-separated integers , her game scores.
 *
 * Constraints
 *
 *  for
 *  for
 * The existing leaderboard, , is in descending order.
 * Alice's scores , are in ascending order.
 * Subtask
 *
 * For  of the maximum score:
 *
 * Output Format
 *
 * Print  integers. The  integer should indicate the rank of alice after playing the  game.
 *
 * Sample Input 0
 *
 * 7
 * 100 100 50 40 40 20 10
 * 4
 * 5 25 50 120
 * Sample Output 0
 *
 * 6
 * 4
 * 2
 * 1
 * Explanation 0
 *
 * Alice starts playing with  players already on the leaderboard, which looks like this:
 *
 * image
 *
 * After Alice finishes game , her score is  and her ranking is :
 *
 * image
 *
 * After Alice finishes game , her score is  and her ranking is :
 *
 * image
 *
 * After Alice finishes game , her score is  and her ranking is tied with Caroline at :
 *
 * image
 *
 * After Alice finishes game , her score is  and her ranking is :
 *
 * Created with ♥ by georgeplaton on 21.04.18.
 */
public class ClimbingLeaderBoard {

    /**
     * Will calculate Alice's ranking based on the given scores.
     * @param scores    - an array of scores of people already played e.g [100 50 50 40 40 20 10] -> sorted in decreasing order
     * @param alice     - an array of n Alice's scores. For each of these scores, we need to calculate the ranking in the given array above.
     *                    E.g [5 25 50 120] -> sorted in increasing order.
     * @return
     */
    static int[] climbingLeaderboard(int[] scores, int[] alice) {

        // A solution would be to go through the alice scores, and then try to position them inside the score's.
        // This would be O(n * m), where n = leaderboard scores length and m = alice score lenght.

        // Another option would be to calculate a map of the scores and their ranking. And then we will iterate the array once, to find Alice's ranking.
        // E.g []

        // Another option would be to go through the arrays at the same time.

        // Let's remove the duplicates from scores, no reason to go many times through the same duplicates.
        List<Integer> scoresUnique = removeDuplicates(scores);

        int[] rankings = new int[alice.length];
        int rankingsIndex = alice.length - 1;
        int j = 0;

        for(int i = rankings.length - 1; i >= 0; i--) { // going through Alice score's in decreasing order.
            int aliceScore = alice[i];

            while(j < scoresUnique.size()) {
                int crtScore = scoresUnique.get(j);

                if(aliceScore >= crtScore) {
                    if(j == 0)
                        rankings[rankingsIndex--] = 1;
                    else
                        rankings[rankingsIndex--] = j + 1;

                    break;
                }
                j++;
            }

            if(j == scoresUnique.size())
                if(rankingsIndex <= 0)
                    System.out.println("oups");
                else
                    rankings[rankingsIndex--] = j + 1;
        }

        return rankings;
    }

    /*
     * Method to remove duplicates from array in Java, without using
     * Collection classes e.g. Set or ArrayList. Algorithm for this
     * method is simple, it first sort the array and then compare adjacent
     * objects, leaving out duplicates, which is already in result.
     */
    public static List<Integer> removeDuplicates(int[] array) {

        List<Integer> result = new ArrayList<>();

        int previous = array[0];
        result.add(previous);

        for (int i = 1; i < array.length; i++) {
            int crt = array[i];
            if (crt != previous) {
                result.add(crt);
            }

            previous = crt;
        }
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int scoresCount = Integer.parseInt(scanner.nextLine().trim());

        int[] scores = new int[scoresCount];

        String[] scoresItems = scanner.nextLine().split(" ");

        for (int scoresItr = 0; scoresItr < scoresCount; scoresItr++) {
            int scoresItem = Integer.parseInt(scoresItems[scoresItr].trim());
            scores[scoresItr] = scoresItem;
        }

        int aliceCount = Integer.parseInt(scanner.nextLine().trim());

        int[] alice = new int[aliceCount];

        String[] aliceItems = scanner.nextLine().split(" ");

        for (int aliceItr = 0; aliceItr < aliceCount; aliceItr++) {
            int aliceItem = Integer.parseInt(aliceItems[aliceItr].trim());
            alice[aliceItr] = aliceItem;
        }

        int[] result = climbingLeaderboard(scores, alice);

        for (int resultItr = 0; resultItr < result.length; resultItr++) {
            //bufferedWriter.write(String.valueOf(result[resultItr]));
            System.out.println(String.valueOf(result[resultItr]));

        }

    }
}
