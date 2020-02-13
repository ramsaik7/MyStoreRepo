package com.example.demo.service.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Customer;
import com.example.demo.entity.CustomerSegment;
import com.example.demo.entity.PostalCode;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.CustomerSegmentRepository;
import com.example.demo.repository.PostalCodeRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceImplTest {

	
	@Mock
	CustomerSegmentRepository customerSegmentRepository;
	
	@Mock
	CustomerRepository customerRepository;
	
	@Mock
	PostalCodeRepository postalCodeRepository;
	
	
	@InjectMocks
	CustomerServiceImpl customerServiceImpl;
	
	
	CustomerSegment customerSegment = new CustomerSegment();
	
	Customer customer = new Customer();
	
	PostalCode postalCode = new PostalCode();
	
	
	@Test
	public void saveCustomerSegment() {
		
		HashSet<String> customerSegmentName=new HashSet<String>();
		HashSet<CustomerSegment> customerSegmentSet=new HashSet<CustomerSegment>();
		List<CustomerSegment> customerSegmentList=new ArrayList<CustomerSegment>();
		customerSegmentName.add("West");
		customerSegmentName.add("Central");
		
		for(String segmentName:customerSegmentName) {
			customerSegment=new CustomerSegment();
			customerSegment.setCustomerSegmentName(segmentName);
			customerSegmentSet.add(customerSegment);
			customerSegmentList.add(customerSegment);
			
		}
		Mockito.when(customerSegmentRepository.saveAll(customerSegmentSet)).thenReturn(customerSegmentList);		
		assertEquals("Successfully Saved Customer Segment",customerServiceImpl.saveCustomerSegment(customerSegmentName));
		
	}
	
	@Test
	public void saveCustomer() {
		
		Customer savedCustomer=null;
		int customerId=1;
		String customerName="Ram";
		String customerSegmentName="West";
		long postalCodeId=760008;
		
		Mockito.when(customerRepository.findByCustomerName(customerName)).thenReturn(savedCustomer);
		customer.setCustomerId(customerId);
		customer.setCustomerName(customerName);
		//customer.setCustomerSegment(customerSegment);
		//customer.setPostalcode(postalCodeId);
		
		Mockito.when(customerRepository.save(customer)).thenReturn(customer);
		
		assertEquals("Successfully Inserted Customer Data",customerServiceImpl.saveCustomers(customerId, customerName, customerSegmentName, postalCodeId));
		
	}
	
	@Test
	public void saveMasterData() {
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("saveMaster");
		HashSet<String> customerSegmentSet=new HashSet<String>();
		Row row = sheet.createRow(0);
		Row row1 = sheet.createRow(1);
		Cell cell0=row1.createCell(8);
		
		
		cell0.setCellValue("West");
		
		String regionName=row1.getCell(8).getStringCellValue();
		customerSegmentSet.add(regionName);
		
		assertEquals("Master Details of Customer Successfully",customerServiceImpl.saveMasterCustomer(sheet));
		
	}
	
	@Test
	public void saveNonMasterData() {
		
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("saveMaster");
		HashSet<String> customerSegmentSet=new HashSet<String>();
		Row row = sheet.createRow(0);
		Row row1 = sheet.createRow(1);
		
		Cell cell0=row1.createCell(5);
		Cell cell1=row1.createCell(6);
		Cell cell2=row1.createCell(8);
		Cell cell3=row1.createCell(18);
		cell0.setCellValue((int)1);
		cell1.setCellValue((String)"Ram");
		cell2.setCellValue("West");
		cell3.setCellValue((long)760002);
		
		
		int customerId=(int) row1.getCell(5).getNumericCellValue(); 
		String customerName=row1.getCell(6).getStringCellValue();
		String customerSegmentName=row1.getCell(8).getStringCellValue();
		long postalCodeId=(long) row1.getCell(18).getNumericCellValue();
		
		
		assertEquals("Succesfully Inserted to Non-Master Data",customerServiceImpl.save_NonMaster_Customer(sheet));
		
	}
	
	
}
