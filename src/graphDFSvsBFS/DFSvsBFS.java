package graphDFSvsBFS;

import edu.princeton.cs.algs4.*;

public class DFSvsBFS {

    public static void main(String[] args) {
        String filename = "resources\\SimpleGraph.txt";
        In in = new In(filename);
        Graph g = new Graph(in);
        int source = 1;
        int vertices = g.V();
        DepthFirstPaths dfp = new DepthFirstPaths(g, source);
        BreadthFirstPaths bfp = new BreadthFirstPaths(g, source);

        System.out.println("Adjacency List:");
        System.out.println("---------------");
        StringBuilder sb = new StringBuilder();
        for (int v = 0; v < vertices; v++) {
            sb.append(v + ": ");
            for (int w : g.adj(v)) {
                sb.append(w + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
        System.out.println("Paths DFS:           Shortest Paths BFS:");
        System.out.println("---------------      -------------------");
        for (int v = 0; v < vertices; v++) {
            StringBuilder dSB = new StringBuilder();
            StringBuilder bSB = new StringBuilder();
            for (int x : dfp.pathTo(v)) {
                if (x == source) dSB.append(x);
                else             dSB.append(".." + x);
            }
            for (int x : bfp.pathTo(v)) {
                if (x == source) bSB.append(x);
                else             bSB.append(".." + x);
            }
            System.out.printf("%-20s %-20s\n", dSB.toString(), bSB.toString());
        }
    }
}
