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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class State {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int stateId;
	
	private String stateName;
	
	@OneToMany(mappedBy = "state",cascade = CascadeType.ALL)
	private List<City> cities;
	

	@OneToOne(cascade = CascadeType.ALL)
	private Region region;
	

	public State() {
		super();
	}

	public State(int stateId, String stateName, List<City> cities, Region region) {
		super();
		this.stateId = stateId;
		this.stateName = stateName;
		this.cities = cities;
		this.region = region;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}
	
	
}
