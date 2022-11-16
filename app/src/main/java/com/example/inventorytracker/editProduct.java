
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

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String pro = intent.getStringExtra("product");
        String categ = intent.getStringExtra("category");

        String supp = intent.getStringExtra("supplier");
        String qty = intent.getStringExtra("qty");
        String price = intent.getStringExtra("price");

        pid.setText(id);
        pname.setText(pro);

        proqty.setText(qty);
        proprice.setText(price);
        //open or create database
        SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);
        //category spinner
        final Cursor c = db.rawQuery("select category from category",null);
        int category = c.getColumnIndex("category");
        cats.clear();
        arrayAdaptercat = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,cats);
        spinnercat.setAdapter(arrayAdaptercat);

        final ArrayList<category> cates = new ArrayList<com.example.inventorytracker.category>();
        if(c.moveToFirst()) {
            do {
                category cate = new category();
                cate.category = c.getString(category);
                cates.add(cate);
                cats.add(c.getString(category) );

            } while (c.moveToNext());
            arrayAdaptercat.notifyDataSetChanged();

        }
        //Supplier spinner
        final Cursor b = db.rawQuery("select supplier from supplier",null);
        int supplier = b.getColumnIndex("supplier");
        sups.clear();
        arrayAdaptersup= new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,sups);
        spinnersup.setAdapter(arrayAdaptersup);
        final  ArrayList<supplier> suppliers = new ArrayList<com.example.inventorytracker.supplier>();

        if(b.moveToFirst()) {
            do {
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



    public void Delete()
    {
        try
        {
            String id = pid.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("inventory",Context.MODE_PRIVATE,null);


            String sql = "delete from product where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1,id);
            statement.execute();
            Toast.makeText(this,"Record Deleted",Toast.LENGTH_LONG).show();


            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);



        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Record Deleting Failed",Toast.LENGTH_LONG).show();
        }



    }






    public void Edit() {
        try {
            String id = pid.getText().toString();
            String proname = pname.getText().toString();
            String category = spinnercat.getSelectedItem().toString();
            String supplier = spinnersup.getSelectedItem().toString();
            String qty = proqty.getText().toString();
            String price = proprice.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);
            String sql = "update product set proname = ?,category=?,supplier = ?,qty = ?,price = ? where id= ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, proname);
            statement.bindString(2, category);
            statement.bindString(3, supplier);
            statement.bindString(4, qty);
            statement.bindString(5, price);
            statement.bindString(6, id);
            statement.execute();
            Toast.makeText(this, "Record Updated", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        } catch (Exception ex) {
            Toast.makeText(this, "Updating Record Failed", Toast.LENGTH_LONG).show();
        }


    }

}







