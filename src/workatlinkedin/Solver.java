package workatlinkedin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Solver implements Iterable<Checkerboard> {

    private final int rows;
    private final int columns;
    private final Dictionary[] horizontalHints;
    private final Dictionary[] verticalHints;

    private List<Checkerboard> solutions;

    public Solver(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        horizontalHints = new Dictionary[rows];
        verticalHints = new Dictionary[columns];
    }

    public void solve() {
        solutions = new ArrayList<>();
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Iterator<Checkerboard> iterator() {
        if (solutions == null)
            throw new RuntimeException("Did you forget to call #solve()?");
        return solutions.iterator();
    }

    public void setHorizontalHint(int rowIndexFromTop, String regex) {
        horizontalHints[rowIndexFromTop] = new Dictionary(regex, columns);
    }

    public void setVerticalHint(int columnIndexFromLeft, String regex) {
        verticalHints[columnIndexFromLeft] = new Dictionary(regex, rows);
    }
}
