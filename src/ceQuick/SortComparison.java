package ceQuick;

import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.Selection;

import java.util.Random;

public class SortComparison {

    private static Integer[] getRandomNumberArray(int arraySize, int numberOfDigits) {

        Integer[] intArray = new Integer[arraySize];
        int m = (int) Math.pow(10, numberOfDigits - 1);
        for(int i = 0; i < arraySize; i++){
            Random random = new Random();
            intArray[i] = random.nextInt(m * 9) + m;
        }
        return intArray;
    }

    public static void main(String[] args) {
        Integer[] intArray1;
        Integer[] intArray2;
        int n = 1000;
        int d = 7;
        long time = 0;

        System.out.println("       n |  Selection  |    Quick    |");
        System.out.println("---------|-------------|-------------|");

        for(int i = 0; i < 10; i++){
            intArray1 = getRandomNumberArray(n, d);
            intArray2 = intArray1;

            time = System.nanoTime();
            Selection.sort(intArray1);
            time = System.nanoTime() - time;
            double timeInSeconds1 = (double) time / 1000000000;

            time = System.nanoTime();
            Quick.sort(intArray2);
            time = System.nanoTime() - time;
            double timeInSeconds2 = (double) time / 1000000000;

            System.out.printf("%,8d |   %8.4fs |   %8.4fs |%n", n , timeInSeconds1, timeInSeconds2);

            n = n << 1;
        }
    }
}
