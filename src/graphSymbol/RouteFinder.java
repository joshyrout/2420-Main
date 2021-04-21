package graphSymbol;

import com.sun.tools.javac.Main;
import edu.princeton.cs.algs4.*;

public class RouteFinder {
    public static void main(String[] args) {
        String filename  = "src\\graphSymbol\\routes.txt";
        String delimiter = " ";
        SymbolGraph sg = new SymbolGraph(filename, delimiter);
        Graph graph = sg.graph();
        System.out.print("Enter an Airports 3 letter identifier: ");

        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            if (sg.contains(source)) {
                int index = sg.indexOf(source);
                BreadthFirstPaths bfs = new BreadthFirstPaths(graph, index);
                System.out.println(source + " has the following routes: ");
                for (int i = 0; i < graph.V(); i++) {
                    if(bfs.hasPathTo(i)) {
                    StringBuilder sb = new StringBuilder();
                        for(int v: bfs.pathTo(i)){
                            if(v == index){
                                sb.append(sg.nameOf(v));
                            } else {
                                sb.append(" " + sg.nameOf(v));
                            }
                        }
                        System.out.println(sb.toString());
                    }
                }
            }
            else {
                System.out.println("The departure " + source + " could not be found.");
            }
        }
    }
}
