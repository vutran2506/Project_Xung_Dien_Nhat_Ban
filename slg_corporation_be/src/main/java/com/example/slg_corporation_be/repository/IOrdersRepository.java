package com.example.slg_corporation_be.repository;

import com.example.slg_corporation_be.model.Orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IOrdersRepository extends JpaRepository<Orders,Long> {
@Query(value = "SELECT o.* FROM orders  o join app_user ap on ap.id = o.id_app_user\n" +
        "where ap.email =:email",nativeQuery = true)
    Orders findOrderByEmail(String email);
}