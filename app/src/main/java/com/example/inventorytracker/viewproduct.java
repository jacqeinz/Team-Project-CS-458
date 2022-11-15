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

public class viewproduct extends AppCompatActivity {
    ListView lstPro;
    ArrayList<String> pros = new ArrayList<String>();
    ArrayAdapter arrayAdapterPro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewproduct);


        SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);

        lstPro = findViewById(R.id.view);
        final Cursor c = db.rawQuery("select * from product", null);
        int id = c.getColumnIndex("id");
        int product = c.getColumnIndex("proname");
        int category = c.getColumnIndex("category");
        int supplier = c.getColumnIndex("supplier");
        int qty = c.getColumnIndex("qty");
        int price = c.getColumnIndex("price");
        pros.clear();


        arrayAdapterPro = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, pros);
        lstPro.setAdapter(arrayAdapterPro);

        final ArrayList<products> productslist = new ArrayList<products>();


        if (c.moveToFirst()) {
            do {

                products pro = new products();
                pro.id = c.getString(id);
                pro.product = c.getString(product);
                pro.category = c.getString(category);
                pro.supplier = c.getString(supplier);
                pro.qty = c.getString(qty);
                pro.price = c.getString(price);
                productslist.add(pro);
                pros.add(c.getString(id) + " \t " + c.getString(product) + " \t " + c.getString(category) + " \t " + c.getString(supplier) + " \t " + c.getString(qty) + " \t " + c.getString(price));

            } while (c.moveToNext());
            arrayAdapterPro.notifyDataSetChanged();
            lstPro.invalidateViews();


        }

        lstPro.setOnItemClickListener((parent, view, position, id1) -> {


            products pro = productslist.get(position);
            Intent intent = new Intent(this, editProduct.class);
            intent.putExtra("id", pro.id);
            intent.putExtra("product", pro.product);
            intent.putExtra("category", pro.category);
            intent.putExtra("supplier", pro.supplier);
            intent.putExtra("qty", pro.qty);
            intent.putExtra("price", pro.price);

            startActivity(intent);
        });

    }
}

