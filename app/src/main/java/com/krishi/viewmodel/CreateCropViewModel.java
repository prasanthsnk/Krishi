package com.krishi.viewmodel;

import android.content.ContentValues;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.krishi.App;

/**
 * Created by Prasanth on 02-06-2020.
 * Honeywell
 */
public class CreateCropViewModel extends BaseViewModel {

    public MutableLiveData<String> area = new MutableLiveData<>();
    public MutableLiveData<String> crop = new MutableLiveData<>();
    public MutableLiveData<String> duration = new MutableLiveData<>();

    public void setupModel(AppCompatActivity context) {
        setUp(context);
        init();
    }

    @Override
    protected void init() {
        setShowBack(true);
        setTitle("Create Crop");
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onUpdateViewModel() {

    }

    public void onClickSaveData(View view) {
        ContentValues cv = new ContentValues();
        cv.put("crop", crop.getValue());
        cv.put("area", area.getValue());
        cv.put("duration", duration.getValue());
        App.dataBase.insert("crops", null, cv);
        showToast("Crop Added...");
        context.finish();
    }
}
