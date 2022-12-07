//Ayana Jackson & Jacqueline Chavez
//CS 458
//Inventory Tracker
package com.example.inventorytracker;

import static android.os.Build.ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;

/**
 * @author Ayana Jackson
 */

//declare db table to hold data of user input
//constant variables for db
public class DBHelper2 extends SQLiteOpenHelper {
    //variables for columns of db
    private static final String TABLE_NAME = "myinventoryorder";
    private static final String PRODUCT_ID = "productID";
    private static final String PRODUCT_NAME = "productName";
    private static final String AMOUNT_ORDERED = "amountOrdered";
    private static final String PRODUCT_CATEGORY = "productCategory";
    private static final String PRODUCT_SUPPLIER = "productSupplier";
    private static final String PRODUCT_COST = "productCost";
    private static final int DB_VERSION = 1;
    //name of db
    public static final String DB2NAME = "InventoryOrder.db";

    //constructor
    public DBHelper2(Context context) {

        super(context,DB2NAME, null, DB_VERSION);
    }

    //create the table, set table to data types
    @Override
    public void onCreate(SQLiteDatabase db) {
        String table_query = "CREATE TABLE " + TABLE_NAME + " (" + PRODUCT_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT," + PRODUCT_NAME + " TEXT,"
                + AMOUNT_ORDERED + " TEXT," + PRODUCT_CATEGORY + " TEXT," + PRODUCT_SUPPLIER
                + " TEXT," + PRODUCT_COST + " TEXT )";

        //execute query
        db.execSQL(table_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    //shown last
    //upgrade
    //check if table exists


    //add inventory
    void addInvOrder(String productName, String amountOrdered, String productCategory, String productSupplier,
                     String productCost) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_NAME, productName);
        contentValues.put(AMOUNT_ORDERED, amountOrdered);
        contentValues.put(PRODUCT_CATEGORY, productCategory);
        contentValues.put(PRODUCT_SUPPLIER, productSupplier);
        contentValues.put(PRODUCT_COST, productCost);
        db.insert(TABLE_NAME,null, contentValues);
        db.close();

    }

    public ArrayList<InvOrder> readOrders(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + TABLE_NAME, null);

        ArrayList<InvOrder> invOrderArrayList = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do{
                invOrderArrayList.add(new InvOrder(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)));
            } while (cursor.moveToNext());

            }
        cursor.close();
        return invOrderArrayList;
        }

  }  }
 }
}

