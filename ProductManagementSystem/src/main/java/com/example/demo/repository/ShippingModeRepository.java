package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ShippingMode;

@Repository
public interface ShippingModeRepository extends JpaRepository<ShippingMode, Integer>{

	
	public ShippingMode findByShippingMode(String shippingMode);
}
