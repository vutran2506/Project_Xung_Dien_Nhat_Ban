package com.example.slg_corporation_be.model;



import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 45,nullable = false)
    private String name;
    @Column(unique = true)
    private String idCard;
    private boolean gender;
    private String dateOfBirth;
    @Column(nullable = false)
    private String numberPhone;
    @Column(length = 45,unique = true)
    private String email;
    @Column(length = 100,nullable = false)
    private String address;
    private boolean isDeleted;
    @OneToOne(mappedBy = "appUser")
    @JsonBackReference
    private Account account;

    @OneToMany(mappedBy = "appUser")
    @JsonBackReference
    private Set<Orders>ordersSet;

    public AppUser() {
    }

    public AppUser(long id, String name, String idCard, boolean gender, String dateOfBirth, String numberPhone, String email, String address, boolean isDeleted, Account account, Set<Orders> ordersSet) {
        this.id = id;
        this.name = name;
        this.idCard = idCard;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.numberPhone = numberPhone;
        this.email = email;
        this.address = address;
        this.isDeleted = isDeleted;
        this.account = account;
        this.ordersSet = ordersSet;
    }

    public AppUser(String email) {
        this.email = email;
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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<Orders> getOrdersSet() {
        return ordersSet;
    }

    public void setOrdersSet(Set<Orders> ordersSet) {
        this.ordersSet = ordersSet;
    }
}
