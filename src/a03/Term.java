package a03;

import java.util.Arrays;
import java.util.Comparator;

public class Term implements Comparable<Term> {

    private String query;
    private double weight;

    // Initialize a term with the given query string and weight.
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

    // Compare the terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder(){
        return new ReverseOrderWeight();
    }

    // Compare the terms in lexicographic order but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r){
        if(r < 0){
            throw new IllegalArgumentException("The integer inputted was less then zero.");
        }
        return new PrefixOrderQuery(r);
    }

    // Compare the terms in lexicographic order by query.
    public int compareTo(Term that){
        return (this.query.compareTo(that.query));
    }

    // Return a string representation of the term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString(){
        String returnString = String.format("%.1f\t%s", weight, query);
        return returnString;
    }

    private static class ReverseOrderWeight implements Comparator<Term>{

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

    private static class PrefixOrderQuery implements Comparator<Term>{
        int count;
        public PrefixOrderQuery(int r){
            count = r;
        }

        @Override
        public int compare(Term o1, Term o2) {
            int maxLength = count;
            if(o1.query.length() < maxLength){
                maxLength = o1.query.length();
            }
            if(o2.query.length() < maxLength){
                maxLength = o2.query.length();
            }
            String substring01 = o1.query.substring(0,maxLength);
            String substring02 = o2.query.substring(0,maxLength);
            // TODO: Unsure if needed / should it be case sensitive.
            substring01 = substring01.toLowerCase();
            substring02 = substring02.toLowerCase();
            return substring01.compareTo(substring02);
        }
    }

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