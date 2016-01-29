package workatlinkedin;

public class CheckerboardPrintTest {

    public static void main(String... args) {
        int rows = 8;
        int columns = 15;
        Checkerboard checkerboard = new Checkerboard(rows, columns);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                checkerboard.write((row == col ? '1' : '.'), row, col);
            }
        }

        checkerboard.print();
    }
}
