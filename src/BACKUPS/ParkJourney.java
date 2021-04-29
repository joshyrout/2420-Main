package BACKUPS;

import TP01.Park;
import TP01.ParkType;
import edu.princeton.cs.algs4.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParkJourney{
    private Park[] parkArray;
    private EdgeWeightedDigraph edgeWeightedDigraph;
    private RedBlackBST<String, Integer> redBlackBST;
    private int vertex;
    private DijkstraSP dSP;

    public ParkJourney(String fileName){
        vertex = -1;
        redBlackBST = new RedBlackBST();
        parkArray = readFile(fileName, ",", ParkType.National);
        edgeWeightedDigraph = createGraph(30);
    }

    public void updateGraph(int maxDistance){
        edgeWeightedDigraph = createGraph(maxDistance);
    }


    private EdgeWeightedDigraph createGraph(int maxDistance){
        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(parkArray.length);
        for(int i = 0; i < parkArray.length; i++){
            for(int j = 0; j < parkArray.length; j++){
                if(i != j){
                    double distance = parkArray[i].getLocation().distanceTo(parkArray[j].getLocation());
                    if(distance < maxDistance) {
                        graph.addEdge(new DirectedEdge(i,j,distance));
                        graph.addEdge(new DirectedEdge(j,i,distance));
                    }
                }
            }
        }
        return graph;
    }

    public Iterable<Park> shortestPath(String start, String end) {
        if(redBlackBST.get(start) != null && redBlackBST.get(end) != null) {
            return shortestPath(redBlackBST.get(start), redBlackBST.get(end));
        } else {
            return null;
        }
    }

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
    public Park[] getParkArray() {
        return parkArray;
    }

    public static void main(String[] args) {
        ParkJourney parkJourney = new ParkJourney("resources\\National Parks.csv");
        Iterable<Park> parks = parkJourney.shortestPath(4, 7);

        System.out.println("You can get to your destination by following this list of places");
        for(Park p: parks){
            System.out.println(p.getName());
        }
    }
}