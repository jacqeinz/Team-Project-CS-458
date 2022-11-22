package com.example.inventorytracker;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper2 extends SQLiteOpenHelper {
    private static final String PRODUCT_ID = "productID";
    private static final String PRODUCT_NAME = "productName";
    private static final String AMOUNT_ORDERED = "amountOrdered";
    private static final String PRODUCT_CATEGORY = "productCategory";
    private static final String PRODUCT_SUPPLIER = "productSupplier";
    private static final String PRODUCT_COST = "productCost";

    public static final String DB2NAME = "Inventory.db";
    public DBHelper2(Context context) {

        super(context, "inventory.db", null, 1);
    }
//create the table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create Table inventoryOrder(PRODUCTID TEXT primary key, PRODUCTNAME TEXT, amountOrdered TEXT, productCategory TEXT, productSupplier TEXT, productCost TEXT)");

    }
//upgrade
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists intventoryorder");

        //create table again
        onCreate(sqLiteDatabase);

    }

    void addInvOrder(Inventory inventory){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(PRODUCT_ID, inventory.getProductID());
        value.put(PRODUCT_NAME, inventory.getProductName());
        value.put(PRODUCT_CATEGORY, inventory.getProductCategory());
        value.put(PRODUCT_SUPPLIER, inventory.getProductSupplier());
        value.put(PRODUCT_COST, inventory.getProductCost());

        //insert data in row
        db.insert(DB2NAME, null, value );
        db.close();

    }

    //for list view
    public List<Inventory> getAllOrders(){
        List<Inventory> orderList = new ArrayList<>();
        //query to select all
        String selectQuery = "SELECT * FROM " + DB2NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping and adding to list
        if(cursor.moveToFirst()){
            do{
                Inventory order = new Inventory();
                order.setProductID(Integer.parseInt(cursor.getString(0)));
                order.setProductName(cursor.getString(1));
                order.setAmountOrdered(cursor.getString(2));
                order.setProductCategory(cursor.getString(3));
                order.setProductSupplier(cursor.getString(4));
                order.setProductCost(Double.parseDouble(cursor.getString(5)));

                //adding order to list
                orderList.add(order);
            }while (cursor.moveToNext());
        }
        return orderList;

    }
}
