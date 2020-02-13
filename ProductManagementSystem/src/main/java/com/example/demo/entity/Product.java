package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;
	
	private String productName;
	
	private double productBaseMargin;
	
	private double unitPrice;
	
	@ManyToOne
	private ProductSubCategory productSubCategory;
	
	@OneToMany(mappedBy = "product")
	private List<Shipment> shipments;
	
	

	public Product() {
		super();
	}



	public Product(int productId, String productName, double productBaseMargin, double unitPrice,
			ProductSubCategory productSubCategory, List<Shipment> shipments) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productBaseMargin = productBaseMargin;
		this.unitPrice = unitPrice;
		this.productSubCategory = productSubCategory;
		this.shipments = shipments;
	}



	public int getProductId() {
		return productId;
	}



	public void setProductId(int productId) {
		this.productId = productId;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public double getProductBaseMargin() {
		return productBaseMargin;
	}



	public void setProductBaseMargin(double productBaseMargin) {
		this.productBaseMargin = productBaseMargin;
	}



	public double getUnitPrice() {
		return unitPrice;
	}



	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}



	public ProductSubCategory getProductSubCategory() {
		return productSubCategory;
	}



	public void setProductSubCategory(ProductSubCategory productSubCategory) {
		this.productSubCategory = productSubCategory;
	}



	public List<Shipment> getShipments() {
		return shipments;
	}



	public void setShipments(List<Shipment> shipments) {
		this.shipments = shipments;
	}
	

	
	
	

}
