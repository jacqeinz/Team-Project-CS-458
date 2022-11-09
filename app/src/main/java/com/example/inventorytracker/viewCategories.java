package com.example.inventorytracker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class viewCategories extends AppCompatActivity {
    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_categories);

        SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);

        lst1 = findViewById(R.id.viewcat);
        final Cursor c = db.rawQuery("select * from category", null);
        int id = c.getColumnIndex("id");
        int category = c.getColumnIndex("category");
        int description = c.getColumnIndex("description");

        titles.clear();
        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, titles);
        lst1.setAdapter(arrayAdapter);
        final ArrayList<categoryVariables> cat = new ArrayList<categoryVariables>();
        if (c.moveToFirst()) {
            do {
                categoryVariables stu = new categoryVariables();
                stu.id = c.getString(id);
                stu.category = c.getString(category);
                stu.description = c.getString(description);
                cat.add(stu);
                titles.add(c.getString(id) + " \t " + c.getString(category) + " \t " + c.getString(description));

            } while (c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();
        }
        lst1.setOnItemClickListener((parent, view, position, id1) -> {

            String aa = titles.get(position);
            categoryVariables stu = cat.get(position);
            Intent intent = new Intent(this, editCategory.class);
            intent.putExtra("id", stu.id);
            intent.putExtra("category", stu.category);
            intent.putExtra("description", stu.description);
            startActivity(intent);
        });
    }
}


