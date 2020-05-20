package com.krishi.repository;

import android.database.Cursor;

import com.github.mikephil.charting.data.PieEntry;
import com.krishi.App;
import com.krishi.model.ExpenseModel;
import com.krishi.utils.Helper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prasanth on 20-05-2020.
 * Honeywell
 */
public class ExpenseDBService {

    public List<ExpenseModel> getExpenses(long timeStamp) {
        List<ExpenseModel> expenses = new ArrayList<>();
        String qry = "SELECT * FROM expenses";
        if (timeStamp > 0) {
            qry = "SELECT * FROM expenses WHERE date_expense >= " + timeStamp;
        }
        Cursor cursor = App.dataBase.rawQuery(qry, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                ExpenseModel model = new ExpenseModel();
                model.setId(cursor.getString(cursor.getColumnIndex("id")));
                model.setAmount(cursor.getString(cursor.getColumnIndex("amount")));
                model.setCategory(cursor.getString(cursor.getColumnIndex("category")));
                model.setDate(Helper.getDate(cursor.getLong(cursor.getColumnIndex("date_expense"))));
                model.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                expenses.add(model);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return expenses;
    }


    public List<PieEntry> getSpent(long timeStamp) {
        ArrayList<PieEntry> expenses = new ArrayList();
        String qry = "SELECT SUM(amount) as total, category FROM expenses GROUP By category";
        if (timeStamp > 0) {
            qry = "SELECT SUM(amount) as total, category FROM expenses WHERE date_expense >= " + timeStamp + " GROUP By category";
        }
        Cursor cursor = App.dataBase.rawQuery(qry, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                expenses.add(new PieEntry(cursor.getFloat(cursor.getColumnIndex("total")), cursor.getString(cursor.getColumnIndex("category"))));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return expenses;
    }
}
