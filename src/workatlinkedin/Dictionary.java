package workatlinkedin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dictionary implements Comparable<Dictionary>, Iterable<CharSequence> {

    private final Pattern pattern;
    private final StringBuilder buffer;

    private List<CharSequence> words;

    public Dictionary(String regex, int length) {
        pattern = Pattern.compile(regex);
        buffer = new StringBuilder(length);
        buffer.setLength(length);
    }

    public void generate() {
        words = new ArrayList<>();
        next(0);
    }

    private void next(int index) {
        if (index == buffer.length() - 1) {
            for (char c = 'A'; c <= 'Z'; c++) {
                buffer.setCharAt(index, c);
                Matcher matcher = pattern.matcher(buffer);
                if (matcher.matches()) {
                    words.add(buffer.toString());
                }
            }
        } else {
            for (char c = 'A'; c <= 'Z'; c++) {
                buffer.setCharAt(index, c);
                next(index + 1);
            }
        }
    }

    public int size() {
        checkState();
        return words.size();
    }

    @Override
    public Iterator<CharSequence> iterator() {
        checkState();
        return words.iterator();
    }

    @Override
    public int compareTo(Dictionary that) {
        return size() - that.size();
    }

    @Override
    public String toString() {
        return pattern.toString();
    }

    private void checkState() {
        if (words == null)
            throw new RuntimeException("This dictionary has not been filled. You must call .generate()");
    }
}
