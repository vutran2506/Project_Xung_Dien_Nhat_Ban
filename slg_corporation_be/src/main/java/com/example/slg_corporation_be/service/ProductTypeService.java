package com.example.slg_corporation_be.service;

import com.example.slg_corporation_be.model.Image;
import com.example.slg_corporation_be.model.ProductType;
import com.example.slg_corporation_be.repository.IProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeService implements IProductTypeService {
    @Autowired
    private IProductTypeRepository iProductTypeRepository;
    @Override
    public List<ProductType> getAll() {
        return this.iProductTypeRepository.findAll();
    }
}
