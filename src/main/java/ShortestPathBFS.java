import datastructures.GraphAdjList;
import datastructures.GraphDirectWeighted;
import datastructures.GraphMat;

import java.util.*;

/**
 *
 * Consider an undirected graph consisting of  nodes where each node is labeled from  to  and the edge between any two nodes is always of length . We define node  to be the starting position for a BFS.
 *
 * Given  queries in the form of a graph and some starting node, , perform each query by calculating the shortest distance from starting node  to all the other nodes in the graph. Then print a single line of  space-separated integers listing node 's shortest distance to each of the  other nodes (ordered sequentially by node number); if  is disconnected from a node, print  as the distance to that node.
 *
 * Input Format
 *
 * The first line contains an integer, , denoting the number of queries. The subsequent lines describe each query in the following format:
 *
 * The first line contains two space-separated integers describing the respective values of  (the number of nodes) and (the number of edges) in the graph.
 * Each line  of the  subsequent lines contains two space-separated integers,  and , describing an edge connecting node  to node .
 * The last line contains a single integer, , denoting the index of the starting node.
 * Constraints
 *
 * Output Format
 *
 * For each of the  queries, print a single line of  space-separated integers denoting the shortest distances to each of the  other nodes from starting position . These distances should be listed sequentially by node number (i.e., ), but should not include node . If some node is unreachable from , print  as the distance to that node.
 *
 * Sample Input
 *
 * 2
 * 4 2
 * 1 2
 * 1 3
 * 1
 * 3 1
 * 2 3
 * 2
 * Sample Output
 *
 * 6 6 -1
 * -1 6
 * Explanation
 *
 * We perform the following two queries:
 *
 * The given graph can be represented as:
 * graph1
 * where our start node, , is node . The shortest distances from  to the other nodes are one edge to node , one edge to node , and an infinite distance to node  (which it's not connected to). We then print node 's distance to nodes , , and  (respectively) as a single line of space-separated integers: 6 6 -1.
 * The given graph can be represented as:
 * graph2
 * where our start node, , is node . There is only one edge here, so node  is unreachable from node  and node  has one edge connecting it to node . We then print node 's distance to nodes  and  (respectively) as a single line of space-separated integers: -1 6.
 * Note: Recall that the actual length of each edge is , and we print  as the distance to any node that's unreachable from .
 *
 * Hard
 * Submitted 11578 times
 * Max Score 45
 * Need Help?
 *
 * View Discussions
 * View Editorial Solution
 * View Top Submissions
 * RATE THIS CHALLENGE
 *
 * Resources

 *
 * Created with ♥ by georgeplaton on 03.04.18.
 */
public class ShortestPathBFS {


    /**
     *
     * Input format :
     *
     * The first line contains an integer, , denoting the number of queries. The subsequent lines describe each query in the following format:
     *
     * The first line contains two space-separated integers describing the respective values of  (the number of nodes) and (the number of edges) in the graph.
     * Each line  of the  subsequent lines contains two space-separated integers,  and , describing an edge connecting node  to node .
     * The last line contains a single integer, , denoting the index of the starting node.
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int noQueries = in.nextInt();

        for (int i = 0; i < noQueries; i++) {
            int noNodes = in.nextInt() + 1; // because they are 0 indexed.
            int noEdges = in.nextInt();

            GraphDirectWeighted graph = new GraphDirectWeighted(noNodes, noEdges);

            for (int j = 0; j < noEdges; j++) {
                int edgeSrc = in.nextInt();
                int edgeDest = in.nextInt();

                graph.addEdge(edgeSrc, edgeDest, 6);
            }

            int startingNode = in.nextInt();
            //System.out.println("Starting with node :" + startingNode);

            // BFS algorithm
            int[] paths = graph.shortestPathUsingBFS(startingNode);

            // Djikstra algorithm
            //int[] paths = graph.shortestPathUsingDjikstra(startingNode);

            // Bellman-Ford algorithm
            //int[] paths = graph.shortestPathUsingBellmanFord(startingNode);

            for(int k = 1; k < noNodes; k++) {
                int value = paths[k];
                if(k == startingNode)
                    continue;

                if(value == Integer.MAX_VALUE)
                    System.out.print("-1 ");
                else
                    System.out.print(value + " ");
            }

            System.out.println();
        }
    }
}