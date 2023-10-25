package com.JEEProject.TableStore.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotNull
    @NotBlank (message = "Tên sản phẩm không được để trống")
    private String name;

    @Column(name="color")
    @NotNull
    @NotBlank (message = "Màu không được để trống")
    private String color;

    @Column(name = "status")
    private String Status;

    @Column(name = "in_stock")
    private Integer inStock;
    @Column(name = "img_path")
    private String imgPath;
    @Min(0)
    @Column(name = "price")
    @NotNull(message = "Giá tiền sản phẩm không được để trống")
    private Integer price;
    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date CreateAt;
    @Temporal(TemporalType.DATE)
    @Column(name = "delete_at")
    private Date DeleteAt;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStatus() {
       return Status;
    }
    public void setStatus(String status) {
        Status = status;
    }

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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
    public Product(String name, String color, String status, Integer price, Category category, Provider provider) {
        this.name = name;
        this.color = color;
        Status = status;
        this.price = price;
        this.category = category;
        this.provider = provider;
    }

    public Product(String name, String color, String status, String imgPath, Integer price, Category category, Provider provider) {
        this.name = name;
        this.color = color;
        Status = status;
        this.inStock = 0;
        this.imgPath = imgPath;
        this.price = price;
        CreateAt = new Date();
        this.category = category;
        this.provider = provider;
    }

    public Product() {
    }
}
