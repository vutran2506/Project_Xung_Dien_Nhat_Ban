package com.example.slg_corporation_be.service;

import com.example.slg_corporation_be.dto.IProductDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {
    Page<IProductDTO>getAllPageProduct(String name, long origin, Pageable pageable);
    IProductDTO findProductById(long id);

}
