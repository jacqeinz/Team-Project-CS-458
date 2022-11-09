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

    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_suppliers2);


        SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);

        lst1 = findViewById(R.id.view);
        final Cursor c = db.rawQuery("select * from supplier", null);
        int id = c.getColumnIndex("id");
        int supplier = c.getColumnIndex("supplier");
        int description = c.getColumnIndex("description");

        titles.clear();


        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, titles);
        lst1.setAdapter(arrayAdapter);

        final ArrayList<supplierVariables> cat = new ArrayList<supplierVariables>();


        if (c.moveToFirst()) {
            do {

                supplierVariables stu = new supplierVariables();

                stu.supplier = c.getString(supplier);
                stu.description = c.getString(description);


                cat.add(stu);


                titles.add(c.getString(id) + " \t " + c.getString(supplier) + " \t " + c.getString(description));


            } while (c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();


        }


        lst1.setOnItemClickListener((parent, view, position, id1) -> {

            String aa = titles.get(position).toString();
            supplierVariables stu = cat.get(position);
            Intent i = new Intent(getApplicationContext(), supplieredit.class);
            i.putExtra("id", stu.id);
            i.putExtra("supplier", stu.supplier);
            i.putExtra("description", stu.description);
            startActivity(i);
        });
    }
}

