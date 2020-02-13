package com.example.demo.service.serviceImpl;

import java.util.Date;
import java.util.HashSet;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.OrderEntity;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductContainer;
import com.example.demo.entity.Shipment;
import com.example.demo.entity.ShippingMode;
import com.example.demo.repository.OrderEntityRepository;
import com.example.demo.repository.ProductContainerRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ShipmentRepository;
import com.example.demo.repository.ShippingModeRepository;
import com.example.demo.service.ShippingService;

@Service
public class ShippingServiceImpl implements ShippingService {

	@Autowired
	ShippingModeRepository shippingModeRepository;

	@Autowired
	OrderEntityRepository orderEntityRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ShipmentRepository shipmentRepository;

	@Autowired
	ProductContainerRepository productContainerRepository;

	@Override
	public String saveMasterShippingData(XSSFSheet sheet) {

		Row row;
		HashSet<String> shippingModeNameSet = new HashSet<String>();

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			String shippingModeName = row.getCell(7).getStringCellValue();
			shippingModeNameSet.add(shippingModeName);

		}
		saveShippingMode(shippingModeNameSet);

		return "Saved Shipping Master Data Successfully";
	}

	
	@Override
	public String saveNonMasterShippingData(XSSFSheet sheet) {
		Row row;
		
		for(int i=1;i<=sheet.getLastRowNum();i++)
		{
			row=sheet.getRow(i);
			int shippingId=(int) row.getCell(0).getNumericCellValue();
			double discount=row.getCell(2).getNumericCellValue();
			double profit=row.getCell(21).getNumericCellValue();
			int quantityOrderedNew = (int) row.getCell(22).getNumericCellValue();
			double sales= row.getCell(23).getNumericCellValue();
			Date shipDate=row.getCell(20).getDateCellValue();
			double shippingCost=row.getCell(4).getNumericCellValue();
			int orderId=(int) row.getCell(24).getNumericCellValue();
			String productName=row.getCell(12).getStringCellValue();
			String productContainerName=row.getCell(11).getStringCellValue();
			String shippingModeName=row.getCell(7).getStringCellValue();
			saveShipping(shippingId,discount, profit, quantityOrderedNew, sales, shipDate, shippingCost, orderId, productName, productContainerName, shippingModeName);
		}
		return "Successfully Inserted into Non-Master Data";
	}

	
	@Override
	public String saveShippingMode(HashSet<String> shippingMode){

		HashSet<ShippingMode> shippingModeSet = new HashSet<ShippingMode>();
		ShippingMode shipMode = new ShippingMode();
		for (String shippingModeName : shippingMode) {
			shipMode = new ShippingMode();
			shipMode.setShippingMode(shippingModeName);
			shippingModeSet.add(shipMode);
		}

		shippingModeRepository.saveAll(shippingModeSet);
		return "Successfully Inserted Shipping Mode";
	}

	
	@Override
	public String saveShipping(int shipId,double discount, double profit, int quantityOrderedNew, double sales, Date shipDate,
			double shippingCost, int orderId, String productName, String productContainerName, String shippingModeName)
			 {
		Shipment shipment=new Shipment();
		if(!shipmentRepository.existsById(shipId)) {
		OrderEntity orderEntity = orderEntityRepository.findById(orderId).get();
		Product savedProduct = productRepository.findByProductName(productName);
		ProductContainer productContainer = productContainerRepository.findByProductContainerName(productContainerName);
		ShippingMode shippingMode = shippingModeRepository.findByShippingMode(shippingModeName);
		shipment = new Shipment();
		shipment.setShipmentId(shipId);
		shipment.setDiscount(discount);
		shipment.setProfit(profit);
		shipment.setQuantityOrderedNew(quantityOrderedNew);
		shipment.setSales(sales);
		Date date = new Date(System.currentTimeMillis());
		shipment.setShipDate(date);
		shipment.setShippingCost(shippingCost);
		shipment.setOrder(orderEntity);
		shipment.setProduct(savedProduct);
		shipment.setProductContainer(productContainer);
		shipment.setShippingMode(shippingMode);
		shipmentRepository.save(shipment);
		}
		return"Successfully Inserted Shipment Data";

	}
	
}
