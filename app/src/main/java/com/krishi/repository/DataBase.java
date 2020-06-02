package com.krishi.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Prasanth on 20-03-2020.
 * Honeywell
 */
public class DataBase extends SQLiteOpenHelper {

    public DataBase(Context context) {
        super(context, "CrowdSourcingDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createTable(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        createTable(sqLiteDatabase);
    }

    private void createTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS notification(id INTEGER PRIMARY KEY AUTOINCREMENT ,title TEXT,description TEXT,dbId TEXT);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS expenses (id INTEGER PRIMARY KEY AUTOINCREMENT ,cropId INTEGER, amount TEXT,category TEXT,description TEXT,date_expense INTEGER);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS crops (id INTEGER PRIMARY KEY AUTOINCREMENT ,crop TEXT,area TEXT,duration TEXT);");
    }
}
