package com.krishi.viewmodel;

import androidx.appcompat.app.AppCompatActivity;

import com.krishi.view.activity.BaseActivity;

/**
 * Created by Prasanth on 01-04-2020.
 * Honeywell
 */
public class AgriInfoViewModel extends BaseViewModel {
    public void setupModel(AppCompatActivity context) {
        setUp(context);
        init();
    }

    @Override
    protected void init() {
        setShowBack(true);
        setTitle("Agri Info");
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onUpdateViewModel() {

    }
}
