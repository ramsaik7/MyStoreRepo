package com.example.demo.entity;


import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class OrderEntity {

	@Id
	private int orderId;
	
	private Date orderDate;
	
	
	@ManyToOne
	private OrderPriority orderPriority;
	
	@ManyToOne
	private OrderStatus orderStatus;
	
	@ManyToOne
	private Customer customer;
	
	@OneToMany(mappedBy = "order")
	private List<Shipment> shipments;
	
	

	public OrderEntity() {
		super();
	}

	public OrderEntity(int orderId, Date orderDate, OrderPriority orderPriority, OrderStatus orderStatus,
			Customer customer, List<Shipment> shipments) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.orderPriority = orderPriority;
		this.orderStatus = orderStatus;
		this.customer = customer;
		this.shipments = shipments;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public OrderPriority getOrderPriority() {
		return orderPriority;
	}

	public void setOrderPriority(OrderPriority orderPriority) {
		this.orderPriority = orderPriority;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Shipment> getShipments() {
		return shipments;
	}

	public void setShipments(List<Shipment> shipments) {
		this.shipments = shipments;
	}
	
	
	
	
	
	
	
	
	
}
