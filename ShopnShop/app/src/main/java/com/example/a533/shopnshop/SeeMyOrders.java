package com.example.a533.shopnshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SeeMyOrders extends AppCompatActivity {

    Button btnCompletedOrder;
    Button btnSavedOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_my_orders);

        btnCompletedOrder = findViewById(R.id.button_completedOrders);
        btnSavedOrder = findViewById(R.id.button_SavedOrders);

        setListeners();
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
        Intent intent = new Intent(this, CompletedOrderList.class);
        startActivity(intent);
    }
    private void MoveToSavedOrderList()
    {
        Intent intent = new Intent(this, SavedOrderList.class);
        startActivity(intent);
    }

}


