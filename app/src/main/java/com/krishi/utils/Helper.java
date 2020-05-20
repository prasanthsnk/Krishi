package com.krishi.utils;

import android.text.format.DateFormat;
import android.util.Base64;

import java.security.spec.KeySpec;
import java.util.Calendar;
import java.util.Locale;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

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

    public static String generateKey(String password) {
        char[] passphraseOrPin = password.toCharArray();
        final int iterations = 1000;
        final int outputKeyLength = 256;
        byte[] salt = new byte[]{(byte) 1};
        String encodedKey = String.valueOf(passphraseOrPin);
        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(Constants.PBKDF2);
            KeySpec keySpec = new PBEKeySpec(passphraseOrPin, salt, iterations, outputKeyLength);
            SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
            encodedKey = Base64.encodeToString(secretKey.getEncoded(), Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodedKey;
    }

    public static String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
        return date;
    }
}
