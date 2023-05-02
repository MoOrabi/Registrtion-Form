package com.registrationform.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class ArabicUtils {

    public static String removeDiacritics(String str) {
        if (str == null) {
            return null;
        }
        String normalized = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("");
    }

}

