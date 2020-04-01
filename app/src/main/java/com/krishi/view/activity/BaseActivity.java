package com.krishi.view.activity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Prasanth on 13-03-2020.
 * Honeywell
 */
public abstract class BaseActivity extends AppCompatActivity {

    public static final String RECEIVER = "activities.action.N_RECEIVED";
    public static final String INTENT_DATA = "data";
    public static final String INTENT_TYPE = "type";

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerReceiver(mReceiver, new IntentFilter(RECEIVER));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (intent.getAction() != null && intent.getAction().equals(RECEIVER)) {
                    updateUI(intent.getStringExtra(INTENT_TYPE), intent.getStringExtra(INTENT_DATA));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public void showDialogPopup(String string) {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog.show(this, "",
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

    protected abstract void initialize();

    protected abstract void updateUI(String type, String data);
}
