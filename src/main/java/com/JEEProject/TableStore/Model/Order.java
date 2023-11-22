package com.JEEProject.TableStore.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "orders")
@AllArgsConstructor @Getter @Setter
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer total_price;
    private String bill_file_name;
    private Date create_at;
    private Date delete_at;
    private String place;
    private ORDERSTATE state;
    private Integer confirm_id;
    private Integer user_id;
    private Date confirm_date;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Collection<OrderDetail> details;

    @ManyToOne
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    @JsonIgnore
    private Account user;

    public Order() {
        this.total_price = 0;
        this.details  = new ArrayList<>();
    }

    public boolean isConfirm(){
        return this.confirm_id!=null;
    }

    public List<OrderDetail> getDetails() {
        return details.stream().toList();
    }

    public boolean orderBy(String userId){
        try {
            return this.getUser().getId() == Integer.valueOf(userId).intValue();
        }catch (Exception e){
            return true;
        }
    }

    public boolean isBefore(String date){
        try {
            return this.getCreate_at().compareTo(new SimpleDateFormat("yyyy-MM-dd").parse(date)) <=0;
        } catch (ParseException ex) {
            return true;
        }
    }
    public boolean isAfter(String date){
        try {
            return this.getCreate_at().compareTo(new SimpleDateFormat("yyyy-MM-dd").parse(date)) >=0;
        } catch (ParseException ex) {
            return true;
        }
    }

    public void addProduct(Integer productId, Integer qty){
        OrderDetail detail = new OrderDetail();
        detail.setOrder_id(this.id);
        detail.setProduct_id(productId);
        detail.setQty(qty);
        this.details.add(detail);
    }

    public void addProduct(OrderDetail detail){
        detail.setOrder_id(this.id);
        this.details.add(detail);
    }

    public void increaseTotalPrice(Integer price){
        this.total_price += price;
    }

    public void decreaseTotalPrice(Integer price){
        this.total_price -= price;
    }

}
