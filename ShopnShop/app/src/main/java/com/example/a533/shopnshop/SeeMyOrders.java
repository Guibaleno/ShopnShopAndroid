package com.example.a533.shopnshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SeeMyOrders extends AppCompatActivity {

    Disconnection disconnection;

    Button btnCompletedOrder;
    Button btnSavedOrder;
    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_my_orders);

        btnCompletedOrder = findViewById(R.id.button_completedOrders);
        btnSavedOrder = findViewById(R.id.button_SavedOrders);

        myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        setListeners();
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
        if (disconnection.getDisconnection() == true) {
            this.finish();
        }

    }

    private void setListeners()
    {
        btnCompletedOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveToCompletedOrderList();
            }
        });
        btnSavedOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveToSavedOrderList();
            }
        });

    }

    private void MoveToCompletedOrderList()
    {
        Intent intent = new Intent(this, OrderList.class);
        intent.putExtra("OrderType", "Completed");
        startActivity(intent);
    }
    private void MoveToSavedOrderList()
    {
        Intent intent = new Intent(this, OrderList.class);
        intent.putExtra("OrderType", "Saved");
        startActivity(intent);
    }

    private void Deconnexion(){
        this.finish();
        disconnection.setDisconnection(true);
    }

}


