//Ayana Jackson & Jacqueline Chavez
//CS 458
//Inventory Tracker
package com.example.inventorytracker;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

        super(context, DB2NAME, null, DB_VERSION);
    }

    //create the table, set table to data types
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + PRODUCT_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_NAME + " TEXT,"
                + AMOUNT_ORDERED + " TEXT," + PRODUCT_CATEGORY + " TEXT," + PRODUCT_SUPPLIER
                + " TEXT," + PRODUCT_COST + " TEXT)";

        //execute query
        db.execSQL(query);
    }
    //shown last
    //upgrade
    //check if table exists


    //add inventory
    public void addInvOrder(String productName, String amountOrdered, String productCategory, String productSupplier, String productCost) {
        //writable method
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();
        //pass values
        value.put(PRODUCT_NAME, productName);
        value.put(AMOUNT_ORDERED, amountOrdered);
        value.put(PRODUCT_CATEGORY, productCategory);
        value.put(PRODUCT_SUPPLIER, productSupplier);
        value.put(PRODUCT_COST, productCost);
        //insert values in table after passing then close db
        db.insert(TABLE_NAME, null, value);
        db.close();

    }



    //method to read all
    public ArrayList<InvOrder> readInvOrder(){
        //create a db to read db
        SQLiteDatabase db = this.getReadableDatabase();
        //cursor with query to read data
        Cursor cursorInvOrder = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        //new arraylist
        ArrayList<InvOrder> invOrderArrayList = new ArrayList<>();

        //move cursor
        if (cursorInvOrder.moveToFirst()){
            do{
                invOrderArrayList.add(new InvOrder(cursorInvOrder.getString(1), cursorInvOrder.getString(2),
                        cursorInvOrder.getString(3), cursorInvOrder.getString(4), cursorInvOrder.getString(5)));
            }while (cursorInvOrder.moveToNext());
            //move cursor to next
        }
        //return arraylist  and read
        cursorInvOrder.close();
        return invOrderArrayList;
    }


    //update method
    public void updateInvOrder(String productName, String amountOrdered, String productCategory, String productSupplier, String productCost) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(PRODUCT_NAME, productName);
        value.put(AMOUNT_ORDERED, amountOrdered);
        value.put(PRODUCT_CATEGORY, productCategory);
        value.put(PRODUCT_SUPPLIER, productSupplier);
        value.put(PRODUCT_COST, productCost);

        //calling update method
        db.update(TABLE_NAME,value, "name=?", new String[]{productName});}
    //method to delete date
    public void deleteOrder(String productName) {
        SQLiteDatabase db = this.getWritableDatabase();

        //call delete method
        db.delete(TABLE_NAME,"name=?", new String[]{productName});
        db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + TABLE_NAME);

        //create table again
        onCreate(db);

    }

}