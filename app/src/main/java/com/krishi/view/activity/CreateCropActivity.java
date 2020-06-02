package com.krishi.view.activity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.krishi.R;
import com.krishi.databinding.ActivityCreateCropBinding;
import com.krishi.viewmodel.CreateCropViewModel;

public class CreateCropActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }

    @Override
    protected void initialize() {
        ActivityCreateCropBinding binding= DataBindingUtil.setContentView(this, R.layout.activity_create_crop);
        CreateCropViewModel viewModel = new ViewModelProvider(this).get(CreateCropViewModel.class);
        viewModel.setupModel(this);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
    }

    @Override
    protected void updateUI(String type, String data) {

    }
}
