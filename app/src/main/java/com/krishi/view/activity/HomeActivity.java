package com.krishi.view.activity;

import android.os.Bundle;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import com.krishi.R;
import com.krishi.databinding.ActivityHomeBinding;
import com.krishi.viewmodel.HomeViewModel;

public class HomeActivity extends BaseActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }

    @Override
    protected void initialize() {
        ActivityHomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.setupModel(this);
        binding.setViewModel(homeViewModel);
        binding.setLifecycleOwner(this);
        drawerLayout = findViewById(R.id.nav_drawer);
    }

    @Override
    protected void updateUI(String type, String data) {

    }

    public void openNavigationView() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public boolean closeNavigationView() {
        if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
        return false;
    }

    public void onClickMenu(View view) {
        openNavigationView();
    }
}
