package a03;

import java.util.Arrays;
import java.util.Comparator;

/**
 * This class creates an binary search that allows you to look for the first and last instance of a key in an array.
 *
 * @author Huakang Zhou, Joshua Routledge
 * @version 1.0
 */
public class BinarySearchDeluxe {


    /**
     * This uses a binary search to find the first instance of a key in a sorted array.
     * @param a is the array to search through
     * @param key is the key you are looking for
     * @param comparator is the comparator that was used to sort the array
     * @return the index of the first instance of the key. If the key is not found returns -1
     */
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator){

        int min = 0;
        int max = a.length - 1;
        while(min <= max){
            int middle = (min + max) / 2;
            int comparedValue = comparator.compare(a[middle],key);
            if(comparedValue < 0){
                min = middle + 1;
            } else if (comparedValue > 0) {
                max = middle - 1;
            } else if(middle != 0) {
                int lowerIndex = comparator.compare(a[middle - 1],key);
                if (lowerIndex == 0) {
                    max = middle - 1;
                } else {
                    return middle;
                }
            } else {
                return middle;
            }
        }
        return -1;
    }

    /**
     * This uses a binary search to find the last instance of a key in a sorted array.
     * @param a is the array to search through
     * @param key is the key you are looking for
     * @param comparator is the comparator that was used to sort the array
     * @return the index of the last instance of the key. If the key is not found returns -1
     */
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator){
        int min = 0;
        int max = a.length - 1;

        while(min <= max){
            int middle = (min + max) / 2;
            int comparedValue = comparator.compare(a[middle],key);
            if(comparedValue < 0){
                min = middle + 1;
            } else if (comparedValue > 0) {
                max = middle - 1;
            } else if(middle != a.length - 1) {
                int lowerIndex = comparator.compare(a[middle + 1],key);
                if (lowerIndex == 0) {
                    min = middle + 1;
                } else {
                    return middle;
                }
            } else {
                return middle;
            }
        }
        return -1;
    }

    /**
     * Main class only used for testing purposes.
     */
    public static void main(String[] args) {
//        Term testTerm = new Term("this", 20);
        Term[] termArray = {new Term("c1", 1.4),
                new Term("c1", 3),
                new Term("c1", 5),
                new Term("c1", 2),
                new Term("if", 1.4),
                new Term("this", 14.2),
                new Term("is", 25),
                new Term("to", 25),
                new Term("end", 25),
                new Term("in", 509),
                new Term("fire", 664),
                new Term("then", 7385),
                new Term("we", 8732),
                new Term("should", 90321),
                new Term("all", 821111),
                new Term("burn", 1911123),
                new Term("together", 1928374623)};

        String prefix = "fire";
        Term testTerm = new Term(prefix, 509);
        Comparator comp = Comparator.naturalOrder();
        Arrays.sort(termArray, comp);
        comp = Term.byPrefixOrder(prefix.length());
        System.out.println(firstIndexOf(termArray, testTerm, comp));
        System.out.println(lastIndexOf(termArray, testTerm, comp));


//
//        int firstIndex = firstIndexOf(termArray, testTerm, Term.byReverseWeightOrder());

//        int[] intArray = {10,10,20,30,30,30,30,30,30,30,30,30,30,30,30,40,50,60,70,70,80,90};
//
//        Integer[] IntegerArray = new Integer[intArray.length];
//        for (int i = 0; i< intArray.length; i++){
//            IntegerArray[i] = intArray[i];
//        }
//
//        Integer testInt = 10;
//        Comparator comp = Comparator.naturalOrder();
//        Arrays.sort(IntegerArray, comp);
//
//        System.out.println(firstIndexOf(IntegerArray, testInt, comp));
//        System.out.println(lastIndexOf(IntegerArray, testInt, comp));

    }


}
