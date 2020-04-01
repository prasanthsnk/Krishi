package com.krishi;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import com.krishi.repository.DataBase;

/**
 * Created by Prasanth on 13-03-2020.
 * Honeywell
 */
public class App extends Application {

    private static SharedPreferences sharedPreferences = null;
    public static SQLiteDatabase dataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = getApplicationContext().getSharedPreferences("CrowdSourcing", Context.MODE_PRIVATE);
        dataBase = new DataBase(this).getWritableDatabase();
    }

    public static boolean saveShared(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public static String readShared(String key) {
        return sharedPreferences.getString(key, "");
    }

    public static void removeShared() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
