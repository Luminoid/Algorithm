package ElementaryGraphAlgorithms;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ethan on 15/12/14.
 */
public class BreadthFirstSearch {
    private enum Color {WHITE, GRAY, BLACK}

    static HashMap<Vertex, Color> color = new HashMap<>();
    static HashMap<Vertex, Integer> discoveryTime = new HashMap<>();
    static HashMap<Vertex, Vertex> predecessor = new HashMap<>();

    public static void BFS(Vertex v1) {
        color.put(v1, Color.GRAY);
        discoveryTime.put(v1, 0);
        predecessor.put(v1, null);
        ArrayList<Vertex> list = new ArrayList<>();
        list.add(v1);

        while (list.size() != 0) {
            Vertex u = list.remove(0);
            for (Edge e : u.getEdges()) {
                Vertex v = e.getEnd();
                if (color.get(v) == null) { // Color.WHITE
                    color.put(v, Color.GRAY);
                    discoveryTime.put(v, discoveryTime.get(u) + 1);
                    predecessor.put(v, u);
                    list.add(v);
                }
            }
            color.put(u, Color.BLACK);
        }
    }

    public static void printPath(Vertex s, Vertex v) {
        System.out.println("The path from " + s.getLabel() + " to " + v.getLabel() + " is:");
        String ret = "";
        while (predecessor.get(v) != null) {
            ret = " --> " + v.getLabel() + ret;
            v = predecessor.get(v);
        }
        if (v == s) {
            System.out.println(s.getLabel() + ret);
        } else {
            System.out.println("No such path!");
        }
    }

    public static void main(String[] args) {
        Vertex v1 = new Vertex("v");
        Vertex v2 = new Vertex("r");
        Vertex v3 = new Vertex("s");
        Vertex v4 = new Vertex("w");
        Vertex v5 = new Vertex("t");
        Vertex v6 = new Vertex("u");
        Vertex v7 = new Vertex("x");
        Vertex v8 = new Vertex("y");

        v1.addEdge(v2);
        v2.addEdge(v1).addEdge(v3);
        v3.addEdge(v2).addEdge(v4);
        v4.addEdge(v3).addEdge(v5).addEdge(v7);
        v5.addEdge(v4).addEdge(v6).addEdge(v7);
        v6.addEdge(v5).addEdge(v7).addEdge(v8);
        v7.addEdge(v4).addEdge(v5).addEdge(v6).addEdge(v8);
        v8.addEdge(v6).addEdge(v7);

        BFS(v1);
        printPath(v1, v6);
        printPath(v1, v8);
        printPath(v6, v7);
    }
}
