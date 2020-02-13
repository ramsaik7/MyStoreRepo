package com.example.demo.service.serviceImpl;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Customer;
import com.example.demo.entity.OrderEntity;
import com.example.demo.entity.OrderPriority;
import com.example.demo.entity.OrderStatus;
import com.example.demo.entity.ProductCategory;
import com.example.demo.entity.ProductContainer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.OrderEntityRepository;
import com.example.demo.repository.OrderPriorityRepository;
import com.example.demo.repository.OrderStatusRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
	
	
	@Mock
	OrderPriorityRepository orderPriorityRepository;
	
	@Mock
	CustomerRepository customerRepository;
	
	@Mock
	OrderEntityRepository orderEntityRepository;
	
	@Mock
	OrderStatusRepository orderStatusRepository;
	
	@InjectMocks
	OrderServiceImpl orderServiceImpl;
	
	OrderPriority orderPriority=new OrderPriority();
	
	OrderEntity orderEntity=new OrderEntity();
	
	OrderStatus orderStatus=new OrderStatus();
	
	Customer customer=new Customer();
	
	
	@Test
	public void saveOrderPriority() {
		
		HashSet<String> orderPriorityNameSet=new HashSet<String>();
		HashSet<OrderPriority> orderPrioritySet=new HashSet<OrderPriority>();
		orderPriorityNameSet.add("High");
		orderPrioritySet.add(orderPriority);
		
		
		List<OrderPriority> productPriorityList=new ArrayList<OrderPriority>();
		
		for (String orderPriorityName: orderPriorityNameSet) {
				orderPriority=new OrderPriority();
				orderPriority.setOrderPriorityName(orderPriorityName);
				//orderPriorityNameproductCategories.add(productCategory);
				productPriorityList.add(orderPriority);
			}
		
		
		orderPriorityRepository.saveAll(orderPrioritySet);
		Mockito.when(orderPriorityRepository.saveAll(orderPrioritySet)).thenReturn(productPriorityList);
		assertEquals("Successfully saved odered proirity",orderServiceImpl.saveOrderPriority(orderPriorityNameSet));
		
	}

	
	
	@Test
	public void saveOrderEntity(){
		
		String orderPriorityName="High";
		String customerName="Ram";
		Date date = new Date();
		int orderId=1234;
		Mockito.when(orderPriorityRepository.findByOrderPriorityName(orderPriorityName)).thenReturn(orderPriority);
		Mockito.when(customerRepository.findByCustomerName(customerName)).thenReturn(customer);
		OrderEntity orderEntity=new OrderEntity();
		orderEntity.setCustomer(customer);
		orderEntity.setOrderPriority(orderPriority);
		orderEntity.setOrderId(orderId);
		orderEntity.setOrderDate(date);
		Mockito.when(orderEntityRepository.save(orderEntity)).thenReturn(orderEntity);
		
		assertEquals("Successfully Inserted Order Entity Data",orderServiceImpl.saveOrderEntity(orderId, orderPriorityName, customerName,date));
		
	}
	
	@Test
	public void orderStatus() {
		String orderStatusName="Returned";
		List<String> orderStatusNameList=new ArrayList<String>();
	
		orderStatusNameList.add(orderStatusName);
		orderStatus.setOrderStatusName(orderStatusName);
		
		Mockito.when(orderStatusRepository.save(orderStatus)).thenReturn(orderStatus);
		assertEquals("Successfully Inserted Order Status",orderServiceImpl.orderStatus());
	}
	
	@Test
	public void saveOrderedMasterData() {
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("saveMaster");
		
		
		Row row = sheet.createRow(0);
		Row row1 = sheet.createRow(1);
		Cell cell0=row1.createCell(1);
		cell0.setCellValue((String)"High");
		
		String orderedPriorityName=row1.getCell(1).getStringCellValue();
		
		HashSet<String> orderPrioritySet=new HashSet<String>();
		orderPrioritySet.add(orderedPriorityName);
		
		assertEquals("Successfully Inserted OrderPriority Values",orderServiceImpl.saveOrderMasterData(sheet));
		
		
	}
	
	
	
	@Test
	public void saveNonMasterData() {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("saveNoNMaster");
		
		
		Row row = sheet.createRow(0);
		Row row1 = sheet.createRow(1);
		Cell cell0=row1.createCell(24);
		cell0.setCellValue((int)1);
		
		Cell cell1=row1.createCell(1);
		cell1.setCellValue((String)"High");
		
		Cell cell2=row1.createCell(6);
		cell2.setCellValue((String)"Ram");
		
		Cell cell3=row1.createCell(19);
		cell3.setCellValue(new Date());
		
		
		
		int orderId=(int) row1.getCell(24).getNumericCellValue();
		String orderPriorityName=row1.getCell(1).getStringCellValue();
		String customerName=row1.getCell(6).getStringCellValue();
		Date orderDate=row1.getCell(19).getDateCellValue();
		assertEquals("Non-Master Data of Order Inserted Successfully",orderServiceImpl.saveNonMasterData(sheet));
		
	}
		

	
}
