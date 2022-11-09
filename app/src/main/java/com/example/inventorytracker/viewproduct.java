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
    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewproduct);


        SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);

        lst1 = findViewById(R.id.view);
        final Cursor c = db.rawQuery("select * from product", null);
        int id = c.getColumnIndex("id");
        int product = c.getColumnIndex("proname");
        int category = c.getColumnIndex("category");
        int supplier = c.getColumnIndex("supplier");
        int qty = c.getColumnIndex("qty");
        int price = c.getColumnIndex("price");
        titles.clear();


        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, titles);
        lst1.setAdapter(arrayAdapter);

        final ArrayList<productview1> product1 = new ArrayList<productview1>();


        if (c.moveToFirst()) {
            do {

                productview1 stu = new productview1();
                stu.id = c.getString(id);
                stu.product = c.getString(product);
                stu.category = c.getString(category);
                stu.supplier = c.getString(supplier);
                stu.qty = c.getString(qty);
                stu.price = c.getString(price);
                product1.add(stu);
                titles.add(c.getString(id) + " \t " + c.getString(product) + " \t " + c.getString(category) + " \t " + c.getString(supplier) + " \t " + c.getString(qty) + " \t " + c.getString(price));

            } while (c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();


        }

        lst1.setOnItemClickListener((parent, view, position, id1) -> {

            String aa = titles.get(position);
            productview1 stu = product1.get(position);
            Intent intent = new Intent(this, editProduct.class);
            intent.putExtra("id", stu.id);
            intent.putExtra("product", stu.product);
            intent.putExtra("category", stu.category);
            intent.putExtra("supplier", stu.supplier);
            intent.putExtra("qty", stu.qty);
            intent.putExtra("price", stu.price);

            startActivity(intent);
        });

    }
}

