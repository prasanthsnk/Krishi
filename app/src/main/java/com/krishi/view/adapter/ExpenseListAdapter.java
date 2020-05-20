package com.krishi.view.adapter;

import com.krishi.R;
import com.krishi.model.ExpenseModel;

import java.util.List;

/**
 * Created by Prasanth on 20-05-2020.
 * Honeywell
 */
public class ExpenseListAdapter extends BaseRecycleViewAdapter {
    private List<ExpenseModel> data;

    public ExpenseListAdapter(List<ExpenseModel> data) {
        this.data = data;
    }

    @Override
    protected Object getDataAtPosition(int position) {
        return data.get(position);
    }

    @Override
    protected Object getViewModel() {
        return null;
    }

    @Override
    protected int getLayoutIdForType(int viewType) {
        return R.layout.item_expense;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void update(List<ExpenseModel> data) {
        this.data = data;
        this.notifyDataSetChanged();
    }
}
