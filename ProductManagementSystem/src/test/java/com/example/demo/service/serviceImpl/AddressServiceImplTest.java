package com.example.demo.service.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.City;
import com.example.demo.entity.Country;
import com.example.demo.entity.Manager;
import com.example.demo.entity.PostalCode;
import com.example.demo.entity.Region;
import com.example.demo.entity.State;
import com.example.demo.repository.CityRepository;
import com.example.demo.repository.CountryRepository;
import com.example.demo.repository.ManagerRepository;
import com.example.demo.repository.PostalCodeRepository;
import com.example.demo.repository.RegionRepository;
import com.example.demo.repository.StateRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceImplTest {
	
	
	@Mock
	CountryRepository countryRepository;
	
	@Mock
	StateRepository stateRepository;
	
	@Mock
	RegionRepository regionRepository;
	
	@Mock
	CityRepository cityRepository;
	
	@Mock
	PostalCodeRepository postalCodeRepository;
	
	@Mock
	ManagerRepository managerRepository;
	
	@InjectMocks
	AddressServiceImpl addressServiceImpl;
	
	Country country=new Country();
	
	Region region=new Region();
	
	State state=new State();
	
	City city=new City();
	
	PostalCode postalCode=new PostalCode();
	
	Manager manager=new Manager();
	
	
	
	@Test
	public void saveCountry() {
		Country country1=null;
		String countryName="India";
		Mockito.when(countryRepository.findByCountryName(Mockito.anyString())).thenReturn(country1);
		country.setCountryId(1);
		country.setCountryName(countryName);
		Mockito.when(countryRepository.save(country1)).thenReturn(country1);
		
		assertEquals(country1,addressServiceImpl.addCountries("India"));
		
	}
	
	@Test
	public void addRegion() {
		Region region1=null;
		String regionName="West";
		String countryName="India";
		country.setCountryName(countryName);
		Mockito.when(regionRepository.findByRegionName(Mockito.anyString())).thenReturn(region1);
		Mockito.when(countryRepository.findByCountryName(Mockito.anyString())).thenReturn(country);
		region.setRegionName(regionName);
		region.setCountry(country);
		Mockito.when(regionRepository.save(region1)).thenReturn(region1);
		assertEquals(region1,addressServiceImpl.addRegions("West", "India"));
		
	}
	
	
	
	@Test
	public void addState() {
		State state1=null;
		String regionName="East";
		String stateName="Odisha";
		region.setRegionName(regionName);
		Mockito.when(regionRepository.findByRegionName(Mockito.anyString())).thenReturn(region);
		Mockito.when(stateRepository.findByStateName(Mockito.anyString())).thenReturn(state1);
		
		state.setStateName(stateName);
		state.setRegion(region);
		Mockito.when(stateRepository.save(state1)).thenReturn(state1);
	
		assertEquals(state1,addressServiceImpl.addStates("Odisha","East"));
		
	}
	
	@Test
	public void addCity() {
		City city1=null;
		String stateName="Odisha";
		String cityName="Berhampur";
		state.setStateName(stateName);
		Mockito.when(stateRepository.findByStateName(Mockito.anyString())).thenReturn(state);
		city.setState(state);
		city.setCityName(cityName);
		Mockito.when(cityRepository.save(city1)).thenReturn(city1);
		
		assertEquals(city1,addressServiceImpl.addCities("Berhampur","Odisha"));
		
		
	}
	
	@Test
	public void addPostal() {
		
		PostalCode postalcode1=null;
		String cityName="Berhampur";
		long postalId=760002;
		city.setCityName(cityName);
		
		postalCode.setPostalCode(postalId);
		Mockito.when(cityRepository.findByCityName(Mockito.anyString())).thenReturn(city);
		
		postalCode.setPostalCode(postalId);
		postalCode.setCity(city);
		
		Mockito.when(postalCodeRepository.save(postalcode1)).thenReturn(postalcode1);
		
		assertEquals(postalcode1,addressServiceImpl.addPostal(760002,"Berhampur"));
	
		
	}
	
	@Test
	public void addManger() {
		Manager manager1=null;
		String regionName="West";
		String managerName="Ram";
		region.setRegionName(regionName);
		Mockito.when(regionRepository.findByRegionName(regionName)).thenReturn(region);
		
		manager.setManagerName(managerName);
		manager.setRegion(region);
		
		Mockito.when(managerRepository.save(manager1)).thenReturn(manager1);
		
		assertEquals(manager1,addressServiceImpl.addManager(managerName, regionName));
		
		
	}
	
	
	
}
