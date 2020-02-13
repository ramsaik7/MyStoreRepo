package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Region {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int regionId;
	
	private String regionName;
	 
	@OneToOne(cascade = CascadeType.ALL)
	private Country country;
	
	//@OneToOne
	//private Manager manager;
	
	@OneToMany(mappedBy = "region",cascade = CascadeType.ALL)
	private List<State> states;
	

	public Region() {
		super();
	}
	
	
	

	public Region(int regionId, String regionName, Country country, List<State> states) {
		super();
		this.regionId = regionId;
		this.regionName = regionName;
		this.country = country;
		this.states = states;
	}




	public int getRegionId() {
		return regionId;
	}


	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}


	public String getRegionName() {
		return regionName;
	}


	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}


	public Country getCountry() {
		return country;
	}


	public void setCountry(Country country) {
		this.country = country;
	}


	public List<State> getStates() {
		return states;
	}


	public void setStates(List<State> states) {
		this.states = states;
	}

	

	

}
