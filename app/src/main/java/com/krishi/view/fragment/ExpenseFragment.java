package com.krishi.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.krishi.R;
import com.krishi.databinding.FragmentExpenseBinding;
import com.krishi.viewmodel.ExpenseViewModel;


public class ExpenseFragment extends BaseFragment {
    private ExpenseViewModel viewModel;

    public static ExpenseFragment newInstance() {
        return new ExpenseFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(ExpenseViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentExpenseBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_expense, container, false);
        binding.setViewModel(viewModel);
        binding.calendarSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                viewModel.setCalSelection(adapterView.getItemAtPosition(i).toString());
                viewModel.onResume();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mView = binding.getRoot();
        return mView;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void updateUi(String type, String data) {

    }
}
