package graphCityConnections;

import edu.princeton.cs.algs4.*;

public class EdgeWeightedSymbolGraph {

    private ST<String, Integer> st;
    private String[] keys;
    private EdgeWeightedGraph graph;

    public EdgeWeightedSymbolGraph(String filename, String delimiter) {
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
        graph = new EdgeWeightedGraph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            int v = st.get(a[0]);
            int w = st.get(a[1]);
            Edge e = new Edge(v, w, Double.parseDouble(a[2]));
            graph.addEdge(e);
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

    public EdgeWeightedGraph edgeWeightedGraph() {
        return graph;
    }

    private void validateVertex(int v) {
        int V = graph.V();
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public static void main(String[] args) {
        String filename  = "src\\graphCityConnections\\CityConnections.csv";
        String delimiter = ",";
        EdgeWeightedSymbolGraph sg = new EdgeWeightedSymbolGraph(filename, delimiter);
        EdgeWeightedGraph graph = sg.edgeWeightedGraph();
        System.out.println("Enter a destination: ");
        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            System.out.println(" Connected cities: ");
            if (sg.contains(source)) {
                int s = sg.indexOf(source);
                for (Edge v : graph.adj(s)) {
                    String name1, name2;
                    name1 = sg.nameOf(v.either());
                    name2 = sg.nameOf(v.other(s));
                    StdOut.println(name1 + " to " + name2 + " (" + v.weight() + ")");
                }
            }
            else {
                StdOut.println("input not contain '" + source + "'");
            }
        }
    }
}
