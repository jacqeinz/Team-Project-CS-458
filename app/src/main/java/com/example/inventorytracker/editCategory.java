//Jacqueline Chavez Ayana Jackson
//Mobile App Development
//Inventory Tracker
package com.example.inventorytracker;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class editCategory extends AppCompatActivity {
    //variables
    EditText cateID, cateName, cateDesc;
    Button edit, delete;
    String id, cate, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);
        //associate to UI components
        cateID = findViewById(R.id.cateID);
        cateName = findViewById(R.id.cateName);
        cateDesc  = findViewById(R.id.catedesc);
        edit = findViewById(R.id.editbtn);
        delete = findViewById(R.id.deletebtn);

        Intent intent = getIntent();
        //get data from activity viewCategories
        String id = intent.getStringExtra("id");
        String cate = intent.getStringExtra("category");
        String desc = intent.getStringExtra("description");
        //display information sent
        cateID.setText(id);
        cateName.setText(cate);
        cateDesc.setText(desc);
        //set button to functions
        delete.setOnClickListener(v -> Delete());
        edit.setOnClickListener(v -> Edit());
    }

    //to edit a category
    public void Edit() {
        try {
            //get information for category
            String id = cateID.getText().toString();
            String category = cateName.getText().toString();
            String catdes = cateDesc.getText().toString();
            //open or create database
            SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);
            //update category with new information
            String sql = "update category set category = ?,description=? where id= ?";
            SQLiteStatement statement = db.compileStatement(sql);
            //bind new information to statement
            statement.bindString(1, category);
            statement.bindString(2, catdes);
            statement.bindString(3,id);
            statement.execute();
            //if successful
            Toast.makeText(this, "Record Updated", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            //if editing fails
        } catch (Exception ex) {
            Toast.makeText(this, "Editing Record Failed", Toast.LENGTH_LONG).show();
        }
    }
    //to delete category
    public void Delete()
    {
        try
        {
            //extract id from UI component
            String id = cateID.getText().toString();
            //open or createdatabase
            SQLiteDatabase db = openOrCreateDatabase("inventory",Context.MODE_PRIVATE,null);
            //delete category with that id
            String sql = "delete from category where id = ?";
            //create statement
            SQLiteStatement statement = db.compileStatement(sql);
            //bind to statement
            statement.bindString(1,id);
            statement.execute();
            //if record is succesffully deleted
            Toast.makeText(this,"Record Deleted",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Deleting Record Failed",Toast.LENGTH_LONG).show();
        }
    }

}

