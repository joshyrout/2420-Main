package graphInternet;

import edu.princeton.cs.algs4.*;

public class InternetCE {

    public static void main(String[] args) {
        String filename = "src\\graphInternet\\GraphInternet.txt";
        In in = new In(filename);
        EdgeWeightedGraph eWGraph = new EdgeWeightedGraph(in);
        KruskalMST kMST = new KruskalMST(eWGraph);
        int routerNode = 8;

        StringBuilder fiberString = new StringBuilder();
        StringBuilder routerString = new StringBuilder();
        for(Edge e: kMST.edges()){
            int v1 = e.either();
            int v2 = e.other(v1);
            if(v1 == routerNode){
                routerString.append(" " + v2);
            } else if(v2 == routerNode){
                routerString.append(" " + v1);
            } else {
                fiberString.append(" " + v1 + "-" + v2);
            }
        }

        System.out.println("Offices needing to be connected by fiber:" + fiberString.toString());
        System.out.println("Offices needing a router:" + routerString.toString());
        System.out.printf("Total cost: $%.2f",kMST.weight());

    }
}
