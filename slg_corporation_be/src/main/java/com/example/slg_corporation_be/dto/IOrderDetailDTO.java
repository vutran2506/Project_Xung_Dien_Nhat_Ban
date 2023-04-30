package com.example.slg_corporation_be.dto;

import com.example.slg_corporation_be.model.Image;

import java.util.List;

public interface IOrderDetailDTO {

    public String getName();

    public int getAmount();

    public double getPrice();

    public void setImageList(List<Image> imageList);

    Long getId();

    List<Image> getImageList ();
}
