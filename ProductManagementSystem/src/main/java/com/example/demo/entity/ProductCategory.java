package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ProductCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productCategoryId;
	
	private String productCategoryName;
	
	
	@OneToMany(mappedBy = "productCategory")
	List<ProductSubCategory> productSubCategories;

	

	public ProductCategory() {
		super();
	}



	public ProductCategory(int productCategoryId, String productCategoryName,
			List<ProductSubCategory> productSubCategories) {
		super();
		this.productCategoryId = productCategoryId;
		this.productCategoryName = productCategoryName;
		this.productSubCategories = productSubCategories;
	}



	public int getProductCategoryId() {
		return productCategoryId;
	}



	public void setProductCategoryId(int productCategoryId) {
		this.productCategoryId = productCategoryId;
	}



	public String getProductCategoryName() {
		return productCategoryName;
	}



	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}



	public List<ProductSubCategory> getProductSubCategories() {
		return productSubCategories;
	}



	public void setProductSubCategories(List<ProductSubCategory> productSubCategories) {
		this.productSubCategories = productSubCategories;
	}
	
	
	
}
