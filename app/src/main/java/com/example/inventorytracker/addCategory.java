//Jacqueline Chavez Ayana Jackson
//Mobile App Development
//Inventory Tracker
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

import java.io.IOException;

/**
 * <h1>Add Category</h1>
 * The addCategory activity allows the user to add a Category to the inventory It takes it
 * a category description and a name and adds it to the database.
 * <p>
 *
 *
 * @author  Jacqueline Chavez
 * @version 1.4
 * @since   Fall 2022
 */
public class addCategory extends AppCompatActivity {
    //declare variables
    EditText name, desc;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        Intent intent = getIntent();
        //assign variables to input from activity
        name = findViewById(R.id.catName);
        desc = findViewById(R.id.catdesc);
        add = findViewById(R.id.addcat);


        add.setOnClickListener(v -> insert());
    }
    /**
     * This is the insert method. It intakes user input to add a category to the inventory.
     * @return Nothing.
     * @exception catch Exception ex. On return error.
     * @see Exception ex
     */

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
            //insert into database
            statement.bindString(1, category);
            statement.bindString(2, description);
            statement.execute();
            //popup message if record is sucessfully added
            Toast.makeText(this, "Category Added", Toast.LENGTH_LONG).show();
            //make EditText empty
            name.setText("");
            desc.setText("");
            name.requestFocus();
            //if record failed 
        } catch (Exception ex) {
            Toast.makeText(this, "Adding Record Failed", Toast.LENGTH_LONG).show();
        }
    }
}


