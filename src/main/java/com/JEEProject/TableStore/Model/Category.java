package com.JEEProject.TableStore.Model;

import jakarta.persistence.*;


import java.util.Date;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Integer id;
    @Column(name = "name")
    private  String name;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private  Date createAt;
    @Temporal(TemporalType.DATE)
    @Column(name = "delete_at")
    private Date deleteAt;
    public  Category(){

    }
    public Category(Integer id, String name, Date createAt, Date deleteAt) {
        this.id = id;
        this.name = name;
        this.createAt = createAt;
        this.deleteAt = deleteAt;
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Date deleteAt) {
        this.deleteAt = deleteAt;
    }
}
