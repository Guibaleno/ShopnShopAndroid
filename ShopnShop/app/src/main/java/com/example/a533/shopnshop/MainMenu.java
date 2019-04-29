package com.example.a533.shopnshop;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;

public class MainMenu extends AppCompatActivity {
    LoginActivity mLoginActivity;

    Button btnOrder;
    Button btnSeeMyOrders;
    Button btnChangeProfilePicture;
    Toolbar myToolbar;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

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

    private void Deconnexion(){
        this.finish();
    }
}
