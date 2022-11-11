//Jacqueline Chavez Ayana Jackson
//Mobile App Development
//Inventory Tracker
package com.example.inventorytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addUser extends AppCompatActivity {

    //declare variables

    EditText firstName;
    EditText lastName;
    EditText email;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        //set values to variables

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        btnRegister = findViewById(R.id.btnRegister);

        //onclick listener
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
        return(!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text){
        CharSequence s = text.getText().toString();
        return TextUtils.isEmpty(s);
    }
    //method to check user input
    void checkDataEntered(){
        if (isEmpty(firstName)){
            Toast t = Toast.makeText(this, "You must enter a first name", Toast.LENGTH_SHORT);
            t.show();
        }

        if(isEmpty(lastName)){
            lastName.setError("Last name required");
        }

        if(isEmail(email) == false){
            email.setError("Invalid email");
        }
    }

}
