package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class OrderPriority {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderPriorityId;
	
	private String orderPriorityName;
	
	@OneToMany(mappedBy = "orderPriority")
	private List<OrderEntity> orders;
	
	

	public OrderPriority() {
		super();
	}



	public OrderPriority(int orderPriorityId, String orderPriorityName, List<OrderEntity> orders) {
		super();
		this.orderPriorityId = orderPriorityId;
		this.orderPriorityName = orderPriorityName;
		this.orders = orders;
	}



	public int getOrderPriorityId() {
		return orderPriorityId;
	}



	public void setOrderPriorityId(int orderPriorityId) {
		this.orderPriorityId = orderPriorityId;
	}



	public String getOrderPriorityName() {
		return orderPriorityName;
	}



	public void setOrderPriorityName(String orderPriorityName) {
		this.orderPriorityName = orderPriorityName;
	}



	public List<OrderEntity> getOrders() {
		return orders;
	}



	public void setOrders(List<OrderEntity> orders) {
		this.orders = orders;
	}

	
	
	
}
