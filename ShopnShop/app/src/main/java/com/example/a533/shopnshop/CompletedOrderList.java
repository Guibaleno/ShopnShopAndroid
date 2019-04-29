package com.example.a533.shopnshop;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CompletedOrderList extends AppCompatActivity {

    RecyclerView recyclerItemsCompletedOrders;
    SQLite dbShop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_order_list);

        dbShop = new SQLite(this);
        dbShop.CreateOrders();

        recyclerItemsCompletedOrders = findViewById(R.id.RecyclerViewCompletedOrdersList);
        InsertDataIntoRecyclerView();
    }

    private void InsertDataIntoRecyclerView()
    {
        recyclerItemsCompletedOrders.setHasFixedSize(true);
        LinearLayoutManager allo = new LinearLayoutManager(this);
        recyclerItemsCompletedOrders.setLayoutManager(allo);
        List<String> OrdersToShow = new ArrayList<String>();
//
        OrdersToShow = dbShop.GetCompletedOrders();
       // RecyclerView.Adapter data = new recycleViewItem(OrdersToShow);
        //recyclerItemsCompletedOrders.setAdapter(data);
//
//
    }
}
