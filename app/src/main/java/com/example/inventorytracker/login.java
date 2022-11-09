package com.example.inventorytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity {

    //declare variables

    EditText username;
    EditText password;
    Button register;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupUI();
        setupListeners();
    }
    //method to connect variables to with elements
    private void setupUI(){
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
    }
    //login and register methods
    private void setupListeners(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
           //     checkUsername();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
            }
        });

       // private void checkUsername() {
            boolean isValid = true;
            if (isEmpty(username)){
                username.setError("you must enter username");
                isValid = false;
            }else{
                if (!isEmail(username)){
                    username.setError("enter valid email");
                    isValid = false;
                }
            }

            if(isEmpty(password)){
                password.setError("must enter password to login");
                isValid = false;
            }else{
                if(password.getText().toString().length() < 8){
                    password.setError("Password must be at least 8 characters");
                    isValid = false;
                }
            }

        }


    //isEmail method
    boolean isEmail(EditText text){
        CharSequence email = text.getText().toString();
        return(!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
    //isEmpty method
    boolean isEmpty(EditText text){
        CharSequence s = text.getText().toString();
        return TextUtils.isEmpty(s);
    }


}
