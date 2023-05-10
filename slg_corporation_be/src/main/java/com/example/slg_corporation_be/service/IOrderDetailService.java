package com.example.slg_corporation_be.service;

import com.example.slg_corporation_be.dto.IOrderDetailDTO;
import com.example.slg_corporation_be.model.OrderDetail;
import com.example.slg_corporation_be.model.Product;

import java.util.List;

public interface IOrderDetailService {

    List<IOrderDetailDTO> getCartByUser(String email);

    List<Product> findProductsByEmail(String email);
    void updateOrDetail(long id, int amount,String email);
    void remove(long idProduct,String email);
    void payCart(OrderDetail orderDetail);
    List<OrderDetail> findOrderDetailByEmail(String email);
    List<OrderDetail>getHistoryOrderDetail(String email,long idOrder);
}
