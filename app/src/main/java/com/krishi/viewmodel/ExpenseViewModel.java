package com.krishi.viewmodel;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.data.PieEntry;
import com.krishi.model.ExpenseModel;
import com.krishi.repository.ExpenseDBService;
import com.krishi.view.activity.CreateExpenseActivity;
import com.krishi.view.activity.ExpenseActivity;
import com.krishi.view.adapter.ExpenseListAdapter;
import com.krishi.view.fragment.BalanceFragment;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Prasanth on 20-05-2020.
 * Honeywell
 */
public class ExpenseViewModel extends BaseViewModel {
    private ExpenseDBService expenseDBService;
    private List<ExpenseModel> expenseModelList;
    private List<PieEntry> chartList;
    private ExpenseListAdapter expenseAdapter;
    private String id, calSelection = "All", calSelectionSpent = "All";

    public void setupModel(AppCompatActivity context, String id) {
        this.id = id;
        setUp(context);
        init();
    }

    @Override
    protected void init() {
        setShowBack(true);
        setTitle("Expense Manager");
        expenseDBService = new ExpenseDBService();
        expenseAdapter = new ExpenseListAdapter();
    }

    @Override
    public void onResume() {
        expenseModelList = expenseDBService.getExpenses(id, getTimestamp(calSelection));
        chartList = expenseDBService.getSpent(id, getTimestamp(calSelectionSpent));
        expenseAdapter.update(expenseModelList);
        Fragment fragment = ((ExpenseActivity) context).getCurrentFragment();
        if (fragment instanceof BalanceFragment) {
            ((BalanceFragment) fragment).updateUi("", "");
        }
    }

    @Override
    public void onUpdateViewModel() {

    }

    private long getTimestamp(String calSelection) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);
        if (calSelection.equals("This Week")) {
            cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
            return cal.getTimeInMillis();
        } else if (calSelection.equals("This Month")) {
            cal.set(Calendar.DAY_OF_MONTH, 1);
            return cal.getTimeInMillis();
        }
        return 0;
    }

    public void onClickCreateExpense(View view) {
        context.startActivity(new Intent(context, CreateExpenseActivity.class).putExtra("id", id));
    }

    public ExpenseListAdapter getExpenseAdapter() {
        return expenseAdapter;
    }

    public void setCalSelection(String calSelection) {
        this.calSelection = calSelection;
    }

    public void setCalSelectionSpent(String calSelection) {
        this.calSelectionSpent = calSelection;
    }

    public List<PieEntry> getChartList() {
        return chartList;
    }
}
