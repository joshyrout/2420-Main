package ceStable;

import java.util.Comparator;

public class Rectangle implements Comparable<Rectangle> {
    private int length;
    private int width;
    public static final CompareByArea BY_Area = new CompareByArea();

    public Rectangle(int length, int width){
        this.length = length;
        this.width = width;
    }
    public int getLength(){
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int perimeter(){
        return ((2 * width) + (2 * length));
    }

    public int area(){
        return (width * length);
    }

    public String toString(){
        String send = "[" + length + "x" + width + "]";
        return send;
    }

    @Override
    public int compareTo(Rectangle o) {
        return this.length - o.length;
    }

    public static class CompareByArea implements Comparator<Rectangle> {
         @Override
        public int compare(Rectangle o1, Rectangle o2) {
            int area1 = o1.area();
            int area2 =o2.area();
            return area1 - area2;
        }
    }

}
