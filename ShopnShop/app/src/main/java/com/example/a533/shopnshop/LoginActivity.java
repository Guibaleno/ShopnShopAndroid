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
               if (dbShop.VerifyCredentials(txtUsername.getText().toString(), txtPassword.getText().toString()))
               {
                    MoveToMainMenu();
               }
               else
               {
                   txtPassword.getText().clear();
                   Toast.makeText(getApplicationContext(), "Username ou mot de passe invalide", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }

    private void MoveToMainMenu()
    {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    //private void SigningUpUser()
    //{
    //    EditText userEmail = findViewById(R.id.editText_username_signup);
    //    EditText password = findViewById(R.id.edittext_password_signup);
    //    EditText passwordConfirmation = findViewById(R.id.editText_confirmpassword_signup);
//
    //    if (!password.getText().toString().equals(passwordConfirmation.getText().toString()))
    //    {
    //        Toast.makeText(getApplicationContext(), "MDP pas pareils", Toast.LENGTH_SHORT).show();
    //        return;
    //    }
    //    auth.createUserWithEmailAndPassword(userEmail.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    //        @Override
    //        public void onComplete(@NonNull Task<AuthResult> task) {
    //            if (task.isSuccessful())
    //            {
    //                sendUserToMainActivity();
    //            }
    //            else
    //            {
    //                Toast.makeText(getApplicationContext(), "Failed to sign up", Toast.LENGTH_SHORT).show();
    //            }
    //        }
    //    });
    //}
}
