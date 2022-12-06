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
/**
 * <h1>View Categories</h1>
 * This activity takes in the database of categories
 * and displays it
 * <p>
 *
 * @author Jacqueline chavez
 * @version 1.4
 * @since   Fall 2022
 */
public class viewCategories extends AppCompatActivity {
    //list variables
    ListView lstCate;
    ArrayList<String> cates = new ArrayList<String>();
    ArrayAdapter arrayAdapterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_categories);
        //Bind UI
        lstCate = findViewById(R.id.viewcat);
        //open or create database
        SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);
        //make rawQuery to database to get categories
        final Cursor c = db.rawQuery("select * from category", null);
        //get column index for all cateogory variables
        int id = c.getColumnIndex("id");
        int category = c.getColumnIndex("category");
        int description = c.getColumnIndex("description");

        cates.clear();
        //set arrayadapter to cates arraylist
        arrayAdapterView = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, cates);
        //set listview to arrayadapter
        lstCate.setAdapter(arrayAdapterView);
        //make arraylist of type cateogory
        final ArrayList<category> cat = new ArrayList<com.example.inventorytracker.category>();
        //looping through rows and add to arraylist
        if (c.moveToFirst()) {
            do {
                //make object of type category
                category cate = new category();
                //get id with cursor
                cate.id = c.getString(id);
                //get cateogory name  with cursor
                cate.category = c.getString(category);
                //get description with cursor
                cate.description = c.getString(description);
                //add object to arraylist
                cat.add(cate);
                //add to category arraylsit
                cates.add(c.getString(id) + " \t " + c.getString(category) + " \t " + c.getString(description));

            } while (c.moveToNext());
            //tell arrayadapter to refresh
            arrayAdapterView.notifyDataSetChanged();
            //force listview to rebuild
            lstCate.invalidateViews();
        }
        //if any categories is clicked
        lstCate.setOnItemClickListener((parent, view, position, id1) -> {

            //create obejct and send data to editCategory
            category cate = cat.get(position);
            Intent intent = new Intent(this, editCategory.class);
            intent.putExtra("id", cate.id);
            intent.putExtra("category", cate.category);
            intent.putExtra("description", cate.description);
            startActivity(intent);
        });
    }
}


