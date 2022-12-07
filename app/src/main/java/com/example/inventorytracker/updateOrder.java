package com.example.inventorytracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class updateOrder extends AppCompatActivity {
    /**
     *
     */
    // variables for our edit text, button, strings and dbhelper2 class.
    private EditText productNameEdt, amountOrderedEdt, productCatEdt, productSupEdt, productCostEdt;
    private Button updateOrderBtn, deleteOrderBtn;
    private DBHelper2 dbHelper2;
    String productName, amountOrdered, productCategory, productSupplier, productCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_inentory_order);
/**
 *
 */
        // initializing all our variables.
        productNameEdt = findViewById(R.id.etProductName);
        amountOrderedEdt = findViewById(R.id.etAmountOrdered);
        productCatEdt = findViewById(R.id.etProductCategory);
        productSupEdt = findViewById(R.id.etProductSupplier);
        productCostEdt = findViewById(R.id.etProductCost);
        updateOrderBtn = findViewById(R.id.idBtnUpdate);
        deleteOrderBtn = findViewById(R.id.idBtnDelete);

        // on below line we are initialing our dbhandler class.
        dbHelper2 = new DBHelper2(updateOrder.this);

        // on below lines we are getting data
        productName = getIntent().getStringExtra("productName");
        amountOrdered = getIntent().getStringExtra("amountOrdered");
        productCategory = getIntent().getStringExtra("productCategory");
        productSupplier = getIntent().getStringExtra("productSupplier");
        productCost = getIntent().getStringExtra("productCost");

        // setting data to edit text
        // of our update activity.
        productNameEdt.setText(productName);
        amountOrderedEdt.setText(amountOrdered);
        productCatEdt.setText(productCategory);
        productSupEdt.setText(productSupplier);
        productCostEdt.setText(productCost);
/**
 *
 */
        // adding on click listener to our update course button.
        updateOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // inside this method we are calling an update course
                // method and passing all our edit text values.
                dbHelper2.updateInvOrder(productName, productNameEdt.getText().toString(), amountOrderedEdt.getText().toString(), productCatEdt.getText().toString(), productSupEdt.getText().toString());

                // displaying a toast message that our course has been updated.
                Toast.makeText(updateOrder.this, "Inventory order has been updated", Toast.LENGTH_SHORT).show();


                Intent i = new Intent(updateOrder.this, addInventoryOrder.class);
                startActivity(i);
            }
        });


        /**
         *
         */

        // adding on click listener for delete button to delete our course.
        deleteOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to delete our course.
                dbHelper2.deleteOrder(productName);
                Toast.makeText(updateOrder.this, "Deleted inventory", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(updateOrder.this, addInventoryOrder.class);
                startActivity(i);
            }
        });
    }
}

