package com.krishi.view.activity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.krishi.R;
import com.krishi.databinding.ActivityExpenseBinding;
import com.krishi.view.adapter.ExpensePagerAdapter;
import com.krishi.viewmodel.ExpenseViewModel;

public class ExpenseActivity extends BaseActivity {
    private ActivityExpenseBinding binding;
    private ExpenseViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }

    @Override
    protected void initialize() {
         binding = DataBindingUtil.setContentView(this, R.layout.activity_expense);
        viewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);
        viewModel.setupModel(this);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        binding.vpPager.setAdapter(new ExpensePagerAdapter(getSupportFragmentManager()));
    }

    @Override
    protected void updateUI(String type, String data) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onResume();
    }
    public Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.vpPager + ":" + binding.vpPager.getCurrentItem());
    }
}
