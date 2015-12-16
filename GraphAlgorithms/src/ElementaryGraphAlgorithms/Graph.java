package ElementaryGraphAlgorithms;

import java.util.*;

/**
 * Created by Ethan on 15/12/15.
 */
public class Graph {
    private ArrayList<Vertex> vertices;
    private HashMap<String, Vertex> nodes;

    public Graph() {
        vertices = new ArrayList<>();
        nodes = new HashMap<>();
    }

    public Graph addVertex(Vertex v) {
        this.vertices.add(v);
        this.nodes.put(v.getLabel(), v);
        return this;
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public Vertex getNode(String label) {
        return nodes.get(label);
    }

    public HashMap<String, Vertex> getNodes() {
        return nodes;
    }
}