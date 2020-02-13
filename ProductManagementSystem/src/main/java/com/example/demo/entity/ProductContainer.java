package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ProductContainer {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ProductConatinerId;
	
	private String productContainerName;
	
	@OneToMany(mappedBy = "productContainer")
	private List<Shipment> shipment;
	
	
	

	public ProductContainer() {
		super();
	}


	public ProductContainer(int productConatinerId, String productContainerName, List<Shipment> shipment) {
		super();
		ProductConatinerId = productConatinerId;
		this.productContainerName = productContainerName;
		this.shipment = shipment;
	}


	public int getProductConatinerId() {
		return ProductConatinerId;
	}


	public void setProductConatinerId(int productConatinerId) {
		ProductConatinerId = productConatinerId;
	}


	public String getProductContainerName() {
		return productContainerName;
	}


	public void setProductContainerName(String productContainerName) {
		this.productContainerName = productContainerName;
	}


	public List<Shipment> getShipment() {
		return shipment;
	}


	public void setShipment(List<Shipment> shipment) {
		this.shipment = shipment;
	}

	
	
	
	
}
