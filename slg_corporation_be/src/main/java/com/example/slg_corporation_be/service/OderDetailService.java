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

    private IOrderDetailRepository iOrderRepository;
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

}
