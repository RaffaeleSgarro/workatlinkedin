package workatlinkedin;

import java.util.ArrayList;
import java.util.List;

public class DictionaryTest {

    private static final List<Dictionary> dictionaries = new ArrayList<>();
    private static int length;

    public static void main(String... args) {

        length = 4;

        add("[LINKED]*IN");
        add("(ENG|INE|E|R)*");
        add("([MBERS]*)\\1");
        add(".(LN|K|D)*");

        add("R+D");
        add("[^WORK]*ING?");
        add(".*[IN].*");
        add("C{0}N[NECT]*");

        for (Dictionary dictionary : dictionaries) {
            dictionary.generate();
        }

        for (Dictionary dictionary : dictionaries) {
            System.out.println("Generated " + dictionary.size() + " words for dictionary " + dictionary.toString());
        }
    }

    private static void add(String regex) {
        Dictionary dictionary = new Dictionary(regex, length);
        dictionaries.add(dictionary);
    }
}
