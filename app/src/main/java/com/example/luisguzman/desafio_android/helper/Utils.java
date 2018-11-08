package com.example.luisguzman.desafio_android.helper;

public class Utils {

    public static String ellipsis(final String text, int length) {
        length += Math.ceil(text.replaceAll("[^iIl]", "").length() / 2.0d);

        if (text.length() > length) {
            return text.substring(0, length - 3) + "...";
        }

        return text;
    }

}
