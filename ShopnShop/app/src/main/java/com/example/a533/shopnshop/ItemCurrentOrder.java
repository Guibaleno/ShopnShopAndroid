package com.example.a533.shopnshop;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class ItemCurrentOrder extends RecyclerView.Adapter <ItemCurrentOrder.MyViewHolder> {
    Context context;
    List<ItemToSell> mDataset;
    private MakeAnorder.onDeleteItem onDeleteItem;

    public ItemCurrentOrder(List<ItemToSell> myDataset, MakeAnorder.onDeleteItem onDeleteItem) {
        this.mDataset = myDataset;
        this.onDeleteItem = onDeleteItem;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewItemName;
        public TextView textViewQuantity;
        public Button btnDelete;

        public MyViewHolder(View v) {
            super(v);
            textViewItemName = (TextView) v.findViewById(R.id.txtCurrentOrderItem);
            textViewQuantity = (TextView) v.findViewById(R.id.txtCurrentOrderQuantity);
            btnDelete = (Button) v.findViewById(R.id.btnDelete);

        }

    }
    @NonNull
    @Override
    public ItemCurrentOrder.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_current_order, parent, false);
        context = parent.getContext();
        ItemCurrentOrder.MyViewHolder vh = new ItemCurrentOrder.MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemCurrentOrder.MyViewHolder holder, final int position) {
        holder.textViewItemName.setText(mDataset.get(position).getName());
        holder.textViewQuantity.setText(mDataset.get(position).getQuantity());
        Log.d("testing", "onclick");
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Do you really want to delete");

// Set up the buttons
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onDeleteItem.deleteItem(holder.getAdapterPosition());
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public List<ItemToSell> getmDataset() {
        return mDataset;
    }
}
