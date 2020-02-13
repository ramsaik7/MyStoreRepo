package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class PostalCode {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int postalId;
	
	private long postalCode;
	
	@ManyToOne
	private City city;
	
	@OneToMany(mappedBy = "postalcode")
	List<Customer> customers;
	
	

	public PostalCode() {
		super();
	}



	public PostalCode(int postalId, long postalCode, City city, List<Customer> customers) {
		super();
		this.postalId = postalId;
		this.postalCode = postalCode;
		this.city = city;
		this.customers = customers;
	}



	public int getPostalId() {
		return postalId;
	}



	public void setPostalId(int postalId) {
		this.postalId = postalId;
	}



	public long getPostalCode() {
		return postalCode;
	}



	public void setPostalCode(long postalCode) {
		this.postalCode = postalCode;
	}



	public City getCity() {
		return city;
	}



	public void setCity(City city) {
		this.city = city;
	}



	public List<Customer> getCustomers() {
		return customers;
	}



	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	
	
	
	
}
