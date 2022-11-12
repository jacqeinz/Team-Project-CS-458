//Jacqueline Chavez Ayana Jackson
//Mobile App Development
//Inventory Tracker
package com.example.inventorytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class supplier extends AppCompatActivity {


    EditText sup,desc;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier);

        sup = findViewById(R.id.supID);
        desc = findViewById(R.id.supplierdesc);

        add = findViewById(R.id.addsup);


        add.setOnClickListener(v -> insert());
    }
    public void insert() {
        try {
            String supplier = sup.getText().toString();
            String description = desc.getText().toString();


            SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS supplier(id INTEGER PRIMARY KEY AUTOINCREMENT,supplier VARCHAR,description VARCHAR)");

            String sql = "insert into supplier(supplier,description)values(?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, supplier);
            statement.bindString(2, description);

            statement.execute();
            Toast.makeText(this, "Supplier Added", Toast.LENGTH_LONG).show();


            sup.setText("");
            desc.setText("");

            sup.requestFocus();


        } catch (Exception ex) {
            Toast.makeText(this, "Adding Record Failed", Toast.LENGTH_LONG).show();
        }

    }


}
