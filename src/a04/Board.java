package a04;

import java.text.Format;

public class Board {

    //use a heap to track the priority of each board. the priority is the Hamming number(future cost estimate) + the number of moves it has already taken(distance from start).
    //then check the lowest priority board in the heap
    //use a* algorithm as it always find the shortest path as long as the future cost estimate is correct
    //build a private class called SearchNode that contains a board, the moves it has made, its priority and the previous board
    /* -- isSolvable()
        you can tell if a board is solvable with a parody based argument. like in chess a white bishop can never end up on black and vice versa
        on an odd board if the inversion is odd then it is unsolvable
        on an even board you add the row number containing the blank space to the number of inversions and if its even it is unsolvable
        on even the sum always changes by an even amount
     */
    //don't cast an iterable to whatever you use. ie don't do this Stack<Board> a = (Stack<Board>) x.neighbors();
    //follow the rules in the book for writing an equals method


    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)

    int[] boardArray;
    int N;

    public Board(int[][] blocks) {
        N = blocks.length;
        int count = N * N;
        boardArray = new int[count];
        for(int i = 0; i < count; i++){
            boardArray[i] = blocks[i / N][i % N];
        }
    }

    // board size N
    public int size(){
        return N;
    }

    // number of blocks out of place
    public int hamming(){
        int count = 0;
        for(int i = 0; i < boardArray.length; i++){

        }
        return 0;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan(){
        return 0;
    }

    // is this board the goal board?
    public boolean isGoal(){
        return false;
    }

    // is this board solvable?
    public boolean isSolvable(){
        return false;
    }

    // does this board equal y?
    public boolean equals(Object y){
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors(){
        return null;
    }

    // string representation of this board (in the output format specified below)
    public String toString(){
        String string = String.format("%2d%2d%2d\n%2d%2d%2d\n%2d%2d%2d",boardArray[0]);
        return string;
    }

    // unit tests (not graded)
    public static void main(String[] args){

        int[][] inputArray = {{0,1,2},{3,4,5},{6,7,8}};
        Board initialBoard = new Board(inputArray);
        System.out.println(initialBoard);

    }
}
