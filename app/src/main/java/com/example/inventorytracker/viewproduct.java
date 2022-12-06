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
/**
 * <h1>View Product</h1>
 * This activity takes in the database of the inventory
 * and displays it
 * <p>
 *
 * @author Jacqueline chavez
 * @version 1.4
 * @since   Fall 2022
 */
public class viewproduct extends AppCompatActivity {
    //list variables
    ListView lstPro;
    ArrayList<String> pros = new ArrayList<String>();
    ArrayAdapter arrayAdapterPro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewproduct);

        //create or open database
        SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);
        //link UI
        lstPro = findViewById(R.id.view);
        //make cursor to loop through database
        final Cursor c = db.rawQuery("select * from product", null);
        //get column index for attributes
        int id = c.getColumnIndex("id");
        int product = c.getColumnIndex("proname");
        int category = c.getColumnIndex("category");
        int supplier = c.getColumnIndex("supplier");
        int qty = c.getColumnIndex("qty");
        int price = c.getColumnIndex("price");
        pros.clear();

        //create arrayadapter with pros arraylist
        arrayAdapterPro = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, pros);
        //set listview to arrayadapter
        lstPro.setAdapter(arrayAdapterPro);
        //make arraylist of type products
        final ArrayList<products> productslist = new ArrayList<products>();

        //loop through database
        if (c.moveToFirst()) {
            do {
                //make object of type products
                products pro = new products();
                //set variables of object
                pro.id = c.getString(id);
                pro.product = c.getString(product);
                pro.category = c.getString(category);
                pro.supplier = c.getString(supplier);
                pro.qty = c.getString(qty);
                pro.price = c.getString(price);
                //add object to arraylist
                productslist.add(pro);
                //add to pros arraylist
                pros.add(c.getString(id) + " \t " + c.getString(product) + " \t " + c.getString(category) + " \t " + c.getString(supplier) + " \t " + c.getString(qty) + " \t " + c.getString(price));

            } while (c.moveToNext());
            //tell arrayadapter to refresh
            arrayAdapterPro.notifyDataSetChanged();
            //force database to rebuild
            lstPro.invalidateViews();


        }
        //if any product is selected
        lstPro.setOnItemClickListener((parent, view, position, id1) -> {

           //make object and send data to editProduct
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

