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
    List<OrdersList> OrdersToShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_order_list);

        dbShop = new SQLite(this);
        OrdersToShow = dbShop.GetCompletedOrders();

        recyclerItemsCompletedOrders = findViewById(R.id.RecyclerViewCompletedOrdersList);
        InsertDataIntoRecyclerView();
    }

    private void InsertDataIntoRecyclerView()
    {
        Log.d("msg", dbShop.GetCompletedOrders().toString());
        recyclerItemsCompletedOrders.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerItemsCompletedOrders.setLayoutManager(layoutManager);
        if (OrdersToShow.size() != 0)
        {
            RecyclerView.Adapter data = new RecycleViewOrders(this, OrdersToShow);
            recyclerItemsCompletedOrders.setAdapter(data);
        }
        else
        {

        }

    }


}
