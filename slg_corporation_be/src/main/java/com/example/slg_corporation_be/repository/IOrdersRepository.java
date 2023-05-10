package com.example.slg_corporation_be.repository;

import com.example.slg_corporation_be.model.Orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface IOrdersRepository extends JpaRepository<Orders,Long> {
@Query(value = "SELECT o.* FROM orders  o join app_user ap on ap.id = o.id_app_user\n" +
        "where ap.email =:email and o.status_payment =false",nativeQuery = true)
    Orders findOrderByEmail(String email);
@Query(value = "select o.* from orders o join app_user ap on o.id_app_user = ap.id where ap.email =:email and status_payment = true",nativeQuery = true)
    List<Orders>getListOrderHistory(String email);
}