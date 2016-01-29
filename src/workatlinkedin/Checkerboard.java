package workatlinkedin;

import java.io.PrintStream;

public class Checkerboard {

    private final char[] board;
    private final int rows;
    private final int columns;

    private PrintStream printer = System.out;

    public Checkerboard(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.board = new char[rows * columns];
    }

    private int translate(int row, int col) {
        return (row * columns) + col;
    }

    public void write(char c, int row, int col) {
        board[translate(row, col)] = c;
    }

    public char read(int row, int col) {
        return board[translate(row, col)];
    }

    public void print() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                System.out.print(read(row, col));
            }
            System.out.println();
        }
    }
}
