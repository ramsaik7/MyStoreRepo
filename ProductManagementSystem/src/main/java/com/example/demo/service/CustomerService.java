package com.example.demo.service;

import java.util.HashSet;

import org.apache.poi.xssf.usermodel.XSSFSheet;

public interface CustomerService {
	
	public String saveCustomerSegment(HashSet<String> customerSegment);
	
	public String saveCustomers(int customerId,String customerName,String customerSegmentName,long postalCode);
	
	public String saveMasterCustomer(XSSFSheet sheet) ;
	
	public String save_NonMaster_Customer(XSSFSheet sheet);

}
