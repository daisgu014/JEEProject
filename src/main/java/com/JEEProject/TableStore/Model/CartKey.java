package com.JEEProject.TableStore.Model;

import java.io.Serializable;

public class CartKey implements Serializable {
    private Integer userID;
    private Integer productID;

    public CartKey() {
    }

    public CartKey(Integer userID, Integer productID) {
        this.userID = userID;
        this.productID = productID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }
}
