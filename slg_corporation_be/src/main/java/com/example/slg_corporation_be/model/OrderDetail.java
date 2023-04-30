package com.example.slg_corporation_be.model;

import javax.persistence.*;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int amount;
    private boolean isDelete;
    private String dayPurchase;
    @ManyToOne
    @JoinColumn(name = "id_order",referencedColumnName = "id")
    private Orders orders;
    @ManyToOne
    @JoinColumn(name = "id_product",referencedColumnName = "id")
    private Product product;

    public OrderDetail() {
    }

    public OrderDetail(long id, int amount, boolean isDelete, String dayPurchase, Orders orders, Product product) {
        this.id = id;
        this.amount = amount;
        this.isDelete = isDelete;
        this.dayPurchase = dayPurchase;
        this.orders = orders;
        this.product = product;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public String getDayPurchase() {
        return dayPurchase;
    }

    public void setDayPurchase(String dayPurchase) {
        this.dayPurchase = dayPurchase;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
