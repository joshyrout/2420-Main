package a03;

import edu.princeton.cs.algs4.Merge;

import java.util.Arrays;
import java.util.Comparator;

/**
 * This class creates an immutable Autocomplete object which once populated with an array of terms, it can be searched
 * through and sorted.
 *
 * @author Huakang Zhou, Joshua Routledge
 * @version 1.0
 */
public class Autocomplete {

    private final Term[] termArray;

    /**
     * This constructs an immutable array of Terms that is sorted using mergesort.
     * @param terms is an array of Terms[] to be added
     */
    public Autocomplete(Term[] terms) {
        if(terms == null){
            throw new NullPointerException("Argument can't be null");
        }
        Merge.sort(terms);
        termArray = terms;
    }


    /**
     * This finds all matches for a given prefix within the Terms array and returns them as an array of terms.
     * @param prefix is a string used to compare to Terms
     * @return an array of Terms
     */
    public Term[] allMatches(String prefix) {
        if(prefix == null){
            throw new NullPointerException("Argument can't be null");
        }
        int numberOfMatches = numberOfMatches(prefix);
        if (numberOfMatches == 0) {
            return new Term[0];
        }
        Term[] searchResults = new Term[numberOfMatches];
        Term prefixAsTerm = new Term(prefix, 0);
        Comparator prefixOrderComp = Term.byPrefixOrder(prefix.length());
        int firstIndex = BinarySearchDeluxe.firstIndexOf(termArray, prefixAsTerm, prefixOrderComp);
        for (int i = 0; i < numberOfMatches; i++) {
            searchResults[i] = termArray[firstIndex + i];
        }
        Comparator byReverseComp = Term.byReverseWeightOrder();
        Arrays.sort(searchResults, byReverseComp);
        return searchResults;
    }

    /**
     * This returns the number of matches found for a given prefix in the Term array.
     * @param prefix is a string used to compare to Terms
     * @return the number of matches found as an integer
     */
    public int numberOfMatches(String prefix) {
        if(prefix == null){
            throw new NullPointerException("Argument can't be null");
        }
        Term prefixAsTerm = new Term(prefix, 0);
        int length = prefix.length();
        Comparator prefixOrderComp = Term.byPrefixOrder(length);
        int firstIndex = BinarySearchDeluxe.firstIndexOf(termArray, prefixAsTerm, prefixOrderComp);
        if (firstIndex == -1) {
            return 0;
        }
        int lastIndex = BinarySearchDeluxe.lastIndexOf(termArray, prefixAsTerm, prefixOrderComp);
        return lastIndex - firstIndex + 1;
    }

    /**
     * Main class only used for testing purposes.
     */
    public static void main(String[] args) {
        Term[] termArray = {
                new Term("c1", 1.4),
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
                new Term("together", 1928374623)
        };

        String prefix = "t";
        Autocomplete autocomplete = new Autocomplete(termArray);
        System.out.println(autocomplete.numberOfMatches(prefix));
        for (int i = 0; i < autocomplete.termArray.length; i++) {
            System.out.println(autocomplete.termArray[i]);
        }

        Term[] results = autocomplete.allMatches(prefix);
        System.out.println();
        System.out.println("Results: ");
        System.out.println("==============");
        for (int i = 0; i < results.length; i++) {
            System.out.println(results[i]);
        }
    }
}