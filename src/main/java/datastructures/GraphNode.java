package datastructures;

/**
 * Created with ♥ by georgeplaton on 18.04.18.
 */
public class GraphNode {
    int no;
    int weight;     /// this can be the weight of this node, or the weight from a specific node to it, in a adjancency list representation

    public GraphNode(int no, int weight) {
        this.no = no;
        this.weight = weight;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}

