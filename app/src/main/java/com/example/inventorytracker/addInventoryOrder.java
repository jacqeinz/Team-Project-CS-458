//Jacqueline Chavez Ayana Jackson
//Mobile App Development
//Inventory Tracker
package com.example.inventorytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;


public class addInventoryOrder extends AppCompatActivity {

    //assign IDs to variables
    //declare variable
    EditText etOrder1 = (EditText) findViewById(R.id.etProductID);
    EditText etOrder2 = (EditText) findViewById(R.id.etProductName);;
    EditText etOrder3 = (EditText) findViewById(R.id.etAmountOrdered);;
    EditText etOrder4 = (EditText) findViewById(R.id.etProductCategory);;
    EditText etOrder5 = (EditText) findViewById(R.id.etProductSupplier);;
    EditText etOrder6 = (EditText) findViewById(R.id.etProductCost);;
    Button button = (Button) findViewById(R.id.btnSubmit);;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventory_order);



    }
}