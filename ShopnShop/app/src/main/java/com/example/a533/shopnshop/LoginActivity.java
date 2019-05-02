package com.example.a533.shopnshop;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    EditText txtUsername;
    EditText txtPassword;
    Button btnConnection;
    SQLite dbShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        txtUsername = findViewById(R.id.editText_username_login);
        txtPassword = findViewById(R.id.editText_password_login);
        btnConnection = findViewById(R.id.button_connexion);
        dbShop = new SQLite(this);
        setListeners();
    }

    void setListeners()
    {
        btnConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dbShop.verifyLoginUsername(txtUsername.getText().toString()))
                {
                    if (dbShop.VerifyCredentials(txtUsername.getText().toString(), txtPassword.getText().toString()))
                    {
                        MoveToMainMenu();
                    }
                    else
                    {
                        txtPassword.getText().clear();
                        txtPassword.requestFocus();
                        Toast.makeText(getApplicationContext(), "Mot de passe invalide", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Username invalide", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void MoveToMainMenu()
    {
        Intent intent = new Intent(this, MainMenu.class);
        intent.putExtra("Username", txtUsername.getText().toString());
        startActivity(intent);
        EraseTextbox();
    }

    public void EraseTextbox()
    {
        txtUsername.getText().clear();
        txtPassword.getText().clear();
    }
}
