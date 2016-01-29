package workatlinkedin;

import java.util.*;

public class Solver implements Iterable<Checkerboard> {

    private final int rows;
    private final int columns;
    private final Dictionary[] horizontalHints;
    private final Dictionary[] verticalHints;

    private List<Checkerboard> solutions;
    private List<Hint> hints;
    private Stack<Checkerboard> layers;

    public Solver(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        horizontalHints = new Dictionary[rows];
        verticalHints = new Dictionary[columns];
    }

    public void solve() {
        solutions = new ArrayList<>();
        hints = new ArrayList<>();
        layers = new Stack<>();

        List<Dictionary> dictionaries = new ArrayList<>();

        Collections.addAll(dictionaries, horizontalHints);
        Collections.addAll(dictionaries, verticalHints);

        for (Dictionary dictionary : dictionaries) {
            dictionary.generate();
        }

        Collections.sort(dictionaries);

        for (Dictionary dictionary : dictionaries) {
            int rowIndexFromTop = indexOf(horizontalHints, dictionary);

            boolean isHorizontal;
            int index;

            if (rowIndexFromTop >= 0) {
                isHorizontal = true;
                index = rowIndexFromTop;
            } else {
                isHorizontal = false;
                index = indexOf(verticalHints, dictionary);
            }

            hints.add(new Hint(isHorizontal, index, dictionary));
        }

        layers.push(new Checkerboard(rows, columns));
        walkTree(0);
    }

    private int indexOf(Object[] arr, Object object) {
        for (int i = 0; i < arr.length; i++) {
            if (object.equals(arr[i])) {
                return i;
            }
        }

        return -1;
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
        horizontalHints[rowIndexFromTop] = new Dictionary(regex, columns);
    }

    public void setVerticalHint(int columnIndexFromLeft, String regex) {
        verticalHints[columnIndexFromLeft] = new Dictionary(regex, rows);
    }
}
