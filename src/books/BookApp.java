package books;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BookApp {

    public static void main(String[] args) {

        Book[] bookArray = getBookArray("src/books/books.csv");
        sortNatural(bookArray);
        sortReverse(bookArray);

    }

    private static void sortNatural(Book[] bookArray){
        System.out.println("\nBooks sorted in natural order:");
        Arrays.sort(bookArray);
        for(int i = 0; i < bookArray.length; i++){
            System.out.println(bookArray[i].toString());
        }
    }

    private static void sortReverse(Book[] bookArray){
        System.out.println("\nBooks sorted in reverse order:");
        Arrays.sort(bookArray, Collections.reverseOrder());
        for(int i = 0; i < bookArray.length; i++){
            System.out.println(bookArray[i].toString());
        }
    }

    private static Book[] getBookArray(String file){
        List<Book> listOfBooks = Book.getList(file);
        System.out.println("Number of books in file:" +listOfBooks.size());
        Book[] bookArray = new Book[listOfBooks.size()];
        for(int i = 0; i < listOfBooks.size(); i++){
            bookArray[i] = listOfBooks.get(i);
        }
        return bookArray;
    }



}
