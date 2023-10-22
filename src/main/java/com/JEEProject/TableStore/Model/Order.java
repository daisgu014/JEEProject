package com.JEEProject.TableStore.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    @Id
    private Integer id;
    private Integer total_price;
    private String bill_file_name;
    private Date create_at;
    private Date delete_at;
    private String place;
    private Integer confirm_id;
    private Integer user_id;
    private Date confirm_date;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private Collection<OrderDetail> details;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public String getBill_file_name() {
        return bill_file_name;
    }

    public void setBill_file_name(String bill_file_name) {
        this.bill_file_name = bill_file_name;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Date getDelete_at() {
        return delete_at;
    }

    public void setDelete_at(Date delete_at) {
        this.delete_at = delete_at;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getConfirm_id() {
        return confirm_id;
    }

    public void setConfirm_id(int confirm_id) {
        this.confirm_id = confirm_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getConfirm_date() {
        return confirm_date;
    }

    public void setConfirm_date(Date confirm_date) {
        this.confirm_date = confirm_date;
    }

    public Collection<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(Collection<OrderDetail> details) {
        this.details = details;
    }
}
