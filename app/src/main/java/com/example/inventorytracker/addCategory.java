package com.example.inventorytracker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class addCategory extends AppCompatActivity {
    EditText name, desc;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        Intent i = getIntent();
        //assign variables to input from activity
        name = findViewById(R.id.catName);
        desc = findViewById(R.id.catdesc);
        add = findViewById(R.id.addcat);


        add.setOnClickListener(v -> insert());
    }
    public void insert() {
        try {
            //extract values from input
            String category = name.getText().toString();
            String description = desc.getText().toString();
            //open or create database inventory
            SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);
            //create table with the following variables and insert them

            db.execSQL("CREATE TABLE IF NOT EXISTS category(id INTEGER PRIMARY KEY AUTOINCREMENT,category VARCHAR,description VARCHAR)");
            //create rows
            String sql = "insert into category(category,description)values(?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            //bind Strings to statement that was created then execute
            statement.bindString(1, category);
            statement.bindString(2, description);
            statement.execute();
            //popup message if record is sucessfully added
            Toast.makeText(this, "Record added", Toast.LENGTH_LONG).show();
            //make EditText empty
            name.setText("");
            desc.setText("");
            name.requestFocus();
            //if record failed 
        } catch (Exception ex) {
            Toast.makeText(this, "Record Fail", Toast.LENGTH_LONG).show();
        }
    }
}


