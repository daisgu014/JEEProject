package com.JEEProject.TableStore.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor @Getter @Setter
public class Order implements Serializable {

    @Id
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

}
