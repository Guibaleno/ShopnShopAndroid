package com.example.a533.shopnshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class OrderList extends AppCompatActivity {

    RecyclerView recyclerItemsCompletedOrders;
    SQLite dbShop;
    List<OrdersList> OrdersToShow;
    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_order_list);

        myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        dbShop = new SQLite(this);
        String orderType = getIntent().getStringExtra("OrderType");
        if (orderType.equals("Completed"))
        {
            orderType = "1";
        }
        else
        {
            orderType = "0";
        }
        OrdersToShow = dbShop.GetOrders(orderType, getIntent().getStringExtra("Username"));
        recyclerItemsCompletedOrders = findViewById(R.id.RecyclerViewCompletedOrdersList);
        InsertDataIntoRecyclerView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Deconnexion();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        // ...
//
        // Define the listener
        MenuItem.OnActionExpandListener expandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when action item collapses
                return true;  // Return true to collapse action view
            }
            //
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                return true;  // Return true to expand action view
            }


        };

        // Get the MenuItem for the action item
        MenuItem actionMenuItem = menu.findItem(R.id.toolbar);
//
        // Assign the listener to that action item

        //actionMenuItem.setOnActionExpandListener(MenuItem.OnActionExpandListener);
//
        // Any other things you have to do when creating the options menu...
//
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Disconnection.getDisconnection() == true) {
            this.finish();
        }

    }

    private void InsertDataIntoRecyclerView()
    {
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

    private void Deconnexion(){
        this.finish();
        Disconnection.setDisconnection(true);
    }


}
