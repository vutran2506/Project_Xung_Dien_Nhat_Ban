package com.example.slg_corporation_be.service;

import com.example.slg_corporation_be.dto.IProductDTO;

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
    public Page<IProductDTO> getAllPageProduct(String name, long origin, Pageable pageable) {
        return this.iProductRepository.getAllPageProduct(name,origin,pageable);
    }

    @Override
    public IProductDTO findProductById(long id) {
        return this.iProductRepository.findProductById(id);
    }
}
