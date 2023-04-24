package com.example.slg_corporation_be.dto;

public interface IProductDTO {
    long getId();

    String getName();

    double getPrice();

    String getDescription();

    int getQuantity();

    String getDate_submitted();

    String getImage();
}