package com.JEEProject.TableStore.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cart_infos")
@IdClass(CartKey.class)
@Data
public class Cart {

   @Id
   @Column(name = "user_id")
    private Integer userID;

   @Id
   @Column(name = "product_id")
    private Integer productID;

   @Column(name = "qty")
   private Integer qty;

    public Cart() {
    }

    public Cart(Integer userID, Integer productID, Integer qty) {
        this.userID = userID;
        this.productID = productID;
        this.qty = qty;
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

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
