package datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with ♥ by georgeplaton on 19.04.18.
 */
public class GraphDirectWeighted {

    List<GraphEdge> edges;

    int noVertices, noEdges;

    public GraphDirectWeighted(int noVertices, int noEdges) {
        this.noVertices = noVertices;
        this.noEdges = noEdges;
        edges = new ArrayList<GraphEdge>();
    }

    // Getters/Setters

    public List<GraphEdge> getEdges() {
        return edges;
    }

    public void setEdges(List<GraphEdge> edges) {
        this.edges = edges;
    }

    public int getNoVertices() {
        return noVertices;
    }

    public void setNoVertices(int noVertices) {
        this.noVertices = noVertices;
    }

    public int getNoEdges() {
        return noEdges;
    }

    public void setNoEdges(int noEdges) {
        this.noEdges = noEdges;
    }

    public void addEdge(int src, int dest, int weight) {
        //System.out.println("Adding edge [" + src + "][" + dest + "] with weight : " + weight);
        edges.add(new GraphEdge(src, dest, weight));
    }

    // bfs, dfs, shortest path

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
        int[] distances = new int[noVertices];
        Arrays.fill(distances, Integer.MAX_VALUE); // distances vector.

        // !! IMPORTANT !! Since we've marked this one with 0, it means all the distances are
        // calculated from this node on (his distance is set to 0, all the other nodes
        // will be referenced to it!
        // otherwise, the algorithm cannot start, since there is no distances entry point initialized!
        // it's really important to initialize this point!
        distances[startNode] = 0;

        // 1. Go through all vertices starting with the current node.
        for(int i = 1; i < noVertices; i++) {
            for(GraphEdge graphEdge : edges) {
                int srcVertex = graphEdge.src;
                int destVertex = graphEdge.dest;
                int weightVertex = graphEdge.weight;

                int newDistanceUpdate = distances[srcVertex] + weightVertex;

                if(distances[srcVertex] != Integer.MAX_VALUE  // means, we can compose crt node based on previous node distance!
                        &&  newDistanceUpdate < distances[destVertex]) {
                    // update the value
                    distances[destVertex] = newDistanceUpdate;
                }
            }
        }

        // 2. Based on above calculation we have found the shortest distance for all vertexes.
        // In case there are more updates, it means that there are negative cycles.
        // We won't take them into consideration, since we only want to calculate for noVertices time.

        return distances;
    }
}
