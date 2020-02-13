package com.example.demo.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Shipment{

	@Id
	private int shipmentId;
	
	private double sales;
	
	private int quantityOrderedNew;
	
	private double discount;
	
	private double shippingCost;
	
	private Date shipDate;
	
	private double profit;
	
	@ManyToOne
	private Product product; 
	
	@ManyToOne
	private OrderEntity order;
	
	@ManyToOne
	private ShippingMode shippingMode;
	
	@ManyToOne
	private ProductContainer productContainer;
	
	

	public Shipment() {
		super();
	}

	public Shipment(int shipmentId, double sales, int quantityOrderedNew, double discount, double shippingCost,
			Date shipDate, double profit, Product product, OrderEntity order, ShippingMode shippingMode,
			ProductContainer productContainer) {
		super();
		this.shipmentId = shipmentId;
		this.sales = sales;
		this.quantityOrderedNew = quantityOrderedNew;
		this.discount = discount;
		this.shippingCost = shippingCost;
		this.shipDate = shipDate;
		this.profit = profit;
		this.product = product;
		this.order = order;
		this.shippingMode = shippingMode;
		this.productContainer = productContainer;
	}

	public int getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(int shipmentId) {
		this.shipmentId = shipmentId;
	}

	public double getSales() {
		return sales;
	}

	public void setSales(double sales) {
		this.sales = sales;
	}

	public int getQuantityOrderedNew() {
		return quantityOrderedNew;
	}

	public void setQuantityOrderedNew(int quantityOrderedNew) {
		this.quantityOrderedNew = quantityOrderedNew;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
	}

	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}

	public ShippingMode getShippingMode() {
		return shippingMode;
	}

	public void setShippingMode(ShippingMode shippingMode) {
		this.shippingMode = shippingMode;
	}

	public ProductContainer getProductContainer() {
		return productContainer;
	}

	public void setProductContainer(ProductContainer productContainer) {
		this.productContainer = productContainer;
	}
	
	
	
}
