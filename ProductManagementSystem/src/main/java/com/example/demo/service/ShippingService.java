package com.example.demo.service;

import java.util.Date;
import java.util.HashSet;

import org.apache.poi.xssf.usermodel.XSSFSheet;

public interface ShippingService {
	
	public String saveShippingMode(HashSet<String> shippingMode);
	
	public String saveShipping(int shipId,double discount,double profit,int quantityOrderedNew,double sales,Date shipDate,double shippingCost,int orderId,String productName,String productContainerName,String shippingModeName);
	
	public String saveMasterShippingData(XSSFSheet sheet);
	
	public String saveNonMasterShippingData(XSSFSheet sheet);

}
