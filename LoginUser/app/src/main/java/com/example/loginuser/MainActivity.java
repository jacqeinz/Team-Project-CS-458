package com.example.loginuser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //declare variables

    EditText firstName;
    EditText lastName;
    EditText email;
    Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //assign value to variable
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        btnRegister = findViewById(R.id.btnRegister);

        //register button

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataEntered();

            }
        });
    }
    //check email
    boolean isEmail(EditText text){
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    //check if field is empty
    boolean isEmpty(EditText text){
        CharSequence a = text.getText().toString();
        return TextUtils.isEmpty(a);
    }

    void checkDataEntered(){
        if (isEmpty(firstName)){
            Toast.makeText(this, "You must enter first name", Toast.LENGTH_SHORT).show();
        }
        if (isEmpty(lastName)){
            lastName.setError("last name is required");
        }
        if (isEmail(email) == false){
            email.setError("invalid email");
        }
    }


}
