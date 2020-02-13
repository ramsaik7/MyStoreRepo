package com.example.demo.service;

import java.util.Date;
import java.util.HashSet;

import org.apache.poi.xssf.usermodel.XSSFSheet;

public interface OrderService {

	public String saveOrderPriority(HashSet<String>  orderPriorityNameSet);
	
	public String saveOrderEntity(int OrderId,String OrderPriorityName,String CustomerName,Date orderDate);
	
	public String saveOrderMasterData(XSSFSheet sheet);
	
	public String saveNonMasterData(XSSFSheet sheet);
	
	public String orderStatus();
	
	public String saveOrderStatus(XSSFSheet sheet);
	
	
}
