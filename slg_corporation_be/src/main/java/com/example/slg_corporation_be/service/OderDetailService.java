package com.example.slg_corporation_be.service;

import com.example.slg_corporation_be.dto.IOrderDetailDTO;
import com.example.slg_corporation_be.model.OrderDetail;
import com.example.slg_corporation_be.model.Product;
import com.example.slg_corporation_be.repository.IOrderDetailRepository;
import com.example.slg_corporation_be.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OderDetailService implements IOrderDetailService {
    @Autowired

    private IOrderDetailRepository iOrderDetailRepository;
    @Autowired
    private IProductRepository iProductRepository;

    @Override
    public List<IOrderDetailDTO> getCartByUser(String email) {
        return this.iProductRepository.getAllOrderDetailByUser(email);
    }

    @Override
    public List<Product> findProductsByEmail(String email) {
        return iProductRepository.findProductsByEmail(email);
    }

    @Override
    public void updateOrDetail(long id, int amount,String email) {
        OrderDetail orderDetail = this.iOrderDetailRepository.findByIdProductAndByEmail(id,email);
        orderDetail.setAmount(amount);
        this.iOrderDetailRepository.save(orderDetail);
    }

    @Override
    public void remove(long idProduct,String email) {
        OrderDetail orderDetail = this.iOrderDetailRepository.findByIdProductAndByEmail(idProduct, email);
        this.iOrderDetailRepository.delete(orderDetail);
    }

    @Override
    public void payCart(OrderDetail orderDetail) {
        this.iOrderDetailRepository.save(orderDetail);
    }

    @Override
    public List<OrderDetail> findOrderDetailByEmail(String email) {
        return this.iOrderDetailRepository.getAllOrderDetailByEmail(email);
    }

    @Override
    public List<OrderDetail> getHistoryOrderDetail(String email, long idOrder) {
        return this.iOrderDetailRepository.getListOrderHistory(email,idOrder);
    }

}
