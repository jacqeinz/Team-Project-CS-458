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
    EditText supid, supn, supd;
    Button edit, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplieredit);
        supid = findViewById(R.id.supplierid);
        supn = findViewById(R.id.supname);
        supd  = findViewById(R.id.supdesc);
        edit = findViewById(R.id.editsup);
        delete = findViewById(R.id.deletesup);

        Intent intent = getIntent();

        String id = intent.getStringExtra("id").toString();
        String name = intent.getStringExtra("supplier").toString();
        String desc = intent.getStringExtra("description").toString();

        supid.setText(id);
        supn.setText(name);
        supd.setText(desc);
        edit.setOnClickListener(v -> Edit());
        delete.setOnClickListener(v -> Delete());

    }

    public void Delete()
    {
        try
        {
            String id = supid.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("inventory",Context.MODE_PRIVATE,null);


            String sql = "delete from supplier where id = ?";
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
            String id = supid.getText().toString();
            String supplier = supn.getText().toString();
            String supplierdes = supd.getText().toString();


            SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);


            String sql = "update supplier set supplier = ?,description=? where id= ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, supplier);
            statement.bindString(2, supplierdes);
            statement.bindString(3,id);
            statement.execute();
            Toast.makeText(this, "Record Updated", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

        } catch (Exception ex) {
            Toast.makeText(this, "Record Fail", Toast.LENGTH_LONG).show();
        }


    }

}
