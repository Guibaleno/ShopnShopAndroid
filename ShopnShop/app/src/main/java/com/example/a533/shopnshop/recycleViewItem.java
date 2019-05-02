package com.example.a533.shopnshop;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class recycleViewItem extends RecyclerView.Adapter <recycleViewItem.MyViewHolder> {
    private List<ItemToSell> mDataset;
    private MakeAnorder.onBuy onBuyCallback;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewItemName;
        public TextView textViewQuantity;
        public Button btnBuy;

        public MyViewHolder(View v) {
            super(v);
            textViewItemName = (TextView) v.findViewById(R.id.txtItemName);
            textViewQuantity = (TextView) v.findViewById(R.id.txtItemName);
            btnBuy = (Button) v.findViewById(R.id.btnBuy);
        }
    }

    public recycleViewItem(List<ItemToSell> myDataset, MakeAnorder.onBuy onBuyCallback) {
        mDataset = myDataset;
        this.onBuyCallback = onBuyCallback;
    }

    @Override
    public recycleViewItem.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_recycle_view_item, parent, false);
        context = parent.getContext();
        MyViewHolder vh = new MyViewHolder(view);
        return vh;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textViewItemName.setText(mDataset.get(position).getName());
        holder.textViewQuantity.setText(mDataset.get(position).getQuantity());
        holder.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Enter a quantity");
// Set up the input
                final EditText input = new EditText(context);
                //input.setRawInputType(InputType.TYPE_CLASS_NUMBER);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //m_Text = input.getText().toString();
                        //RecyclerView.Adapter data = new ItemCurrentOrder(input.getText().toString());
                        onBuyCallback.addToOrder(0,Integer.parseInt(input.getText().toString()));
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
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
}

