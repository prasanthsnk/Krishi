package com.krishi.view.activity;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.krishi.R;
import com.krishi.databinding.ActivityCropListBinding;
import com.krishi.viewmodel.CropsViewModel;

public class CropListActivity extends BaseActivity {

    private CropsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }

    @Override
    protected void initialize() {
        ActivityCropListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_crop_list);
        viewModel = new ViewModelProvider(this).get(CropsViewModel.class);
        viewModel.setupModel(this);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
//        binding.recycleView.setAdapter(viewModel.getCropsAdapter());
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onResume();
    }

    @Override
    protected void updateUI(String type, String data) {

    }
}
