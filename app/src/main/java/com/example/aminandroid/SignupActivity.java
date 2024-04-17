package com.example.aminandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignupActivity extends AppCompatActivity {
    EditText email,username,password,cpassword,adminCode;
    Switch Isadmin;
    TextView error,goTo_login;
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
        error = findViewById(R.id.signup_error);
        Isadmin = findViewById(R.id.admin_switch);
        adminCode = findViewById(R.id.admin_code);
        mAuth = FirebaseAuth.getInstance();
        signUpButton = findViewById(R.id.signup_button);
        goTo_login = findViewById(R.id.alreadyHaveAcc);
        adminCode.setVisibility(View.GONE);
        goTo_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });

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
                else {
                    adminCode.setVisibility(View.GONE);
                }


            }
        });

    }

    private void createNewAccount(){
        error.setVisibility(View.GONE);
        if (email.getText().toString().equals("")){
            error.setVisibility(View.VISIBLE);
            error.setText("Please enter your email");
        }
        else if(username.getText().toString().equals("")){
            error.setVisibility(View.VISIBLE);
            error.setText("Please enter your username");
        }
        else if(username.getText().toString().startsWith("admin: ")){
            error.setVisibility(View.VISIBLE);
            error.setText("No.");
        }
        else if(password.getText().toString().equals("")){
            error.setVisibility(View.VISIBLE);
            error.setText("Please enter your password");
        }
        else if(!password.getText().toString().equals(cpassword.getText().toString())){
            error.setVisibility(View.VISIBLE);
            error.setText("password doesn't match");
        }
        else if (Isadmin.isChecked() && !adminCode.getText().toString().equals("1")){
            error.setVisibility(View.VISIBLE);
            error.setText("admin code is incorrect");
        }
        else{
            //sign-up with firebase
            mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                String name = username.getText().toString();
                                FirebaseUser user = mAuth.getCurrentUser();
                                if(Isadmin.isChecked()){
                                    name = "admin: "+name;
                                }
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(name).build();
                                user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString());
                                        startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                    }
                                });

                            } else {
                                // If sign in fails, display a message to the user.
                                error.setVisibility(View.VISIBLE);
                                error.setText("Email is already in use");
                            }
                        }
                    });
        }
    }
}