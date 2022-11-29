package com.example.inventorytracker;



import android.content.ContentValues;
import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper2 extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "myinventoryorder";
    private static final String PRODUCT_ID = "productID";
    private static final String PRODUCT_NAME = "productName";
    private static final String AMOUNT_ORDERED = "amountOrdered";
    private static final String PRODUCT_CATEGORY = "productCategory";
    private static final String PRODUCT_SUPPLIER = "productSupplier";
    private static final String PRODUCT_COST = "productCost";

    public static final String DB2NAME = "InventoryOrder.db";
    //constructor
    public DBHelper2(Context context) {

        super(context, DB2NAME, null, 1);
    }
//create the table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + PRODUCT_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_NAME + " TEXT,"
                + AMOUNT_ORDERED + " TEXT," + PRODUCT_CATEGORY + " TEXT," + PRODUCT_SUPPLIER
                + " TEXT," + PRODUCT_COST + " TEXT)";

        //execute query
        db.execSQL(query);
    }
//upgrade
    //check if table exists
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + TABLE_NAME);

        //create table again
        onCreate(db);

    }

    public void addInvOrder(String productName, String amountOrdered, String productCategory, String productSupplier, String productCost){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();

        value.put(PRODUCT_NAME, productName);
        value.put(AMOUNT_ORDERED, amountOrdered);
        value.put(PRODUCT_CATEGORY, productCategory);
        value.put(PRODUCT_SUPPLIER, productSupplier);
        value.put(PRODUCT_COST, productCost);

        //insert data in row
        //close database after adding data
        db.insert(TABLE_NAME, null, value );
        db.close();

    }

}
