package ceMerge;

import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.StdRandom;

public class MergeComparison {



    public static Integer[] getNumbers(int size){
        Integer[] array = new Integer[size];

        for(int i = 0; i < size; i++){
            array[i] = StdRandom.uniform(999999);
        }

        return array;
    }

    private static void timedSort(Integer startNumber){

        System.out.printf("%9s %9s\n","n","Merge");
        System.out.println("---------- ------------");
        for(int i = 0; i < 9; i++){

            Integer[] array = getNumbers(startNumber);
            startNumber = startNumber * 2;
            long startTime = System.nanoTime();
            Merge.sort(array);
            long endTime = System.nanoTime();
            float timeTaken = (endTime - startTime) / (float) 1000000000;
            System.out.printf("%9d %9.3fs\n", array.length, timeTaken);
        }
    }

    private static void timedSortSlow(Integer startNumber){

        System.out.printf("%9s %13s\n","n","MergeSlow");
        System.out.println("---------- ------------");
        for(int i = 0; i < 9; i++){

            Integer[] array = getNumbers(startNumber);
            startNumber = startNumber * 2;
            long startTime = System.nanoTime();
            MergeSlow.sort(array);
            long endTime = System.nanoTime();
            float timeTaken = (endTime - startTime) / (float) 1000000000;
            System.out.printf("%9d %9.3fs\n", array.length, timeTaken);
        }
    }

    public static void main(String[] args) {
        System.out.println("Sorting comparison based on Professor Sedgewick's Merge class");
        System.out.println("---------- ------------");
        timedSort(1024);
        System.out.println();
        timedSortSlow(1024);



    }

}
