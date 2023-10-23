package com.JEEProject.TableStore.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
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
    private Integer confirm_id;
    private Integer user_id;
    private Date confirm_date;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private Collection<OrderDetail> details;

    @ManyToOne
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    private Account user;



    public boolean isConfirm(){
        return this.confirm_date!=null;
    }

    public List<OrderDetail> getDetails() {
        return details.stream().toList();
    }
}
