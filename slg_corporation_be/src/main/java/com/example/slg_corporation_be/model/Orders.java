package com.example.slg_corporation_be.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String codeOrder;
    private String dayPurchase;
    private String methodPay;

    @OneToMany(mappedBy = "orders")
    @JsonBackReference
    private Set<OrderDetail> orderDetailSet;
    @ManyToOne
    @JoinColumn(name = "id_appUser", referencedColumnName = "id")
    private AppUser appUser;

    public Orders() {
    }

    public Orders(long id, String codeOrder, String dayPurchase, String methodPay, Set<OrderDetail> orderDetailSet, AppUser appUser) {
        this.id = id;
        this.codeOrder = codeOrder;
        this.dayPurchase = dayPurchase;
        this.methodPay = methodPay;
        this.orderDetailSet = orderDetailSet;
        this.appUser = appUser;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodeOrder() {
        return codeOrder;
    }

    public void setCodeOrder(String codeOrder) {
        this.codeOrder = codeOrder;
    }

    public String getDayPurchase() {
        return dayPurchase;
    }

    public void setDayPurchase(String dayPurchase) {
        this.dayPurchase = dayPurchase;
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
