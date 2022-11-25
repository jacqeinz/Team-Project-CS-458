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
    //varialbes
    ListView viewSales;
    ArrayList<String> sales = new ArrayList<String>();
    ArrayAdapter arrayAdapterSale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sales);

        SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);

        viewSales = findViewById(R.id.view);
        //get information from database
        final Cursor c = db.rawQuery("select * from sales",null);
        //create columns
        int proid = c.getColumnIndex("proid");
        int proname = c.getColumnIndex("proname");
        int qty = c.getColumnIndex("qty");
        int price = c.getColumnIndex("price");
        int total = c.getColumnIndex("total");
        sales.clear();

        //get arrayadapter to display on listview
        arrayAdapterSale = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,sales);
        viewSales.setAdapter(arrayAdapterSale);
        //make object of type products
        final  ArrayList<products> products1 = new ArrayList<products>();

        //loop through rows
        if(c.moveToFirst())
        {
            do{
                //make object
                products pro = new products();
                //set object values
                pro.id = c.getString(proid);
                pro.product = c.getString(proname);
                pro.qty= c.getString(qty);
                pro.price = c.getString(price);
                pro.total = c.getString(total);
                //add to array
                products1.add(pro);
                //add to sales arraylist
                sales.add(c.getString(proid) + " \t " + c.getString(proname) + " \t "  + c.getString(qty)   + " \t " + c.getString(price) + " \t " + c.getString(total));

            }
            while(c.moveToNext());
            //tell arrrayadapter to refresh
            arrayAdapterSale.notifyDataSetChanged();
            //force listview to rebuild
            viewSales.invalidateViews();
        }

    }
}



