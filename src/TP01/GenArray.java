package TP01;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GenArray {
    Park[] nationalParkArray;
    Park[] themeParkArray;

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
            while (scanner.hasNext()){
                String colName = scanner.next();
                double colLat = Double.parseDouble(scanner.next());
                double colLong = Double.parseDouble(scanner.next());
                Point2D p = new Point2D(colLat, colLong);
                Park park = new Park(colName, count, type, p);
                queue.enqueue(park);
                count++;
            }
            parkArray = new Park[count];
            for(int i = 0; i < queue.size(); i++){
                parkArray[i] = queue.dequeue();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return parkArray;
    }


    public static void main(String[] args) {
        GenArray genArray = new GenArray();

        for(Park p: genArray.nationalParkArray){
            System.out.println(p);
        }
        for(Park p: genArray.nationalParkArray){
            System.out.println(p);
        }

    }
}
