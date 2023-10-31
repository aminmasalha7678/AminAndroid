package com.example.aminandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    TextView goTo_signup,error;

    EditText email,password;

    Button login_button;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        goTo_signup = findViewById(R.id.createacc);
        email = findViewById(R.id.email_login);
        password = findViewById(R.id.password_login);
        error = findViewById(R.id.error_login);
        login_button = findViewById(R.id.login_button);
        mAuth = FirebaseAuth.getInstance();

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        goTo_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
    }

    private void login() {
        if (email.getText().toString().equals("")){
            error.setVisibility(View.VISIBLE);
            error.setText("Please enter your email");
        }
        else if(password.getText().toString().equals("")){
            error.setVisibility(View.VISIBLE);
            error.setText("Please enter your password");
        }
        else{
            mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            } else {
                                // If sign in fails, display a message to the user.
                                error.setVisibility(View.VISIBLE);
                                error.setText(task.getException().getMessage());
                            }
                        }
                    });

        }
    }
}