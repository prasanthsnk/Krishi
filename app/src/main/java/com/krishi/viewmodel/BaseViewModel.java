package com.krishi.viewmodel;

import android.app.ProgressDialog;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Prasanth on 14-03-2020.
 * Honeywell
 */
public abstract class BaseViewModel extends ViewModel {
    private String title = "";
    private ProgressDialog mProgressDialog;
    private boolean isShowBack = false;
    protected AppCompatActivity context;
    protected FirebaseDatabase database;

    protected void setUp(AppCompatActivity context) {
        this.context = context;
    }

    protected BaseViewModel() {
        database = FirebaseDatabase.getInstance();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void showDialogPopup(String string) {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog.show(context, "",
                    string, true);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }
    }

    public void dismissDialogPopup() {
        if (mProgressDialog != null) {
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
                mProgressDialog = null;
            }
        }
    }

    public void showToast(final String text) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, text,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void onClickBack() {
        context.finish();
    }

    public boolean isShowBack() {
        return isShowBack;
    }

    public void setShowBack(boolean showBack) {
        isShowBack = showBack;
    }

    protected abstract void init();

    public abstract void onResume();

    public abstract void onUpdateViewModel();
}
