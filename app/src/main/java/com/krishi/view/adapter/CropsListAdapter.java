package com.krishi.view.adapter;

import com.krishi.R;
import com.krishi.model.CropModel;
import com.krishi.model.ExpenseModel;
import com.krishi.viewmodel.CropsViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prasanth on 20-05-2020.
 * Honeywell
 */
public class CropsListAdapter extends BaseRecycleViewAdapter {
    private List<CropModel> data = new ArrayList<>();
    private  CropsViewModel viewModel;

    public CropsListAdapter(CropsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    protected Object getDataAtPosition(int position) {
        return data.get(position);
    }

    @Override
    protected Object getViewModel() {
        return viewModel;
    }

    @Override
    protected int getLayoutIdForType(int viewType) {
        return R.layout.item_crop;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void update(List<CropModel> data) {
        this.data = data;
        this.notifyDataSetChanged();
    }
}
