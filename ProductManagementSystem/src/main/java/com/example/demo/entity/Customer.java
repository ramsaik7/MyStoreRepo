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
public class Customer {

	@Id
	private int customerId;
	
	private String customerName;
	
	@ManyToOne
	private CustomerSegment customerSegment;
	
	@ManyToOne
	private PostalCode postalcode;
	
	@OneToMany(mappedBy = "customer")
	private List<OrderEntity> orders;
	
	

	public Customer() {
		super();
	}



	public Customer(int customerId, String customerName, CustomerSegment customerSegment, PostalCode postalcode,
			List<OrderEntity> orders) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerSegment = customerSegment;
		this.postalcode = postalcode;
		this.orders = orders;
	}



	public int getCustomerId() {
		return customerId;
	}



	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}



	public String getCustomerName() {
		return customerName;
	}



	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



	public CustomerSegment getCustomerSegment() {
		return customerSegment;
	}



	public void setCustomerSegment(CustomerSegment customerSegment) {
		this.customerSegment = customerSegment;
	}



	public PostalCode getPostalcode() {
		return postalcode;
	}



	public void setPostalcode(PostalCode postalcode) {
		this.postalcode = postalcode;
	}



	public List<OrderEntity> getOrders() {
		return orders;
	}



	public void setOrders(List<OrderEntity> orders) {
		this.orders = orders;
	}

	

	
	
}
