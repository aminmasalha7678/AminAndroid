package com.example.aminandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    EditText email;
    EditText username;
    EditText password;
    EditText cpassword;
    Switch Isadmin;
    TextView error;
    EditText adminCode;
    Button signUpButton;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        email = findViewById(R.id.signup_email);
        username = findViewById(R.id.signup_username);
        password = findViewById(R.id.signup_password);
        cpassword = findViewById(R.id.signup_cpassword);
        Isadmin = findViewById(R.id.admin_switch);
        error = findViewById(R.id.signup_error);
        adminCode = findViewById(R.id.admin_code);
        mAuth = FirebaseAuth.getInstance();
        signUpButton = findViewById(R.id.signup_button);

        signUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                createNewAccount();
            }
        });
        Isadmin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    adminCode.setVisibility(View.VISIBLE);
                }

            }
        });

    }

    private void createNewAccount(){
        if (email.getText().toString().equals("")){
            error.setVisibility(View.VISIBLE);
            error.setText("Please enter your email");
        }
        else if(username.getText().toString().equals("")){
            error.setVisibility(View.VISIBLE);
            error.setText("Please enter your username");
        }
        else if(password.getText().toString().equals("")){
            error.setVisibility(View.VISIBLE);
            error.setText("Please enter your password");
        }
        else if(!password.getText().toString().equals(cpassword.getText().toString())){
            error.setVisibility(View.VISIBLE);
            error.setText("password doesn't match");
        }
        else if (Isadmin.isChecked() && !adminCode.getText().toString().equals("1323548631")){
            error.setVisibility(View.VISIBLE);
            error.setText("admin code is incorrect");
        }
        else{
            //sign-up with firebase
        }
    }
}