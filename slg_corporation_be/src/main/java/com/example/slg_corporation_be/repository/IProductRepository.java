package com.example.slg_corporation_be.repository;

import com.example.slg_corporation_be.dto.IProductDTO;
import com.example.slg_corporation_be.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface
IProductRepository extends JpaRepository<Product,Long>{
    @Query(value = "SELECT * FROM product  where is_deleted = false and `name` like :name",nativeQuery = true)
    Page<Product> getAllPageProduct(String name, Pageable pageable);

    @Query(value = "SELECT * FROM product where id=:id and is_deleted = false",nativeQuery = true)
    Product findProductDTOById(long id);
}
