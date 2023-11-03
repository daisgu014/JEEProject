package com.JEEProject.TableStore.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "users")
public class Account {
    @Id
    @Column(name = "id")
    private  int id;
    @Column(name = "username")
    private  String username;
    @Column(name = "password")
    private  String password;
    @Column(name = "full_name")
    private  String fullname;
    @Column(name = "role")
    private  String role;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private  String address;
    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt;
    @Temporal(TemporalType.DATE)
    @Column(name = "delete_at")
    private Date deleteAt;
    public Account(){

    }
    public Account(int id, String username, String password,String fullname, String role, String phone, String email, String address, Date createAt, Date deleteAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.createAt = createAt;
        this.deleteAt = deleteAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
