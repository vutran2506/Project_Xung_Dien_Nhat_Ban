package com.example.slg_corporation_be.service;

import com.example.slg_corporation_be.model.OrderDetail;
import com.example.slg_corporation_be.repository.IOrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OderDetailService implements IOrderDetailService {
    @Autowired

    private IOrderDetailRepository iOrderRepository;
    @Override
    public List<OrderDetail> addCartByUser(long idProduct, long idUser) {
        return null;
    }

    @Override
    public List<OrderDetail> getCartByUser(long id) {
        return null;
    }

    @Override
    public List<OrderDetail> removeCartByUser(long idCard, long idUser) {
        return null;
    }
}
