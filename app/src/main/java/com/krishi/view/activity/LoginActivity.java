package com.krishi.view.activity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.krishi.R;
import com.krishi.databinding.ActivityLoginBinding;
import com.krishi.viewmodel.LoginViewModel;

public class LoginActivity  extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }

    @Override
    protected void initialize() {
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        LoginViewModel loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.setupModel(this);
        binding.setViewModel(loginViewModel);
        binding.setLifecycleOwner(this);
    }

    @Override
    protected void updateUI(String type, String data) {

    }
}
