package ceStock;

import edu.princeton.cs.algs4.ST;

import javax.swing.text.DateFormatter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StockApp {

    private static ST<Date, Double> readCSV(String fileName, int keyField, int valField){
        ST<Date, Double> st = new ST<Date, Double>();
        try {
            File file = new File("C:\\Users\\Josh\\IdeaProjects\\2420_GettingStarted\\resources\\" + fileName);
            if (file.exists()) {
                BufferedReader br;
                String line;
                String csvSplitBy = ",";
                br = new BufferedReader(new FileReader(file));
                br.readLine();
                while ((line = br.readLine()) != null) {
                    String[] currentLine = line.split(csvSplitBy);
                    try{
                        java.util.Date date = stringToDate(currentLine[keyField], "yyyy-MM-dd");
                        Double valDouble = Double.parseDouble(currentLine[valField]);
                        st.put(date, valDouble);
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return st;
    }

    public static void main(String[] args) {
        ST<Date, Double> st = readCSV("AMZN.csv", 0, 4);
        System.out.println("Number of elements: " + st.size() + "\n");

        // A-C
        Date key1 = st.min();
        double d1 = st.get(key1);
        print("A) Oldest closing price: ", key1, "%tF", d1);

        Date key2 = st.max();
        double d2 = st.get(key2);
        print("B) Newest closing price: ", key2, "%tF", d2);

        difference("C) The closing price ", d1, d2);

        //D-F
        key1 = stringToDate("08/31/18", "MM/dd/yy");
        d1 = st.get(key1);
        print("D) Closing price from ", key1, "%tm/%<td/%<tY", d1);

        key2 = stringToDate("03/10/20", "MM/dd/yy");
        d2 = st.get(key2);
        print("E) Closing price from ", key2, "%tm/%<td/%<tY", d2);

        difference("F) The closing price ", d1, d2);

        //G & H
        key1 = st.ceiling(stringToDate("01/01/19", "MM/dd/yy"));
        d1 = st.get(key1);
        print("G) first available date in 2019: ", key1, "%tm/%<td/%<tY", d1);

        key1 = st.floor(stringToDate("01/01/21", "MM/dd/yy"));
        d1 = st.get(key1);
        print("H) first available date in 2020: ", key1, "%tm/%<td/%<tY", d1);

    }

    private static void print(String print, Date date, String dateFormat, double dollars){
        StringBuilder sb = new StringBuilder();
        sb.append(print);
        sb.append(String.format(dateFormat, date));
        sb.append(String.format(" $%.2f.", dollars));
        System.out.println(sb.toString());
    }

    private static void difference(String print, double d1, double d2){
        StringBuilder sb = new StringBuilder();
        sb.append(print);
        double difference = d2 - d1;

        if(difference >= 0) sb.append("increased by");
        else                sb.append("decreased by");
        sb.append(String.format(" $%.2f.", Math.abs(difference)));
        System.out.println(sb.toString());
    }

    private static Date stringToDate(String dateString, String pattern){
        try{
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            java.util.Date date = formatter.parse(dateString);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
