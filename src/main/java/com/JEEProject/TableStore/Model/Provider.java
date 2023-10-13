package com.JEEProject.TableStore.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "providers")
public class Provider {
    @Id
    @Column(name="id")
    private Integer id;
    @Column(name="name")
    @NotBlank(message = "Tên nhà cung cấp không được bỏ trống!")
    private String name;
    @Column(name = "create_at")
    private Date CreateAt;
    @Column(name = "delete_at")
    private Date DeleteAt;

    public Provider(Integer id, String name, Date createAt, Date deleteAt) {
        this.id = id;
        this.name = name;
        CreateAt = createAt;
        DeleteAt = deleteAt;
    }

    public Provider() {

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
        return CreateAt;
    }

    public void setCreateAt(Date createAt) {
        CreateAt = createAt;
    }

    public Date getDeleteAt() {
        return DeleteAt;
    }

    public void setDeleteAt(Date deleteAt) {
        DeleteAt = deleteAt;
    }
}
