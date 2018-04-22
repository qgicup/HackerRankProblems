package matrix;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * Consider a matrix with  rows and  columns, where each cell contains either a  or a  and any cell containing a  is called a filled cell. Two cells are said to be connected if they are adjacent to each other horizontally, vertically, or diagonally; in other words, cell  is connected to cells , , , , , , , and , provided that the location exists in the matrix for that .
 *
 * If one or more filled cells are also connected, they form a region. Note that each cell in a region is connected to at least one other cell in the region but is not necessarily directly connected to all the other cells in the region.
 *
 * Task
 * Given an  matrix, find and print the number of cells in the largest region in the matrix. Note that there may be more than one region in the matrix.
 *
 * Input Format
 *
 * The first line contains an integer, , denoting the number of rows in the matrix.
 * The second line contains an integer, , denoting the number of columns in the matrix.
 * Each line  of the  subsequent lines contains  space-separated integers describing the respective values filling each row in the matrix.
 *
 * Constraints
 *
 * Output Format
 *
 * Print the number of cells in the largest region in the given matrix.
 *
 * Sample Input
 *
 * 4
 * 4
 * 1 1 0 0
 * 0 1 1 0
 * 0 0 1 0
 * 1 0 0 0
 * Sample Output
 *
 * 5
 * Explanation
 *
 * The diagram below depicts two regions of the matrix; for each region, the component cells forming the region are marked with an X:
 *
 * X X 0 0     1 1 0 0
 * 0 X X 0     0 1 1 0
 * 0 0 X 0     0 0 1 0
 * 1 0 0 0     X 0 0 0
 * The first region has five cells and the second region has one cell. Because we want to print the number of cells in the largest region of the matrix, we print .
 * Created with ♥ by georgeplaton on 19.04.18.
 */
public class CountConnectedCells {

    /**
     * We use these 2 layers, to detect if a index around our index, is actually a
     * neighbour (checking the rows and cols indexes)
     */
    static int[] rowsNeighboursLayer = new int[] {-1, -1, -1,  0, 0,  1, 1, 1};
    static int[] colsNeighboursLayer = new int[] {-1,  0,  1, -1, 1, -1, 0, 1};


    /***
     * Will go in depth of this matrix, and try to mark as visited all the neighbours of
     * the current index
     * @param mat           - 2 dimensional array
     * @param visited       - 2 dimensional array to mark which items were visited
     * @param crtRow        - the number of rows
     * @param crtCol        - the number of cols
     * @param matRows       - the number of mat rows
     * @param matCols       - the number of mat cols
     */
    static int dfs(int[][] mat, int[][] visited, int crtRow, int crtCol, int matRows, int matCols) {
        visited[crtRow][crtCol] = 1; // mark current row/col as 1.

        // a cell only has 8 neighbours, so let's go through them
        // we need to advance both with rowsIndex and colsIndex, so we're having only one loop.
        //          [-1, -1] [-1, 0]  [-1, 1]
        //          [0, -1]   0     [0, 1]          -> this will get substracted from the current row, which now is 0 (in center)
        //          [1, -1] [1, 0]  [1, 1]

        int countRegionItems = 0;

        for(int i = 0; i < 8; i++) {            // we just go 8 times
            int neighbourRow = rowsNeighboursLayer[i] + crtRow;     // we substract from the current row
            int neighbourCol = colsNeighboursLayer[i] + crtCol;     // we substract from the current col

            if(neighbourRow >= 0 && neighbourCol >= 0
                    && neighbourRow < matRows && neighbourCol < matCols
                    && visited[neighbourRow][neighbourCol] == 0
                    && mat[neighbourRow][neighbourCol] == 1) {
                // go to the other neighbours, and mark all the items as visited, as long as we're having 1's.
                countRegionItems++;
                countRegionItems += dfs(mat, visited, neighbourRow, neighbourCol, matRows, matCols);
            }
        }
        return countRegionItems;
    }

    /**
     * Will count the number of connected cells in a matrix.
     *  A connected component of an undirected graph is a subgraph
     *  in which every two vertices are connected to each other by a path(s),
     *  and which is connected to no other vertices outside the subgraph.
     * @param mat          - 2 dimensional matrix
     * @param matRows      - the number of rows
     * @param matCols      - the number of cols
     * @return          return the count of connected components in the matrix
     */
    static int countConnectedCells(int[][] mat, int matRows, int matCols) {
        int[][] visited = new int[matRows][matCols];
        for(int i = 0; i < matRows; i++)
            Arrays.fill(visited[i], 0);

        int maxRegion = 0;

        for(int i = 0; i < matRows; i++) {
            for(int j = 0; j < matCols; j++) {

                if(visited[i][j] == 0 && mat[i][j] == 1) {
                    // Start searching this island using DFS
                    int countPerThisRegion = 1 + dfs(mat, visited, i, j, matRows, matCols); // we've found one here
                    if(countPerThisRegion > maxRegion)
                        maxRegion = countPerThisRegion;
                }
            }
        }
        return maxRegion;
    }

    /**
     * Input Format
     *
     * The first line contains an integer, , denoting the number of rows in the matrix.
     * The second line contains an integer, , denoting the number of columns in the matrix.
     * Each line  of the  subsequent lines contains  space-separated integers describing the respective values filling each row in the matrix.
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int mat[][] = new int[n][m];
        for(int grid_i=0; grid_i < n; grid_i++){
            for(int grid_j=0; grid_j < m; grid_j++){
                mat[grid_i][grid_j] = in.nextInt();
            }
        }

        // At this point the matrix has been constructed.
        System.out.print(countConnectedCells(mat, n, m));
    }

}
