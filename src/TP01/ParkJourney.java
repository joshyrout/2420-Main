package TP01;

import edu.princeton.cs.algs4.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class used to find the shortest path between a provided list of national parks
 *
 * @author Josh Routledge
 * @version 1.0
 */
public class ParkJourney {
    private Park[] parkArray;
    private EdgeWeightedDigraph edgeWeightedDigraph;
    private RedBlackBST<String, Integer> redBlackBST;
    private int vertex;
    private DijkstraSP dSP;

    /**
     * The constructor for the ParkJourneyClass. It takes in a file location as a string which it will use to create
     * the array of parks.
     * @param fileName Location of the parks file as a String
     */
    public ParkJourney(String fileName){
        vertex = -1;
        redBlackBST = new RedBlackBST();
        parkArray = readFile(fileName, ",", ParkType.National);
        edgeWeightedDigraph = createGraph();
    }

    /**
     * A helper node class used to store data used in creating the edge weighted digraph
     */
    private class Node implements Comparable<Node>{

        int v1;
        int v2;
        Double distance;

        public Node(int v1, int v2, double distance){
            this.v1 = v1;
            this.v2 = v2;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance.compareTo(o.distance);
        }
    }

    /**
     * Creates an edgeWeightedDigraph using from the list of parks. It will continue adding edges until there is only
     * one large connected component that spans the whole graph.
     * @return EdgeWeightedDigraph
     */
    private EdgeWeightedDigraph createGraph(){
        int[] connections = new int[parkArray.length];
        for(int i = 0; i < connections.length; i++){
            connections[i] = i;
        }
        boolean isEqual = false;
        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(parkArray.length);
        while(!isEqual) {
            for (int i = 0; i < parkArray.length; i++) {
                Node shortest = null;
                for (int j = 0; j < parkArray.length; j++) {
                    if (i != j && connections[i] != connections[j]) {
                        double distance = parkArray[i].getLocation().distanceTo(parkArray[j].getLocation());
                        Node node = new Node(i, j, distance);
                        if (shortest == null) {
                            shortest = node;
                        } else {
                            if (node.compareTo(shortest) < 0) {
                                shortest = node;
                            }
                        }
                    }
                }
                if(shortest != null) {
                    int a = shortest.v1;
                    int b = shortest.v2;
                    graph.addEdge(new DirectedEdge(a, b, shortest.distance));
                    graph.addEdge(new DirectedEdge(b, a, shortest.distance));

                    int newGroupId = connections[b];
                    int oldGroupId = connections[a];
                    if (connections[a] < connections[b]) {
                        newGroupId = connections[a];
                        oldGroupId = connections[b];
                    }
                    for (int j = 0; j < connections.length; j++) {
                        if (connections[j] == oldGroupId) {
                            connections[j] = newGroupId;
                        }
                    }
                }
            }

            //Tests to see if all components are connected
            for(int i = 0; i < connections.length; i++) {
                if(connections[i] != connections[0]){
                    isEqual = false;
                    break;
                } else {
                    isEqual = true;
                }
            }
        }
        return graph;
    }

    /**
     * Converts the parks name to its associated integer and runs shortestPath(int start, int end).
     * @param start Origin park's name as a string
     * @param end Destination park's name as a string
     * @return Iterable<Park> An iterable containing a list of parks
     */
    public Iterable<Park> shortestPath(String start, String end) {
        if(redBlackBST.get(start) != null && redBlackBST.get(end) != null) {
            return shortestPath(redBlackBST.get(start), redBlackBST.get(end));
        } else {
            return null;
        }
    }


    /**
     * Takes in two parks associated integers and returns a list of parks between those places.
     * @param start Origin park's associated integer
     * @param end Destination park's associated integer
     * @return Iterable<Park> An iterable containing a list of parks
     */
    public Iterable<Park> shortestPath(int start, int end) {
        if(edgeWeightedDigraph == null) throw new NullPointerException("The Graph cant be null when calculating the shortest Path");
        if (vertex != start) {
            vertex = start;
            dSP = new DijkstraSP(edgeWeightedDigraph, vertex);
        }
        Queue<Park> queue = new Queue<Park>();
        if (dSP.hasPathTo(end)) {
            Iterable<DirectedEdge> edges = dSP.pathTo(end);
            for(DirectedEdge e: edges){
                if(queue.size() == 0){
                    queue.enqueue(parkArray[e.from()]);
                }
                queue.enqueue(parkArray[e.to()]);
            }
        } else {
            System.out.println("There is no path between " + parkArray[start].getName() + " and " + parkArray[end].getName());
        }
        return queue;
    }

    /**
     * Takes in a file and creates an array of parks.
     * @param filename Location of the parks file as a String
     * @param delimiter The delimiter used for the file as a String
     * @param type The type of park
     * @return Park[] An array of parks
     */
    private Park[] readFile(String filename, String delimiter, ParkType type){
        Park[] parkArray = new Park[0];
        try {
            Scanner scanner = new Scanner(new File(filename));
            scanner.useDelimiter(delimiter);
            int count = 0;
            if(this.parkArray != null) count = count + this.parkArray.length;
            Queue<Park> queue = new Queue<Park>();
            while(scanner.hasNextLine()){
                String[] line = scanner.nextLine().split(delimiter);
                try{
                    String colName = line[0] + " " + line[1];
                    double colLat = Double.parseDouble(line[2]);
                    double colLong = Double.parseDouble(line[3]);
                    Point2D p = new Point2D(colLat, colLong);
                    Park park = new Park(colName, count, type, p);
                    queue.enqueue(park);
                    count++;
                } catch (Exception e){
                    continue;
                }
            }
            parkArray = new Park[queue.size()];
            int i = 0;
            while(!queue.isEmpty()){
                Park park = queue.dequeue();
                parkArray[i] = park;
                redBlackBST.put(park.getName(),i);
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return parkArray;
    }

    /**
     * A getter for the park array.
     * @return Park[] An array of parks.
     */
    public Park[] getParkArray() {
        return parkArray;
    }

    /**
     * The main method was used for testing.
     */
    public static void main(String[] args) {
        ParkJourney parkJourney = new ParkJourney("resources\\National Parks.csv");

        Iterable<Park> parks = parkJourney.shortestPath(4, 7);

        System.out.println("You can get to your destination by following this list of places");
        for(Park p: parks){
            System.out.println(p.getName());
        }
    }
}