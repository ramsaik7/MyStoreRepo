package com.example.demo.service.serviceImpl;

import java.util.HashSet;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.CustomerSegment;
import com.example.demo.entity.PostalCode;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.CustomerSegmentRepository;
import com.example.demo.repository.PostalCodeRepository;
import com.example.demo.service.CustomerService;


@Service
public class CustomerServiceImpl  implements CustomerService{

	@Autowired
	CustomerSegmentRepository customerSegmentRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	PostalCodeRepository postalCodeRepository;
	
	@Override
	public String saveMasterCustomer(XSSFSheet sheet){
		
		
		Row row;
		HashSet<String> customerSegmentSet=new HashSet<String>();
		for(int i=1;i<=sheet.getLastRowNum();i++)
		{
			row=sheet.getRow(i);
			String CustomerSegmentName=row.getCell(8).getStringCellValue();
			customerSegmentSet.add(CustomerSegmentName);			
		}
		saveCustomerSegment(customerSegmentSet);
		return "Master Details of Customer Successfully";
		
	}
	
	

	@Override
	public String save_NonMaster_Customer(XSSFSheet sheet) {
		
		Row row;
		
		for(int i=1;i<=sheet.getLastRowNum();i++)
		{
			row=sheet.getRow(i);
			int customerId=(int) row.getCell(5).getNumericCellValue();
			String customerName=row.getCell(6).getStringCellValue();
			String customerSegmentName=row.getCell(8).getStringCellValue();
			long postalCodeId=(long) row.getCell(18).getNumericCellValue();
			saveCustomers(customerId, customerName, customerSegmentName, postalCodeId);
			
		
		}
		return "Succesfully Inserted to Non-Master Data";
	}

	
	
	
	
	
	
	@Override
	public String saveCustomerSegment(HashSet<String> customerSegmentNameSet){
		
		
		HashSet<CustomerSegment> customerSegmentSet=new HashSet<CustomerSegment>();
		
		for (String customerSegmentName : customerSegmentNameSet) {
			CustomerSegment customerSegment=new CustomerSegment();
			customerSegment.setCustomerSegmentName(customerSegmentName);
			customerSegmentSet.add(customerSegment);
		}
		customerSegmentRepository.saveAll(customerSegmentSet);
		return "Successfully Saved Customer Segment";
	}
	
	

	@Override
	public String saveCustomers(int customerId,String customerName,String customerSegmentName,long postalCodeId) {
	
			Customer customer=new Customer();
			CustomerSegment customerSegment=customerSegmentRepository.findByCustomerSegmentName(customerSegmentName);
			PostalCode postalCode=postalCodeRepository.findByPostalCode(postalCodeId);
			Customer savedCustomerName=customerRepository.findByCustomerName(customerName);
			if(savedCustomerName==null) {
				customer = new Customer();
				customer.setCustomerId(customerId);
				customer.setCustomerName(customerName);
				customer.setCustomerSegment(customerSegment);
				customer.setPostalcode(postalCode);
				customerRepository.save(customer);	
				}
		
		
		return "Successfully Inserted Customer Data";
	}

}
