//Jacqueline Chavez Ayana Jackson
//Mobile App Development
//Inventory Tracker
package com.example.inventorytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;


public class addInventoryOrder extends AppCompatActivity {
    //declare variable
    EditText etOrder1;
    EditText etOrder2;
    EditText etOrder3;
    EditText etOrder4;
    EditText etOrder5;
    EditText etOrder6;
    Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventory_order);

        //assign IDs to variables
        etOrder1 = (EditText) findViewById(R.id.etProductID);
        etOrder2 = (EditText) findViewById(R.id.etProductName);
        etOrder3 = (EditText) findViewById(R.id.etAmountOrdered);
        etOrder4 = (EditText) findViewById(R.id.etProductCategory);
        etOrder5 = (EditText) findViewById(R.id.etProductSupplier);
        etOrder6 = (EditText) findViewById(R.id.etProductCost);
        button = (Button) findViewById(R.id.btnSubmit);
    }
}