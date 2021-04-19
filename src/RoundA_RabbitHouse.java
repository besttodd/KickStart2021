import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.abs;

public class RoundA_RabbitHouse {
    private final static int MAX_HEIGHT = 1;
    public static void main(String[] args) {
        long beginTime = System.nanoTime();

        List<int[][]> input = getInput();
        int caseNum = 0;
        int numBoxes = 0;

        for (int[][] grid : input) {
            caseNum++;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    numBoxes += checkAdjacentCells(i, j, grid);
                    //TODO if boxes added, check again
                }
            }
            System.out.println("Case #"+caseNum+": "+numBoxes);
            numBoxes = 0;
        }

        System.err.println("Done in " + ((System.nanoTime() - beginTime) / 1e9) + " seconds.");
    }

    private static int checkAdjacentCells(int x, int y, int[][] grid) {
        int count = 0;
        final int[][] ADJACENT_CELLS = new int[][]{{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        for (int[] adjacentCell : ADJACENT_CELLS) {
            int row = x + adjacentCell[0];
            int col = y + adjacentCell[1];
            //check for edges
            if (row < 0 || row >= grid.length || col < 0 || col >= grid[x].length) continue;
            while (abs(grid[row][col] - grid[x][y]) > RoundA_RabbitHouse.MAX_HEIGHT) {
                if (grid[row][col] > grid[x][y]) grid[x][y]++;
                else grid[row][col]++;
                count++;
                //System.out.println("Add a box!!");
            }
        }
        return count;
    }

    private static List<int[][]> getInput() {
        List<int[][]> input = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        int totalCases = in.nextInt();

        for (int t = 0; t < totalCases; t++) {
            int rows = in.nextInt();
            int cols = in.nextInt();
            int[][] grid = new int[rows][cols];

            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    grid[r][c] = in.nextInt();
                }
            }
            input.add(grid);
        }
        return input;
    }
}
