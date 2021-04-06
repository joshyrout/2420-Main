package a04;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    MinPQ<SearchNode> pq;
    SearchNode shortestSearchNode;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial){
        if(!initial.isSolvable()) throw new IllegalArgumentException("Not solvable");
        if(initial ==  null) throw new IllegalArgumentException("Not solvable");
        pq = new  MinPQ<SearchNode>();
        SearchNode parentNode = new SearchNode(initial);
        while(!parentNode.board.isGoal()) {
            Iterable<Board> neighbors = parentNode.board.neighbors();
            for (Board board : neighbors) {
                SearchNode childNode = new SearchNode(board, parentNode);
                pq.insert(childNode);
            }
            parentNode = pq.delMin();
        }
        shortestSearchNode = parentNode;
    }

    // min number of moves to solve initial board
    public int moves(){
        return shortestSearchNode.moves;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution(){
        SearchNode searchNode = shortestSearchNode;
        Stack<Board> stack = new Stack<Board>();
        stack.push(searchNode.board);
        while(!(searchNode.previousBoard == null)){
            stack.push(searchNode.previousBoard.board);
            searchNode = searchNode.previousBoard;
        }
        return stack;
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args){

        int[][] inputArray = {{1, 2, 6}, {4, 3, 5}, {7, 8, 0}};
        Board initialBoard = new Board(inputArray);
        Solver solver = new Solver(initialBoard);
        Iterable<Board> stack = solver.solution();
        System.out.println(solver.moves());
        for(Board board : stack){
            System.out.println(board);
        }
    }

//    public static void main(String[] args) {
//
//        // create initial board from file
//        In in = new In(args[0]);
//        int N = in.readInt();
//        int[][] blocks = new int[N][N];
//        for (int i = 0; i < N; i++)
//            for (int j = 0; j < N; j++)
//                blocks[i][j] = in.readInt();
//        Board initial = new Board(blocks);
//
//        // check if puzzle is solvable; if so, solve it and output solution
//        if (initial.isSolvable()) {
//            Solver solver = new Solver(initial);
//            StdOut.println("Minimum number of moves = " + solver.moves());
//            for (Board board : solver.solution())
//                StdOut.println(board);
//        }
//
//        // if not, report unsolvable
//        else {
//            StdOut.println("Unsolvable puzzle");
//        }
//    }

    class SearchNode implements Comparable<SearchNode> {
        Board board;
        int moves;
        int priority;
        SearchNode previousBoard;

        public SearchNode(Board board, SearchNode previousBoard){
            this.board = board;
            this.previousBoard = previousBoard;
            this.moves = previousBoard.moves + 1;
            this.priority = board.manhattan() + this.moves;
        }

        public SearchNode(Board board){
            this.board = board;
            this.previousBoard = null;
            this.moves = 0;
            this.priority = board.manhattan() + this.moves;
        }

        @Override
        public int compareTo(SearchNode o) {
            return this.priority - o.priority;
        }
    }
}


