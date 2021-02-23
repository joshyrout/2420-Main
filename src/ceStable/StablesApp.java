package ceStable;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Selection;

import java.util.Arrays;
import java.util.Comparator;

public class StablesApp {


    public static void main(String[] args) {

        int[][] intArray = {{4, 2}, {3, 5}, {4, 3}, {6, 2}, {3, 4}, {4, 4}, {6, 4}, {12, 2}, {2, 4}, {4, 6}, {2, 12}};
        Rectangle[] rectangles = new Rectangle[11];

        for (int i = 0; i < intArray.length; i++) {
            rectangles[i] = new Rectangle(intArray[i][0], intArray[i][1]);
        }


        print(rectangles, "rectangles :");
        System.out.println();

        System.out.println("Stable Sort;");
        Selection.sort(rectangles, Comparator.naturalOrder());
        print(rectangles,"sorted by length");
        Insertion.sort(rectangles, Rectangle.BY_Area);
        print(rectangles,"sorted by area");

        System.out.println();
        System.out.println("Not-Stable Sort;");
        Selection.sort(rectangles, Comparator.naturalOrder());
        print(rectangles,"sorted by length");
        Selection.sort(rectangles, Rectangle.BY_Area);
        print(rectangles,"sorted by area");
    }


    private static void print(Rectangle[] array, String title){
        System.out.print(title + " : [");
        for (int i = 0; i < array.length; i++) {
            if (i < array.length-1) {
                System.out.print(array[i].toString() + ", ");
            } else{
                System.out.println(array[i].toString() + "]");
            }
        }
    }
}
