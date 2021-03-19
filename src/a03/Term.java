package a03;

import java.util.Arrays;
import java.util.Comparator;

/**
 * This class creates an immutable Term object used for Autocomplete searches.
 *
 * @author Huakang Zhou, Joshua Routledge
 * @version 1.0
 */
public class Term implements Comparable<Term> {

    private final String query;
    private final double weight;

    /**
     * This constructs a Term using an inputted weight and query.
     * @param query is a string that will be used for comparison
     * @param weight is a double that determines the priority of a Term when sorting
     */
    public Term(String query, double weight){
        if(query == null){
            throw new NullPointerException("The query was empty.");
        }
        if(weight < 0){
            throw new IllegalArgumentException("The weight was less then Zero.");
        }
        this.query = query;
        this.weight = weight;
    }

    /**
     * This instantiates a comparator for sorting Terms using the ReverseOrderWeight class.
     * @return a comparator
     */
    public static Comparator<Term> byReverseWeightOrder(){
        return new ReverseOrderWeight();
    }

    /**
     * This instantiates a comparator for sorting Terms using the PrefixOrderQuery class.
     * @param r number of letters are used for comparison
     * @return a comparator
     */
    public static Comparator<Term> byPrefixOrder(int r){
        if(r < 0){
            throw new IllegalArgumentException("The integer inputted was less then zero.");
        }
        return new PrefixOrderQuery(r);
    }

    /**
     * This compares two terms and returns a negative number, zero, or a positive number.
     * @param that is the term you are comparing this one too
     * @return a negative, a zero, or a positive number
     */
    public int compareTo(Term that){
        return (this.query.compareTo(that.query));
    }

    /**
     * This returns the Term as a string in the format of:  (weight)  (query).
     * @return term as a string
     */
    public String toString(){
        String returnString = String.format("%.1f\t%s", weight, query);
        return returnString;
    }

    /**
     * This class creates a comparator for sorting Terms by weight in descending order.
     */
    private static class ReverseOrderWeight implements Comparator<Term>{

        /**
         * This compares two Terms based on their weight in descending order
         * and outputs either a negative number, zero, or a positive number.
         * @param o1 is the first Term to compare
         * @param o2 is the second Term to compare
         * @return a negative number, zero, or a positive number
         */
        @Override
        public int compare(Term o1, Term o2) {
            double weight1 = o1.weight;
            double weight2 = o2.weight;
            int compareResult;
            //Handles weights that have a difference of less then .5
            if(weight1 > weight2){
                compareResult = (int) - (Math.ceil(weight1 - weight2));
            } else {
                compareResult = (int) Math.ceil(weight2 - weight1);
            }
            //Used to flip the input order if they are equal
            if(compareResult == 0){
                return -1;
            }
            return compareResult;
        }
    }

    /**
     * This class creates a comparator for sorting Terms in lexicographic order using the first r characters.
     */
    private static class PrefixOrderQuery implements Comparator<Term>{
        int count;

        /**
         * This constructs the Compatator for length r
         * @param r is how many letters to use for comparison of each string
         */
        public PrefixOrderQuery(int r){
            count = r;
        }

        /**
         * This compares two Terms together based on the first r number of letters
         * and outputs either a negative number, zero, or a positive number.
         * @param o1 is the first Term to compare
         * @param o2 is the second Term to compare
         * @return a negative number, zero, or a positive number
         */
        @Override
        public int compare(Term o1, Term o2) {
            String substring01;
            String substring02;
            if(o1.query.length() < count) {
                substring01 = o1.query;
            } else {
                substring01 = o1.query.substring(0, count);
            }

            if(o2.query.length() < count) {
                substring02 = o2.query;
            } else {
                substring02 = o2.query.substring(0, count);
            }
            return substring01.compareTo(substring02);
        }
    }

    /**
     * Main class only used for testing purposes.
     */
    public static void main(String[] args) {
        Term[] termArray = {new Term("c1", 1.4),
                            new Term("c2", 1.4),
                            new Term("c3", 1.6),
                            new Term("c4", 2),
                            new Term("If", 1.4),
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

        for(int i = 0; i < termArray.length; i++){
            System.out.println(termArray[i].toString());
        }
        System.out.println();
        Arrays.sort(termArray, Term.byReverseWeightOrder());
        for(int i = 0; i < termArray.length; i++){
            System.out.println(termArray[i].toString());
        }

    }
}