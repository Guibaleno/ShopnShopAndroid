package com.example.a533.shopnshop;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button loginBtn;
    Button inscriptionBtn;
    Button test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        loginBtn = (Button) findViewById(R.id.btn_connection);
        inscriptionBtn = (Button) findViewById(R.id.btn_signup);
        setListener();


    }


    private void setListener()
    {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTologin();
            }
        });

        inscriptionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignup();
            }
        });
    }

    public void goTologin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void goToSignup(){
        Intent intent = new Intent(this, InscriptionActivity.class);
        startActivity(intent);
    }
}
