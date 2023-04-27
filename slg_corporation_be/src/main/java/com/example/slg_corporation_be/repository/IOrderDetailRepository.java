package com.example.slg_corporation_be.repository;

import com.example.slg_corporation_be.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail,Long> {

}
