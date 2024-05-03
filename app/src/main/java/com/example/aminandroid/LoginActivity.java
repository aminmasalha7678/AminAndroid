package com.example.aminandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView goTo_signup;

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
        login_button = findViewById(R.id.login_button);
        mAuth = FirebaseAuth.getInstance();

        login_button.setOnClickListener(this);
        goTo_signup.setOnClickListener(this);
    }

    private void login() {
        if (email.getText().toString().equals("")){
            Toast.makeText(LoginActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
        }
        else if(password.getText().toString().equals("")){
            Toast.makeText(LoginActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(LoginActivity.this, "Email or password are incorrect", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.login_button)
            login();
        else
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
    }
}