//Jacqueline Chavez Ayana Jackson
//Mobile App Development
//Inventory Tracker
package com.example.inventorytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;


public class addInventoryOrder extends AppCompatActivity {

    //assign IDs to variables
    //declare variable
    private EditText etOrder2;
    private EditText etOrder3;
    private EditText etOrder4;
    private EditText etOrder5;
    private EditText etOrder6;
    private Button button;
    private DBHelper2 dbHelper2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventory_order);
        //initialize variables

        etOrder2 = findViewById(R.id.etProductName);
        etOrder3 = findViewById(R.id.etAmountOrdered);
        etOrder4 = findViewById(R.id.etProductCategory);
        etOrder5 = findViewById(R.id.etProductSupplier);
        etOrder6 = findViewById(R.id.etProductCost);
        button = findViewById(R.id.btnSubmit);

        //new  class
        dbHelper2 = new DBHelper2(addInventoryOrder.this);


        //click listener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get  all data from edit text
                String productName = etOrder2.getText().toString();
                String amountOrdered = etOrder3.getText().toString();
                String productCategory = etOrder4.getText().toString();
                String productSupplier = etOrder5.getText().toString();
                String productCost = etOrder6.getText().toString();

                if(productName.isEmpty() && amountOrdered.isEmpty()
                && productCategory.isEmpty() && productSupplier.isEmpty() & productCost.isEmpty()){
                    Toast.makeText(addInventoryOrder.this, "Please enter info in all text fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                //method to add invOrder to sqlite
                dbHelper2.addInvOrder(productName, amountOrdered, productCategory, productSupplier, productCost);

                Toast.makeText(addInventoryOrder.this, "Inventory has been submitted successfully", Toast.LENGTH_SHORT).show();
                etOrder2.setText("");
                etOrder3.setText("");
                etOrder4.setText("");
                etOrder5.setText("");
                etOrder6.setText("");


            }
        });


    }


}