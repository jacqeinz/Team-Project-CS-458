//Jacqueline Chavez Ayana Jackson
//Mobile App Development
//Inventory Tracker
package com.example.inventorytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;


public class addInventoryOrder extends AppCompatActivity {

    //assign IDs to variables
    //declare variable
    EditText etOrder1;
    EditText etOrder2;
    EditText etOrder3;
    EditText etOrder4;
    EditText etOrder5;
    EditText etOrder6;
    Button button = (Button) findViewById(R.id.btnSubmit);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventory_order);

        etOrder1 = findViewById(R.id.etProductID);
        etOrder2 = findViewById(R.id.etProductName);
        etOrder3 = findViewById(R.id.etAmountOrdered);
        etOrder4 = findViewById(R.id.etProductCategory);
        etOrder5 = findViewById(R.id.etProductSupplier);
        etOrder6 = findViewById(R.id.etProductCost);

    }

    public void addInventory(View view){
        String etOrder1value = etOrder1.getText().toString();
        String etOrder2value = etOrder2.getText().toString();
        String etOrder3value = etOrder3.getText().toString();
        String etOrder4value = etOrder4.getText().toString();
        String etOrder5value = etOrder5.getText().toString();


        DBHelper2 db = new DBHelper2(this);
        db.addInvOrder(new Inventory(etOrder1value, etOrder2value, etOrder3value, etOrder4value, etOrder5value));



    }
}