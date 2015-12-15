package ElementaryGraphAlgorithms;

import java.util.HashMap;

/**
 * Created by Ethan on 15/12/15.
 */
public class Graph {
    private HashMap<String, Vertex> nodes;

    public Graph() {
        nodes = new HashMap<>();
    }

    public Graph addVertex(Vertex v) {
        this.nodes.put(v.getLabel(), v);
        return this;
    }

    public Vertex getNode(String label) {
        return nodes.get(label);
    }

    public HashMap<String, Vertex> getNodes() {
        return nodes;
    }
}
