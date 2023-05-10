package com.example.slg_corporation_be.repository;



import com.example.slg_corporation_be.dto.IOrderDetailDTO;
import com.example.slg_corporation_be.model.OrderDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Long> {

@Query(value = "select od.* from order_detail od join orders o on od.id_order = o.id\n" +
        "            join app_user ap on ap.id= o.id_app_user\n" +
        "            where od.id_product =:idProduct and ap.email =:email and od.is_delete =false",nativeQuery = true)
OrderDetail findByIdProductAndByEmail(long idProduct, String email);

    @Query(value = "select od.* from order_detail od\n" +
            " join product p on p.id= od.id_product\n" +
            "join orders o on o.id = od.id_order\n" +
            "join app_user ap on ap.id = o.id_app_user\n" +
            "where ap.email =:email and od.is_delete= false", nativeQuery = true)
    List<OrderDetail> getAllOrderDetailByEmail(@Param("email") String email);
    @Query(value = "select od.* from orders o join app_user ap on o.id_app_user = ap.id\n" +
            "join order_detail od on o.id = od.id_order\n" +
            " where ap.email =:email and o.status_payment = true and od.is_delete = true and o.id =:idOrder",nativeQuery = true)
    List<OrderDetail>getListOrderHistory(String email,long idOrder);
}
