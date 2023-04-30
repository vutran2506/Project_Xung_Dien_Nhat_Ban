package com.example.slg_corporation_be.repository;

import com.example.slg_corporation_be.dto.IOrderDetailDTO;
import com.example.slg_corporation_be.dto.IProductDTO;
import com.example.slg_corporation_be.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface
IProductRepository extends JpaRepository<Product,Long>{
    @Query(value = "SELECT * FROM product where is_deleted = false and `name` like :name",nativeQuery = true)
    Page<Product> getAllPageProduct(String name, Pageable pageable);

    @Query(value = "SELECT * FROM product where id=:id and is_deleted = false",nativeQuery = true)
    Product findProductDTOById(long id);

    @Query(value = "select  p.*,od.amount from order_detail od\n" +
            " join product p on p.id= od.id_product\n" +
            "join orders o on o.id = od.id_order\n" +
            "join app_user ap on ap.id = o.id_app_user\n" +
            "join payment pm on pm.id = o.payment_id \n" +
            "where ap.email =:email", nativeQuery = true)
    List<IOrderDetailDTO> getAllOrderDetailByUser(@Param("email") String email);

    @Query(value = "select p.* from order_detail od\n" +
            " join product p on p.id= od.id_product\n" +
            "join orders o on o.id = od.id_order\n" +
            "join app_user ap on ap.id = o.id_app_user\n" +
            "join payment pm on pm.id = o.payment_id \n" +
            "where ap.email = :email", nativeQuery = true)
    List<Product> findProductsByEmail(String email);
}
