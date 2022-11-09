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
    EditText ed1,ed2,ed3;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplieredit);
        ed1 = findViewById(R.id.supplierid);
        ed2 = findViewById(R.id.supname);
        ed3  = findViewById(R.id.supdesc);
        b1 = findViewById(R.id.editsup);
        b2 = findViewById(R.id.deletesup);

        Intent i = getIntent();

        String t1 = i.getStringExtra("id").toString();
        String t2 = i.getStringExtra("supplier").toString();
        String t3 = i.getStringExtra("description").toString();

        ed1.setText(t1);
        ed2.setText(t2);
        ed3.setText(t3);

        b2.setOnClickListener(v -> Delete());




        b1.setOnClickListener(v -> Edit());




    }

    public void Delete()
    {
        try
        {
            String id = ed1.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("inventory",Context.MODE_PRIVATE,null);


            String sql = "delete from supplier where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1,id);
            statement.execute();
            Toast.makeText(this,"Record Deleted",Toast.LENGTH_LONG).show();


            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);



        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Record Fail",Toast.LENGTH_LONG).show();
        }



    }






    public void Edit() {
        try {
            String id = ed1.getText().toString();
            String supplier = ed2.getText().toString();
            String supplierdes = ed3.getText().toString();


            SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);


            String sql = "update supplier set supplier = ?,description=? where id= ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, supplier);
            statement.bindString(2, supplierdes);
            statement.bindString(3,id);
            statement.execute();
            Toast.makeText(this, "Record Updated", Toast.LENGTH_LONG).show();

            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);

        } catch (Exception ex) {
            Toast.makeText(this, "Record Fail", Toast.LENGTH_LONG).show();
        }


    }

}
