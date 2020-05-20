package com.krishi.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Prasanth on 20-05-2020.
 * Honeywell
 */
public abstract class BaseRecycleViewAdapter extends RecyclerView.Adapter<BaseRecycleViewAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ViewDataBinding binding;

        ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Object viewModel, Object obj, int position) {
            binding.setVariable(BR.viewModel, viewModel);
            binding.setVariable(BR.obj, obj);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, getLayoutIdForType(viewType), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getViewModel(), getDataAtPosition(position), position);
    }


    protected abstract Object getDataAtPosition(int position);

    protected abstract Object getViewModel();

    protected abstract int getLayoutIdForType(int viewType);

}