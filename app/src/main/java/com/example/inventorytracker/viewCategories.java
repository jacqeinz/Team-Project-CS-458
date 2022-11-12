//Jacqueline Chavez Ayana Jackson
//Mobile App Development
//Inventory Tracker
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
    ListView lstCate;
    ArrayList<String> cates = new ArrayList<String>();
    ArrayAdapter arrayAdapterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_categories);

        SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);

        lstCate = findViewById(R.id.viewcat);
        final Cursor c = db.rawQuery("select * from category", null);
        int id = c.getColumnIndex("id");
        int category = c.getColumnIndex("category");
        int description = c.getColumnIndex("description");

        cates.clear();
        arrayAdapterView = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, cates);
        lstCate.setAdapter(arrayAdapterView);
        final ArrayList<category> cat = new ArrayList<com.example.inventorytracker.category>();
        if (c.moveToFirst()) {
            do {
                category cate = new category();
                cate.id = c.getString(id);
                cate.category = c.getString(category);
                cate.description = c.getString(description);
                cat.add(cate);
                cates.add(c.getString(id) + " \t " + c.getString(category) + " \t " + c.getString(description));

            } while (c.moveToNext());
            arrayAdapterView.notifyDataSetChanged();
            lstCate.invalidateViews();
        }
        lstCate.setOnItemClickListener((parent, view, position, id1) -> {


            category cate = cat.get(position);
            Intent intent = new Intent(this, editCategory.class);
            intent.putExtra("id", cate.id);
            intent.putExtra("category", cate.category);
            intent.putExtra("description", cate.description);
            startActivity(intent);
        });
    }
}


