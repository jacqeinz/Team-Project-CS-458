//Jacqueline Chavez Ayana Jackson
//Mobile App Development
//Inventory Tracker
package com.example.inventorytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class viewSales extends AppCompatActivity {
    ListView viewSales;
    ArrayList<String> sales = new ArrayList<String>();
    ArrayAdapter arrayAdapterSale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sales);

        SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);

        viewSales = findViewById(R.id.view);
        final Cursor c = db.rawQuery("select * from sales",null);
        int proid = c.getColumnIndex("proid");
        int proname = c.getColumnIndex("proname");
        int qty = c.getColumnIndex("qty");
        int price = c.getColumnIndex("price");
        int total = c.getColumnIndex("total");
        sales.clear();


        arrayAdapterSale = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,sales);
        viewSales.setAdapter(arrayAdapterSale);

        final  ArrayList<products> products1 = new ArrayList<products>();

        //loop through rows and add to arraylist
        if(c.moveToFirst())
        {
            do{
                products pro = new products();
                pro.id = c.getString(proid);
                pro.product = c.getString(proname);
                pro.qty= c.getString(qty);
                pro.price = c.getString(price);
                pro.total = c.getString(total);
                products1.add(pro);
                sales.add(c.getString(proid) + " \t " + c.getString(proname) + " \t "  + c.getString(qty)   + " \t " + c.getString(price) + " \t " + c.getString(total));

            }
            while(c.moveToNext());
            arrayAdapterSale.notifyDataSetChanged();
            viewSales.invalidateViews();
        }

    }
}



