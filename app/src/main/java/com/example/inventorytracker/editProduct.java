
//Jacqueline Chavez Ayana Jackson
//Mobile App Development
//Inventory Tracker
package com.example.inventorytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.ArrayList;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;


public class editProduct extends AppCompatActivity {
    //variables
    private Spinner spinnercat;
    private Spinner spinnersup;
    ArrayAdapter arrayAdaptercat;
    ArrayAdapter arrayAdaptersup;
    EditText pid, pname, proqty, proprice;
    Button edit, delete;
    ArrayList<String> cats = new ArrayList<String>();
    ArrayList<String> sups = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editproduct);

        //link to UI components
        pid = findViewById(R.id.pid);
        pname = findViewById(R.id.pname);
        spinnercat = findViewById(R.id.cateid);
        spinnersup = findViewById(R.id.supid);
        proqty = findViewById(R.id.proqty);
        proprice = findViewById(R.id.proprice);
         edit = findViewById(R.id.edit);
        delete = findViewById(R.id.delete);
        //get information from previos activity
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String pro = intent.getStringExtra("product");
        String categ = intent.getStringExtra("category");

        String supp = intent.getStringExtra("supplier");
        String qty = intent.getStringExtra("qty");
        String price = intent.getStringExtra("price");
        //display information
        pid.setText(id);
        pname.setText(pro);

        proqty.setText(qty);
        proprice.setText(price);
        //open or create database

        //category spinner
        //make cursor so you can loop through categories/rows
        //send a raw sql call to sqlite
        SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);
        //create cursor
        final Cursor c = db.rawQuery("select category from category",null);
        //get index and store in category
        int category = c.getColumnIndex("category");
        cats.clear();
        // store cats in arrayadapter
        arrayAdaptercat = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,cats);
        //set spinner to arrayadapter
        spinnercat.setAdapter(arrayAdaptercat);
        //array of categories
        final ArrayList<category> cates = new ArrayList<category>();
        //loop through cateogires, start at the beginning
        if(c.moveToFirst()) {
            do {
                //create object of type category
                category cate = new category();
                //pass retrieved cateogory to object
                cate.category = c.getString(category);
                //add object to arraylist
                cates.add(cate);
                //add category to cats
                cats.add(c.getString(category) );

            } while (c.moveToNext());
            arrayAdaptercat.notifyDataSetChanged();

        }
        //Supplier spinner
        //make cursor so you can loop through categories/rows
        //send a raw sql call to sqlite
        final Cursor b = db.rawQuery("select supplier from supplier",null);
        int supplier = b.getColumnIndex("supplier");
        sups.clear();
        //setspinner to supplier
        arrayAdaptersup= new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,sups);
        spinnersup.setAdapter(arrayAdaptersup);
        final  ArrayList<supplier> suppliers = new ArrayList<supplier>();

        if(b.moveToFirst()) {
            do {
                //create an object of type supplier and add to supplier array
                supplier sup = new supplier();
                sup.supplier = b.getString(supplier);
                suppliers.add(sup);
                sups.add(b.getString(supplier) );
            } while (b.moveToNext());
            arrayAdaptersup.notifyDataSetChanged();

        }
        edit.setOnClickListener(v -> Edit());


        delete.setOnClickListener(v -> Delete());

    }


    //delete supplier
    public void Delete()
    {
        try
        {   //get id
            String id = pid.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("inventory",Context.MODE_PRIVATE,null);

            //delete from database
            String sql = "delete from product where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1,id);
            statement.execute();
            Toast.makeText(this,"Record Deleted",Toast.LENGTH_LONG).show();


            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);



        }
        //if deleting was successful
        catch (Exception ex)
        {
            Toast.makeText(this,"Record Deleting Failed",Toast.LENGTH_LONG).show();
        }



    }


    //edit product
    public void Edit() {
        try {
            //get information from EditText
            String id = pid.getText().toString();
            String proname = pname.getText().toString();
            String category = spinnercat.getSelectedItem().toString();
            String supplier = spinnersup.getSelectedItem().toString();
            String qty = proqty.getText().toString();
            String price = proprice.getText().toString();
            //access database
            SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);
            //create rows
            String sql = "update product set proname = ?,category=?,supplier = ?,qty = ?,price = ? where id= ?";
            SQLiteStatement statement = db.compileStatement(sql);
            //attach variables to statement and execute
            statement.bindString(1, proname);
            statement.bindString(2, category);
            statement.bindString(3, supplier);
            statement.bindString(4, qty);
            statement.bindString(5, price);
            statement.bindString(6, id);
            statement.execute();
            //if successful
            Toast.makeText(this, "Record Updated", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }
        //if failed
        catch (Exception ex) {
            Toast.makeText(this, "Updating Record Failed", Toast.LENGTH_LONG).show();
        }


    }

}







