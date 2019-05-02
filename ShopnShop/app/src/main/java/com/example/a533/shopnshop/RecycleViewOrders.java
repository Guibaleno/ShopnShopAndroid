package com.example.a533.shopnshop;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

public class RecycleViewOrders extends RecyclerView.Adapter <RecycleViewOrders.MyViewHolder>  {

    private List<OrdersList> mDataset;
    Context context;
    CompletedOrderList completedOrderList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewItemName;

        public MyViewHolder(View v) {
            super(v);
            textViewItemName = (TextView) v.findViewById(R.id.txtOrderName);
        }
    }

    public RecycleViewOrders(CompletedOrderList newCompletedOrderList,List<OrdersList> myDataset) {
        completedOrderList = newCompletedOrderList;
        mDataset = myDataset;
    }

    @Override
    public RecycleViewOrders.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_recycle_view_orders, parent, false);
        context = parent.getContext();
        RecycleViewOrders.MyViewHolder vh = new RecycleViewOrders.MyViewHolder(view);
        return vh;

    }

    @Override
    public void onBindViewHolder(RecycleViewOrders.MyViewHolder holder, int position) {
        holder.textViewItemName.setText(mDataset.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
