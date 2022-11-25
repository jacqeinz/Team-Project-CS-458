//Jacqueline Chavez Ayana Jackson
//Mobile App Development
//Inventory Tracker
package com.example.inventorytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class supplieredit extends AppCompatActivity {
    //variables
    EditText supid, supn, supd;
    Button edit, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplieredit);
        //link UI compoenents
        supid = findViewById(R.id.supplierid);
        supn = findViewById(R.id.supname);
        supd  = findViewById(R.id.supdesc);
        edit = findViewById(R.id.editsup);
        delete = findViewById(R.id.deletesup);
        //get data sent from previous activity
        Intent intent = getIntent();

        String id = intent.getStringExtra("id").toString();
        String name = intent.getStringExtra("supplier").toString();
        String desc = intent.getStringExtra("description").toString();
        //set UI components to imported data
        supid.setText(id);
        supn.setText(name);
        supd.setText(desc);
        edit.setOnClickListener(v -> Edit());
        delete.setOnClickListener(v -> Delete());

    }
    //delete supplier
    public void Delete()
    {
        try
        {
            //get id from editText
            String id = supid.getText().toString();
            //create or open database
            SQLiteDatabase db = openOrCreateDatabase("inventory",Context.MODE_PRIVATE,null);

            //use delete from and link to statement
            String sql = "delete from supplier where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);
            //delete that ID
            statement.bindString(1,id);
            statement.execute();
            Toast.makeText(this,"Record Deleted",Toast.LENGTH_LONG).show();

            //go to mainactivity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);



        }
        //if deleting failed
        catch (Exception ex)
        {
            Toast.makeText(this,"Record Fail",Toast.LENGTH_LONG).show();
        }

    }
    //edit supplier

    public void Edit() {
        try {
            //get input from editText
            String id = supid.getText().toString();
            String supplier = supn.getText().toString();
            String supplierdes = supd.getText().toString();

            //open or create database
            SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);

            //use update
            String sql = "update supplier set supplier = ?,description=? where id= ?";
            //create statement to update supplier
            SQLiteStatement statement = db.compileStatement(sql);
            //bind to statement and execute
            //update
            statement.bindString(1, supplier);
            statement.bindString(2, supplierdes);
            statement.bindString(3,id);
            statement.execute();
            //if succesful
            Toast.makeText(this, "Record Updated", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

        }
        //if not successful
        catch (Exception ex) {
            Toast.makeText(this, "Record Fail", Toast.LENGTH_LONG).show();
        }


    }

}
