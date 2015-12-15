package ElementaryGraphAlgorithms;

/**
 * Created by Ethan on 15/12/15.
 */
public class Edge {
    private Vertex start, end;
    private int weight;

    public Edge(Vertex start, Vertex end) {
        this(start, end, 1);
    }

    public Edge(Vertex start, Vertex end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public Vertex getStart() {
        return start;
    }

    public Vertex getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }
}
