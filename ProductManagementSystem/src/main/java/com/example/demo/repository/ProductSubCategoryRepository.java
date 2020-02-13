package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ProductSubCategory;


@Repository
public interface ProductSubCategoryRepository extends JpaRepository<ProductSubCategory, Integer>{

	
	public ProductSubCategory findByProductSubCategoryName(String productSubCategoryName);
}
