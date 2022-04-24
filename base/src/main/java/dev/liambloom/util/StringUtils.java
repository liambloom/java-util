package dev.liambloom.util;

import java.util.Locale;
import java.util.regex.Pattern;

public final class StringUtils {
    private static final Pattern WHITESPACE = Pattern.compile("\\s");

    private StringUtils() {}

    public static String[] getWords(String s) {
        return s.split(
            WHITESPACE.matcher(s).find() ? "\\s+"
                : s.contains("_") ? "_"
                : s.contains("-") ? "-"
                : "(?<=[a-z])(?=[A-Z])"
        );
    }

    public static char[] initials(String s) {
        String[] a = getWords(s);
        char[] r = new char[a.length];
        for (int i = 0; i < a.length; i++)
            r[i] = a[i].charAt(0);
        return r;
    }

    public static String convertCase(String s, Case c) {
        if (s.length() == 0)
            return s;

        String[] words = getWords(s);

        switch (c) {
            case SNAKE:
            case CONST:
            case SKEWER: {
                String r = String.join(c == Case.SKEWER ? "-" : "_", words);
                if (c == Case.CONST)
                    return r.toUpperCase(Locale.ENGLISH);
                else
                    return r.toLowerCase(Locale.ENGLISH);
            }
            case SPACE:
            case SENTENCE:
                String r = String.join(" ", words).toLowerCase(Locale.ENGLISH);
                if (c == Case.SPACE || r.isEmpty())
                    return r;
                else
                    return Character.toUpperCase(r.charAt(0)) + r.substring(1);
            case CAMEL:
            case PASCAL:
            case TITLE:
                StringBuilder builder = new StringBuilder(s.length());
                for (int i = 0; i < words.length; i++) {
                    char[] chars = words[i].toCharArray();
                    int j;
                    if (c == Case.CAMEL && i == 0)
                        j = 0;
                    else {
                        j = 1;
                        chars[0] = Character.toUpperCase(chars[0]);
                    }
                    for (; j < chars.length; j++)
                        chars[j] = Character.toLowerCase(chars[j]);
                    if (i > 0 && c == Case.TITLE)
                        builder.append(' ');
                    builder.append(chars);
                }
                return builder.toString();
            default:
                throw new RuntimeException("This state cannot be reached");
        }
    }

    /**
     * A utility class used to convert between cases. Currently only works
     * on english.
     */
    public enum Case {
        /**
         * Capitalizes the first letter of every word except the first. thisIsAnExampleOfCamelCase
         */
        CAMEL,

        /**
         * Capitalizes the first letter of every word. ThisIsAnExampleOfPascalCase
         */
        PASCAL,

        /**
         * All lowercase, words separated by underscores. this_is_an_example_of_snake_case
         */
        SNAKE,

        /**
         * All uppercase, words separated by underscores. THIS_IS_AN_EXAMPLE_OF_CONST_CASE
         */
        CONST,

        /**
         * All lowercase, words separated by hyphens. this-is-an-example-of-skewer-case
         */
        SKEWER,

        /**
         * All lowercase, words separated by a single space. this is an example of space case
         */
        SPACE,

        /**
         * Capitalizes the first letter of every word, separating words with spaces. This Is An Example Of Title Case
         */
        TITLE,

        /**
         * Capitalizes the first character of the string, separates words with spaces. This is an example of sentence case
         */
        SENTENCE;
    }
}
