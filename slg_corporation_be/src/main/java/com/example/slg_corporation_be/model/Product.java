package com.example.slg_corporation_be.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double price;
    private String description;
    private int quantity;
    private String dateSubmitted;
    private boolean isDeleted;

    @OneToMany(mappedBy = "product")
    @JsonBackReference
    private Set<OrderDetail> orderDetailSet;

    @ManyToOne
    @JoinColumn(name = "id_productType",referencedColumnName = "id")
    private ProductType productType;

    @OneToMany(mappedBy = "product")
    @JsonBackReference
    private Set<Image>imageSet;

    public Product() {
    }

    public Product(long id, String name, double price, String description, int quantity, String dateSubmitted, boolean isDeleted, Set<OrderDetail> orderDetailSet, ProductType productType, Set<Image> imageSet) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.dateSubmitted = dateSubmitted;
        this.isDeleted = isDeleted;
        this.orderDetailSet = orderDetailSet;
        this.productType = productType;
        this.imageSet = imageSet;
    }

    public Product(long idProduct) {
        this.id = id;
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

    public Set<OrderDetail> getOrderDetailSet() {
        return orderDetailSet;
    }

    public void setOrderDetailSet(Set<OrderDetail> orderDetailSet) {
        this.orderDetailSet = orderDetailSet;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Set<Image> getImageSet() {
        return imageSet;
    }

    public void setImageSet(Set<Image> imageSet) {
        this.imageSet = imageSet;
    }
}
