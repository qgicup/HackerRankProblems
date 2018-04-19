package datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * This is a Graph with matrix representation
 *
 * Created with ♥ by georgeplaton on 19.04.18.
 */

public class GraphMat {

    public static final int MAT_NULL_VALUE = 0;

    int[][] mat;        // will hold weight, if there is an edge from i to j, otherwise Integer.MAX_VALUE.
    int noVertices;

    public GraphMat(int noVertices) {
        this.noVertices = noVertices;
        mat = new int[noVertices][noVertices];

        // Fill each row with 1.0
        for (int[] row: mat)
            Arrays.fill(row, MAT_NULL_VALUE);
    }

    public GraphMat(int[][] mat, int noVertices) {
        this.mat = mat;
        this.noVertices = noVertices;
    }

    // Getters/Setters
    public int[][] getMat() {
        return mat;
    }

    public void setMat(int[][] mat) {
        this.mat = mat;
    }

    public int getNoVertices() {
        return noVertices;
    }

    public void setNoVertices(int noVertices) {
        this.noVertices = noVertices;
    }

    public void addEdge(int src, int dest, int weight) {
        System.out.println("Adding edge [" + src + "][" + dest + "] with weight : " + weight);
        mat[src][dest] = weight;
    }

    // BFS, DFS, Djikstra, Bellman-Ford

    void bfs() {

    }

    void dfs() {

    }

    void shortestPathUsingDjkistra(GraphNode startNode) {
        // TODO
    }

    /**
     * Will find the shortest path using Bellman-Ford algorithm.
     *
     * This algorithm goes through all the vertices several times.
     * It looks for adjacent nodes pointing to current node, and see if their weight + their distance is smaller than
     * current distance stored for this current node.
     * It will do this, until there are no more changes and optimizations.
     *
     * It is said that this algorithm can get into a negative cycle. We need to protect it from that as well.
     *
     * @param startNode     - the starting node
     * @return
     */
    public int[] shortestPathUsingBellmanFord(int startNode) {
        int[] distances = new int[noVertices]; // will hold the updated distances

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startNode] = 0;

        boolean changesExists = true;  // will stop when there are no more optimizations to be done - usually O(V*E)
        while(changesExists) {
            changesExists = false;

            // Go through all the vertices in order
            for(int i = 0; i < noVertices - 1; i++) { // go through rows
                // current vertex is [i]

                // we need to get now all the nodes adjacent to i (nodes that are pointing to i)

                //   A B   -> i = rows, j = columns
                // A 0 1
                // B 2 0    => A->B(1), B->A(2)
                // we need to find B's adjacent nodes to it.
                // in this case it's A with weight of the edge being 1.

                for(int col = 0; col < noVertices; col++) { // go through columns
                    int weightCrt = mat[i][col];

                    if(weightCrt != MAT_NULL_VALUE
                            && distances[i] > (distances[col] + weightCrt)) {
                        distances[i] = distances[col] + weightCrt;
                        changesExists = true;
                    }
                }
            }
        }

        return distances;
    }

    public void printMat() {
        for(int i =0;i < noVertices; i++) {
            for(int j=0; j < noVertices; j++) {

                System.out.print(mat[i][j] + " ");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }

}

