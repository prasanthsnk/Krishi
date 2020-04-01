package com.krishi.utils;

/**
 * Created by Prasanth on 04-10-2019.
 * Honeywell
 */

public class Helper {

    public static boolean isNullOrEmpty(String string) {
        String s = string;
        if (s != null) {
            s = s.trim();
        }
        return s == null || s.length() == 0;
    }


}
