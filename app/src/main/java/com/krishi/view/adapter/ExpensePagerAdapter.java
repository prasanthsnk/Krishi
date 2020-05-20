package com.krishi.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.krishi.view.fragment.BalanceFragment;
import com.krishi.view.fragment.ExpenseFragment;

/**
 * Created by Prasanth on 20-05-2020.
 * Honeywell
 */
public class ExpensePagerAdapter extends FragmentPagerAdapter {
    private String[] pages = {"Expenses", "Balance"};

    public ExpensePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Override
    public int getCount() {
        return pages.length;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return ExpenseFragment.newInstance();
        } else {
            return BalanceFragment.newInstance();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pages[position];
    }
}