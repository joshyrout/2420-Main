package TP01;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GenArray {
    private Park[] nationalParkArray;
    private Park[] themeParkArray;

    public GenArray(){
        nationalParkArray = readFile("src/TP01/NationalParks.txt", ",", ParkType.National);
        themeParkArray = readFile("src/TP01/NationalParks.txt", ",", ParkType.National);
    }

    private Park[] readFile(String filename, String delimiter, ParkType type){
        Park[] parkArray = new Park[0];
        try {
            Scanner scanner = new Scanner(new File(filename));
            scanner.useDelimiter(delimiter);
            int count = 0;
            if(nationalParkArray != null) count = count + nationalParkArray.length;
            if(themeParkArray != null) count = count + themeParkArray.length;
            Queue<Park> queue = new Queue<Park>();
            while(scanner.hasNextLine()){
                String[] line = scanner.nextLine().split(delimiter);
                try{
                    String colName = line[0];
                    double colLat = Double.parseDouble(line[1]);
                    double colLong = Double.parseDouble(line[2]);
                    Point2D p = new Point2D(colLat, colLong);
                    Park park = new Park(colName, count, type, p);
                    queue.enqueue(park);
                    count++;
                } catch (Exception e){
                    System.out.println("Hit");
                    continue;
                }
            }
            parkArray = new Park[queue.size()];
            int i = 0;
            while(!queue.isEmpty()){
                Park test = queue.dequeue();
                parkArray[i] = test;
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return parkArray;
    }

    public Park[] getNationalParkArray() {
        return nationalParkArray;
    }

    public Park[] getThemeParkArray() {
        return themeParkArray;
    }

    public static void main(String[] args) {
        GenArray genArray = new GenArray();

        for(Park p: genArray.nationalParkArray){
            System.out.println(p);
        }
        for(Park p: genArray.themeParkArray){
            System.out.println(p);
        }
    }
}
