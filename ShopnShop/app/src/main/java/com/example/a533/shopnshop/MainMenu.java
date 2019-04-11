package com.example.a533.shopnshop;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toolbar;

public class MainMenu extends AppCompatActivity {

    Button btnOrder;
    Button btnSeeMyOrders;
    Button btnChangeProfilePicture;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //ActionBar actionbar = getSupportActionBar();
        //actionbar.setDisplayHomeAsUpEnabled(true);
        //actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        //drawerLayout = findViewById(R.id.drawer_layout);
//
        //mDrawerLayout.addDrawerListener(
        //        new DrawerLayout.DrawerListener() {
        //            @Override
        //            public void onDrawerSlide(View drawerView, float slideOffset) {
        //                // Respond when the drawer's position changes
        //            }
//
        //            @Override
        //            public void onDrawerOpened(View drawerView) {
        //                // Respond when the drawer is opened
        //            }
//
        //            @Override
        //            public void onDrawerClosed(View drawerView) {
        //                // Respond when the drawer is closed
        //            }
//
        //            @Override
        //            public void onDrawerStateChanged(int newState) {
        //                // Respond when the drawer motion state changes
        //            }
        //        }
        //);


        btnOrder = findViewById(R.id.btnOrder);
        btnSeeMyOrders = findViewById(R.id.btnSeeMyOrders);
        btnChangeProfilePicture = findViewById(R.id.btnChangeProfilePicture);
        setListeners();
        StartAnimation();
        DialogMenu();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
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


    private void DialogMenu(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Vous avez X commande");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Okay",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();


    }

    private void StartAnimation()
    {
        ImageView myView = (ImageView)findViewById(R.id.imageViewShopnShop);

        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(myView, "alpha",  1f, .3f);
        fadeOut.setDuration(500);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(myView, "alpha", .3f, 1f);
        fadeIn.setDuration(500);

        final AnimatorSet mAnimationSet = new AnimatorSet();

        mAnimationSet.play(fadeIn).after(fadeOut);

        mAnimationSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mAnimationSet.start();
            }
        });
        mAnimationSet.start();
    }
}
