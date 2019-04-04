package com.example.a533.shopnshop;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class recycleViewItem extends RecyclerView.Adapter <recycleViewItem.MyViewHolder> {
    private List<String> mDataset;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public MyViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.textRecycleViewItem);
        }
    }

    public recycleViewItem(List<String> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public recycleViewItem.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_recycle_view_item, parent, false);

        MyViewHolder vh = new MyViewHolder(view);
        return vh;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

