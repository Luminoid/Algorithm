package ElementaryGraphAlgorithms;

import java.util.HashMap;

/**
 * Created by Ethan on 15/12/15.
 */
public class DepthFirstSearch {
    static int time;

    private enum Color {WHITE, GRAY, BLACK}

    static HashMap<Vertex, Color> color = new HashMap<>();
    static HashMap<Vertex, Integer> discoveryTime = new HashMap<>();
    static HashMap<Vertex, Integer> finishTime = new HashMap<>();
    static HashMap<Vertex, Vertex> predecessor = new HashMap<>();

    public static void DFS(Graph g) {
        for (Vertex u : g.getVertices()) {
            color.put(u, Color.WHITE);
            predecessor.put(u, null);
        }
        time = 0;
        for (Vertex u : g.getVertices()) {
            if (color.get(u) == Color.WHITE) {
                DfsVisit(u, time);
            }
        }
    }

    private static void DfsVisit(Vertex u, int time) {
        time += 1; // white vertex u has just been discovered
        discoveryTime.put(u, time);
        color.put(u, Color.GRAY);
        for (Edge e : u.getEdges()) { // explore edge (u, v)
            Vertex v = e.getEnd();
            if (color.get(v) == Color.WHITE) {
                predecessor.put(v, u);
                DfsVisit(v, time);
            }
        }
        color.put(u, Color.BLACK); // blacken u; it is finished
        time += 1;
        finishTime.put(u, time);
    }


    public static void main(String[] args) {
        Vertex v1 = new Vertex("u");
        Vertex v2 = new Vertex("v");
        Vertex v3 = new Vertex("w");
        Vertex v4 = new Vertex("x");
        Vertex v5 = new Vertex("y");
        Vertex v6 = new Vertex("z");

        v1.addEdge(v2).addEdge(v4);
        v2.addEdge(v5);
        v3.addEdge(v5).addEdge(v6);
        v4.addEdge(v2);
        v5.addEdge(v4);
        v6.addEdge(v6);

        Graph g = new Graph();
        g.addVertex(v1).addVertex(v2).addVertex(v3).addVertex(v4).addVertex(v5).addVertex(v6);
        DFS(g);

        System.out.println(predecessor.get(v2).getLabel());
        System.out.println(predecessor.get(v4).getLabel());
        System.out.println(predecessor.get(v5).getLabel());
        System.out.println(predecessor.get(v6).getLabel());
    }
}
