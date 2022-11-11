package com.example.loginuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginUser extends AppCompatActivity {
    //declare variables

    EditText username;
    EditText password;
    Button login;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        //methods
        setupUI();
        setupListeners();
    }

    private void setupUI(){
        //assign value to variable
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

    }

    private void setupListeners(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUsername();
            }
        });
//redirect user to register screen
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(loginUser.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

    void checkUsername(){
        boolean isValid = true;
        if(isEmpty(username)){
            username.setError("enter username!");
            isValid = false;
        }else{
            if(!isEmail(username)){
                username.setError("enter correct email!");
                isValid = false;
            }
        }

        if(isEmpty(password)){
            password.setError("enter password!");
            isValid = false;
        }else{
            if(password.getText().toString().length() < 8){
                password.setError("password must be at least 8 characters long");
            }
        }


        if(isValid){
            String usernameInput = username.getText().toString();
            String passwordInput = password.getText().toString();
            if(usernameInput.equals("ayana.jackson@enmu.edu") && passwordInput.equals("password123")){
                //add menu activity in replacement class
                Intent i = new Intent(loginUser.this, main.class);
                startActivity(i);
                this.finish();
            }else{
                Toast t = Toast.makeText(this, "wrong email or password", Toast.LENGTH_SHORT);
                t.show();
            }

        }


    }

    boolean isEmail(EditText text){
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    //check if field is empty
    boolean isEmpty(EditText text){
        CharSequence a = text.getText().toString();
        return TextUtils.isEmpty(a);
    }

}