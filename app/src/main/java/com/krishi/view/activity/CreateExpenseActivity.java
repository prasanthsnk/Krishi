package com.krishi.view.activity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.krishi.R;
import com.krishi.databinding.ActivityCreateExpenseBinding;
import com.krishi.viewmodel.CreateExpenseViewModel;


public class CreateExpenseActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }

    @Override
    protected void initialize() {
        ActivityCreateExpenseBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_create_expense);
        CreateExpenseViewModel viewModel = new ViewModelProvider(this).get(CreateExpenseViewModel.class);
        viewModel.setupModel(this);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
    }

    @Override
    protected void updateUI(String type, String data) {

    }
}
