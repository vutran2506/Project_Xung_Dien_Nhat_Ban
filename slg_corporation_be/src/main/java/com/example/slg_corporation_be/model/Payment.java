package com.example.slg_corporation_be.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean status;
    private String type;
    @OneToMany(mappedBy = "payment")
    private Set<Orders> ordersSet;

    public Payment(int id, boolean status, String type, Set<Orders> ordersSet) {
        this.id = id;
        this.status = status;
        this.type = type;
        this.ordersSet = ordersSet;
    }

    public Payment() {
    }

    public Payment(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Orders> getOrdersSet() {
        return ordersSet;
    }

    public void setOrdersSet(Set<Orders> ordersSet) {
        this.ordersSet = ordersSet;
    }
}
