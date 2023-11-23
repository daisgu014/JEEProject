package com.JEEProject.TableStore.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
   @Column(name = "state")
   private boolean state;

    public Cart() {
    }

    public Cart(Integer userID, Integer productID, Integer qty, boolean state) {
        this.userID = userID;
        this.productID = productID;
        this.qty = qty;
        this.state=state;
    }

    public Integer getQty() {
        return qty;
    }
}
