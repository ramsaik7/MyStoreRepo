package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.OrderStatus;


@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer>{
	
	public OrderStatus findByOrderStatusName(String orderStatus);

}
