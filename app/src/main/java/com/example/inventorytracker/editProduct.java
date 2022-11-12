
//Jacqueline Chavez Ayana Jackson
//Mobile App Development
//Inventory Tracker
package com.example.inventorytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;


public class editProduct extends AppCompatActivity {
    private Spinner spinnercat;
    private Spinner spinnersup;
    ArrayAdapter arrayAdaptercat;
    ArrayAdapter arrayAdaptersup;
    EditText pid, pname, cateid, supid, proqty, proprice;
    Button edit, delete;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editproduct);


        pid = findViewById(R.id.pid);
        pname = findViewById(R.id.pname);
        cateid = findViewById(R.id.cateid);
        supid = findViewById(R.id.supid);
        proqty = findViewById(R.id.proqty);
        proprice = findViewById(R.id.proprice);
         edit = findViewById(R.id.addsup);
        delete = findViewById(R.id.delete);

        Intent i = getIntent();
        String t1 = i.getStringExtra("id");
        String t2 = i.getStringExtra("products");
        String t3 = i.getStringExtra("category");

        String t4 = i.getStringExtra("addSupplier");
        String t5 = i.getStringExtra("qty");
        String t6 = i.getStringExtra("price");

        ;



        
        edit.setOnClickListener(v -> Delete());


        delete.setOnClickListener(v -> Edit());


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
            Toast.makeText(this,"Record Fail",Toast.LENGTH_LONG).show();
        }



    }






    public void Edit() {
        try {
            String id = pid.getText().toString();
            String product = pname.getText().toString();
            String productdes = cateid.getText().toString();


            SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);


            String sql = "update product set product = ?,description=? where id= ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, product);
            statement.bindString(2, productdes);
            statement.bindString(3,id);
            statement.execute();
            Toast.makeText(this, "Record Updated", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        } catch (Exception ex) {
            Toast.makeText(this, "Record Fail", Toast.LENGTH_LONG).show();
        }


    }

}







