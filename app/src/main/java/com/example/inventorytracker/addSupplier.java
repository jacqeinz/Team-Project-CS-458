//Jacqueline Chavez Ayana Jackson
//Mobile App Development
//Inventory Tracker
package com.example.inventorytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addSupplier extends AppCompatActivity {

    //declare UI components
    EditText sup,desc;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier);
        //connecct to UI components
        sup = findViewById(R.id.supID);
        desc = findViewById(R.id.supplierdesc);
        add = findViewById(R.id.edit);


        add.setOnClickListener(v -> insert());
    }
    //insert sale
    public void insert() {
        try {
            //get input from user
            String supplier = sup.getText().toString();
            String description = desc.getText().toString();

            //open or create database
            SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);
            //create table
            db.execSQL("CREATE TABLE IF NOT EXISTS supplier(id INTEGER PRIMARY KEY AUTOINCREMENT,supplier VARCHAR,description VARCHAR)");
            //create rows
            String sql = "insert into supplier(supplier,description)values(?,?)";
            //create statement to add to database
            SQLiteStatement statement = db.compileStatement(sql);
            //add to database
            statement.bindString(1, supplier);
            statement.bindString(2, description);
            statement.execute();
            //if adding supplier successful
            Toast.makeText(this, "Supplier Added", Toast.LENGTH_LONG).show();
            //empty fields
            sup.setText("");
            desc.setText("");

            sup.requestFocus();


        } catch (Exception ex) {
            //if failed
            Toast.makeText(this, "Adding Record Failed", Toast.LENGTH_LONG).show();
        }

    }


}
