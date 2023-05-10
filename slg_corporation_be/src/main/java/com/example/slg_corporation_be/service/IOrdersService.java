package com.example.slg_corporation_be.service;

import com.example.slg_corporation_be.model.Orders;

import java.util.List;

public interface IOrdersService {
   void addOrders(long idProduct, String email, int amount);
   void buyNowOrders(long idProduct, String email);
   Orders findOrderById(String email);
   void updateOrder(Orders orders);
   List<Orders> getHistoryListOrder(String email);
}
