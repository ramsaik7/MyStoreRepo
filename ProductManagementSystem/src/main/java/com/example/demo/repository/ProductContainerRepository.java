package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ProductContainer;

@Repository
public interface ProductContainerRepository extends JpaRepository<ProductContainer, Integer>{

	public ProductContainer findByProductContainerName(String productContaninerName);
}
