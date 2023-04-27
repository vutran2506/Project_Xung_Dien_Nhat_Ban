package com.example.slg_corporation_be.service;

import com.example.slg_corporation_be.dto.IProductDTO;

import com.example.slg_corporation_be.model.Product;
import com.example.slg_corporation_be.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository iProductRepository;

    @Override
    public Page<Product> getAllPageProduct(String name, Pageable pageable) {
        return this.iProductRepository.getAllPageProduct("%"+name+"%",pageable);
    }

    @Override
    public Product findProductDTOById(long id) {
        return this.iProductRepository.findProductDTOById(id);
    }

    @Override
    public Product findProductById(long id) {
        return this.findProductById(id);
    }
}
