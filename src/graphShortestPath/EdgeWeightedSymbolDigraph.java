package graphShortestPath;

import edu.princeton.cs.algs4.*;

public class EdgeWeightedSymbolDigraph {

    private ST<String, Integer> st;
    private String[] keys;
    private EdgeWeightedDigraph graph;

    public EdgeWeightedSymbolDigraph(String filename, String delimiter) {
        st = new ST<String, Integer>();

        In in = new In(filename);
        while (!in.isEmpty()) {
            String[] a = in.readLine().split(delimiter);
            if (!st.contains(a[0])) st.put(a[0], st.size());
            if (!st.contains(a[1])) st.put(a[1], st.size());
        }

        // inverted index to get string keys in an array
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        // second pass builds the graph by connecting first vertex on each
        // line to all others
        graph = new EdgeWeightedDigraph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            int v = st.get(a[0]);
            int w = st.get(a[1]);
            DirectedEdge edge = new DirectedEdge(v, w, Double.parseDouble(a[2]));
            graph.addEdge(edge);
        }
    }

    public boolean contains(String s) {
        return st.contains(s);
    }

    public int indexOf(String s) {
        return st.get(s);
    }

    public String nameOf(int v) {
        validateVertex(v);
        return keys[v];
    }

    public EdgeWeightedDigraph edgeWeightedDigraph() {
        return graph;
    }

    private void validateVertex(int v) {
        int V = graph.V();
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public static void main(String[] args) {
        String filename  = "src\\graphShortestPath\\airports.txt";
        String delimiter = " ";
        EdgeWeightedSymbolDigraph sg = new EdgeWeightedSymbolDigraph(filename, delimiter);
        EdgeWeightedDigraph graph = sg.edgeWeightedDigraph();
        System.out.println("Enter a destination: ");
        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            System.out.println(" Connected cities: ");
            if (sg.contains(source)) {
                int s = sg.indexOf(source);
                for (DirectedEdge v : graph.adj(s)) {
                    String name1, name2;
                    name1 = sg.nameOf(v.from());
                    name2 = sg.nameOf(v.to());
                    StdOut.println(name1 + " to " + name2 + " (" + v.weight() + ")");
                }
            }
            else {
                StdOut.println("input not contain '" + source + "'");
            }
        }
    }
}
