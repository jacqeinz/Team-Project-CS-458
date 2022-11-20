//Jacqueline Chavez Ayana Jackson
//Mobile App Development
//Inventory Tracker
package com.example.inventorytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.EditText;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class addSale extends AppCompatActivity {
    //variables
    EditText pid, pname, proqty, price;
    private TextView date, total;
    Button addSale, calcSale;
    private Calendar calendarDate;
    private SimpleDateFormat dateFormat;
    private String saleDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sale);
        //associate variables to UI components
        pid = findViewById(R.id.pid);
        pname = findViewById(R.id.pname);
        proqty = findViewById(R.id.proqty);
        price = findViewById(R.id.proprice);
        date = findViewById(R.id.date);
        total = findViewById(R.id.total);
        calendarDate = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        saleDate = dateFormat.format(calendarDate.getTime());
        date.setText(saleDate);


        addSale = findViewById(R.id.addSale);
        calcSale = findViewById(R.id.calcSale);


        //bind buttons to functions
        addSale.setOnClickListener(v -> insert());
       calcSale.setOnClickListener(V -> calculateSale());

    }
    public void calculateSale(){
       // calculate sale get qty and price
        Double qtysale = Double.parseDouble(proqty.getText().toString());
        Double pricepro =  Double.parseDouble(price.getText().toString());
        //do math
       double totalSale = qtysale * pricepro;
       total.setText(String.valueOf(totalSale));

   }

    public void insert() {
        try {
            //read in input
            String proid = pid.getText().toString();
            String proname = pname.getText().toString();
            int qtysale = Integer.parseInt(proqty.getText().toString().trim());
            String pricepro =  price.getText().toString();
            String totalsale = total.getText().toString();


            //open or create database
            SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);
            String id = pid.getText().toString();
            //look for products with that ID
            final Cursor c = db.rawQuery("select * from product where id = '"+id+"' ",null);
            //get quantity of that products
            int qty = c.getColumnIndex("qty");

            final  ArrayList<products> products1 = new ArrayList<products>();
            if(c.moveToFirst())
            {
                do{
                    //create object
                    products products = new products();
                    //set qty
                    products.qty= c.getString(qty);

                    products1.add(products);


                    int oldqty = Integer.parseInt(c.getString(qty));

                    //compare how much is being sold to how much is in stock
                    if(qtysale > oldqty )
                    {
                        //if there is not enough in stock
                        Toast.makeText(addSale.this,String.valueOf( oldqty),Toast.LENGTH_LONG).show();
                        Toast.makeText(this,"Qty is Not Enough",Toast.LENGTH_LONG).show();

                        proqty.setText("");

                    }
                    else {

                        //create or open database
                        db.execSQL("CREATE TABLE IF NOT EXISTS sales(proid VARCHAR,proname VARCHAR,qty VARCHAR, price VARCHAR,total VARCHAR)");
                        String sql = "insert into sales(proid,proname,qty,price,total)values(?,?,?,?,?)";
                        //create statement
                        SQLiteStatement statement = db.compileStatement(sql);
                        //attach to statement
                        statement.bindString(1, proid);
                        statement.bindString(2, proname);
                        statement.bindLong(3, qty);
                        statement.bindString(4, pricepro);
                        statement.bindString(5, totalsale);
                        statement.execute();
                        //update products after sale
                        String sql1 = "update product set qty = qty - ? where id = ? ";
                        SQLiteStatement statement1 = db.compileStatement(sql1);
                        statement1.bindLong(1, qtysale);
                        statement1.bindString(2, proid);
                        statement1.execute();
                        //if adding sale was successful
                        Toast.makeText(this, "Sale added", Toast.LENGTH_LONG).show();
                        //empty fields
                        pid.setText("");
                        pname.setText("");
                        proqty.setText("");
                        price.setText("");
                        total.setText("");
                        pid.requestFocus();
                    }

                } while(c.moveToNext());
            }

        } catch (Exception ex) {

            //in case sale was not able to be added
             Toast.makeText(this, "Adding Record Failed",  Toast.LENGTH_LONG).show();
        }
    }
}


