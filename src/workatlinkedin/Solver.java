package workatlinkedin;

import java.util.*;

public class Solver implements Iterable<Checkerboard> {

    private final int rows;
    private final int columns;
    private final List<Hint> hints = new ArrayList<>();

    private List<Checkerboard> solutions;
    private Stack<Checkerboard> layers;

    public Solver(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public void solve() {
        solutions = new ArrayList<>();
        layers = new Stack<>();

        for (Hint hint : hints) {
            hint.computeDictionary();
        }

        Collections.sort(hints);

        layers.push(new Checkerboard(rows, columns));
        walkTree(0);
    }

    private void walkTree(int depth) {
        Attempt attempt = new Attempt(layers.peek(), hints.get(depth));

        while (attempt.hasNext()) {
            attempt.next();
            if (attempt.canWrite()) {
                Checkerboard newBoard = attempt.copyCheckerboardAndWriteWord();
                if (depth == hints.size() - 1) {
                    solutions.add(newBoard);
                } else {
                    layers.push(newBoard);
                    walkTree(depth + 1);
                }
            }
        }

        layers.pop();
    }

    @Override
    public Iterator<Checkerboard> iterator() {
        if (solutions == null)
            throw new RuntimeException("Did you forget to call #solve()?");
        return solutions.iterator();
    }

    public void setHorizontalHint(int rowIndexFromTop, String regex) {
        hints.add(new Hint(true, rowIndexFromTop, new Dictionary(regex, columns)));
    }

    public void setVerticalHint(int columnIndexFromLeft, String regex) {
        hints.add(new Hint(false, columnIndexFromLeft, new Dictionary(regex, rows)));
    }
}
