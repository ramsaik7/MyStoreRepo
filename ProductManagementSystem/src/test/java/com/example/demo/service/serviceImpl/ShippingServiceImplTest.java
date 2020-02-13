package com.example.demo.service.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.HashSet;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShippingServiceImplTest {

	
	@Mock
	ShipmentRepository shipmentRepository;
	
	@Mock
	ShippingModeRepository shippingModeRepository;
	
	@Mock
	OrderEntityRepository orderEntityRepository;
	
	@Mock
	ProductContainerRepository productContainerRepository;
	
	@Mock
	ProductRepository productRepository;
	
	@InjectMocks
	ShippingServiceImpl shippingServiceImpl;
	
	ShippingMode shippingMode=new ShippingMode();
	
	Shipment shipment=new Shipment();
	
	OrderEntity orderEntity =new OrderEntity();
	
	Product product = new Product();
	
	ProductContainer productContainer=new ProductContainer();
	
	
	
	@Test
	public void saveShippingMode(){
		
		HashSet<String> shippingModeNameSet=new HashSet<String>();
		HashSet<ShippingMode> shippingModeSet=new HashSet<ShippingMode>();
		shippingModeNameSet.add("Regular Air");
		ShippingMode shippingmode=new ShippingMode();
		for (String shippingModeName : shippingModeNameSet) {
			shippingMode = new ShippingMode();
			shippingMode.setShippingMode(shippingModeName);
			shippingModeSet.add(shippingMode);
		}
		Mockito.when(shippingModeRepository.saveAll(shippingModeSet)).thenReturn(null);
		assertEquals("Successfully Inserted Shipping Mode",shippingServiceImpl.saveShippingMode(shippingModeNameSet));
		
	}
	
	@Test
	public void saveShipping() {
		
		double discount=10.0;
		double profit=2.0;
		int quantityOrderedNew=5;
		double sales=3;
		Date shipDate=new Date();
		double shippingCost=200.0;
		int orderId=89847;
		String productName="Apple";
		String productContainerName="SmallBox";
		String shippingModeName="Regular Air";

		//Mockito.when(orderEntityRepository.findById(orderId).get()).thenReturn(orderEntity);
		Mockito.when(productRepository.findByProductName(productName)).thenReturn(product);
		Mockito.when(productContainerRepository.findByProductContainerName(productContainerName)).thenReturn(productContainer);

		Mockito.when(shippingModeRepository.findByShippingMode(shippingModeName)).thenReturn(shippingMode);
		shipment.setDiscount(discount);
		shipment.setProfit(profit);
		shipment.setQuantityOrderedNew(quantityOrderedNew);
		shipment.setSales(sales);
		shipment.setShipDate(shipDate);
		shipment.setShippingCost(shippingCost);
		//shipment.setOrder(orderEntity);
		shipment.setProduct(product);
		shipment.setProductContainer(productContainer);
		shipment.setShippingMode(shippingMode);
		Mockito.when(shipmentRepository.save(shipment)).thenReturn(shipment);

		//assertEquals("Successfully Inserted Shipment Data",shippingServiceImpl.saveShipping(discount, profit, quantityOrderedNew, sales, shipDate, shippingCost, orderId, productName, productContainerName, shippingModeName));
		
	}
	
	@Test
	public void saveMasterData() {
		HashSet<String> shippingModeNameSet = new HashSet<String>();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("saveMaster");
		
		
		Row row = sheet.createRow(0);
		
		Row row1=sheet.createRow(1);
		Cell cell0=row1.createCell(7);
		cell0.setCellValue((String)"Living");
		
		String shippingModeName = row1.getCell(7).getStringCellValue();
		
		shippingModeNameSet.add(shippingModeName);
		
		assertEquals("Saved Shipping Master Data Successfully",shippingServiceImpl.saveMasterShippingData(sheet));
		
		
	}
	
	@Test
	public void saveNoNMasterData() {
		
		//HashSet<String> shippingModeNameSet = new HashSet<String>();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("saveMaster");
		
		Row row = sheet.createRow(0);
		Row row1=sheet.createRow(1);
		
		Cell cell0=row1.createCell(2);
		cell0.setCellValue((double)10);
		
		Cell cell1=row1.createCell(21);
		cell1.setCellValue((double)15);
		
		Cell cell2=row1.createCell(22);
		cell2.setCellValue((int) 12);
		
		Cell cell3=row1.createCell(23);
		cell3.setCellValue((int) 20);
		
		Cell cell4=row1.createCell(20);
		cell4.setCellValue(new Date());

		Cell cell5=row1.createCell(4);
		cell5.setCellValue((double)100);
		
		Cell cell6=row1.createCell(24);
		cell6.setCellValue((int)7);
		
		Cell cell7=row1.createCell(12);
		cell7.setCellValue("Apple");
		
		Cell cell8=row1.createCell(11);
		cell8.setCellValue("Small Box");
		
		Cell cell9=row1.createCell(7);
		cell9.setCellValue("Regular Air");
		
		
		
		double discount=row1.getCell(2).getNumericCellValue();
		double profit=row1.getCell(21).getNumericCellValue();
		int quantityOrderedNew = (int) row1.getCell(22).getNumericCellValue();
		double sales= row1.getCell(23).getNumericCellValue();
		Date shipDate=row1.getCell(20).getDateCellValue();
		double shippingCost=row1.getCell(4).getNumericCellValue();
		int orderId=(int) row1.getCell(24).getNumericCellValue();
		String productName=row1.getCell(12).getStringCellValue();
		String productContainerName=row1.getCell(11).getStringCellValue();
		String shippingModeName=row1.getCell(7).getStringCellValue();

	//assertEquals("Successfully Inserted into Non-Master Data",shippingServiceImpl.saveNonMasterShippingData(sheet));
		
	}
	
	
	
	
	
		
		
		
}
	
