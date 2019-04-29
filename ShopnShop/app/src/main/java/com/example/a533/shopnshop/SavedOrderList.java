package com.example.a533.shopnshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class SavedOrderList extends AppCompatActivity {

    RecyclerView recyclerItemsSavedOrders;
    SQLite dbShop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_order_list);

        recyclerItemsSavedOrders = findViewById(R.id.RecyclerViewSavedOrdersList);
        //InsertDataIntoRecyclerView();
    }

    //private void InsertDataIntoRecyclerView()
    //{
    //    recyclerItemsCompletedOrders.setHasFixedSize(true);
    //    LinearLayoutManager allo = new LinearLayoutManager(this);
    //    recyclerItemsCompletedOrders.setLayoutManager(allo);
    //    List<String> OrdersToShow = new ArrayList<String>();
//
    //    Cursor OrderCompleted = dbShop.GetCompletedOrders();
    //    if (OrderCompleted.getCount() != 0)
    //    {
    //        while (OrderCompleted.moveToNext())
    //        {
    //            OrdersToShow.add(OrderCompleted.getString(0));
    //        }
    //        OrdersToShow.add("One");
    //        OrdersToShow.add("Four");
    //        RecyclerView.Adapter data = new recycleViewItem(OrdersToShow);
    //        recyclerItemsCompletedOrders.setAdapter(data);
    //    }
    //    else
    //    {
//
    //    }
//
//
    //}
}
