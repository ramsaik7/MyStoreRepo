package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.ApiResponse;
import com.example.demo.service.AddInfluxDB;
import com.example.demo.service.SaveDataService;
import com.example.demo.utility.GetWorkSheet;

@RestController
public class ApplicationController {

	/*
	@Autowired
	AddressService addressService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ShippingService shippingService;*/
	
	@Autowired
	SaveDataService saveDataService;
	
	@Autowired
	AddInfluxDB addInfluxDB;
	
	GetWorkSheet workSheet=new GetWorkSheet();
	
	@PostMapping("/uploadExcel")
	public ResponseEntity<ApiResponse> ReadExcel2DB(@RequestBody MultipartFile multiPartFile) throws Exception
	{
		
		String result=saveDataService.saveData(multiPartFile);
		
		return new ResponseEntity<ApiResponse>(
				new ApiResponse("Reading Excel Data.", false, result, HttpStatus.OK), HttpStatus.OK);

	}
	
	
	
	@PostMapping("/addData/InfluxDB")
	public ResponseEntity<ApiResponse> addCountryIntoInluxDB(){	
		String countryData=addInfluxDB.addCountryIntoInflux();
		String shipmentData=addInfluxDB.addShipmentsIntoInflux();
		//String result3=addInfluxDB.addOrderIntoInflux();
		String result= "Successfully Added"+" " +countryData+" "+shipmentData;//+" "+result3;
		
		return new ResponseEntity<ApiResponse>(
				new ApiResponse("Reading Excel Data.", false, result, HttpStatus.OK), HttpStatus.OK);

	}
	
	
}
