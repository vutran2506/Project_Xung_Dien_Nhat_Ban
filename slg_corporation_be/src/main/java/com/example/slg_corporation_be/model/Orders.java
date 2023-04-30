package com.example.slg_corporation_be.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String dayPurchase;

    @OneToMany(mappedBy = "orders")
    @JsonBackReference
    private Set<OrderDetail> orderDetailSet;
    @ManyToOne
    @JoinColumn(name = "id_appUser", referencedColumnName = "id")
    private AppUser appUser;

    public Orders() {
    }
  @ManyToOne
    @JoinColumn(name = "payment_id",referencedColumnName = "id")
    private  Payment payment;

    public Orders(long id, String dayPurchase, Set<OrderDetail> orderDetailSet, AppUser appUser, Payment payment) {
        this.id = id;
        this.dayPurchase = dayPurchase;
        this.orderDetailSet = orderDetailSet;
        this.appUser = appUser;
        this.payment = payment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDayPurchase() {
        return dayPurchase;
    }

    public void setDayPurchase(String dayPurchase) {
        this.dayPurchase = dayPurchase;
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

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
