package com.example.a533.shopnshop;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InscriptionActivity extends AppCompatActivity {
    private SQLite dbShop;
    Button btnSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_activity);
        dbShop = new SQLite(this);

        btnSignup = (Button) findViewById(R.id.btn_signup);
        setListener();


    }

    private void setListener()
    {

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpUser();
                viewAllData();
            }
        });

    }

    private void SignUpUser()
    {
        EditText txtUserEmail = findViewById(R.id.editText_username_signup);
        EditText txtPassword = findViewById(R.id.edittext_password_signup);
        EditText txtPasswordConfirmation = findViewById(R.id.editText_confirmpassword_signup);

        if (!txtPassword.getText().toString().equals(txtPasswordConfirmation.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "MDP pas pareils", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
           if(!dbShop.verifyUsername(txtUserEmail.getText().toString())){
               dbShop.InsertUser(txtUserEmail.getText().toString(), txtPassword.getText().toString());
               Toast.makeText(getApplicationContext(), "Vous êtes inscrit!", Toast.LENGTH_SHORT).show();
           }
           else
           {
               Toast.makeText(getApplicationContext(), "Username déjà pris", Toast.LENGTH_SHORT).show();
           }

        }

    }

    public void viewAllData(){
        Cursor res = dbShop.getAllData();
        if(res.getCount() == 0){
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("Username :" + res.getString(1)+"\n");
            buffer.append("Password :" + res.getString(2)+"\n\n");
        }
    }

}