package graphShortestPath;

import edu.princeton.cs.algs4.*;

public class ShortestPathCE {
    public static void main(String[] args) {
        StdOut.println("Shortest path from Start to End:");
        StdOut.println("--------------------------------");
        String filename  = "src\\graphShortestPath\\airports.txt";
        String delimiter = " ";
        EdgeWeightedSymbolDigraph sg = new EdgeWeightedSymbolDigraph(filename, delimiter);
        EdgeWeightedDigraph graph = sg.edgeWeightedDigraph();
        DijkstraSP dijkstraSP = new DijkstraSP(graph, sg.indexOf("Start"));

        double totalWeight = 0;
        for(DirectedEdge e: dijkstraSP.pathTo(sg.indexOf("End"))){
            String name1, name2;
            name1 = sg.nameOf(e.from());
            name2 = sg.nameOf(e.to());
            totalWeight = totalWeight + e.weight();
            StdOut.println(name1 + " to " + name2 + " (" + e.weight() + ")");
        }
        StdOut.println("\nTotal length from Start to End: " + totalWeight);
    }
}
