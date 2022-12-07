//Jacqueline Chavez Ayana Jackson
//Mobile App Development
//Inventory Tracker
package com.example.inventorytracker;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Ayana Jackson
 *
 */

/**
 *
 */
public class addInventoryOrder extends AppCompatActivity {


    //declare variable
    private EditText etOrder2;
    private EditText etOrder3;
    private EditText etOrder4;
    private EditText etOrder5;
    private EditText etOrder6;
    private DBHelper2 dbHelper2;
    private Button button, button2;


    /**
     * @param savedInstanceState
     */
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
        button2 = findViewById(R.id.btnView);

        //create new dbhelper2 class
        dbHelper2 = new DBHelper2(addInventoryOrder.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productName = etOrder2.getText().toString();
                String amountOrdered = etOrder3.getText().toString();
                String productCategory = etOrder4.getText().toString();
                String productSupplier = etOrder5.getText().toString();
                String productCost = etOrder6.getText().toString();

                if(productName.isEmpty() || amountOrdered.isEmpty() || productCategory.isEmpty() ||
                    productSupplier.isEmpty() || productCost.isEmpty()){
                    Toast.makeText(addInventoryOrder.this, "enter all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHelper2.addInvOrder(productName, amountOrdered, productCategory, productSupplier, productCost);
                Toast.makeText(addInventoryOrder.this, "order was added", Toast.LENGTH_SHORT).show();
                etOrder2.setText("");
                etOrder3.setText("");
                etOrder4.setText("");
                etOrder5.setText("");
                etOrder6.setText("");

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(addInventoryOrder.this, viewInventoryOrders.class);
                startActivity(i);
            }
        });
    }
}