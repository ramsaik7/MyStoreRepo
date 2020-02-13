package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CustomerSegment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerSegmentId;
	
	private String customerSegmentName;
	
	@OneToMany(mappedBy = "customerSegment")
	List<Customer> customer;
	

	public CustomerSegment() {
		super();
	}

	public CustomerSegment(int customerSegmentId, String customerSegmentName, List<Customer> customer) {
		super();
		this.customerSegmentId = customerSegmentId;
		this.customerSegmentName = customerSegmentName;
		this.customer = customer;
	}

	public int getCustomerSegmentId() {
		return customerSegmentId;
	}

	public void setCustomerSegmentId(int customerSegmentId) {
		this.customerSegmentId = customerSegmentId;
	}

	public String getCustomerSegmentName() {
		return customerSegmentName;
	}

	public void setCustomerSegmentName(String customerSegmentName) {
		this.customerSegmentName = customerSegmentName;
	}

	public List<Customer> getCustomer() {
		return customer;
	}

	public void setCustomer(List<Customer> customer) {
		this.customer = customer;
	}
	
	
	
}
