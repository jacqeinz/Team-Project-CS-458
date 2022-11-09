package com.example.inventorytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Product extends AppCompatActivity {
    //declare componenets, variables
    private Spinner spinnercat;
    private Spinner spinnersup;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayList<String> titles1 = new ArrayList<String>();
    ArrayAdapter arrayAdapter;
    ArrayAdapter arrayAdapter1;
    EditText name,proqty, proprice;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        name = findViewById(R.id.pname);
        spinnercat = findViewById(R.id.catID);
        spinnersup = findViewById(R.id.supID);
        proqty = findViewById(R.id.proqty);
        proprice = findViewById(R.id.proprice);
        add = findViewById(R.id.addbtn);
        add.setOnClickListener(v -> insert());
        //category spinner
        SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);
        final Cursor c = db.rawQuery("select category from category",null);
        int category = c.getColumnIndex("category");
        titles.clear();
        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,titles);
        spinnercat.setAdapter(arrayAdapter);

        final  ArrayList<categoryVariables> stud = new ArrayList<categoryVariables>();
        if(c.moveToFirst()) {
            do {
                categoryVariables stu = new categoryVariables();
                stu.category = c.getString(category);
                stud.add(stu);
                titles.add(c.getString(category) );

            } while (c.moveToNext());
            arrayAdapter.notifyDataSetChanged();

        }
        //Supplier spinner
        final Cursor b = db.rawQuery("select supplier from supplier",null);
        int supplier = b.getColumnIndex("supplier");
        titles1.clear();
        arrayAdapter1= new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,titles1);
        spinnersup.setAdapter(arrayAdapter1);
        final  ArrayList<supplierVariables> suppliers = new ArrayList<supplierVariables>();

        if(b.moveToFirst()) {
            do {
                supplierVariables aa = new supplierVariables();
                aa.supplier = b.getString(supplier);
                suppliers.add(aa);
                titles1.add(b.getString(supplier) );
            } while (b.moveToNext());
            arrayAdapter1.notifyDataSetChanged();

        }

    }
//add product
    public void insert() {
        try {
            //extra variables
            String productname = name.getText().toString();
            String category = spinnercat.getSelectedItem().toString();
            String supplier = spinnersup.getSelectedItem().toString();
            String qty = proqty.getText().toString();
            String price = proprice.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS product(id INTEGER PRIMARY KEY AUTOINCREMENT,proname VARCHAR,category VARCHAR,supplier VARCHAR,qty VARCHAR,price VARCHAR)");

            String sql = "insert into product(proname,category,supplier,qty,price)values(?,?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
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
            Toast.makeText(this, "Record Failed", Toast.LENGTH_LONG).show();
        }
    }

}
