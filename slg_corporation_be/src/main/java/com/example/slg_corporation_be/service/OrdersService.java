package com.example.slg_corporation_be.service;

import com.example.slg_corporation_be.model.*;
import com.example.slg_corporation_be.repository.IAppUserRepository;
import com.example.slg_corporation_be.repository.IOrderDetailRepository;
import com.example.slg_corporation_be.repository.IOrdersRepository;
import com.example.slg_corporation_be.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrdersService implements IOrdersService {
    @Autowired
    private IOrdersRepository iOrdersRepository;
    @Autowired
    private IProductRepository iProductRepository;
    @Autowired
    private IOrderDetailRepository iOrderDetailRepository;
    @Autowired
    private IAppUserRepository iAppUserRepository;

    @Override
    public void addOrders(long idProduct, String email, int amount) {
        Orders order = this.iOrdersRepository.findOrderByEmail(email);
        OrderDetail orderDetail = new OrderDetail();
        if (order == null){
            Orders orders = new Orders();
            orders.setDayPurchase(String.valueOf(new Date()));
            AppUser appUser = this.iAppUserRepository.findByEmail(email);
            orders.setAppUser(appUser);
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
    public void buyNowOrders(long idProduct, String email) {
        Orders order = this.iOrdersRepository.findOrderByEmail(email);
        OrderDetail orderDetail = new OrderDetail();
        if (order == null){
            Orders orders = new Orders();
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(date);
            orders.setDayPurchase(dateString);
            AppUser appUser = this.iAppUserRepository.findByEmail(email);
            orders.setAppUser(appUser);
            this.iOrdersRepository.save(orders);
            orderDetail.setAmount(1);
            orderDetail.setDayPurchase(dateString);
            orderDetail.setDelete(false);
            orderDetail.setProduct(new Product(idProduct));
            orderDetail.setOrders(orders);
            this.iOrderDetailRepository.save(orderDetail);
        }else {
            OrderDetail orderDetailExist = this.iOrderDetailRepository.findByIdProductAndByEmail(idProduct, email);
            if (orderDetailExist != null){
                orderDetailExist.setAmount(orderDetailExist.getAmount()+1);
                this.iOrderDetailRepository.save(orderDetailExist);
            }else {
                Orders orders = this.iOrdersRepository.findOrderByEmail(email);
                orderDetail.setAmount(1);
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = formatter.format(date);
                orderDetail.setDayPurchase(dateString);
                orderDetail.setDelete(false);
                Product product = this.iProductRepository.findById(idProduct).orElse(null);
                orderDetail.setProduct(product);
                orderDetail.setOrders(orders);
                this.iOrderDetailRepository.save(orderDetail);
            }

        }
    }

    @Override
    public Orders findOrderById(String email) {
        return this.iOrdersRepository.findOrderByEmail(email);
    }

    @Override
    public void updateOrder(Orders orders) {
        this.iOrdersRepository.save(orders);
    }

    @Override
    public List<Orders> getHistoryListOrder(String email) {

        return this.iOrdersRepository.getListOrderHistory(email);
    }


}
