package com.example.slg_corporation_be.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double totalPrice;
    private String methodPay;

    @OneToMany(mappedBy = "orders")
    @JsonBackReference
    private Set<OrderDetail> orderDetailSet;
    @ManyToOne
    @JoinColumn(name = "id_appUser", referencedColumnName = "id")
    private AppUser appUser;

    public Orders() {
    }
    public Orders(long id, double totalPrice, String methodPay, Set<OrderDetail> orderDetailSet, AppUser appUser) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.methodPay = methodPay;
        this.orderDetailSet = orderDetailSet;
        this.appUser = appUser;
    }

    public double getTotalPrice() {
        double sum =0.0;
        for (OrderDetail odd: orderDetailSet ){
            sum += odd.getProduct().getPrice();
        }
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getMethodPay() {

        return methodPay;
    }
    public void setMethodPay(String methodPay) {
        this.methodPay = methodPay;
    }

    public Set<OrderDetail> getOrderDetailSet() {
        return orderDetailSet;
    }

    public void setOrderDetailSet(Set<OrderDetail> orderDetailSet) {
        this.orderDetailSet = orderDetailSet;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
