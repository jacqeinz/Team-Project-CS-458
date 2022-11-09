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
    private Spinner spinner;
    private Spinner spinner1;
    ArrayAdapter arrayAdapter;
    ArrayAdapter arrayAdapter1;
    EditText ed1, ed2, ed3, ed4, ed5, ed6;
    Button b1, b2;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editproduct);


        ed1 = findViewById(R.id.pid);
        ed2 = findViewById(R.id.pname);
        ed5 = findViewById(R.id.cateid);
        ed6 = findViewById(R.id.supid);
        ed3 = findViewById(R.id.proqty);
        ed4 = findViewById(R.id.proprice);
        b1 = findViewById(R.id.addsup);
        b2 = findViewById(R.id.delete);

        Intent i = getIntent();
        String t1 = i.getStringExtra("id");
        String t2 = i.getStringExtra("product");
        String t3 = i.getStringExtra("category");

        String t4 = i.getStringExtra("supplier");
        String t5 = i.getStringExtra("qty");
        String t6 = i.getStringExtra("price");

        ;


        ed1.setText(t1);
        ed2.setText(t2);
        ed5.setText(t3);
        ed6.setText(t4);
       ed3.setText(t5);
       ed4.setText(t6);


        
        b2.setOnClickListener(v -> Delete());


        b1.setOnClickListener(v -> Edit());


    }



    public void Delete()
    {
        try
        {
            String id = ed1.getText().toString();

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
            String id = ed1.getText().toString();
            String product = ed2.getText().toString();
            String productdes = ed3.getText().toString();


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







