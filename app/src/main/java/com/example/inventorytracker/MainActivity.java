//Jacqueline Chavez Ayana Jackson
//Mobile App Development
//Inventory Tracker
package com.example.inventorytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity {
    TextView MainTitle;
    Button addPro, AddInv, addSuppplier, addCategory, viewCat, viewSuppliers, viewInventory, addS, viewSales, addUser, loginU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainTitle = findViewById(R.id.MainTitle);

        if(!checkIfDataExists()) {
            createDatabases();
            createSalesDatabase();
            createCategoryDatabase();
            createSupplierDatabase();


        }

    }
    //main menu
    public void goToLogin(View view){
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }
    public void goToAddUser(View view){
        Intent intent = new Intent(this, addUser.class);
        startActivity(intent);
    }
    public void goToViewSales (View view){
        Intent intent = new Intent(this, viewSales.class);
        startActivity(intent);
    }
    public void goToAddSale (View view){
        Intent intent = new Intent(this, addSale.class);
        startActivity(intent);
    }
    public void goToViewInventory(View view){
        Intent intent = new Intent(this, viewproduct.class);
        startActivity(intent);
    }
    public void goToViewSuppliers(View view){
        Intent intent = new Intent(this, viewSuppliers.class);
        startActivity(intent);
    }
    public void goToViewCategories(View view){
        Intent intent = new Intent(this, viewCategories.class);
        startActivity(intent);
    }
    public void goToAddCategory(View view){
        Intent intent = new Intent(this, addCategory.class);
        startActivity(intent);
    }
    public void goToAddSupplier(View view){
        Intent intent = new Intent(this, addSupplier.class);
        startActivity(intent);
    }
    public void goToAddInventoryOrder(View view){
        Intent intent = new Intent(this, addInventoryOrder.class);
        startActivity(intent);
    }
    public void goToViewInventoryOrders(View view){
        Intent intent = new Intent(this, viewInventoryOrders.class);
        startActivity(intent);
    }
    public void goToAddProduct(View view){
        Intent intent = new Intent(this, addProduct.class);
        startActivity(intent);
    }
    private boolean checkIfDataExists(){
        SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='product'", null);
        boolean result = cursor.getCount() > 0;
        db.close();
        return result;

    }
    private void createDatabases(){
        SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS product(id INTEGER PRIMARY KEY AUTOINCREMENT,proname VARCHAR,category VARCHAR,supplier VARCHAR,qty VARCHAR,price VARCHAR)");
        //select from category to check if anything is here. if so, return
        String sql = "insert into product(proname,category,supplier,qty,price)values(?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.bindString(1, "band-aids");
        statement.bindString(2, "blue");
        statement.bindString(3, "Johnson & Johnson");
        statement.bindString(4, "20");
        statement.bindString(5, "10.99");
        statement.execute();
        db.execSQL("CREATE TABLE IF NOT EXISTS category(id INTEGER PRIMARY KEY AUTOINCREMENT,category VARCHAR,description VARCHAR)");




        db.close();
    }
    private void createCategoryDatabase(){
        //select from category to check if anything is here. if so, return
        SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);

        String sql2 = "insert into category(category,description)values(?,?)";
        SQLiteStatement statement2 = db.compileStatement(sql2);
        statement2.bindString(1, "blue");
        statement2.bindString(2, "Categorized by the color blue.");
        statement2.execute();
        db.execSQL("CREATE TABLE IF NOT EXISTS supplier(id INTEGER PRIMARY KEY AUTOINCREMENT,supplier VARCHAR,description VARCHAR)");
        db.close();

    }
    private void createSupplierDatabase(){
        //select from addSupplier to check if anything is here. if so, return
        SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS supplier(id INTEGER PRIMARY KEY AUTOINCREMENT,supplier VARCHAR,description VARCHAR)");
        String sql3 = "insert into supplier(supplier,description)values(?,?)";
        SQLiteStatement statement3 = db.compileStatement(sql3);
        statement3.bindString(1, "Johnson & Johnson");
        statement3.bindString(2, "One Johnson & Johnson Plaza\n" +
                "\n" +
                "New Brunswick, New Jersey 08933\n" +
                "\n" +
                "(732) 524-0400");
        statement3.execute();
        db.close();

    }
    private void createSalesDatabase(){
        //select from sales to check if anything is there
        SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS sales(proid VARCHAR,proname VARCHAR,qty VARCHAR,price VARCHAR,total VARCHAR)");
        String sql4 = "insert into sales(proid,proname,qty,price,total)values(?,?,?,?,?)";
        SQLiteStatement statement4 = db.compileStatement(sql4);
        statement4.bindString(1, "1");
        statement4.bindString(2, "band-aids");
        statement4.bindLong(3, 10);
        statement4.bindString(4, "10.99");
        statement4.bindString(5, "21.98");
        statement4.execute();
        db.close();


    }

}