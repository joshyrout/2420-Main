package a04;

import edu.princeton.cs.algs4.Stack;

import java.util.ArrayList;
import java.util.List;

public class Board {

    //use a heap to track the priority of each board. the priority is the Hamming number(future cost estimate) + the number of moves it has already taken(distance from start).
    //then check the lowest priority board in the heap
    //use a* algorithm as it always find the shortest path as long as the future cost estimate is correct
    //build a private class called SearchNode that contains a board, the moves it has made, its priority and the previous board

    private final int[][] boardArray;
    private final int N;
    private int zeroRow;
    private int zeroCol;

    public Board(int[][] blocks) {
        this.N = blocks.length;
        int count = 0;

        int[][] tempArray = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tempArray[i][j] = blocks[i][j];
                if(tempArray[i][j] == 0){
                    zeroRow = i;
                    zeroCol = j;
                }
                count++;
            }
        }
        this.boardArray = tempArray;
    }

    // board size N
    public int size() {
        return N;
    }

    // number of blocks out of place
    public int hamming() {
        int count = 0;
        int index = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                index++;
                if (boardArray[i][j] != 0) {
                    if ((index) != boardArray[i][j]) count++;
                }
            }
        }
        return count;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int count = 0;
        int index = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                index++;
                if (boardArray[i][j] != 0) {
                    int compare = Math.abs((index) - boardArray[i][j]);
                    if (compare != 0) {
                        while (compare >= N) {
                            compare = compare - N;
                            count++;
                        }
                        while (compare >= 1) {
                            compare = compare - 1;
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // is this board solvable?
    public boolean isSolvable() {
        int inversions = 0;
        int primaryIndex = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                primaryIndex++;
                if (boardArray[i][j] != 0) {
                    int comparedIndex = 0;
                    for (int k = 0; k < boardArray.length; k++) {
                        for (int l = 0; l < N; l++) {
                            comparedIndex++;
                            if (boardArray[i][j] != 0) {
                                if (boardArray[i][j] < boardArray[k][l] && comparedIndex < primaryIndex) {
                                    inversions++;
                                }
                            }
                        }
                    }
                }
            }
        }
        if ((N % 2) == 0) {
            inversions += zeroRow;
            if ((inversions % 2) == 0) {
                return false;
            } else {
                return true;
            }
        } else {
            if ((inversions % 2) == 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (this.getClass() != y.getClass()) return false;

        Board other = (Board) y;
        for (int i = 0; i < N ; i++)
            for (int j = 0; j < N ; j++)
                if (this.boardArray[i][j] != other.boardArray[i][j]) return false;

        return true;

    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Stack<Board> stack = new Stack<Board>();

        if (zeroCol != 0) {
            Board board = new Board(swap(zeroRow, zeroCol - 1));
            stack.push(board);
        }
        if (zeroRow != 0) {
            Board board = new Board(swap(zeroRow - 1, zeroCol));
            stack.push(board);
        }
        if (zeroRow != (N - 1)) {
            Board board = new Board(swap(zeroRow + 1, zeroCol));
            stack.push(board);
        }
        if (zeroCol != (N - 1)) {
            Board board = new Board(swap(zeroRow, zeroCol + 1));
            stack.push(board);
        }
        return stack;
    }

    private int[][] swap(int row, int col){
        int[][] neighborArray = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                neighborArray[i][j] = boardArray[i][j];
            }
        }
        neighborArray[zeroRow][zeroCol] = neighborArray[row][col];
        neighborArray[row][col] = 0;
        return neighborArray;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(String.format("%2d ", boardArray[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }




    // unit tests (not graded)
    public static void main(String[] args) {

        int[][] inputArray = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board initialBoard = new Board(inputArray);
        System.out.println(initialBoard.hamming());
        System.out.println(initialBoard.manhattan());
        System.out.println(initialBoard.isGoal());
        System.out.println(initialBoard.isSolvable());
        System.out.println(initialBoard);

        Iterable<Board> neighbor = initialBoard.neighbors();

        for(Board board : neighbor){
            System.out.println(board);
        }
    }
}