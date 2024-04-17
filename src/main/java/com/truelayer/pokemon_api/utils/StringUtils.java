package com.truelayer.pokemon_api.utils;

import java.util.regex.Pattern;

public class StringUtils {

    public static String sanitize(String string) {
        Pattern pattern = Pattern.compile("\\p{Cntrl}|\\p{Cf}");
        return pattern.matcher(string).replaceAll("");
    }
}
