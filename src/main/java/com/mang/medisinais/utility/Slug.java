package com.mang.medisinais.utility;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public class Slug {
//    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
//    private static final Pattern WHITESPACE = Pattern.compile("\\s");

    private Slug() {
        throw new IllegalStateException("Utility class");
    }

    public static String makeSlug(String input) {
        if (input == null)
            throw new IllegalArgumentException();

        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        String slugValue = Pattern
                .compile("\\p{InCombiningDiacriticalMarks}+")
                .matcher(normalized)
                .replaceAll("");

        return slugValue.toLowerCase(Locale.ENGLISH).replaceAll("\\s+", "-");
    }

    // PS: esse daqui eu encontrei na internet mas o de cima foi eu quem fiz :D

//    public static String makeSlug(String input) {
//        if (input == null)
//            throw new IllegalArgumentException();
//
//        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
//        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
//        String slug = NONLATIN.matcher(normalized).replaceAll("");
//
//        return slug.toLowerCase(Locale.ENGLISH);
//    }

}
