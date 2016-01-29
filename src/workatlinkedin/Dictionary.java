package workatlinkedin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dictionary implements Comparable<Dictionary>, Iterable<CharSequence> {

    private final Pattern pattern;
    private final StringBuilder buffer;
    private final List<CharSequence> words = new ArrayList<>();

    public Dictionary(String regex, int length) {
        pattern = Pattern.compile(regex);
        buffer = new StringBuilder(length);
        buffer.setLength(length);
    }

    public void generate() {
        words.clear();
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
        return words.size();
    }

    @Override
    public Iterator<CharSequence> iterator() {
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
}
