package a05;


import edu.princeton.cs.algs4.*;
import java.math.BigDecimal;

public class KdTreeST<Value> {
    private Node root;
    private int size;

    private class Node implements Comparable<Node> {
        private Point2D p;      // the point
        private Value value;    // the symbol table maps the point to this value
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree
        public int layer;       // current layer of the tree
        public int size;

        public Node(Point2D p, Value value, RectHV rect, int layer, int size){
            this.p = p;
            this.value = value;
            this.rect = rect;
            this.layer = layer;
            this.size = size;
        }

        @Override
        public int compareTo(Node o) {
            return compareTo(o.p);
        }

        public int compareTo(Point2D p) {
            double compare1;
            double compare2;
            int compareResult;

            if(this.layer % 2 == 0){
                compare1 = this.p.x();
                compare2 = p.x();
            } else {
                compare1 = this.p.y();
                compare2 = p.y();
            }

            if(compare1 > compare2)compareResult = (int) - (Math.ceil(compare1 - compare2));
            else                   compareResult = (int) Math.ceil(compare2 - compare1);

            return compareResult;
        }

        public int trueCompare(Point2D p){
            int compare1;
            int compare2;

            BigDecimal bd1;
            BigDecimal bd2;
            if(this.layer % 2 == 0){
                bd1 = BigDecimal.valueOf(this.p.x());
                bd2 = BigDecimal.valueOf(p.x());
            } else {
                bd1 = BigDecimal.valueOf(this.p.y());
                bd2 = BigDecimal.valueOf(p.y());
            }

            bd1 = bd1.movePointRight(6);
            bd2 = bd2.movePointRight(6);

            compare1 = bd1.intValue();
            compare2 = bd2.intValue();
            return (compare1 - compare2);
        }

        public double distance(Point2D p){
            //TODO : this is where i was
            double compare1;
            double compare2;

            if(this.layer % 2 == 0){
                compare1 = this.p.x();
                compare2 = p.x();
            } else {
                compare1 = this.p.y();
                compare2 = p.y();
            }

            return (compare1 - compare2);
        }

        public void updateLayer(){
            layer++;
        }

        public void updateRect(int compare, Point2D p){
            //TODO: add rect update functionality
            double minX = this.rect.xmin();
            double minY = this.rect.ymin();
            double maxX = this.rect.xmax();
            double maxY = this.rect.ymax();

            if (this.layer % 2 != 0){
                if (compare < 0) maxX = p.x();
                else             minX = p.x();
            } else {
                if (compare < 0) maxY = p.y();
                else             minY = p.y();
            }
            this.rect = new RectHV(minX, minY, maxX, maxY);
        }
    }


    // construct an empty symbol table of points
    public KdTreeST(){
    }

    // is the symbol table empty?
    public boolean isEmpty(){
        return size != 0;
    }

    // number of points
    public int size(){
        return size(root);
    }

    private int size(Node node){
        if(node == null) return 0;
        return node.size;
    }

    // TODO: associate the value val with point p
    // TODO: may need to check if already contains the point
    public void put(Point2D p, Value val){
        if(p == null || val == null) throw new NullPointerException("inputs can't be null");
        RectHV rect = new RectHV(Double.NEGATIVE_INFINITY,Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY);
        Node child = new Node(p, val, rect, 0, 1);
        root = put(root, child);
    }

    private Node put(Node parent, Node child){
        if (parent == null) {
//            StdOut.println("child created: " + child.p + "  layer: "+ child.layer + "  rect: " + child.rect);
            return child;
        }
        int compare = parent.compareTo(child);
//        System.out.println("Parent: " + parent.p + "  Child: " + child.p + "  Compare: " + compare + "  Layer: " + parent.layer);
        child.updateLayer();
        child.updateRect(compare, parent.p);
        if (compare < 0) parent.lb  = put(parent.lb,  child);
        else             parent.rt = put(parent.rt, child);
        parent.size = size(parent.lb) + size(parent.rt) + 1;
        return parent;
    }


    // TODO: value associated with point p
    public Value get(Point2D p){
        if(p == null) throw new NullPointerException("input can't be null");
        return get(root, p);
    }

    private Value get(Node parent, Point2D p) {
        if (parent == null) return null;
        int compare = p.compareTo(parent.p);
        if(compare == 0) return parent.value;
        compare = parent.compareTo(p);
        if      (compare < 0) return get(parent.lb, p);
        else                  return get(parent.rt, p);
    }

    // does the symbol table contain point p?
    public boolean contains(Point2D p){
        if(p == null) throw new NullPointerException("input can't be null");
        // check if the point is in a nodes rectangle if it is continue and check the point and its subtrees if not stop.
        Value value = get(p);
        return (value == null);
    }

    // TODO: all points in the symbol table
    public Iterable<Point2D> points(){
        Queue<Point2D> queue = new Queue<Point2D>();
        points(root, queue);
        return queue;
    }

    private void points(Node parent, Queue<Point2D> queue){
        if(parent == null) return;
        queue.enqueue(parent.p);
        points(parent.lb, queue);
        points(parent.rt, queue);
    }

    // TODO: all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect){
        Queue<Point2D> queue = new Queue<Point2D>();
        range(root, queue, rect);
        return queue;
    }

    private void range(Node parent, Queue<Point2D> queue, RectHV rect){
        if(parent == null) return;
        if(!rect.intersects(parent.rect)) return;
        if(rect.contains(parent.p)) queue.enqueue(parent.p);
        range(parent.lb, queue, rect);
        range(parent.rt, queue, rect);
    }

    // TODO: a nearest neighbor to point p; null if the symbol table is empty
    public Point2D nearest(Point2D p){
        if(p == null) throw new NullPointerException("input can't be null");
        if(isEmpty()) return null;
        Point2D nearestPoint = nearest(root, p, root).p;
        return nearestPoint;
    }

    private Node nearest(Node node, Point2D p, Node nearest){
        if(nearest == null) throw new NullPointerException("The nodes cant be null");
        if(node == null) return nearest;
        double currentDistance = nearest.distance(p);
        System.out.println("current distance: " + Math.abs(currentDistance) + " node distance: " + Math.abs(node.distance(p)));
        if(Math.abs(node.distance(p)) > Math.abs(currentDistance)) return nearest;
        if(Math.abs(node.p.distanceSquaredTo(p)) < Math.abs(nearest.p.distanceSquaredTo(p))) nearest = node;

        if(currentDistance >= 0){
            nearest = nearest(node.rt, p, nearest);
            nearest = nearest(node.lb, p, nearest);
        } else {
            nearest = nearest(node.lb, p, nearest);
            nearest = nearest(node.rt, p, nearest);
        }

        return nearest;
    }


    // unit testing of the methods (not graded)
    public static void main(String[] args){
        String filename = "resources/a05/input1K.txt";
        In in = new In(filename);
//        RectHV rect = new RectHV(0.25, 0.25, 0.75, 0.75);
        RectHV rect = new RectHV(0.75, 0.75, 1, 1);

        KdTreeST<Integer> kdTree = new KdTreeST<Integer>();
        for (int i = 0; !in.isEmpty(); i++) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdTree.put(p, i);
        }

        System.out.println("kdTree size: " + kdTree.size());
        System.out.println("Is kdTree empty: " + kdTree.isEmpty());

        int count = 0;
        for(Point2D p: kdTree.points()){
            count ++;
        }
        System.out.println("Number of points: " + count);

        count = 0;
        for(Point2D p: kdTree.range(rect)){
            count ++;
        }
        System.out.println("Number of points in rect: " + count);
        System.out.println("Nearest point: " + kdTree.nearest(new Point2D(0,0)));
    }

}
