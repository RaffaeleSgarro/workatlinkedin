package workatlinkedin;

import java.io.PrintStream;

public class Checkerboard {

    /**
     * Current status of board, arranged row-major
     */
    private final char[] board;
    private final int rows;
    private final int columns;

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

    public void print(PrintStream printer) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                printer.print(read(row, col));
            }
            printer.println();
        }
    }

    public Checkerboard copy() {
        Checkerboard dst = new Checkerboard(rows, columns);
        System.arraycopy(board, 0, dst.board, 0, board.length);
        return dst;
    }

    public int countRows() {
        return rows;
    }

    public int countColumns() {
        return columns;
    }
}
