package com.JEEProject.TableStore.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "orderDetails")
@IdClass(OdKey.class)
@Data
public class OrderDetail {

    @Id
    private Integer order_id;

    @Id
    private Integer product_id;
    private Integer qty;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    private Order order;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
