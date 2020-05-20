package com.krishi.viewmodel;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.krishi.view.activity.AgriInfoActivity;
import com.krishi.view.activity.ExpenseActivity;

/**
 * Created by Prasanth on 01-04-2020.
 * Honeywell
 */
public class HomeViewModel extends BaseViewModel {

    public void setupModel(AppCompatActivity context) {
        setUp(context);
        init();
    }

    @Override
    protected void init() {
        setShowBack(false);
        setTitle("Dashboard");
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onUpdateViewModel() {

    }

    public void onClickAgriInfo(View view) {
        context.startActivity(new Intent(context, AgriInfoActivity.class));
    }
    public void onClickExpense(View view) {
        context.startActivity(new Intent(context, ExpenseActivity.class));
    }

}
