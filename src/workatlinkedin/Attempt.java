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

    private int row(int index) {
        if (hint.isHorizontal()) {
            return hint.index();
        } else {
            return checkerboard.countRows() - 1 - index;
        }
    }

    private int column(int index) {
        if (hint.isHorizontal()) {
            return index;
        } else {
            return hint.index();
        }
    }
}
