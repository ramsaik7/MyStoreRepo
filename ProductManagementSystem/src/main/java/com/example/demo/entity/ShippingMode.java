package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ShippingMode {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int shippingModeId;
	
	private String shippingMode;
	
	@OneToMany(mappedBy = "shippingMode")
	private List<Shipment> shipment;
	

	public ShippingMode() {
		super();
	}

	public ShippingMode(int shippingModeId, String shippingMode, List<Shipment> shipment) {
		super();
		this.shippingModeId = shippingModeId;
		this.shippingMode = shippingMode;
		this.shipment = shipment;
	}

	public int getShippingModeId() {
		return shippingModeId;
	}

	public void setShippingModeId(int shippingModeId) {
		this.shippingModeId = shippingModeId;
	}

	
	public String getShippingMode() {
		return shippingMode;
	}

	public void setShippingMode(String shippingMode) {
		this.shippingMode = shippingMode;
	}

	public List<Shipment> getShipment() {
		return shipment;
	}

	public void setShipment(List<Shipment> shipment) {
		this.shipment = shipment;
	}
	
	
}
