package books;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Book implements Comparable<Book> {

    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year){
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString(){
        // return string of "name by author (year)"
        String asString = title + " by " + author + " (" + year + ")";
        return asString;
    }

    public static List<Book> getList(String file){
        List list = new ArrayList<Book>();
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while((line = br.readLine()) != null){
                String[] bookCSV = line.split(",");
                if (inValid(line)){
                    String name = bookCSV[0];
                    String author = bookCSV[1];
                    int year = Integer.parseInt(bookCSV[2]);
                    list.add(new Book(name, author, year));

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static boolean isInt(String testString){
        try{
            int isInt = Integer.parseInt(testString);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
     private static boolean inValid(String line){

        String[] testArray = line.split(",");
        if(testArray.length == 3) {
            if (!testArray[0].isEmpty() && !testArray[1].isEmpty() && !testArray[2].isEmpty()) {
                try {
                    int isInt = Integer.parseInt(testArray[2]);
                    return true;
                } catch (Exception e) {
                    System.err.println("Unable to read: \"" + line + "\" The year was not an integer.");
                    return false;
                }
            } else {
                System.err.println("Unable to read: \"" + line + "\" One or more of the entries are empty");
                return false;
            }
        } else {
            System.err.println("Unable to read: \"" + line + "\" Invalid number of entries in line");
            return false;
        }
     }

    @Override
    public int compareTo(Book o) {
        return this.title.compareTo(o.title);
    }
}
