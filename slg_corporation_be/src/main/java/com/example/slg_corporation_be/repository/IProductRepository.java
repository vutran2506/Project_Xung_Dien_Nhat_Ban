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
public interface IProductRepository extends JpaRepository<Product,Long>{
    @Query(value = "SELECT p.*,i.image FROM product p join image i on p.id = i.id_product where p.is_deleted = false",nativeQuery = true)
    Page<IProductDTO> getAllPageProduct(String name, long origin, Pageable pageable);

    @Query(value = "SELECT p.*,i.image FROM product p join image i on p.id = i.id_product where p.id=:id and p.is_deleted = false",nativeQuery = true)
    IProductDTO findProductById(long id);
}
