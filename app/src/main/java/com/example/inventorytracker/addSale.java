package com.example.inventorytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class addSale extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4,ed5;
    Button b1;
    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sale);

        ed1 = findViewById(R.id.pid);
        ed2 = findViewById(R.id.pname);
        ed3 = findViewById(R.id.proqty);
        ed4 = findViewById(R.id.proprice);
        ed5 = findViewById(R.id.total);

        b1 = findViewById(R.id.addSale);




        b1.setOnClickListener(v -> insert());

    }

    public void insert() {
        try {
            String proid = ed1.getText().toString();
            String proname = ed2.getText().toString();
            int qty1 = Integer.parseInt(ed3.getText().toString().trim());
            String price =  ed4.getText().toString();
            String total1 = ed5.getText().toString();


            SQLiteDatabase db = openOrCreateDatabase("inventory", Context.MODE_PRIVATE, null);
            String id = ed1.getText().toString();
            final Cursor c = db.rawQuery("select * from product where id = '"+id+"' ",null);
            int qty = c.getColumnIndex("qty");

            final  ArrayList<productview1> product1 = new ArrayList<productview1>();
            if(c.moveToFirst())
            {
                do{
                    productview1 stu = new productview1();

                    stu.qty= c.getString(qty);

                    product1.add(stu);


                    int oldqty = Integer.parseInt(c.getString(qty));


                    if(qty1 > oldqty )
                    {
                        Toast.makeText(addSale.this,String.valueOf( oldqty),Toast.LENGTH_LONG).show();
                        Toast.makeText(this,"Qty is Not Enough",Toast.LENGTH_LONG).show();

                        ed3.setText("");

                    }
                    else {


                        db.execSQL("CREATE TABLE IF NOT EXISTS sales(proid VARCHAR,proname VARCHAR,qty VARCHAR,price VARCHAR,total VARCHAR)");
                        String sql = "insert into sales(proid,proname,qty,price,total)values(?,?,?,?,?)";
                        SQLiteStatement statement = db.compileStatement(sql);
                        statement.bindString(1, proid);
                        statement.bindString(2, proname);
                        statement.bindLong(3, qty1);
                        statement.bindString(4, price);
                        statement.bindString(5, total1);
                        statement.execute();

                        String sql1 = "update product set qty = qty - ? where id = ? ";
                        SQLiteStatement statement1 = db.compileStatement(sql1);
                        statement1.bindLong(1, qty1);
                        statement1.bindString(2, proid);
                        statement1.execute();
                        Toast.makeText(this, "Sale added", Toast.LENGTH_LONG).show();


                        ed1.requestFocus();
                    }

                } while(c.moveToNext());
            }

        } catch (Exception ex) {


             Toast.makeText(this, "Record Fail", Toast.LENGTH_LONG).show();
        }
    }
}


