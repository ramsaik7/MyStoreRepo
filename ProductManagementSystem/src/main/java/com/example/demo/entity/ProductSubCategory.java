package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProductSubCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productSubCategoryId;
	
	private String productSubCategoryName;
	
	@ManyToOne
	private ProductCategory productCategory;
	
	

	public ProductSubCategory() {
		super();
	}



	public ProductSubCategory(int productSubCategoryId, String productSubCategoryName,
			ProductCategory productCategory) {
		super();
		this.productSubCategoryId = productSubCategoryId;
		this.productSubCategoryName = productSubCategoryName;
		this.productCategory = productCategory;
	}



	public int getProductSubCategoryId() {
		return productSubCategoryId;
	}



	public void setProductSubCategoryId(int productSubCategoryId) {
		this.productSubCategoryId = productSubCategoryId;
	}



	public String getProductSubCategoryName() {
		return productSubCategoryName;
	}



	public void setProductSubCategoryName(String productSubCategoryName) {
		this.productSubCategoryName = productSubCategoryName;
	}



	public ProductCategory getProductCategory() {
		return productCategory;
	}



	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	
	
}
