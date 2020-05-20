package com.krishi.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.krishi.R;
import com.krishi.databinding.FragmentBalanceBinding;
import com.krishi.viewmodel.ExpenseViewModel;


public class BalanceFragment extends BaseFragment {

    private FragmentBalanceBinding binding;
    private ExpenseViewModel viewModel;

    public static BalanceFragment newInstance() {
        return new BalanceFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(ExpenseViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_balance, container, false);
        binding.setViewModel(viewModel);
        mView = binding.getRoot();
        binding.calendarSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                viewModel.setCalSelectionSpent(adapterView.getItemAtPosition(i).toString());
                viewModel.onResume();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        initialize();
        return mView;
    }


    @Override
    public void initialize() {
        updateGraph();
    }

    private void updateGraph() {
        PieChart pieChart = binding.pieChart;
        PieDataSet dataSet = new PieDataSet(viewModel.getChartList(), "Spent");
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.animateXY(500, 500);

    }

    @Override
    public void updateUi(String type, String data) {
        updateGraph();
    }
}
