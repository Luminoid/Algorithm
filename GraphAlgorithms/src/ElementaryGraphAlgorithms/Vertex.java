package ElementaryGraphAlgorithms;

import java.util.LinkedList;

/**
 * Created by Ethan on 15/12/15.
 */
public class Vertex {
    private String label;
    private LinkedList<Edge> edges;

    public Vertex() {

    }

    public Vertex(String label) {
        this.label = label;
        edges = new LinkedList<>();
    }

    public Vertex addEdge(Vertex end) {
        this.edges.add(new Edge(this, end));
        return this;
    }

    public String getLabel() {
        return label;
    }

    public LinkedList<Edge> getEdges() {
        return edges;
    }

}
