package com.example.demo.entity;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class OrderStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderStatusId;
	
	private String orderStatusName;
	
	@OneToMany(mappedBy = "orderStatus")
	private List<OrderEntity> order;
	
	

	public OrderStatus() {
		super();
	}



	public OrderStatus(int orderStatusId, String orderStatusName, List<OrderEntity> order) {
		super();
		this.orderStatusId = orderStatusId;
		this.orderStatusName = orderStatusName;
		this.order = order;
	}



	public int getOrderStatusId() {
		return orderStatusId;
	}



	public void setOrderStatusId(int orderStatusId) {
		this.orderStatusId = orderStatusId;
	}



	public String getOrderStatusName() {
		return orderStatusName;
	}



	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}



	public List<OrderEntity> getOrder() {
		return order;
	}



	public void setOrder(List<OrderEntity> order) {
		this.order = order;
	}



	
	
	
}
