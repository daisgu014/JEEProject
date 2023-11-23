package com.JEEProject.TableStore.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orderDetails")
@IdClass(OdKey.class)
@Data @Getter @Setter
public class OrderDetail {

    @Id
    private Integer order_id;

    @Id
    private Integer product_id;
    private Integer qty;

    @ManyToOne
    @JoinColumn(name = "order_id",insertable = false,updatable = false)
    @JsonBackReference
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id",insertable = false,updatable = false)
    private Product product;

}
