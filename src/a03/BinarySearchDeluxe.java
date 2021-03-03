package a03;

import java.util.Arrays;
import java.util.Comparator;

public class BinarySearchDeluxe {


    // Return the index of the first key in a[] that equals the search key, or -1 if no such key.
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

    // Return the index of the last key in a[] that equals the search key, or -1 if no such key.
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
