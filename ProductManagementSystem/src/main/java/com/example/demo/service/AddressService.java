package com.example.demo.service;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.example.demo.entity.City;
import com.example.demo.entity.Country;
import com.example.demo.entity.Manager;
import com.example.demo.entity.PostalCode;
import com.example.demo.entity.Region;
import com.example.demo.entity.State;

public interface AddressService {
	
	public String addAddress(XSSFSheet sheet);
	
	//public String addRegions(XSSFSheet sheet) throws FileNotFoundException;
	public Country addCountries(String countryName);

	public Region addRegions(String regionName,String CountryName);
	
	public State addStates(String stateName,String regionName);
	
	//public String addCountries(XSSFSheet sheet) throws FileNotFoundException;
	
	public String saveManger(XSSFSheet sheet);
	
	public City addCities(String cityName,String StateName);
	
	public PostalCode addPostal(long postalCode,String cityName);
	
	public Manager addManager(String managerName,String regionName);
	
	//public String addPostal(XSSFSheet sheet) throws FileNotFoundException;
	
	//public String addManager(XSSFSheet sheet) throws FileNotFoundException;

	
}
