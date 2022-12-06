//Ayana Jackson and Jacqueline Chavez
//Inventory Tracker
//Adapter for list view
package com.example.inventorytracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class invOrderRVAdapter extends RecyclerView.Adapter<invOrderRVAdapter.ViewHolder> {

    //variables for our arraylist and context
    private ArrayList<InvOrder> invOrderArrayList;
    private Context context;

    //constructor
    public invOrderRVAdapter(ArrayList<InvOrder> invOrderArrayList, Context context) {
        this.invOrderArrayList = invOrderArrayList;
        this.context = context;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        //to view items in recycler view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.invorder_rv, parent, false);
        return new ViewHolder(view);

    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        //setting data to recycler view
        InvOrder invOrder = invOrderArrayList.get(position);
        holder.productNameTV.setText(invOrder.getProductName());
        holder.amountOrderedTV.setText(invOrder.getAmountOrdered());
        holder.prodCateTV.setText(invOrder.getProductCategory());
        holder.prodSupTV.setText(invOrder.getProductSupplier());
        holder.productCostTV.setText(invOrder.getProductCost());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call intent
                Intent i = new Intent(context, updateOrder.class);
                //pass values
                i.putExtra("productName", invOrder.getProductName());
                i.putExtra("amountOrdered", invOrder.getAmountOrdered());
                i.putExtra("productCategory", invOrder.getProductCategory());
                i.putExtra("productSupplier", invOrder.getProductSupplier());
                i.putExtra("productCost", invOrder.getProductCost());

                context.startActivity(i);

            }
        });
    }

    public  int getItemCount(){
        //return size of arraylist
        return invOrderArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //create variable for textview
        private TextView productNameTV, amountOrderedTV, prodCateTV, prodSupTV, productCostTV;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            //initialize the text views to id
            productNameTV = itemView.findViewById(R.id.idTVproductName);
            amountOrderedTV = itemView.findViewById(R.id.idTVamountOrdered);
            prodCateTV = itemView.findViewById(R.id.idTVprodCat);
            prodSupTV = itemView.findViewById(R.id.idTVprodSup);
            productCostTV = itemView.findViewById(R.id.idTVprodCost);
        }

    }



}
