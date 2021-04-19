import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

/*
Kick Start 2021 - Round A
L Shaped Plots
https://codingcompetitions.withgoogle.com/kickstart/round/0000000000436140/000000000068c509
*/
public class RoundA_LShapedPlots {
    public static void main(String[] args) {
        long beginTime = System.nanoTime();

        List<int[][]> input = getInput();
        int caseNum = 0;
        int numShapes = 0;

        List<Cell[]> hSegments;
        List<Cell[]> vSegments;

        for (int[][] grid : input) {
            caseNum++;
            //find 'good segments within the grid
            //System.out.println("Horizontal");
            hSegments = findHSegments(grid.length, grid[0].length, grid);
            //System.out.println("Vertical");
            vSegments = findVSegments(grid.length, grid[0].length, grid);
            //System.out.println("==================");

            //find the cell common in both directions
            for (Cell[] vSegment : vSegments) {
                for (Cell[] hSegment : hSegments) {
                    for (Cell c : hSegment) {
                        for (Cell c2 : vSegment) {
                            if (c.equals(c2)) {
                                numShapes+= findLShapes(c, grid);
                            }
                        }
                    }
                }
            }

            System.out.println("Case #"+caseNum+": "+numShapes);
            numShapes = 0;
        }
        System.err.println("Done in " + ((System.nanoTime() - beginTime) / 1e9) + " seconds.");
    }

    private static int findLShapes(Cell temp, int[][] grid) {
        int maxUp = 0, maxDown = 0, maxLeft = 0, maxRight = 0, count = 0;

            //check Up direction
            for (int z = temp.getRow()-1; z > -1; z--) {
                if (grid[z][temp.getCol()] == 1) count++;
                else break;
                if (count > maxUp) maxUp = count;
            }
            maxUp++; //include the Endpoint itself
            count = 0;

            //check left direction
            for (int y = temp.getCol()-1; y > -1; y--) {
                if (grid[temp.getRow()][y] == 1) count++;
                else break;
                if (count > maxLeft) maxLeft = count;
            }
            maxLeft++; //include the endpoint
            count = 0;

            //check down direction
            for (int x = temp.getRow()+1; x < grid.length; x++) {
                if (grid[x][temp.getCol()] == 1) { count++; }
                else break;
                if(count > maxDown) maxDown = count;
            }
            maxDown++;
            count = 0;

            //check right direction
            for (int w = temp.getCol()+1; w < grid[0].length; w++) {
                if (grid[temp.getRow()][w] == 1) { count++; }
                else break;
                if(count > maxRight) maxRight = count;
            }
            maxRight++; //include the endpoint
            count = 0;

        if (maxUp >= 2) count+= max(0, min(maxUp/2, maxLeft)-1);
        if (maxUp >= 2) count+= max(0, min(maxUp/2, maxRight)-1);
        if (maxDown >= 2) count+= max(0, min(maxDown/2, maxLeft)-1);
        if (maxDown >= 2) count+= max(0, min(maxDown/2, maxRight)-1);
        if (maxLeft >= 2) count+= max(0, min(maxLeft/2, maxUp)-1);
        if (maxLeft >= 2) count+= max(0, min(maxLeft/2, maxDown)-1);
        if (maxRight >= 2) count+= max(0, min(maxRight/2, maxUp)-1);
        if (maxRight >= 2) count+= max(0, min(maxRight/2, maxDown)-1);

        return count;
    }

    private static List<Cell[]> findHSegments(int row, int col, int[][] grid) {
        List<Cell[]> segments = new ArrayList<>();
        List<Integer> direction = new ArrayList<>();
        int count = 1;

        for(int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    direction.add(j);
                    if (direction.contains(j-1)) count++;
                }
            }
            if (count > 1) {
                Cell[] segment = new Cell[direction.size()];
                for (int z = 0; z < direction.size(); z++) {
                    segment[z] = new Cell(i, direction.get(z));
                }
                segments.add(segment);
            }
            count = 1;
            direction.clear();
        }
        //for (Cell[] segment : segments) for (Cell c : segment) System.out.println(c.showCell());
        return segments;
    }

    private static List<Cell[]> findVSegments(int row, int col, int[][] grid) {
        List<Cell[]> segments = new ArrayList<>();
        List<Integer> direction = new ArrayList<>();
        int count = 1;

        for(int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (grid[j][i] == 1) {
                    direction.add(j);
                    if (direction.contains(j-1)) count++;
                }
            }
            if (count > 1) {
                Cell[] segment = new Cell[direction.size()];
                for (int z = 0; z < direction.size(); z++) {
                    segment[z] = new Cell(direction.get(z), i);
                }
                segments.add(segment);
            }
            count = 1;
            direction.clear();
        }
        //for (Cell[] segment : segments) for (Cell c : segment) System.out.println(c.showCell());
        return segments;
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

    private static class Cell {
        int row, col;

        public Cell(int r, int c) {
            row = r;
            col = c;
        }

        public boolean equals(Cell object) { return object.getRow() == row && object.getCol() == col; }

        public String showCell() { return "("+row+","+col+")"; }

        public int getRow() { return row; }

        public int getCol() { return col; }

    }
}
