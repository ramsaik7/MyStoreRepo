package com.example.demo.service.serviceImpl;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.example.demo.service.AddressService;


@Service
public class AddressServiceImpl implements AddressService{

	
	@Autowired
	RegionRepository regionRepository;
	
	@Autowired
	StateRepository stateRepository;
	
	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	PostalCodeRepository postalCodeRepository;
	
	@Autowired
	ManagerRepository managerRepository;
	
	
	@Override
	public String addAddress(XSSFSheet sheet)  {
		Row row;
		for(int i=1;i<=sheet.getLastRowNum();i++)
		{
			row=sheet.getRow(i);
			String regionName=row.getCell(15).getStringCellValue();
			String countryName=row.getCell(14).getStringCellValue();
			String stateName=row.getCell(16).getStringCellValue();
			String cityName=row.getCell(17).getStringCellValue();
			long postalCode=(long) row.getCell(18).getNumericCellValue();
			addCountries(countryName);
			addRegions(regionName, countryName);
			addStates(stateName, regionName);
			addCities(cityName, stateName);
			addPostal(postalCode, cityName);
			
			
		}
		return "added address";
	}
	
	@Override
	public String saveManger(XSSFSheet sheet) {
		
		Row row;
		for(int i=1;i<=sheet.getLastRowNum();i++)
		{
			row=sheet.getRow(i);
			
			String regionName=row.getCell(0).getStringCellValue();
			String managerName=row.getCell(1).getStringCellValue();
			
			addManager(managerName, regionName);
		}
		return "Insertion Successfully";
		
	}

	
	
	
	@Override
	public Country addCountries(String countryName) {
		Country country=new Country();
		Country savedCountry=countryRepository.findByCountryName(countryName);
		if(savedCountry==null) {
			country.setCountryName(countryName);
			savedCountry=countryRepository.save(country);
		}
		return savedCountry;
	}

	
	@Override
	public Region addRegions(String regionName,String countryName) {
		
			Region region=new Region();
			Region savedRegion=regionRepository.findByRegionName(regionName);
			Country country=countryRepository.findByCountryName(countryName);
			if(savedRegion==null)
			{
				region.setCountry(country);
				region.setRegionName(regionName);
				savedRegion = regionRepository.save(region);
			}
		return savedRegion;
		
	}
	

	
	@Override
	public State addStates(String stateName,String regionName)  {
		State state=new State();
		State savedState=stateRepository.findByStateName(stateName);
		Region savedRegion=regionRepository.findByRegionName(regionName);
		if(savedState==null) {
			state=new State();
			state.setStateName(stateName);
			state.setRegion(savedRegion);
			savedState=stateRepository.save(state);
			
		}
		return savedState;
		
		
	}

	@Override
	public City addCities(String cityName, String stateName) {
		
		City city=new City();
		City savedCity=cityRepository.findByCityName(cityName);
		State savedState=stateRepository.findByStateName(stateName);
		if(savedCity==null) {
			city=new City();
			city.setCityName(cityName);
			city.setState(savedState);
			savedCity=cityRepository.save(city);
		}
		return savedCity;
		
	}

	@Override
	public PostalCode addPostal(long postalCode, String cityName) {
		PostalCode postCode=new PostalCode();
		
		PostalCode savedPostalCode=postalCodeRepository.findByPostalCode(postalCode);
		City savedCity=cityRepository.findByCityName(cityName);
		if(savedPostalCode==null) {
			postCode=new PostalCode();
			postCode.setPostalCode(postalCode);
			postCode.setCity(savedCity);
			savedPostalCode=postalCodeRepository.save(postCode);
		}
		return savedPostalCode;
		
		
	}

	

	@Override
	public Manager addManager(String managerName, String regionName) {
		Manager manager=new Manager();
		Region region=regionRepository.findByRegionName(regionName);
		manager.setManagerName(managerName);
		manager.setRegion(region);
		manager=managerRepository.save(manager);
		return manager;
	}

}
