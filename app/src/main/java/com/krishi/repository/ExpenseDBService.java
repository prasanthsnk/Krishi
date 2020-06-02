package com.krishi.repository;

import android.database.Cursor;

import com.github.mikephil.charting.data.PieEntry;
import com.krishi.App;
import com.krishi.model.CropModel;
import com.krishi.model.ExpenseModel;
import com.krishi.utils.Helper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prasanth on 20-05-2020.
 * Honeywell
 */
public class ExpenseDBService {

    public List<CropModel> getCrops() {
        List<CropModel> crops = new ArrayList<>();
        String qry = "SELECT c.Id, c.crop, c.area, c.duration, sum(e.amount)as amount FROM crops c left Join expenses as e on c.id = e.cropId GROUP BY c.id";

        Cursor cursor = App.dataBase.rawQuery(qry, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                CropModel model = new CropModel();
                model.setId(cursor.getString(cursor.getColumnIndex("id")));
                model.setAmount(cursor.getString(cursor.getColumnIndex("amount")));
                model.setCrop(cursor.getString(cursor.getColumnIndex("crop")));
                model.setArea(Helper.getDate(cursor.getLong(cursor.getColumnIndex("area"))));
                model.setDuration(cursor.getString(cursor.getColumnIndex("duration")));
                crops.add(model);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return crops;
    }

    public List<ExpenseModel> getExpenses(String id, long timeStamp) {
        List<ExpenseModel> expenses = new ArrayList<>();
        String qry = "SELECT * FROM expenses WHERE cropId = '" + id + "'";
        if (timeStamp > 0) {
            qry = "SELECT * FROM expenses WHERE cropId = '"+id+"' AND date_expense >= " + timeStamp;
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


    public List<PieEntry> getSpent(String id, long timeStamp) {
        ArrayList<PieEntry> expenses = new ArrayList();
        String qry = "SELECT SUM(amount) as total, category FROM expenses WHERE  cropId = '" + id + "' GROUP By category";
        if (timeStamp > 0) {
            qry = "SELECT SUM(amount) as total, category FROM expenses WHERE cropId = '" + id + "' AND date_expense >= " + timeStamp + " GROUP By category";
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
