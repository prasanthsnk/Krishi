package com.krishi.viewmodel;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.krishi.model.CropModel;
import com.krishi.repository.ExpenseDBService;
import com.krishi.view.activity.CreateCropActivity;
import com.krishi.view.activity.ExpenseActivity;
import com.krishi.view.adapter.CropsListAdapter;

import java.util.List;

/**
 * Created by Prasanth on 02-06-2020.
 * Honeywell
 */
public class CropsViewModel extends BaseViewModel {
    private CropsListAdapter cropsAdapter;
    private List<CropModel> cropsList;
    private ExpenseDBService expenseDBService;

    public void setupModel(AppCompatActivity context) {
        setUp(context);
        init();
    }

    @Override
    protected void init() {
        setShowBack(true);
        setTitle("Crops");
        expenseDBService = new ExpenseDBService();
        cropsAdapter = new CropsListAdapter(this);
    }

    @Override
    public void onResume() {
        cropsList = expenseDBService.getCrops();
        cropsAdapter.update(cropsList);
    }

    @Override
    public void onUpdateViewModel() {

    }

    public void onClickCreateCrop(View view) {
        context.startActivity(new Intent(context, CreateCropActivity.class));
    }

    public void onItemClicked(CropModel model) {
        context.startActivity(new Intent(context, ExpenseActivity.class).putExtra("id", model.getId()));
    }

    public CropsListAdapter getCropsAdapter() {
        return cropsAdapter;
    }
}
