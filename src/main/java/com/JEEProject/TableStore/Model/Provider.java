package com.JEEProject.TableStore.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "providers")
public class Provider {
    @Id
    @Column(name = "id")
    private  int id;
    @Column(name = "name")
    private  String name;
    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private  Date createAt;
    @Temporal(TemporalType.DATE)
    @Column(name = "delete_at")
    private Date deleteAt;
    public  Provider(){

    }
    public Provider(int id, String name, Date createAt, Date deleteAt) {
        this.id = id;
        this.name = name;
        this.createAt = createAt;
        this.deleteAt = deleteAt;
    }

    public Provider(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
