package com.krishi.view.activity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.krishi.R;
import com.krishi.databinding.ActivityRegisterBinding;
import com.krishi.viewmodel.RegisterViewModel;

public class RegisterActivity  extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }

    @Override
    protected void initialize() {
        ActivityRegisterBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        RegisterViewModel viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        viewModel.setupModel(this);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
    }

    @Override
    protected void updateUI(String type, String data) {

    }
}
