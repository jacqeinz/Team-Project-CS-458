//Jacqueline Chavez Ayana Jackson
//Mobile App Development
//Inventory Tracker
package com.example.inventorytracker;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * <h1>Add Supplier to Inventory</h1>
 * The addSupplier activity takes in a supplier name,
 * description, email, phone number and address and then
 * added to the database of the inventory.
 * <p>
 *
 * @author Jacqueline chavez
 * @version 1.4
 * @since   Fall 2022
 */
public class addSupplier extends AppCompatActivity {

    //declare UI components
    EditText sup,desc, eMaile, phoneNum, addressR;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_add);
        //connecct to UI components
        sup = findViewById(R.id.supID);
        desc = findViewById(R.id.supplierdesc);
        add = findViewById(R.id.edit);
        eMaile = findViewById(R.id.eMail);
        phoneNum = findViewById(R.id.phoneNumber);
        addressR = findViewById(R.id.address);



        add.setOnClickListener(v -> insert());
    }
    /**
     * This is the insert method which takes in user input and adds
     * a Supplier to the inventory.
     * @return Nothing.
     * @exception Exception ex On return error.
     * @see Exception ex
     */
    public void insert() {
        try {
            //get input from user
            String supplier = sup.getText().toString();
            String description = desc.getText().toString();
            String email = eMaile.getText().toString();
            String phoneNumber = phoneNum.getText().toString();
            String address = addressR.getText().toString();

            //open or create database
            SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);
            //create table
            db.execSQL("CREATE TABLE IF NOT EXISTS supplier(id INTEGER PRIMARY KEY AUTOINCREMENT,supplier VARCHAR,description VARCHAR,email VARCHAR,phoneNumber VARCHAR,address VARCHAR)");
            //create rows
            String sql = "insert into supplier(supplier,description,email,phoneNumber,address)values(?,?,?,?,?)";
            //create statement to add to database
            SQLiteStatement statement = db.compileStatement(sql);
            //add to database
            statement.bindString(1, supplier);
            statement.bindString(2, description);
            statement.bindString(3, email);
            statement.bindString(4, phoneNumber);
            statement.bindString(5, address);

            statement.execute();
            //if adding supplier successful
            Toast.makeText(this, "Supplier Added", Toast.LENGTH_LONG).show();
            //empty fields
            sup.setText("");
            desc.setText("");
            eMaile.setText("");
            phoneNum.setText("");
            addressR.setText("");

            sup.requestFocus();


        } catch (Exception ex) {
            //if failed
            Toast.makeText(this, "Adding Record Failed", Toast.LENGTH_LONG).show();
        }

    }


}
