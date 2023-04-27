package com.example.slg_corporation_be.service;

import com.example.slg_corporation_be.model.OrderDetail;

import java.util.List;

public interface IOrderDetailService {
   List<OrderDetail> addCartByUser(long idProduct, long idUser );
   List<OrderDetail> getCartByUser(long id);
   List<OrderDetail> removeCartByUser(long idCard, long idUser);

}
