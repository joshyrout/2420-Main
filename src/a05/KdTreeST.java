package a05;


import edu.princeton.cs.algs4.*;
import java.math.BigDecimal;
import java.security.cert.PolicyNode;

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
            Double compare1;
            Double compare2;

            if(this.layer % 2 == 0){
                compare1 = this.p.x();
                compare2 = p.x();
            } else {
                compare1 = this.p.y();
                compare2 = p.y();
            }

            return Double.compare(compare2, compare1);
        }

        public double distance(Point2D p){
            double compare1;
            double compare2;

            if(this.layer % 2 == 0){
                compare1 = this.p.x();
                compare2 = p.x();
            } else {
                compare1 = this.p.y();
                compare2 = p.y();
            }

            return compare2 - compare1;
        }

        public double distanceBD(Point2D p){ //TODO: remove
            BigDecimal compare1;
            BigDecimal compare2;

            if(this.layer % 2 == 0){
                compare1 = BigDecimal.valueOf(this.p.x());
                compare2 = BigDecimal.valueOf(p.x());
            } else {
                compare1 = BigDecimal.valueOf(this.p.y());
                compare2 = BigDecimal.valueOf(p.y());
            }

            compare1 = compare2.subtract(compare1);

            return compare1.doubleValue();
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
                if (compare > 0) maxX = p.x();
                else             minX = p.x();
            } else {
                if (compare > 0) maxY = p.y();
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
        return (root == null);
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
        Node child = new Node(p, val, rect, 1, 1);
        root = put(root, child);
    }

    private Node put(Node parent, Node child){
        if (parent == null) {
//            StdOut.println("child created: " + child.p + "  layer: "+ child.layer + "  rect: " + child.rect); TODO: remove
            return child;
        }
        int compare = child.compareTo(parent);
//        System.out.println("Parent: " + parent.p + "  Child: " + child.p + "  Compare: " + compare + "  Layer: " + parent.layer); TODO: remove
        child.updateLayer();
        child.updateRect(compare, parent.p);
        if (compare < 0) parent.lb  = put(parent.lb,  child);
        else if (compare > 0) parent.rt = put(parent.rt, child);
        else if(child.p.compareTo(parent.p) == 0) parent.value = child.value;
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
        int compare = parent.p.compareTo(p);
        if      (compare < 0) return get(parent.lb, p);
        else if (compare < 0) return get(parent.rt, p);
        else return parent.value;
    }

    // does the symbol table contain point p?
    public boolean contains(Point2D p){
        if(p == null) throw new NullPointerException("input can't be null");
        Value value = get(p);
        return (value != null);
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

    // a nearest neighbor to point p; null if the symbol table is empty
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
//        System.out.println("current distance: " + Math.abs(currentDistance) + " node distance: " + Math.abs(node.distance(p))); //TODO: remove
        if(!node.rect.contains(p) && Math.abs(node.distance(p)) > Math.abs(currentDistance)) return nearest;
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
        String filename = "resources/a05/input100k.txt";
        In in = new In(filename);
//        RectHV rect = new RectHV(0.25, 0.25, 0.75, 0.75);
        RectHV rect = new RectHV(0.75, 0.75, 1, 1);

        KdTreeST<Integer> kdTree = new KdTreeST<Integer>();
        System.out.println("Is kdTree empty: " + kdTree.isEmpty());
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
        in = new In(filename);
        for (int i = 0; !in.isEmpty(); i++) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            if(kdTree.contains(p)) count++;
        }

        System.out.println("kdTree contains check: " + count);

        count = 0;
        for(Point2D p: kdTree.range(rect)){
            count ++;
        }
        System.out.println("Number of points in rect: " + count);
        System.out.println("Nearest point: " + kdTree.nearest(new Point2D(0,0)));
    }

}
