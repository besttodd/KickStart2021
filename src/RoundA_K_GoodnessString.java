/*
Kick Start 2021 - Round A
K-Goodness String
https://codingcompetitions.withgoogle.com/kickstart/round/0000000000436140/000000000068cca3
Charles defines the goodness score of a string as the number of indices i such that Si≠SN−i+1
where 1≤i≤N/2 (1-indexed). For example, the string CABABC has a goodness score of 2 since S2≠S5
and S3≠S4.

Charles gave Ada a string S of length N, consisting of uppercase letters and asked her to convert it
into a string with a goodness score of K. In one operation, Ada can change any character in the
string to any uppercase letter. Could you help Ada find the minimum number of operations required to
transform the given string into a string with goodness score equal to K?

Sample Input     Sample Output
2
5 1
ABCAA            Case #1: 0
4 2
ABAA             Case #2: 1
*/

import java.util.Locale;
import java.util.Scanner;

class RoundA_K_GoodnessString { //Change to "Soultion" when submitting
    public static void main(String[] args) {
        long beginTime = System.nanoTime();
        try {
            Scanner in = new Scanner(System.in);
            int totalCases = in.nextInt();
            int caseNum = 1;

            while (totalCases > 0) {
                int stringLength = in.nextInt();
                int goodnessGoal = in.nextInt();
                String input = in.next().toUpperCase(Locale.ROOT);
                int operations;
                int score = getGoodnessScore(input);

                if (score == goodnessGoal) {
                    operations = 0;
                } else if (score > goodnessGoal) {
                    operations = score - goodnessGoal;
                } else {
                    operations = goodnessGoal - score;
                }

                System.out.println("Case #" + caseNum + ": " + operations);
                totalCases--;
                caseNum++;
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
        System.err.println("Done in " + ((System.nanoTime() - beginTime) / 1e9) + " seconds.");
    }

    private static int getGoodnessScore(String input) {
        int score = 0;
        for (int z = 0; z < input.length() / 2; z++) {
            if (input.charAt(z) != input.charAt(input.length() - 1 - z)) {
                score++;
            }
        }
        return score;
    }
}
