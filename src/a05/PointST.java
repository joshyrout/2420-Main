package a05;

import edu.princeton.cs.algs4.*;


public class PointST<Value>{

    private RedBlackBST<Point2D, Value> redBlackBST;

    // construct an empty symbol table of points
    public PointST(){
        redBlackBST = new RedBlackBST<Point2D, Value>();
    }

    // is the symbol table empty?
    public boolean isEmpty(){
        return redBlackBST.size() == 0;
    }

    // number of points
    public int size(){
        return redBlackBST.size();
    }

    // associate the value val with point p
    public void put(Point2D p, Value val){
        if(p == null || val == null) throw new NullPointerException("inputs can't be null");
        redBlackBST.put(p, val);
    }

    // value associated with point p
    public Value get(Point2D p){
        if(p == null) throw new NullPointerException("input can't be null");
        return redBlackBST.get(p);
    }

    // does the symbol table contain point p?
    public boolean contains(Point2D p){
        if(p == null) throw new NullPointerException("input can't be null");
        return redBlackBST.contains(p);
    }

    // all points in the symbol table
    public Iterable<Point2D> points(){
        return redBlackBST.keys();
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect){
        if(rect == null) throw new NullPointerException("input can't be null");
        Iterable<Point2D> allKeys = redBlackBST.keys();
        Stack<Point2D> includedKeys = new Stack<Point2D>();

        for(Point2D p: allKeys){
            if(p.x() >= rect.xmin() && p.x() <= rect.xmax() && p.y() >= rect.ymin() && p.y() <= rect.ymax()){
                includedKeys.push(p);
            }
        }

        return includedKeys;
    }

    // a nearest neighbor to point p; null if the symbol table is empty
    public Point2D nearest(Point2D p){
        if(p == null) throw new NullPointerException("input can't be null");
        Iterable<Point2D> allKeys = redBlackBST.keys();
        Point2D nearestPoint = null;
        double currentDistance = 0;

        for(Point2D p2: allKeys){
            double distance = p.distanceSquaredTo(p2);
            if(nearestPoint == null){
                nearestPoint = p2;
                currentDistance = distance;
            } else if (distance < currentDistance){
                nearestPoint = p2;
                currentDistance = distance;
            }
        }
        return nearestPoint;
    }

    // unit testing of the methods (not graded)
    public static void main(String[] args){
        String filename = "C:\\Users\\Josh\\IdeaProjects\\2420_GettingStarted\\src\\a05\\input100K.txt";
        In in = new In(filename);
        RectHV rect = new RectHV(0.25, 0.25, 0.75, 0.75);

        PointST<Integer> brute = new PointST<Integer>();
        for (int i = 0; !in.isEmpty(); i++) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            brute.put(p, i);
        }

        System.out.println("Brute size: " + brute.size());
        System.out.println("Is brute empty: " + brute.isEmpty());

        int count = 0;
        for(Point2D p: brute.points()){
            count ++;
        }
        System.out.println("Number of points: " + count);

        count = 0;
        for(Point2D p: brute.range(rect)){
            count ++;
        }
        System.out.println("Number of points in rect: " + count);
        System.out.println("Nearest point: " + brute.nearest(new Point2D(0.5,0.5)));
    }
}
