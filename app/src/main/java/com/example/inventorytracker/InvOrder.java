package com.example.inventorytracker;

//Ayana Jackson and Jacqueline Chavez
//Inventory Tracker

public class InvOrder {
    //variables for table
    private int productID;
    private String productName;
    private String amountOrdered;
    private String productCategory;
    private String productSupplier;
    private String productCost;

    //getter and setter methods


    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAmountOrdered() {
        return amountOrdered;
    }

    public void setAmountOrdered(String amountOrdered) {
        this.amountOrdered = amountOrdered;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void getProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductSupplier() {
        return productSupplier;
    }

    public void setProductSupplier(String productSupplier) {
        this.productSupplier = productSupplier;
    }

    public String getProductCost() {
        return productCost;
    }

    public void setProductCost(String productCost) {
        this.productCost = productCost;
    }
    //constructor
    public InvOrder(String productName, String amountOrdered, String productCategory, String productSupplier, String productCost){
        this.productName = productName;
        this.amountOrdered = amountOrdered;
        this.productCategory = productCategory;
        this.productSupplier = productSupplier;
        this.productCost = productCost;
    }

}
