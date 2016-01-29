package workatlinkedin;

import java.util.Iterator;

public class Hint implements Iterable<CharSequence> {

    private final boolean horizontal;
    private final int index;
    private final Dictionary dictionary;

    public Hint(boolean horizontal, int index, Dictionary dictionary) {
        this.horizontal = horizontal;
        this.index = index;
        this.dictionary = dictionary;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public int index() {
        return index;
    }

    @Override
    public Iterator<CharSequence> iterator() {
        return dictionary.iterator();
    }

    public boolean isVertical() {
        return !horizontal;
    }
}
