package com.lovejjfg.recycleviewdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lovejjfg.recycleviewdemo.R;

/**
 * Created by Joe on 2016/4/21.
 * Email lovejjfg@gmail.com
 */
public class MyAdapter extends RecyclerView.Adapter {
    public MyAdapter() {
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return onViewHolder(parent);

    }

    private RecyclerView.ViewHolder onViewHolder(ViewGroup parent) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).onBindView(position);

    }

    @Override
    public int getItemCount() {
        return 50;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_name);
        }

        public void onBindView(int position) {
            name.setText("这是" + position);
        }
    }

}
