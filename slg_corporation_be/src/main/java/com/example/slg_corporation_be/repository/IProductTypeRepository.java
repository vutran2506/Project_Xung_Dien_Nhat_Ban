package com.example.slg_corporation_be.repository;

import com.example.slg_corporation_be.model.Image;
import com.example.slg_corporation_be.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductTypeRepository extends JpaRepository<ProductType,Long> {
}
