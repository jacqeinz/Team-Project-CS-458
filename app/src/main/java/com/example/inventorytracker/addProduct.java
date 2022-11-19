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
        final Cursor c = db.rawQuery("select category from category",null);
        int category = c.getColumnIndex("category");
        cats.clear();
        //attach array of cats to arrayAdapter
        arrayAdaptercat = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,cats);
        //set spinner to what is in arrayAdapter
        spinnercat.setAdapter(arrayAdaptercat);

        final  ArrayList<category> cates = new ArrayList<category>();
        //testing if there is an empty set
        if(c.moveToFirst()) {
            do {
                category cate = new category();
                cate.category = c.getString(category);
                cates.add(cate);
                cats.add(c.getString(category) );

            } while (c.moveToNext());
            arrayAdaptercat.notifyDataSetChanged();

        }
        //Supplier spinner
        final Cursor b = db.rawQuery("select supplier from supplier",null);
        int supplier = b.getColumnIndex("supplier");
        sups.clear();
        //bind array of suppliers to arrayAdapter
        arrayAdaptersup= new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,sups);
        //set spinner to what is in ArrayAdapter
        spinnersup.setAdapter(arrayAdaptersup);
        final  ArrayList<supplier> suppliers = new ArrayList<com.example.inventorytracker.supplier>();

        if(b.moveToFirst()) {
            do {
                supplier sup = new supplier();
                sup.supplier = b.getString(supplier);
                suppliers.add(sup);
                sups.add(b.getString(supplier) );
            } while (b.moveToNext());
            arrayAdaptersup.notifyDataSetChanged();

        }

    }
//add products
    public void insert() {
        try {
            // variables
            String productname = name.getText().toString();
            String category = spinnercat.getSelectedItem().toString();
            String supplier = spinnersup.getSelectedItem().toString();
            String qty = proqty.getText().toString();
            String price = proprice.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);
            //create or open table and create rows
            db.execSQL("CREATE TABLE IF NOT EXISTS product(id INTEGER PRIMARY KEY AUTOINCREMENT,proname VARCHAR,category VARCHAR,supplier VARCHAR,qty VARCHAR,price VARCHAR)");

            String sql = "insert into product(proname,category,supplier,qty,price)values(?,?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            //bind values to statement
            statement.bindString(1, productname);
            statement.bindString(2, category);
            statement.bindString(3, supplier);
            statement.bindString(4, qty);
            statement.bindString(5, price);
            statement.execute();
            Toast.makeText(this, "Product added", Toast.LENGTH_LONG).show();
            name.setText("");
            proqty.setText("");
            proprice.setText("");
            name.requestFocus();

        } catch (Exception ex) {
            Toast.makeText(this, "Adding Record Failed", Toast.LENGTH_LONG).show();
        }
    }

}
