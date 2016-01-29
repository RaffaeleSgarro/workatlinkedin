package workatlinkedin;

import java.util.Iterator;

public class Attempt {

    private final Checkerboard checkerboard;
    private final Hint hint;
    private final Iterator<CharSequence> words;

    private CharSequence word;

    public Attempt(Checkerboard checkerboard, Hint hint) {
        this.checkerboard = checkerboard;
        this.hint = hint;
        this.words = hint.iterator();
    }

    public boolean hasNext() {
        return words.hasNext();
    }

    public void next() {
        word = words.next();
    }

    /**
     * Check if the current state of the checkerboard is compatible
     * with the current word. For each character of the current word, the
     * corresponding one in the checkerboard must either be uninitialized
     * or equal.
     *
     * @return the current word is compatible with the current state of the board
     */
    public boolean canWrite() {
        for (int i = 0; i < word.length(); i++) {
            char c = checkerboard.read(row(i), column(i));
            if (c != 0 && c != word.charAt(i))
                return false;
        }

        return true;
    }

    public Checkerboard copyCheckerboardAndWriteWord() {
        Checkerboard dst = checkerboard.copy();

        for (int i = 0; i < word.length(); i++) {
            dst.write(word.charAt(i), row(i), column(i));
        }

        return dst;
    }

    /**
     * Compute the row index in the checkerboard of the index-th character
     * of the current hint.
     *
     * Remember that characters of vertical hints are numbered from bottom
     * to top.
     *
     * @param index the index of the character in the current word
     *
     * @return an index suitable for reading/writing to checkerboard
     */
    private int row(int index) {
        if (hint.isHorizontal()) {
            return hint.index();
        } else {
            return checkerboard.countRows() - 1 - index;
        }
    }

    /**
     * Compute the column index in the checkerboard of the index-th character
     * of the current hint.
     *
     * @param index the index of the character in the current word
     *
     * @return an index suitable for reading/writing to checkerboard
     */
    private int column(int index) {
        if (hint.isHorizontal()) {
            return index;
        } else {
            return hint.index();
        }
    }
}
