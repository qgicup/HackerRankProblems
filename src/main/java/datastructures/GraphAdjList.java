package datastructures;

import java.util.*;

/**
 * Created with ♥ by georgeplaton on 18.04.18.
 */
public class GraphAdjList
{
    private int noVertices;   // No. of vertices
    private LinkedList<GraphNode> adj[]; // Adjacency Lists

    // Constructor
    public GraphAdjList(int noVertices)
    {
        this.noVertices = noVertices;
        adj = new LinkedList[noVertices];
        for (int i = 0; i< noVertices; ++i)
            adj[i] = new LinkedList();
    }

    // Function to add an edge into the graph
    public void addEdge(int src, int target, int targetWeight)
    {
        adj[src].add(new GraphNode(target, targetWeight));
    }

    // Function to add an edge into the graph
    public void addEdge(int src, int target)
    {
        addEdge(src, target, 6);  // by default we keep the weight 6, as that's what our current problem requests.
    }

    // prints BFS traversal from a given source s

    /**
     * Prints the BFS (breadth first traversal) from a given source s.
     * In order to do BFS, we usually use a queue, and go through all the breadth neighbours as we add items to queue, and pop them out of
     * the queue.
     * @param s - source vertex
     */
    public void printBFS(int s)
    {
        // Mark all the vertices as not visited(By default
        // set as false)
        boolean visited[] = new boolean[noVertices];

        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // Mark the current node as visited and enqueue it
        visited[s]=true;
        queue.add(s);

        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue and print it
            s = queue.poll();
            System.out.print(s+" ");

            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<GraphNode> i = adj[s].listIterator();
            while (i.hasNext())
            {
                int n = i.next().getNo();
                if (!visited[n])
                {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    /**
     * This function will go through the all nodes that have not been visited, and will
     * choose the ones with the minimal distance in a greedy manner.
     *
     * @param src           - the starting node
     * @param visited       - the array which tells us which nodes have been visited
     * @param distances     - the current distances found to all nodes.
     * @return              the node with the minimal distance from the current @param src
     */
    public int minDistanceNode(int src, boolean visited[], int distances[]) {
        int minDistance = Integer.MAX_VALUE;
        int minIndex = -1;

        // this algorithm can be further optimized
        for(int i = 0; i < noVertices; i++) {
            if(!visited[i] &&  distances[i] <= minDistance) {
                minDistance = distances[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    /**
     * Will return the shortest path from src to dest.
     *
     * We can use Djikstra, in case there are no negative weights.
     * Since we have non-negative weights, let's use djikstra.
     *
     * As discussed in the previous post, in Dijkstra’s algorithm, two sets are maintained, one set contains
     * list of vertices already included in SPT (Shortest Path Tree), other set contains vertices not yet included
     *
     * Djikstra can be used via :
     *      1) matrix representation - need to traverse all the vertices in a for loop
     *      2) adjancency list (using Min Heap) - need to traverse all the adjancency lists, using BFS (so we go to pair neighbours, in breadth!).
     *         Use a MinHeap, in order to keep ordered the vertices that have not been visited. This way, we don't have to compare the adjacency neighbours
     *         all the time.
     *         See https://www.geeksforgeeks.org/greedy-algorithms-set-7-dijkstras-algorithm-for-adjacency-list-representation/ for more inspiration.
     *
     * @param start
     * @param target
     * @return
     */
    public int shortestPathDjikstraFromSrcToTarget(GraphNode start, int target) {

        boolean visited[]   = new boolean[noVertices];
        int distances[] = new int[noVertices];

        /**
         * Djisktra using Graph adjancency list
         *
         * 1) Create a Min Heap of size V where V is the number of vertices in the given graph. Every node of min heap contains vertex number and distance value of the vertex.
         * 2) Initialize Min Heap with source vertex as root (the distance value assigned to source vertex is 0). The distance value assigned to all other vertices is INF (infinite).
         * 3) While Min Heap is not empty, do following
         * …..a) Extract the vertex with minimum distance value node from Min Heap. Let the extracted vertex be u.
         * …..b) For every adjacent vertex v of u, check if v is in Min Heap. If v is in Min Heap and distance value is more than weight of u-v plus distance value of u, then update the distance value of v.
         */

        PriorityQueue<GraphNode> minHeap = new PriorityQueue<GraphNode>(new Comparator<GraphNode>() {
            @Override
            public int compare(GraphNode o1, GraphNode o2) {
                if(o1.weight == o2.weight)
                    return 0;
                else if(o1.weight > o2.weight)
                    return 1;
                else
                    return -1;
            }
        });

        // Add all nodes

        // initialiaze all the visited and distances data structures
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(visited, false);


        // Start with source and do DFS
        distances[start.getNo()] = 0;

        // 1. Go through all the adjacent vertices, starting from src
        Iterator<GraphNode> iterator1 = adj[start.getNo()].listIterator();
        while (iterator1.hasNext()) {
            GraphNode srcNode = iterator1.next();

            // 1. Find min distance vertex to continue with, from crt srcNode (greedy manner)
            // we just compare the min distance recorded so far inside distances[] vector
            int nextVertex = minDistanceNode(srcNode.getNo(), visited, distances);
            visited[nextVertex] = true;

            // 2. Go through the adjancecy nodes of nextVertex, in order to update their distances, but dont mark
            // them as visited yet (we just need to know how much it will cost to choose their neighbours).
            Iterator<GraphNode> iterator2 = adj[nextVertex].listIterator();
            while (iterator2.hasNext())
            {
                // 2.1 Update their distances, in case they have not been yet visited
                GraphNode neighbourValue = iterator2.next();
                int neighbourNo =  neighbourValue.getNo();

                // 2.2 The nextVertex neighbour value is distance to it plus neighbour weight
                int distanceToNewNode = distances[srcNode.getNo()] + neighbourValue.getWeight();

                if(visited[neighbourNo] == false &&
                        distances[neighbourNo] > distanceToNewNode) {

                    distances[neighbourNo] = distanceToNewNode;
                }
            }
        }

        return 0;
    }

    /**
     * Will return the shortest path from src to dest.
     *
     * We will use an algorithm, which can handle also negative weights, and which
     * considers that all weights are the same.
     *
     *
     * @param src
     * @param dest
     * @return
     */
    public int shortestPathFromSrcToTarget(int src, int dest) {

        return 0;
    }

    /**
     * Will return the shortest path from the given node to all the other nodes.
     *
     * Should we use Djkistra ?
     *
     * @param startingNode      - the node to start the search from.
     * @return
     */
    public List<Integer> getShortestPathFromSrcToAllDest(int startingNode) {
        ArrayList<Integer> list = new ArrayList<Integer>();

        for(int i = 0; i < noVertices; i++) {
            if(i !=  startingNode) {
                list.add(shortestPathDjikstraFromSrcToTarget(startingNode, i));
            }
        }

        return list;
    }
}
