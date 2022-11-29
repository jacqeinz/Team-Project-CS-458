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
    EditText supid, supn, supd, emails, phone, add;
    Button edit, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplieredit);
        //link UI compoenents
        supid = findViewById(R.id.supplierid);
        supn = findViewById(R.id.supn);
        supd  = findViewById(R.id.supdesc);
        emails = findViewById(R.id.emailE);
        phone = findViewById(R.id.phoneNumberE);
        add = findViewById(R.id.addressE);
        edit = findViewById(R.id.editsup);
        delete = findViewById(R.id.deletesup);


        //get data sent from previous activity
        Intent intent = getIntent();

        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("supplier");
        String desc = intent.getStringExtra("description");
        String em = intent.getStringExtra("email");
        String number = intent.getStringExtra("phoneNumber");
        String addr = intent.getStringExtra("address");

        //set UI components to imported data
        supid.setText(id);
        supn.setText(name);
        supd.setText(desc);
        emails.setText(em);
        phone.setText(number);
        add.setText(addr);
        //set buttons to functions
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
            String description = supd.getText().toString();
            String email = emails.getText().toString();
            String phoneNumber = phone.getText().toString();
            String address = add.getText().toString();

            //open or create database
            SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);

            //use update
            String sql = "update supplier set supplier = ?,description=?,email = ?,phoneNumber= ?,address = ? where id= ?";
            //create statement to update supplier
            SQLiteStatement statement = db.compileStatement(sql);
            //bind to statement and execute
            //update
            statement.bindString(1, supplier);
            statement.bindString(2, description);
            statement.bindString(3, email);
            statement.bindString(4,phoneNumber);
            statement.bindString(5, address);
            statement.bindString(6, id);
            statement.execute();
            //if succesful
            Toast.makeText(this, "Record Updated", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this , MainActivity.class);
            startActivity(intent);

        }
        //if not successful
        catch (Exception ex) {
            Toast.makeText(this, "Record Fail", Toast.LENGTH_LONG).show();
        }


    }

}
