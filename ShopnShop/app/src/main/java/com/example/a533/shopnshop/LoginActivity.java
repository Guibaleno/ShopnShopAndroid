package com.example.a533.shopnshop;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
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
