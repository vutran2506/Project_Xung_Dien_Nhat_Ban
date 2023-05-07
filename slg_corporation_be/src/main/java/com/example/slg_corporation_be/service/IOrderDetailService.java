package com.example.slg_corporation_be.service;

import com.example.slg_corporation_be.dto.IOrderDetailDTO;
import com.example.slg_corporation_be.model.OrderDetail;
import com.example.slg_corporation_be.model.Product;

import java.util.List;

public interface IOrderDetailService {

    List<IOrderDetailDTO> getCartByUser(String email);

    List<Product> findProductsByEmail(String email);
    void updateOrDetail(long id, int amount);
    OrderDetail findByIdProduct(long id);
    void remove(long idProduct);
}
