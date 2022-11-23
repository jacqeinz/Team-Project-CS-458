//Jacqueline Chavez Ayana Jackson
//Mobile App Development
//Inventory Tracker
package com.example.inventorytracker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class viewSuppliers extends AppCompatActivity {
    //list varialbes
    ListView Suppliers;
    ArrayList<String> sups = new ArrayList<String>();
    ArrayAdapter arrayAdapterSup;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_suppliers2);

        //open or create database
        SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);
        //link UI
        Suppliers = findViewById(R.id.view);
        //make cursor to loop
        final Cursor c = db.rawQuery("select * from supplier", null);
        //get columnindex of suppliers
        int id = c.getColumnIndex("id");
        int supplier = c.getColumnIndex("supplier");
        int description = c.getColumnIndex("description");

        sups.clear();

        //set arrayadapter to arraylist sups
        arrayAdapterSup = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, sups);
        //set listview to arrayadapter
        Suppliers.setAdapter(arrayAdapterSup);
        //create object
        final ArrayList<supplier> supps = new ArrayList<supplier>();

        //lopp through rows and add to arraylist
        if (c.moveToFirst()) {
            do {

                supplier sup = new supplier();
                sup.id = c.getString(id);
                sup.supplier = c.getString(supplier);
                sup.description = c.getString(description);


                supps.add(sup);


                sups.add(c.getString(id) + " \t " + c.getString(supplier) + " \t " + c.getString(description));


            } while (c.moveToNext());
            arrayAdapterSup.notifyDataSetChanged();
            Suppliers.invalidateViews();


        }
        //if any suppliers is clicked
        Suppliers.setOnItemClickListener((parent, view, position, id1) -> {
            //make object and send data to supplieredit
            supplier supp = supps.get(position);
            Intent intent = new Intent(this, supplieredit.class);
            intent.putExtra("id", supp.id);
            intent.putExtra("supplier", supp.supplier);
            intent.putExtra("description", supp.description);
            startActivity(intent);
        });
    }
}

