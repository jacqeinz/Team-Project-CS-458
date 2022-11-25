//Jacqueline Chavez Ayana Jackson
//Mobile App Development
//Inventory Tracker
package com.example.inventorytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class addProduct extends AppCompatActivity {
    //declare componenets, variables
    private Spinner spinnercat;
    private Spinner spinnersup;
    ArrayList<String> cats = new ArrayList<String>();
    ArrayList<String> sups = new ArrayList<String>();
    ArrayAdapter arrayAdaptersup;
    ArrayAdapter arrayAdaptercat;
    EditText name,proqty, proprice;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        //link to UI components
        name = findViewById(R.id.pname);
        spinnercat = findViewById(R.id.catID);
        spinnersup = findViewById(R.id.supID);
        proqty = findViewById(R.id.proqty);
        proprice = findViewById(R.id.proprice);
        add = findViewById(R.id.addbtn);
        add.setOnClickListener(v -> insert());


        //open or create database
        SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);
        //category spinner
        //make cursor so you can loop through categories/rows
        //send a raw sql call to sqlite
        final Cursor c = db.rawQuery("select category from category",null);
        //returns index and store it in category
        int category = c.getColumnIndex("category");
        cats.clear();
        //attach array of cats to arrayAdapter
        arrayAdaptercat = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,cats);
        //set spinner to what is in arrayAdapter
        spinnercat.setAdapter(arrayAdaptercat);
        //make arraylist to store cateogories
        final  ArrayList<category> cates = new ArrayList<category>();
        //loop through categories start at the top using cursor
        if(c.moveToFirst()) {
            do {
                //create cateogry object
                category cate = new category();
                cate.category = c.getString(category);
                //add to arraylist
                cates.add(cate);
                //add  selected category to category arraylist
                cats.add(c.getString(category) );

            } while (c.moveToNext());
            arrayAdaptercat.notifyDataSetChanged();

        }
        
        //Supplier spinner
        //make cursor so you can loop through suppliers/rows
        //send a raw sql call to sqlite
        final Cursor b = db.rawQuery("select supplier from supplier",null);
        //get columnn index of supplier and strore in supplier
        int supplier = b.getColumnIndex("supplier");
        sups.clear();
        //bind array of suppliers to arrayAdapter
        arrayAdaptersup= new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,sups);
        //set spinner to what is in ArrayAdapter
        spinnersup.setAdapter(arrayAdaptersup);

        final  ArrayList<supplier> suppliers = new ArrayList<supplier>();
        //loop through categories start at the top using cursor
        if(b.moveToFirst()) {
            do {
                //create supplier object
                supplier sup = new supplier();
                //store supplier in sup supplier attribute
                sup.supplier = b.getString(supplier);
                //add to arraylist
                suppliers.add(sup);
                //add  selected supplier to supplier arraylist
                sups.add(b.getString(supplier));
            } while (b.moveToNext());
            //tell arrayadapter to refresh
            arrayAdaptersup.notifyDataSetChanged();

        }

    }
//add products
    public void insert() {
        try {
        
            // extract values from UI
            String productname = name.getText().toString();
            String category = spinnercat.getSelectedItem().toString();
            String supplier = spinnersup.getSelectedItem().toString();
            String qty = proqty.getText().toString();
            String price = proprice.getText().toString();
            //open or create datanse
            SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);
            //create or open table
            db.execSQL("CREATE TABLE IF NOT EXISTS product(id INTEGER PRIMARY KEY AUTOINCREMENT,proname VARCHAR,category VARCHAR,supplier VARCHAR,qty VARCHAR,price VARCHAR)");
            //create columns
            String sql = "insert into product(proname,category,supplier,qty,price)values(?,?,?,?,?)";
            //create statement to add to database
            SQLiteStatement statement = db.compileStatement(sql);
            //insert into database by binding
            statement.bindString(1, productname);
            statement.bindString(2, category);
            statement.bindString(3, supplier);
            statement.bindString(4, qty);
            statement.bindString(5, price);
            statement.execute();
            Toast.makeText(this, "Product added", Toast.LENGTH_LONG).show();
            //empty fields
            name.setText("");
            proqty.setText("");
            proprice.setText("");
            name.requestFocus();

        } catch (Exception ex) {
            //if adding record fails
            Toast.makeText(this, "Adding Record Failed", Toast.LENGTH_LONG).show();
        }
    }

}
