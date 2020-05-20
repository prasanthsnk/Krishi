package com.krishi.view.fragment;

import android.view.View;

import androidx.fragment.app.Fragment;

/**
 * Created by Prasanth on 20-05-2020.
 * Honeywell
 */
public abstract class BaseFragment extends Fragment {
    protected View mView;

    public abstract void initialize();

    public abstract void updateUi(String type, String data);
}
