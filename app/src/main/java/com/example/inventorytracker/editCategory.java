package com.example.inventorytracker;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class editCategory extends AppCompatActivity {
    EditText ed1,ed2,ed3;
    Button edit, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);

        ed1 = findViewById(R.id.cateID);
        ed2 = findViewById(R.id.cateName);
        ed3  = findViewById(R.id.catedesc);
        edit = findViewById(R.id.editbtn);
        delete = findViewById(R.id.deletebtn);

        Intent intent = getIntent();

        String t1 = intent.getStringExtra("id");
        String t2 = intent.getStringExtra("category");
        String t3 = intent.getStringExtra("description");

        ed1.setText(t1);
        ed2.setText(t2);
        ed3.setText(t3);

        delete.setOnClickListener(v -> Delete());
        edit.setOnClickListener(v -> Edit());
    }
    public void Delete()
    {
        try
        {
            String id = ed1.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("inventory",Context.MODE_PRIVATE,null);
            String sql = "delete from category where id = ?";
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
            String category = ed2.getText().toString();
            String catdes = ed3.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);
            String sql = "update category set category = ?,description=? where id= ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, category);
            statement.bindString(2, catdes);
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

