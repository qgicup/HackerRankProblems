package datastructures;

import java.util.*;

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
        if(src > noVertices || dest > noVertices)
            return;
        edges.add(new GraphEdge(src, dest, weight));
    }

    // bfs, dfs, shortest path

    /**
     * Will find the shortest path using Bellman-Ford algorithm. It is a Dynamic Programming algorithm, which constructs
     * the solution in a bottom-up manner.
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
        boolean changes = true;

        while(changes) {            // this while loop might go for a long time, in case we have negative values.
            changes = false;
            for(GraphEdge graphEdge : edges) {
                int srcVertex = graphEdge.src;
                int destVertex = graphEdge.dest;
                int weightVertex = graphEdge.weight;

                if(distances[srcVertex] != Integer.MAX_VALUE  // means, we can compose crt node based on previous node distance!
                        &&  (distances[srcVertex] + weightVertex) < distances[destVertex]) {

                    // update the value
                    distances[destVertex] = distances[srcVertex] + weightVertex;
                    changes = true;
                }
            }
        }

        // 2. Based on above calculation we have found the shortest distance for all vertexes.
        // In case there are more updates, it means that there are negative cycles.
        // We won't take them into consideration, since we only want to calculate for noVertices time.

        return distances;
    }

    /**
     * Will do a simple BFS and find the shortest Path associated with it,
     * by counting the hops.
     * @param startNode the starting node
     * @return
     */
    public int[] shortestPathUsingBFS(int startNode) {
        int[] distances = new int[noVertices];
        Arrays.fill(distances, -1); // O(n) space.
        Queue<Integer> que = new LinkedList<>();

        que.add(startNode); // Initialize queue.
        distances[startNode] = 0;
        HashSet<Integer> seenList = new HashSet<>(); // O(n) space. Could be further optimized. I leave it to you to optimize it.

        seenList.add(startNode);
        while(!que.isEmpty()) { // Standard BFS loop.
            Integer curr = que.poll();

            for(GraphEdge graphEdge : edges) {
                // go through the adjacency nodes of this "curr" node
                if(graphEdge.src == curr && !seenList.contains(graphEdge.dest)) {
                    que.offer(graphEdge.dest); // similar to add, but manages to past the capacity problems
                    // Right place to add the visited set.
                    seenList.add(graphEdge.dest);
                    // keep on increasing distance level by level.
                    distances[graphEdge.dest] = distances[graphEdge.src] + graphEdge.weight;
                }
            }
        }
        return distances;

    }

    /**
     * Djikstra is a Greedy algorithms which uses 2 sets :
     *       - SPT {} contains the list of nodes already visited
     *       - distances [] contains the distances found so far for all the nodes in the graph
     *
     * @param startNode
     * @return
     */
    public int[] shortestPathUsingDjikstra(int startNode) {

        int[] spt = new int[noVertices];
        int[] distance = new int[noVertices];

        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(spt, 0);

        distance[startNode] = 0;

        // Go through all the vertices
        for(int i = 0; i < noVertices; i++) {
            // Go through the adjacent nodes of this node
            // find the minimum node adjacent to these nodes, and mark it as visited afterwards.
            int nextVertex = getMinDistanceNeighbour(distance, spt);

            spt[nextVertex] = 1; // mark current vertex as visited.
            // go to the next neighbours reachable from this vertex and calculate their distances

            for(GraphEdge graphEdge : edges) {
                if(graphEdge.src == nextVertex
                        && distance[graphEdge.dest] > (distance[graphEdge.src] + graphEdge.weight)) {
                    distance[graphEdge.dest] = distance[graphEdge.src] + graphEdge.weight;
                }
            }
        }

        return distance;
    }

    /**
     * This function will get the neighbour with the minimal value.
     * The idea is that the neighbours are not visited, hence, even if we go through all the vertices,
     * only the reachable nodes will be calculated.
     *
     * @param distance  - vector with the distances recorded so far.
     * @param spt       - vector with the nodes visited so far.
     * @return
     */
    public int getMinDistanceNeighbour(int[] distance, int[] spt) {
        int min = Integer.MAX_VALUE;
        int index = -1;

        for(int i = 0; i < noVertices;  i++) {
            if(spt[i] == 0 && distance[i] <= min) {
                min = distance[i];
                index = i;
            }
        }

        return index;
    }
}
