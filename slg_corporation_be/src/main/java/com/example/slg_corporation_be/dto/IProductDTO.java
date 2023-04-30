package com.example.slg_corporation_be.dto;

import com.example.slg_corporation_be.model.Image;
import com.example.slg_corporation_be.model.Product;

import java.util.List;

public class IProductDTO {
    private long id;
    private String name;
    private double price;
    private String description;
    private int quantity;
    private String dateSubmitted;
    private boolean isDeleted;
    private List<Image> imageList;

    public IProductDTO(long id) {
        this.id = id;
    }
    public IProductDTO() {
    }

    public IProductDTO(long id, String name, double price, String description, int quantity, String dateSubmitted, boolean isDeleted, List<Image> imageList) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.dateSubmitted = dateSubmitted;
        this.isDeleted = isDeleted;
        this.imageList = imageList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(String dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }
}