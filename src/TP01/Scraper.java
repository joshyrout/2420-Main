package TP01;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Scraper {
    public static void main(String[] args) {

        try {
            Document doc = Jsoup.connect("https://www.latlong.net/category/national-parks-236-42.html").get();
            Elements elementstr = doc.getElementsByTag("td");
            int count = 0;
            Element e1 = null, e2 = null, e3 = null;
            File file = new File("src\\TP01\\NationalParks.txt");;

            if(!file.exists()){
                file.createNewFile();
            } else {
                file.delete();
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for(Element e: elementstr){

                if(e1 == null){
                    e1 = e;
                } else if(e2 == null){
                    e2 = e;
                } else {
                    e3 = e;
                    String s1 = e1.text().split(",")[0] + "," + e2.text() + "," + e3.text();
                    printWriter.println(s1);
                    e1 = null;
                    e2 = null;
                    e3 = null;
                }
            }

            printWriter.flush();
            printWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
