package com.example.slg_corporation_be.repository;



import com.example.slg_corporation_be.model.OrderDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Long> {

@Query(value = "select od.* from order_detail od join orders o on od.id_order = o.id\n" +
        "            join app_user ap on ap.id= o.id_app_user\n" +
        "            where od.id_product =:idProduct and ap.email =:email",nativeQuery = true)
OrderDetail findByIdProductAndByEmail(long idProduct, String email);

    @Query(value = "select od.* from order_detail od join orders o on od.id_order = o.id\n" +
            "            join app_user ap on ap.id= o.id_app_user\n" +
            "            where od.id_product =:idProduct ",nativeQuery = true)
    OrderDetail findByIdProduct(long idProduct);

}
