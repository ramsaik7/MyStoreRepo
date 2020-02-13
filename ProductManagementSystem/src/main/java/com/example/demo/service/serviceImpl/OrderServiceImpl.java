package com.example.demo.service.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.OrderEntity;
import com.example.demo.entity.OrderPriority;
import com.example.demo.entity.OrderStatus;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.OrderEntityRepository;
import com.example.demo.repository.OrderPriorityRepository;
import com.example.demo.repository.OrderStatusRepository;
import com.example.demo.service.OrderService;


@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderPriorityRepository orderPriorityRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	OrderEntityRepository orderEntityRepository;
	
	@Autowired
	OrderStatusRepository orderStatusRepository;
	
	
	@Override
	public String saveOrderMasterData(XSSFSheet sheet){
		HashSet<String> orderPrioritySet=new HashSet<String>();
		Row row;
		for(int i=1;i<=sheet.getLastRowNum();i++){
			row=sheet.getRow(i);
			String orderedPriorityName=row.getCell(1).getStringCellValue();
			orderPrioritySet.add(orderedPriorityName);	
		}
		saveOrderPriority(orderPrioritySet);
		orderStatus();
		return "Successfully Inserted OrderPriority Values";
	}
	
	
	@Override
	public String saveNonMasterData(XSSFSheet sheet) {
		Row row;
		for(int i=1;i<=sheet.getLastRowNum();i++)
		{
			row=sheet.getRow(i);
			int orderId=(int) row.getCell(24).getNumericCellValue();
			String orderPriorityName=row.getCell(1).getStringCellValue();
			String customerName=row.getCell(6).getStringCellValue();
			Date orderDate=row.getCell(19).getDateCellValue();
			saveOrderEntity(orderId, orderPriorityName, customerName, orderDate);
		}
		return "Non-Master Data of Order Inserted Successfully";
	}

	
	
	@Override
	public String saveOrderStatus(XSSFSheet sheet) {
		Row row;
		//OrderEntity orderEntity=new OrderEntity();
		//List<OrderStatus> orderStatusList = orderStatusRepository.findAll();
		for(int i=1;i<=sheet.getLastRowNum();i++)
		{
			
			row=sheet.getRow(i);
			int orderId=(int) row.getCell(0).getNumericCellValue();
			if(orderEntityRepository.existsById(orderId)) {
					OrderEntity orderEntity1=orderEntityRepository.findById(orderId).get();
					OrderStatus orderStatus2=orderStatusRepository.findByOrderStatusName("Delivered");
					orderEntity1.setOrderStatus(orderStatus2);
					orderEntityRepository.save(orderEntity1);
				
			}
		}
		return "saved ordered status successfully";
			
	}
	
	
	
	@Override
	public String saveOrderPriority(HashSet<String> orderPriorityNameSet) {
		
		HashSet<OrderPriority> orderPrioritySet=new HashSet<OrderPriority>();
		OrderPriority orderPriority=new OrderPriority();
		for (String orderPriorityName : orderPriorityNameSet) {
			orderPriority=new OrderPriority();
			orderPriority.setOrderPriorityName(orderPriorityName);
			orderPrioritySet.add(orderPriority);
				
		}
		orderPriorityRepository.saveAll(orderPrioritySet);
		return "Successfully saved odered proirity";
		
	}
	

	@Override
	public String saveOrderEntity(int orderId,String orderPriorityName,String customerName,Date orderDate){
	
			OrderEntity orderEntity=new OrderEntity();
			if(!orderEntityRepository.existsById(orderId)) {
			OrderPriority orderPriority=orderPriorityRepository.findByOrderPriorityName(orderPriorityName);
			Customer customer=customerRepository.findByCustomerName(customerName);
			orderEntity=new OrderEntity();
			orderEntity.setOrderPriority(orderPriority);
			orderEntity.setOrderId(orderId);
			orderEntity.setCustomer(customer);
			orderEntity.setOrderDate(orderDate);
		
			
			orderEntityRepository.save(orderEntity);
			}
			
		return "Successfully Inserted Order Entity Data";
		
		
		
	}

	
	@Override
	public String orderStatus() {
		
		List<String> orderStatusList=new ArrayList<String>();
		orderStatusList.add("Delivered");
		orderStatusList.add("Not-Delivered");
		
		orderStatusList.forEach(orderStatusName->{
			OrderStatus orderStatus=new OrderStatus();
			orderStatus.setOrderStatusName(orderStatusName);
			orderStatusRepository.save(orderStatus);
			
		});
		
		
		return "Successfully Inserted Order Status";
		
		
	}
	
}
