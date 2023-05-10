package com.example.slg_corporation_be.service;



import com.example.slg_corporation_be.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    Page<Product>getAllPageProduct(String name, Pageable pageable);
    Product findProductDTOById(long id);
    Product findProductById(long id);
List<Product>getAllProduct();
}
