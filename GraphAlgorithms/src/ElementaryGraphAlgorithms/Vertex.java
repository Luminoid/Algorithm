package ElementaryGraphAlgorithms;

import java.util.ArrayList;

/**
 * Created by Ethan on 15/12/15.
 */
public class Vertex {
    private String label;
    private ArrayList<Edge> edges;

    public Vertex() {

    }

    public Vertex(String label) {
        this.label = label;
        edges = new ArrayList<>();
    }

    public Vertex addEdge(Vertex end) {
        this.edges.add(new Edge(this, end));
        return this;
    }

    public String getLabel() {
        return label;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

}
