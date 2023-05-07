package com.example.slg_corporation_be.service;

import com.example.slg_corporation_be.model.Orders;

public interface IOrdersService {
   void addOrders(long idProduct, String email, int amount);
   void buyNowOrders(long idProduct, String email);
   Orders findOrderByEmail(String email);
}
