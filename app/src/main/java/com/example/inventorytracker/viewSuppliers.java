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

    ListView Suppliers;
    ArrayList<String> sups = new ArrayList<String>();
    ArrayAdapter arrayAdapterSup;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_suppliers2);


        SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);

        Suppliers = findViewById(R.id.view);
        final Cursor c = db.rawQuery("select * from supplier", null);
        int id = c.getColumnIndex("id");
        int supplier = c.getColumnIndex("addSupplier");
        int description = c.getColumnIndex("description");

        sups.clear();


        arrayAdapterSup = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, sups);
        Suppliers.setAdapter(arrayAdapterSup);

        final ArrayList<supplier> cat = new ArrayList<com.example.inventorytracker.supplier>();


        if (c.moveToFirst()) {
            do {

                supplier sup = new supplier();

                sup.supplier = c.getString(supplier);
                sup.description = c.getString(description);


                cat.add(sup);


                sups.add(c.getString(id) + " \t " + c.getString(supplier) + " \t " + c.getString(description));


            } while (c.moveToNext());
            arrayAdapterSup.notifyDataSetChanged();
            Suppliers.invalidateViews();


        }

        Suppliers.setOnItemClickListener((parent, view, position, id1) -> {

            supplier supp = cat.get(position);
            Intent intent = new Intent(getApplicationContext(), supplieredit.class);
            intent.putExtra("id", supp.id);
            intent.putExtra("addSupplier", supp.supplier);
            intent.putExtra("description", supp.description);
            startActivity(intent);
        });
    }
}

