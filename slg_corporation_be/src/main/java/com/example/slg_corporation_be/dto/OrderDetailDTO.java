package com.example.slg_corporation_be.dto;

import com.example.slg_corporation_be.model.Image;
import com.example.slg_corporation_be.model.Product;

import java.util.List;

public class OrderDetailDTO {
    private Long id;
    private String name;
    private int amount;

    private double price;
    private List<Image> imageList;

    public OrderDetailDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

}
