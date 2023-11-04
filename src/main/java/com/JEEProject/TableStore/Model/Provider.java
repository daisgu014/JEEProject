package com.JEEProject.TableStore.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "providers")
public class Provider {
    @Id
    @Column(name = "id")
    private  Integer id;
    @Column(name = "name")
    private  String name;
    @Column(name = "create_at")
    private  Date createAt;
    @Column(name = "delete_at")
    private Date deleteAt;
    public  Provider(){

    }
    public Provider(Integer id, String name, Date createAt, Date deleteAt) {
        this.id = id;
        this.name = name;
        this.createAt = createAt;
        this.deleteAt = deleteAt;
    }

    public Provider(Integer id, String name) {
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
