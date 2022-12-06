package com.example.inventorytracker;

//Ayana Jackson and Jacqueline Chavez
//Inventory Tracker

/**
 *
 */
public class InvOrder {
    //variables for table
    private int productID;
    private String productName;
    private String amountOrdered;
    private String productCategory;
    private String productSupplier;
    private String productCost;

    //getter and setter methods

    /**
     *
     * @return
     */
    public int getProductID() {
        return productID;
    }

    /**
     *
     * @param productID
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     *
     * @return
     */
    public String getProductName() {
        return productName;
    }

    /**
     *
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     *
     * @return
     */
    public String getAmountOrdered() {
        return amountOrdered;
    }

    /**
     *
     * @param amountOrdered
     */
    public void setAmountOrdered(String amountOrdered) {
        this.amountOrdered = amountOrdered;
    }

    /**
     *
     * @return
     */
    public String getProductCategory() {
        return productCategory;
    }

    /**
     *
     * @param productCategory
     */
    public void getProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    /**
     *
     * @return
     */
    public String getProductSupplier() {
        return productSupplier;
    }

    /**
     *
     * @param productSupplier
     */
    public void setProductSupplier(String productSupplier) {
        this.productSupplier = productSupplier;
    }

    /**
     *
     * @return
     */
    public String getProductCost() {
        return productCost;
    }

    /**
     *
     * @param productCost
     */
    public void setProductCost(String productCost) {
        this.productCost = productCost;
    }

    /**
     *
     * @param productName
     * @param amountOrdered
     * @param productCategory
     * @param productSupplier
     * @param productCost
     */
    //constructor
    public InvOrder(String productName, String amountOrdered, String productCategory, String productSupplier, String productCost){
        this.productName = productName;
        this.amountOrdered = amountOrdered;
        this.productCategory = productCategory;
        this.productSupplier = productSupplier;
        this.productCost = productCost;
    }

}
