package com.krishi.view.bindings;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import androidx.databinding.BindingAdapter;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.krishi.R;

import java.util.List;

/**
 * Created by Prasanth on 18-03-2020.
 * Honeywell
 */
public class CustomViewBindings {

    @BindingAdapter("setRecycleAdapter")
    public static void bindRecyclerViewAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("setExpandableListViewAdapter")
    public static void bindExpandableListViewAdapter(ExpandableListView view, BaseExpandableListAdapter adapter) {
        view.setAdapter(adapter);
    }

    @BindingAdapter("setTag")
    public static void setViewTag(View view, Object value) {
        view.setTag(value);
    }

    @BindingAdapter("setViewPagerAdapter")
    public static void bindViewPagerAdapter(ViewPager view, FragmentPagerAdapter adapter) {
        view.setAdapter(adapter);
    }

    @BindingAdapter("setSpinnerSelectedValue")
    public static void bindSpinnerSelectedValue(Spinner spinner, int position) {
        spinner.setSelection(position);
    }
    @BindingAdapter("setPieChartValue")
    public static void bindPieChartValue(PieChart chart, List<PieEntry> values) {
        PieDataSet dataSet = new PieDataSet(values, "Spent");
        PieData data = new PieData(dataSet);
        chart.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.animateXY(500, 500);
    }

    @BindingAdapter("addView")
    public static void bindAddView(ViewGroup viewGroup, View view) {
        if (viewGroup != null && view != null) {
            viewGroup.addView(view);
        }
    }

    @BindingAdapter("removeView")
    public static void bindRemoveView(ViewGroup viewGroup, View view) {
        if (viewGroup != null && view != null) {
            viewGroup.removeView(view);
        }
    }
}
