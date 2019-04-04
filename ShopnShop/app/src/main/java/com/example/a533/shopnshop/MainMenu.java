package com.example.a533.shopnshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    Button btnOrder;
    Button btnSeeMyOrders;
    Button btnChangeProfilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        btnOrder = findViewById(R.id.btnOrder);
        btnSeeMyOrders = findViewById(R.id.btnSeeMyOrders);
        btnChangeProfilePicture = findViewById(R.id.btnChangeProfilePicture);
    }

    private void setListeners()
    {
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveToMakeAnOrder();
            }
        });
        btnSeeMyOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveToSeeMyOrders();
            }
        });
        btnChangeProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveToProfilePicture();
            }
        });
    }

    private void MoveToMakeAnOrder()
    {
        Intent intent = new Intent(this, MakeAnorder.class);
        startActivity(intent);
    }
    private void MoveToSeeMyOrders()
    {
        Intent intent = new Intent(this, SeeMyOrders.class);
        startActivity(intent);
    }
    private void MoveToProfilePicture()
    {
        Intent intent = new Intent(this, photoActivity.class);
        startActivity(intent);
    }
}
