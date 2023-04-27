package com.example.slg_corporation_be.repository;

import com.example.slg_corporation_be.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrdersRepository extends JpaRepository<Orders,Long>{
//Orders findByCodeOrderContaining(long idOrder);
}