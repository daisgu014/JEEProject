package com.JEEProject.TableStore.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column(name = "id")
    private int Id;
    @Column(name = "name")
    private String Name;
    @Column(name = "create_at")
    private Date CreateAt;
    @Column(name = "delete_at")
    private Date DeleteAt;

    public Category(String name, Date createAt, Date deleteAt) {
        this.Name = name;
        CreateAt = createAt;
        DeleteAt = deleteAt;
    }
    public Category(){

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


}
