package com.example.slg_corporation_be.service;

import com.example.slg_corporation_be.model.*;
import com.example.slg_corporation_be.repository.IOrderDetailRepository;
import com.example.slg_corporation_be.repository.IOrdersRepository;
import com.example.slg_corporation_be.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrdersService implements IOrdersService {
    @Autowired
    private IOrdersRepository iOrdersRepository;
    @Autowired
    private IProductRepository iProductRepository;
    @Autowired
    private IOrderDetailRepository iOrderDetailRepository;

    @Override
    public void addOrders(long idProduct, String email, int amount) {
        Orders order = this.iOrdersRepository.findOrderByEmail(email);
        OrderDetail orderDetail = new OrderDetail();
        if (order == null){
            Orders orders = new Orders();
            orders.setDayPurchase(String.valueOf(new Date()));
            orders.setAppUser(new AppUser(email));
            this.iOrdersRepository.save(orders);
            orderDetail.setAmount(amount);
            orderDetail.setDayPurchase(String.valueOf(new Date()));
            orderDetail.setDelete(false);
            orderDetail.setProduct(new Product(idProduct));
            orderDetail.setOrders(orders);
            this.iOrderDetailRepository.save(orderDetail);
        }else {
            OrderDetail orderDetailExist = this.iOrderDetailRepository.findByIdProductAndByEmail(idProduct, email);
            if (orderDetailExist != null){
                orderDetailExist.setAmount(orderDetailExist.getAmount()+amount);
                this.iOrderDetailRepository.save(orderDetailExist);
            }else {
                Orders orders = this.iOrdersRepository.findOrderByEmail(email);
                orderDetail.setAmount(amount);
                orderDetail.setDayPurchase(String.valueOf(new Date()));
                orderDetail.setDelete(false);
                Product product = this.iProductRepository.findById(idProduct).orElse(null);
                orderDetail.setProduct(product);
                orderDetail.setOrders(orders);
                this.iOrderDetailRepository.save(orderDetail);
            }

        }
    }
    @Override
    public Orders findOrderByEmail(String email) {
        return this.iOrdersRepository.findOrderByEmail(email);
    }

}
