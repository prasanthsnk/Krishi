package com.krishi.viewmodel;

import androidx.appcompat.app.AppCompatActivity;

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
}
