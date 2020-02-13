package com.example.demo.service.serviceImpl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductCategory;
import com.example.demo.entity.ProductContainer;
import com.example.demo.entity.ProductSubCategory;
import com.example.demo.repository.ProductCategoryRepository;
import com.example.demo.repository.ProductContainerRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ProductSubCategoryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
	
	
	
	@Mock
	ProductRepository productrepository;
	
	@Mock
	ProductCategoryRepository productCategoryRepository;
	
	@Mock
	ProductContainerRepository productContainerRepository;
	
	@Mock
	ProductSubCategoryRepository productSubCategoryRepository;
		
	@InjectMocks
	ProductServiceImpl productServiceImpl;
	
	ProductCategory productCategory=new ProductCategory();
	
	ProductContainer productContainer=new ProductContainer();
	
	ProductSubCategory productSubCategory=new ProductSubCategory();
	
	Product product=new Product();
	
	@Test
	public void saveProductCategory()  {
		
	
		HashSet<String> productCategorySet=new HashSet<String>();
		productCategorySet.add("Technology");
		HashSet<ProductCategory> productCategories=new HashSet<ProductCategory>();
		List<String> productCategoryList=new ArrayList<String>();
		productCategoryList.add("Technology");
		List<ProductCategory> productCategoriesList=new ArrayList<ProductCategory>();
		
		for (String productCategoryName : productCategorySet) {
				productCategory=new ProductCategory();
				productCategory.setProductCategoryName(productCategoryName);
				productCategories.add(productCategory);
				productCategoriesList.add(productCategory);
			}
		
		
		Mockito.when(productCategoryRepository.saveAll(productCategories)).thenReturn((productCategoriesList));
		//productCategoryRepository.saveAll(productCategories);
		assertEquals(productCategories.size(),productServiceImpl.saveProductCategory(productCategorySet).size());
		
		
		
	}
	
	
	@Test
	public void saveNonMasterData()  {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("saveNoNMaster");
		
		Row row1 = sheet.createRow(0);
		Row row = sheet.createRow(1);
		
		
		Cell cell0=row.createCell(13);
		cell0.setCellValue((double)10.0);
		
		
		Cell cell1=row.createCell(3);
		cell1.setCellValue((double)16.0);
		
		Cell cell2=row.createCell(12);
		cell2.setCellValue((String)"Apple");
		
		Cell cell3=row.createCell(10);
		cell3.setCellValue((String)"Technology");
		
		
		
		double productBaseMargin=row.getCell(13).getNumericCellValue();
		double productUnitPrice=row.getCell(3).getNumericCellValue();
		String productName=row.getCell(12).getStringCellValue();
		String productSubCategory=row.getCell(10).getStringCellValue();
	
		
		assertEquals("Successfully Added Changable Product",productServiceImpl.saveNon_MasterProductData(sheet));
		
	}
	
	@Test
	public void saveProductContainer(){
		
		
		HashSet<String> productNameContainerSet=new HashSet<String>();
		productNameContainerSet.add("SmallBox");
		HashSet<ProductContainer> productContainerSet=new HashSet<ProductContainer>();
		List<String> productConatinerNameList=new ArrayList<String>();
		List<ProductContainer> productConatinersList=new ArrayList<ProductContainer>();
		productConatinerNameList.add("SmallBox");
		productConatinersList.add(productContainer);
		
		for (String productContainerName : productNameContainerSet) {
				ProductContainer productContainer=new ProductContainer();
				productContainer.setProductContainerName(productContainerName);
				productContainerSet.add(productContainer);
				productConatinersList.add(productContainer);
			}
		Mockito.when(productContainerRepository.saveAll(productContainerSet)).thenReturn(productConatinersList);
		
		assertEquals(productContainerSet.size(),productServiceImpl.saveProductContainer(productNameContainerSet).size());
	}
	
	
	@Test
	public void saveProductSubCategory(){
		HashMap<String,String> productCategoryMap=new HashMap<String,String>();
		
		productCategoryMap.put("Furniture","Living Room");

		productSubCategory.setProductSubCategoryName("Living Room");
		//ProductCategory productCategory=productCategoryRepository.findByProductCategoryName(v);
		
		Mockito.when(productCategoryRepository.findByProductCategoryName("Furniture")).thenReturn(productCategory);
		productSubCategory.setProductCategory(productCategory);	
		//productSubCategoryRepository.saveAndFlush(productSubCategory);
		Mockito.when(productSubCategoryRepository.saveAndFlush(productSubCategory)).thenReturn(productSubCategory);
		
	
		assertEquals("successfully added product sub category",productServiceImpl.saveProductSubCategory(productCategoryMap));
		
		
	}
	
	
	
	
	@Test
	public void saveMasterProduct() {

		HashSet<String> productCategorySet=new HashSet<String>();
		HashSet<String> productContainerSet=new HashSet<String>();
		HashMap<String,String> productCategoryMap=new HashMap<String,String>();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("saveMaster");
		
		
		Row row = sheet.createRow(0);
		
		Row row1=sheet.createRow(1);
		Cell cell0=row1.createCell(9);
		cell0.setCellValue((String)"Living");
		
		Cell cell1=row1.createCell(10);
		cell1.setCellValue((String)"SmallBox");
		
		Cell cell2=row1.createCell(11);
		cell2.setCellValue((String)"Paper");
		
		//row.getCell(0).setCellValue("Living");
		//row.getCell(1).setCellValue("SmallBox");
		//row.getCell(2).setCellValue("Paper");
		
		String productCategoryName=row1.getCell(9).getStringCellValue();
		String productContainerName=row1.getCell(10).getStringCellValue();
		String productSubCategoryName=row1.getCell(11).getStringCellValue();
		productCategorySet.add(productCategoryName);
		productContainerSet.add(productContainerName);
		productCategoryMap.put(productSubCategoryName,productCategoryName);
		
		assertEquals("Inserted Successfully",productServiceImpl.saveMasterProduct(sheet));
		
		
		
	}
	
	
	
	@Test
	public void saveProduct()  {
		Product savedProduct=null;
		String productName="Apple";
		double product_Base_Margin=100.0;
		double product_Unit_Price=2.0;
		String product_Sub_Category="Technology";
		Mockito.when(productrepository.findByProductName(productName)).thenReturn(savedProduct);
	
		product.setProductBaseMargin(product_Base_Margin);
		product.setUnitPrice(product_Unit_Price);
		product.setProductName(productName);
		//product.setProductSubCategory(productSubCategory);
		Mockito.when(productrepository.save(product)).thenReturn(product);
		assertEquals("Successfully Inserted Product Data",productServiceImpl.addProducts(productName, product_Base_Margin, product_Unit_Price, product_Sub_Category));
		}
	
		
	}
	




