package com.JEEProject.TableStore.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    @NotBlank (message = "Tên sản phẩm không được để trống")
    private String name;
    @Column(name="color")
    private String color;
    @Column(name = "status")
    private String Status;

    @Column(name = "in_stock")
    private Integer inStock;
    @Column(name = "img_path")
    private String imgPath;

    @Column(name = "price")
    @NotNull(message = "Giá trị sản phẩm không được để trống")
    private Integer price;
    @Column(name = "create_at")
    private Date CreateAt;
    @Column(name = "delete_at")
    private Date DeleteAt;

    @Column(name = "category_id")
    @NotNull(message = "Vui lòng chọn thể loại")
    private Integer categoryId;
    @Column(name = "provider_id")
    @NotNull(message = "Vui lòng chọn nhà cung cấp")
    private Integer providerId;

    public Product(String name, String color, String status, Integer inStock, String imgPath, Integer price, Date createAt, Date deleteAt, Integer categoryId, Integer providerId) {
        this.name = name;
        this.color = color;
        Status = status;
        this.inStock = inStock;
        this.imgPath = imgPath;
        this.price = price;
        CreateAt = createAt;
        DeleteAt = deleteAt;
        this.categoryId = categoryId;
        this.providerId = providerId;
    }

    public Product() {
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }
}
