package graphCityConnections;

import edu.princeton.cs.algs4.*;

public class CityConnectionsCE {
    public static void main(String[] args) {
        StdOut.println("Cities to connect with a bike trail:");
        StdOut.println("------------------------------------");
        String filename  = "src\\graphCityConnections\\CityConnections.csv";
        String delimiter = ",";
        EdgeWeightedSymbolGraph sg = new EdgeWeightedSymbolGraph(filename, delimiter);
        EdgeWeightedGraph graph = sg.edgeWeightedGraph();
        PrimMST primMST = new PrimMST(graph);
        for(Edge e : primMST.edges()){
            String name1, name2;
            name1 = sg.nameOf(e.either());
            name2 = sg.nameOf(e.other(e.either()));
            StdOut.println(name1 + " to " + name2 + " (" + e.weight() + ")");
        }
        StdOut.println("\nTotal length of the bike trail: " + primMST.weight());
    }
}
