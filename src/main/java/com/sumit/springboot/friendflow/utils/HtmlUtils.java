package com.sumit.springboot.friendflow.utils;

public class HtmlUtils {
    public static String convertNewlinesToBr(String input) {
        if (input == null) {
            return null;
        }
        return input.replaceAll("(\r\n|\n)", "<br>");
    }
}