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
import android.widget.Button;
import android.widget.TextView;
/**
 * <h1>Main Activity</h1>
 * The MainActivity serves to navigate through the menu
 * <p>
 *
 * @author Jacqueline chavez
 * @version 1.0
 * @since   Fall 2022
 */
public class MainActivity extends AppCompatActivity {
    //declare variables
    TextView MainTitle;
    Button addPro, AddInv, addSuppplier, addCategory, viewCategories, viewSuppliers, viewInventory, addS, viewSales, addUser, loginU, viewInv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //connect UI components
        setContentView(R.layout.activity_main);
        MainTitle = findViewById(R.id.MainTitle);
        addPro = findViewById(R.id.addPro);
        AddInv = findViewById(R.id.AddInv);
        addSuppplier = findViewById(R.id.addSupplier);
        addCategory = findViewById(R.id.addCategory);
        viewCategories = findViewById(R.id.viewCategories);
        viewSuppliers = findViewById(R.id.viewSuppliers);
        viewInventory = findViewById(R.id.viewInventory);
        viewInv = findViewById(R.id.viewInv);
        addS = findViewById(R.id.addS);
        viewSales = findViewById(R.id.viewSales);
        addUser = findViewById(R.id.addUser);
        loginU = findViewById(R.id.loginU);
        //connect buttons to functions
        addPro.setOnClickListener(v -> goToAddProduct());
        AddInv.setOnClickListener(v ->  goToAddInventoryOrder());
        addSuppplier.setOnClickListener(v -> goToAddSupplier());
        addCategory.setOnClickListener(v -> goToAddCategory());
        viewCategories.setOnClickListener(v -> goToViewCategories());
        viewSuppliers.setOnClickListener(v -> goToViewSuppliers());
        viewInventory.setOnClickListener(v -> goToViewInventory());
        addS.setOnClickListener(v -> goToAddSale());
        viewSales.setOnClickListener(v -> goToViewSales());
        addUser.setOnClickListener(v -> goToAddUser());
        loginU.setOnClickListener(v -> goToLogin());
        viewInv.setOnClickListener(v -> goToViewInventoryOrders());
        //run functions to check is databases exist
        if(!checkIfDataExists()) {
            createDatabases();
            createSalesDatabase();
            createCategoryDatabase();
            createSupplierDatabase();


        }

    }
    //main menu
    //go to login
    /**
     * This is the goToLogin method which takes the user to the Login
     * page.
     * @return Nothing.
     *
     */
    public void goToLogin(){
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }
    //Add user
    /**
     * This is the goToAddUser method which takes the user to the addUser
     * page.
     * @return Nothing.
     *
     */
    public void goToAddUser(){
        Intent intent = new Intent(this, addUser.class);
        startActivity(intent);
    }
    //view list of sales
    /**
     * This is the goToviewSales method which takes the user to the viewSales
     * page.
     * @return Nothing.
     *
     */
    public void goToViewSales (){
        Intent intent = new Intent(this, viewSales.class);
        startActivity(intent);
    }
    //add sale
    /**
     * This is the goToAddSale method which takes the user to the addSale
     * page.
     * @return Nothing.
     *
     */
    public void goToAddSale (){
        Intent intent = new Intent(this, addSale.class);
        startActivity(intent);
    }
    //view total inventory of products
    /**
     * This is the goToViewIventory method which takes the user to the viewInventory
     * page.
     * @return Nothing.
     *
     */
    public void goToViewInventory(){
        Intent intent = new Intent(this, viewproduct.class);
        startActivity(intent);
    }
    //view suppliers
    /**
     * This is the goToViewSuppliers method which takes the user to the addSale
     * page.
     * @return Nothing.
     *
     */
    public void goToViewSuppliers(){
        Intent intent = new Intent(this, viewSuppliers.class);
        startActivity(intent);
    }
    //view all categories
    /**
     * This is the goToViewCategories method which takes the user to the viewCategories
     * page.
     * @return Nothing.
     *
     */
    public void goToViewCategories(){
        Intent intent = new Intent(this, viewCategories.class);
        startActivity(intent);
    }
    //add category to list
    /**
     * This is the goToAddCategories
     * method which takes the user to the addCategory
     * page.
     * @return Nothing.
     *
     */
    public void goToAddCategory(){
        Intent intent = new Intent(this, addCategory.class);
        startActivity(intent);
    }
    //add supplier
    /**
     * This is the goToAddSupplier
     * method which takes the user to the addSupplier
     * page.
     * @return Nothing.
     *
     */
    public void goToAddSupplier(){
        Intent intent = new Intent(this, addSupplier.class);
        startActivity(intent);
    }
    //make inventory order
    /**
     * This is the goToAddInventoryOrder
     * method which takes the user to the addInventory
     * page.
     * @return Nothing.
     *
     */
    public void goToAddInventoryOrder(){
        Intent intent = new Intent(this, addInventoryOrder.class);
        startActivity(intent);
    }
    //view histry of inventory orders
    /**
     * This is the goToViewInventoryOrders
     * method which takes the user to the viewInventoryOrders
     * page.
     * @return Nothing.
     *
     */
    public void goToViewInventoryOrders(){
        Intent intent = new Intent(this, viewInventoryOrders.class);
        startActivity(intent);
    }
    //add product
    /**
     * This is the goToAddProduct
     * method which takes the user to the addProduct
     * page.
     * @return Nothing.
     *
     */
    public void goToAddProduct(){
        Intent intent = new Intent(this, addProduct.class);
        startActivity(intent);
    }
    //check if databases exist already
    /**
     * This is the checkIfDataExists method
     * checks if there is an existing database
     * @return Nothing.
     *
     */
    private boolean checkIfDataExists(){
        SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='product'", null);
        boolean result = cursor.getCount() > 0;
        db.close();
        return result;

    }
    //create databse and add dummy data
    /**
     * This is the createDatabases method
     * create database for product and input dummy data
     * page.
     * @return Nothing.
     *
     */
    private void createDatabases(){
        //create or open database
        SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);
        //create table
        db.execSQL("CREATE TABLE IF NOT EXISTS product(id INTEGER PRIMARY KEY AUTOINCREMENT,proname VARCHAR,category VARCHAR,supplier VARCHAR,qty VARCHAR,price VARCHAR)");
        //create rows
        String sql = "insert into product(proname,category,supplier,qty,price)values(?,?,?,?,?)";
        //create statement to insert
        SQLiteStatement statement = db.compileStatement(sql);
        //bind dummy data to statement
        statement.bindString(1, "band-aids");
        statement.bindString(2, "blue");
        statement.bindString(3, "Johnson & Johnson");
        statement.bindString(4, "20");
        statement.bindString(5, "10.99");
        statement.execute();





        db.close();
    }
    //create databse and add dummy data for
    /**
     * This is the createCategoryDatabase
     * method which creates a table for the categories and inputs dummy data
     * page.
     * @return Nothing.
     *
     */
    private void createCategoryDatabase(){
        //open or create database
        SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS category(id INTEGER PRIMARY KEY AUTOINCREMENT,category VARCHAR,description VARCHAR)");
        //create columns
        String sql2 = "insert into category(category,description)values(?,?)";
        //create statement to insert categories
        SQLiteStatement statement2 = db.compileStatement(sql2);
        //bind dummy data to statement to add
        statement2.bindString(1, "blue");
        statement2.bindString(2, "Categorized by the color blue.");
        statement2.execute();
        db.close();

    }
    //create databse and add dummy data for suppliers
    /**
     * This is the createSupplierDatabase
     * method which creates a table for the supplier and inputs dummy data
     * page.
     * @return Nothing.
     *
     */
    private void createSupplierDatabase(){

        SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);
        //create table
        db.execSQL("CREATE TABLE IF NOT EXISTS supplier(id INTEGER PRIMARY KEY AUTOINCREMENT,supplier VARCHAR,description VARCHAR,email VARCHAR,phoneNumber VARCHAR,address VARCHAR)");
        //create columns
        String sql3 = "insert into supplier(supplier,description,email,phoneNumber, address)values(?,?,?,?,?)";
        //create statement to add to database
        SQLiteStatement statement3 = db.compileStatement(sql3);
        //bind dummy data to statemnt to add
        statement3.bindString(1, "Johnson & Johnson");
        statement3.bindString(2, "Medical and aide company");
        statement3.bindString(3, "johnsonj@johnson.com");
        statement3.bindString(4, "534-6745-2058");
        statement3.bindString(5, "101 South Main Road Dallas Texas, 79936");
        statement3.execute();
        db.close();

    }
    //create databse and add dummy data for sales
    /**
     * This is the goToAddSupplier
     * method which takes the user to the addSupplier
     * page.
     * @return Nothing.
     *
     */
    private void createSalesDatabase(){
        //open or create database
        SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);
        //create tables
        db.execSQL("CREATE TABLE IF NOT EXISTS sales(proid VARCHAR,proname VARCHAR,qty VARCHAR,price VARCHAR,total VARCHAR)");
        //create columns
        String sql4 = "insert into sales(proid,proname,qty,price,total)values(?,?,?,?,?)";
        //create statment to attach dummy data to
        SQLiteStatement statement4 = db.compileStatement(sql4);
        //execute to add to database
        statement4.bindString(1, "1");
        statement4.bindString(2, "band-aids");
        statement4.bindLong(3, 10);
        statement4.bindString(4, "10.99");
        statement4.bindString(5, "21.98");
        statement4.execute();
        db.close();


    }

}