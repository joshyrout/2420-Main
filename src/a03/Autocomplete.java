package a03;

import edu.princeton.cs.algs4.Merge;

import java.util.Arrays;
import java.util.Comparator;


public class Autocomplete {

    private final Term[] termArray;

    // Initialize the data structure from the given array of terms.
    public Autocomplete(Term[] terms){
        Merge.sort(terms);
        termArray = terms;
    }

    // Return all terms that start with the given prefix, in descending order of weight.
    public Term[] allMatches(String prefix){

        int numberOfMatches = numberOfMatches(prefix);
        if (numberOfMatches == 0){
            return new Term[0];
        }
        Term[] searchResults = new Term[numberOfMatches];
        Term prefixAsTerm = new Term(prefix, 0);
        Comparator prefixOrderComp = Term.byPrefixOrder(prefix.length());
        int firstIndex = BinarySearchDeluxe.firstIndexOf(termArray, prefixAsTerm,prefixOrderComp);
        for(int i = 0; i < numberOfMatches; i++){
            searchResults[i] = termArray[firstIndex + i];
        }
        Comparator byReverseComp = Term.byReverseWeightOrder();
        Arrays.sort(searchResults, byReverseComp);
        return searchResults;
    }

    // Return the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix){
        Term prefixAsTerm = new Term(prefix, 0);
        int length = prefix.length();
        Comparator prefixOrderComp = Term.byPrefixOrder(length);
        int firstIndex = BinarySearchDeluxe.firstIndexOf(termArray, prefixAsTerm,prefixOrderComp);
        if(firstIndex == -1){
            return 0;
        }
        int lastIndex = BinarySearchDeluxe.lastIndexOf(termArray, prefixAsTerm,prefixOrderComp);
        return lastIndex - firstIndex + 1;
    }

    public static void main(String[] args) {
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


        Autocomplete autocomplete = new Autocomplete(termArray);
        System.out.println(autocomplete.numberOfMatches("i"));
        for(int i = 0; i < autocomplete.termArray.length; i++){
            System.out.println(autocomplete.termArray[i]);
        }
        System.out.println();
        String prefix = "bhgyugyg";
        Term[] results = autocomplete.allMatches(prefix);
        for(int i = 0; i < results.length; i++){
            System.out.println(results[i]);
        }

    }
}