package com.JEEProject.TableStore.Model;

import java.util.List;

public class ProductSearchCriteria {
    private String name;
    private String minPrice;
    private String maxPrice;
    private List<String> colors;
    private List<String> categoryID;

    public ProductSearchCriteria(String name, String minPrice, String maxPrice, List<String> colors, List<String> categoryID) {
        this.name = name;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.colors = colors;
        this.categoryID = categoryID;
    }

    public ProductSearchCriteria() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public List<String> getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(List<String> categoryID) {
        this.categoryID = categoryID;
    }
}
