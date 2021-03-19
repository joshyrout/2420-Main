package a04;

import edu.princeton.cs.algs4.Stack;

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

    private final int[][] boardArray;
    private final int N;
    private int indexOf0;

    public Board(int[][] blocks) {
        this.N = blocks.length;
        this.boardArray = blocks;
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(boardArray[i][j] == 0){
                    this.indexOf0 = count;
                }
                count++;
            }
        }
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
            inversions += (indexOf0 % N);
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

        if(y == null) return false;
        if(this.getClass() != y.getClass()) return false;
        Board other = (Board) y;
        if(this.boardArray != other.boardArray) return false;
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Stack<Board> stack = new Stack<Board>();
        int row = (indexOf0 / N);
        int column = (indexOf0 % N);
        if (row != 0) {
            int[][] neighborArray = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    neighborArray[i][j] = boardArray[i][j];
                }
            }

            neighborArray[row][column] = neighborArray[row - 1][column];
            neighborArray[row - 1][column] = 0;
            Board board = new Board(neighborArray);
            stack.push(board);
        }
        if (row != (N - 1)) {
            int[][] neighborArray = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    neighborArray[i][j] = boardArray[i][j];
                }
            }
            neighborArray[row][column] = neighborArray[row + 1][column];
            neighborArray[row + 1][column] = 0;
            Board board = new Board(neighborArray);
            stack.push(board);
        }
        if (column != 0) {
            int[][] neighborArray = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    neighborArray[i][j] = boardArray[i][j];
                }
            }
            neighborArray[row][column] = neighborArray[row][column - 1];
            neighborArray[row][column - 1] = 0;
            Board board = new Board(neighborArray);
            stack.push(board);
        }
        if (column != (N - 1)) {
            int[][] neighborArray = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    neighborArray[i][j] = boardArray[i][j];
                }
            }
            neighborArray[row][column] = neighborArray[row][column + 1];
            neighborArray[row][column + 1] = 0;
            Board board = new Board(neighborArray);
            stack.push(board);
        }
        return stack;
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

        System.out.println(initialBoard);

    }
}
    /*
    private int[][] boardArray;
    private int N;
//    private int indexOf0;

    public Board(int[][] blocks) {
        N = blocks.length;
        boardArray = blocks;
//        int count = 0;
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                boardArray[i][j] = blocks[i][j];
//                if(boardArray[i][j] == 0){
//                    indexOf0 = count;
//                    return;
//                }
//                count++;
//            }
//        }
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
        int rowOf0 = 0;
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
                } else {
                    rowOf0 = i;;
                }
            }
        }
        if ((N % 2) == 0) {
            inversions += rowOf0;
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
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Queue<Board> queue = new Queue<Board>();
        for(){

        }
        int row = (indexOf0 / N);
        int column = (indexOf0 % N);
        if (row != 0) {
            int[][] neighborArray = boardArray;
            neighborArray[row][column] = neighborArray[row - 1][column];
            neighborArray[row - 1][column] = 0;
            Board board = new Board(neighborArray);
            queue.enqueue(board);
        }
        if (row != (N - 1)) {
            int[][] neighborArray = boardArray;
            neighborArray[row][column] = neighborArray[row + 1][column];
            neighborArray[row + 1][column] = 0;
            Board board = new Board(neighborArray);
            queue.enqueue(board);
        }
        if (column != 0) {
            int[][] neighborArray = boardArray;
            neighborArray[row][column] = neighborArray[row][column - 1];
            neighborArray[row][column - 1] = 0;
            Board board = new Board(neighborArray);
            queue.enqueue(board);
        }
        if (column != (N - 1)) {
            int[][] neighborArray = boardArray;
            neighborArray[row][column] = neighborArray[row][column + 1];
            neighborArray[row][column + 1] = 0;
            Board board = new Board(neighborArray);
            queue.enqueue(board);
        }
        return queue;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(String.format("%2d", boardArray[i][j]));
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
     */

