package com.krishi.viewmodel;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.view.View;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.krishi.App;
import com.krishi.utils.Helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Prasanth on 20-05-2020.
 * Honeywell
 */
public class CreateExpenseViewModel extends BaseViewModel {
    public MutableLiveData<String> amount = new MutableLiveData<>();
    public MutableLiveData<String> date = new MutableLiveData<>();
    public MutableLiveData<String> category = new MutableLiveData<>();
    public MutableLiveData<String> description = new MutableLiveData<>();
    private String id;
    private DatePickerDialog.OnDateSetListener dateDialog = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            monthOfYear++;
            String day = dayOfMonth > 9 ? dayOfMonth + "" : "0" + dayOfMonth;
            String month = monthOfYear > 9 ? monthOfYear + "" : "0" + monthOfYear;
            date.setValue(day + "-" + month + "-" + year);
        }
    };

    public void setupModel(AppCompatActivity context, String id) {
        this.id = id;
        setUp(context);
        init();
    }

    @Override
    protected void init() {
        setShowBack(true);
        setTitle("Add Expense");
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onUpdateViewModel() {

    }

    public void onClickDate(View view) {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(context, dateDialog, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void onClickSaveData(View view) {
        if (Helper.isNullOrEmpty(amount.getValue()) || Helper.isNullOrEmpty(category.getValue()) || Helper.isNullOrEmpty(description.getValue()) || Helper.isNullOrEmpty(date.getValue())) {
            showToast("Please enter all fields");
            return;
        }
        try {
            Date formattedDate = new SimpleDateFormat("dd-MM-yyyy").parse(date.getValue());

            ContentValues cv = new ContentValues();
            cv.put("amount", amount.getValue());
            cv.put("category", category.getValue());
            cv.put("description", description.getValue());
            cv.put("date_expense", formattedDate.getTime());
            cv.put("cropId", id);
            App.dataBase.insert("expenses", null, cv);
            showToast("Saved...");
            context.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
