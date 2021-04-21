package a05;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;

/**
 * The KdTreeST class is a symbol table used to store two dimensional points allowing
 * them to be can be queried efficiently. The two queries this class enables are searching for
 * points within a specified rectangle and to find the point nearest to a specified point.
 *
 * @param <Value> The value type associated with a key to create a key-value pair.
 * @author Jedidiah Schmith, Josh Routledge
 * @version 1.0
 */

public class KdTreeST<Value> {
    private Node root;

    /**
     * This class is used to hold all the information needed by the symbol table.
     */
    private class Node implements Comparable<Node> {
        private Point2D p;      // the point
        private Value value;    // the symbol table maps the point to this value
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree
        public boolean vertical;
        public int size;

        /**
         * This is the constructor for the node class.
         * @param p Point2D
         * @param value Value
         * @param rect RectHV
         * @param size int
         * @param vertical boolean
         */
        public Node(Point2D p, Value value, RectHV rect, int size, boolean vertical){
            this.p = p;
            this.value = value;
            this.rect = rect;
            this.size = size;
            this.vertical = vertical;
        }

        /**
         * This inverts the nodes layer.
         */
        public void updateLayer(){
            vertical = !vertical;
        }

        /**
         *  This method compares this node to another node. Only compares either the x coordinates or the
         *  y coordinates depending on whether the node is vertical or horizontal.
         * @param o The other Node you want to compare against.
         * @return int Returns 1 other node is smaller, returns 0 if the nodes are equal, and returns -1 if its larger.
         */
        @Override
        public int compareTo(Node o) {
            return compareTo(o.p);
        }

        /**
         *  This method compares this node's point against another point. Only compares either the x coordinates or the
         *  y coordinates depending on whether the node is vertical or horizontal.
         * @param p The Point2D you want to compare against.
         * @return int Returns 1 point is smaller, returns 0 if they are equal, and returns -1 if its larger.
         */
        public int compareTo(Point2D p) {
            Double compare = distance(p);
            return Double.compare(compare, 0);
        }

        /**
         *  This method compares the distance between a node's point and another point. Only compares either the x
         *  coordinates or the y coordinates depending on whether the node is vertical or horizontal.
         * @param p The Point2D you want to compare against.
         * @return double The distance between the points x or y values.
         */
        public double distance(Point2D p){
            double compare1;
            double compare2;

            if(vertical){
                compare1 = this.p.x();
                compare2 = p.x();
            } else {
                compare1 = this.p.y();
                compare2 = p.y();
            }
            return compare2 - compare1;
        }

        /**
         * Updates a nodes rectangle.
         * @param compare The compare result from this node and its parent.
         * @param p The point of its parent.
         */
        public void updateRect(int compare, Point2D p){
            double minX = this.rect.xmin();
            double minY = this.rect.ymin();
            double maxX = this.rect.xmax();
            double maxY = this.rect.ymax();

            if (!vertical){
                if (compare < 0) maxX = p.x();
                else             minX = p.x();
            } else {
                if (compare < 0) maxY = p.y();
                else             minY = p.y();
            }
            this.rect = new RectHV(minX, minY, maxX, maxY);
        }

        /**
         * This returns the distance from a given point's x or y value to the closest side of the nodes rectangle
         * depending on if the node is vertical or horizontal.
         * @param p Point2D The point being compared against.
         * @return double The distance.
         */
        public double distanceToRect(Point2D p) {
            double compare = 0.0;
            if(vertical){
                if      (p.x() < rect.xmin()) compare = p.x() - rect.xmin();
                else if (p.x() > rect.xmax()) compare = p.x() - rect.xmax();
            } else {
                if      (p.y() < rect.ymin()) compare = p.y() - rect.ymin();
                else if (p.y() > rect.ymax()) compare = p.y() - rect.ymax();
            }
            return Math.abs(compare);
        }
    }


    /**
     * Constructs an empty symbol table of points.
     */
    public KdTreeST(){
    }

    /**
     * Checks the if the symbol table is empty.
     * @return true if empty else returns false.
     */
    public boolean isEmpty(){
        return (root == null);
    }

    /**
     * Returns the number of entries in the symbol table.
     * @return int The size.
     */
    public int size(){
        return size(root);
    }

    /**
     * This method returns the current number of entries within a nodes subtrees plus itself.
     * @return int The size.
     */
    private int size(Node node){
        if(node == null) return 0;
        return node.size;
    }

    /**
     * This runs a recursive method to add an new node into the symbol table.
     * @param p Point2D A point used as the key for the node.
     * @param val Value A Value used as the value for the node.
     */
    public void put(Point2D p, Value val){
        if(p == null || val == null) throw new NullPointerException("inputs can't be null");
        RectHV rect = new RectHV(Double.NEGATIVE_INFINITY,Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY);
        Node child = new Node(p, val, rect, 1, true);
        root = put(root, child);
    }

    private Node put(Node parent, Node child){
        if (parent == null) {
            return child;
        }
        child.updateLayer();
        int compare = parent.compareTo(child);
        child.updateRect(compare, parent.p);
        if(parent.p.compareTo(child.p) == 0) {parent.value = child.value; return parent;}
        if (compare < 0) parent.lb  = put(parent.lb,  child);
        else if (compare >= 0) parent.rt = put(parent.rt, child);
        parent.size = size(parent.lb) + size(parent.rt) + 1;
        return parent;
    }

    /**
     * This runs a recursive method to get the value associated with the given key from the symbol table.
     * @param p Point2D The point you are looking for a value for.
     * @return Value This returns the value associated with point p, if the point is not found, it will returns null.
     */
    public Value get(Point2D p){
        if(p == null) throw new NullPointerException("input can't be null");
        return get(root, p);
    }

    private Value get(Node parent, Point2D p) {
        if (parent == null) return null;
        if(parent.p.compareTo(p) == 0) return parent.value;
        int compare = parent.compareTo(p);
        if (compare < 0) return get(parent.lb, p);
        else             return get(parent.rt, p);
    }

    /**
     * Checks for a given point in the symbol table.
     * @param p Point2D the point you are checking for.
     * @return boolean returns true if the point is found else returns false.
     */
    public boolean contains(Point2D p){
        if(p == null) throw new NullPointerException("input can't be null");
        Value value = get(p);
        return (value != null);
    }

    /**
     * Returns all points in the symbol table.
     * @return Iterable<Point2D> An iterable of points.
     */
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

    /**
     * Returns all points in the symbol table that fall within a given rectangle.
     * @param rect The given rectangle.
     * @return Iterable<Point2D> An iterable of points.
     */
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

    /**
     * This returns the nearest neighbor to point p.
     * @param p Point2D The point being compared against.
     * @return Point2D The nearest neighbor. Will return null if the table is empty.
     */
    public Point2D nearest(Point2D p){
        if(p == null) throw new NullPointerException("input can't be null");
        if(isEmpty()) return null;
        Point2D nearestPoint = nearest(root, p, root).p;
        return nearestPoint;
    }

    private Node nearest(Node node, Point2D p, Node nearest){
        if(nearest == null) throw new NullPointerException("The nodes cant be null");
        if(node == null) return nearest;

        if(node.distanceToRect(p) > nearest.p.distanceTo(p)) return nearest;
        if(Math.abs(node.p.distanceSquaredTo(p)) < Math.abs(nearest.p.distanceSquaredTo(p))) nearest = node;

        if(nearest.distance(p) >= 0){
            nearest = nearest(node.rt, p, nearest);
            nearest = nearest(node.lb, p, nearest);
        } else {
            nearest = nearest(node.lb, p, nearest);
            nearest = nearest(node.rt, p, nearest);
        }
        return nearest;
    }

    /**
     * The Main method was only used for testing this class.
     */
    public static void main(String[] args){
//        String filename = "resources/a05/input10.txt";
//        In in = new In(filename);
//        RectHV rect = new RectHV(0.75, 0.75, 1, 1);
//
//        KdTreeST<Integer> kdTree = new KdTreeST<Integer>();
//        System.out.println("Is kdTree empty: " + kdTree.isEmpty());
//        for (int i = 0; !in.isEmpty(); i++) {
//            double x = in.readDouble();
//            double y = in.readDouble();
//            Point2D p = new Point2D(x, y);
//            kdTree.put(p, i);
//        }
//
//        System.out.println("kdTree size: " + kdTree.size());
//        System.out.println("Is kdTree empty: " + kdTree.isEmpty());
//
//        int count = 0;
//        for(Point2D p: kdTree.points()){
//            count ++;
//        }
//        System.out.println("Number of points: " + count);
//
//        count = 0;
//        in = new In(filename);
//        for (int i = 0; !in.isEmpty(); i++) {
//            double x = in.readDouble();
//            double y = in.readDouble();
//            Point2D p = new Point2D(x, y);
//            if(kdTree.contains(p)) count++;
//        }
//
//        System.out.println("kdTree contains check: " + count);
//        count = 0;
//        for(Point2D p: kdTree.range(rect)){
//            count ++;
//        }
//        System.out.println("Number of points in rect: " + count);
//        System.out.println("Nearest point: " + kdTree.nearest(new Point2D(0,0)));
    }

}
