//Jacqueline Chavez Ayana Jackson
//Mobile App Development
//Inventory Tracker
package com.example.inventorytracker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class viewInventoryOrders extends AppCompatActivity {

    //create variables for arraylist
    private ArrayList<InvOrder> invOrderArrayList;
    private DBHelper2 dbHelper2;
    private invOrderRVAdapter invOrderRVAdapter;
    private RecyclerView ordersRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_inventory_orders);

        //initialize variables
        invOrderArrayList = new ArrayList<>();
        dbHelper2 = new DBHelper2(viewInventoryOrders.this);

        //get inventory order list from dbhelper2 class
        invOrderArrayList = dbHelper2.readOrders();

        //pass array to adapter class
        invOrderRVAdapter = new invOrderRVAdapter(invOrderArrayList, viewInventoryOrders.this);
        ordersRV = findViewById(R.id.idRVorders);

        //layout for recyclerview
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(viewInventoryOrders.this, RecyclerView.VERTICAL, false);
        ordersRV.setLayoutManager(linearLayoutManager);

        //set adapter to recycler view
        ordersRV.setAdapter(invOrderRVAdapter);
    }
}