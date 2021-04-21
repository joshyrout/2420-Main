package graphDirected;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Topological;

public class DirectedCE {

    public static void main(String[] args) {
        String filename = "resources\\TopologicalOrderGraph.txt";
        In in = new In(filename);
        Digraph dG = new Digraph(in);
        Topological T = new Topological(dG);
        StringBuilder sb = new StringBuilder();
        for(int i: T.order()){
            if(sb.toString().equals("")) System.out.print(" ");
            System.out.print(i);
        }
    }
}
