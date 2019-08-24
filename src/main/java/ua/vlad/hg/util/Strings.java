package ua.vlad.hg.util;

import java.util.regex.Matcher;

public class Strings {

    public static String replace(String message, Object[] params) {
        if (message != null && params != null) {
            for (int i = 0; i < params.length; i++) {
                message = message.replaceAll("\\{" + i + "}", params[i] != null ? Matcher.quoteReplacement(params[i].toString()) : "null");
            }
        }
        return message;
    }
}
