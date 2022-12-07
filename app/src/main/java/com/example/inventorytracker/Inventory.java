package com.example.inventorytracker;
/**
 * @author Ayana Jackson
 *
 */

public class Inventory {
    int productID;
    String productName;
    String amountOrdered;
    String productCategory;
    String productSupplier;
    double productCost;
    public Inventory(){  }
    //constructor
    public Inventory(int productID, String productName, String amountOrdered, String productCategory, String productSupplier, double productCost){
        this.productID = productID;
        this.productName = productName;
        this.amountOrdered = amountOrdered;
        this.productCategory = productCategory;
        this.productSupplier = productSupplier;
        this.productCost = productCost;

    }

    public Inventory(String productName, String amountOrdered, String productCategory, String productSupplier, String productCost){
        this.productName = productName;
        this.amountOrdered = amountOrdered;
        this.productCategory = productCategory;
        this.productSupplier = productSupplier;
        this.productCost = Double.parseDouble(productCost);

    }

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

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductSupplier() {
        return productSupplier;
    }

    public void setProductSupplier(String productSupplier) {
        this.productSupplier = productSupplier;
    }

    public double getProductCost() {
        return productCost;
    }

    public void setProductCost(double productCost) {
        this.productCost = productCost;
    }
}
