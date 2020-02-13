package com.example.demo.service.serviceImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductCategory;
import com.example.demo.entity.ProductContainer;
import com.example.demo.entity.ProductSubCategory;
import com.example.demo.repository.ProductCategoryRepository;
import com.example.demo.repository.ProductContainerRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ProductSubCategoryRepository;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductCategoryRepository productCategoryRepository;
	
	@Autowired
	ProductSubCategoryRepository productSubCategoryRepository;
	
	@Autowired
	ProductContainerRepository productContainerRepository;
	
	@Autowired
	ProductRepository productRepository;

	
	

	@Override
	public String saveMasterProduct(XSSFSheet sheet) {
		Row row;
		HashSet<String> productCategorySet=new HashSet<String>();
		HashSet<String> productContainerSet=new HashSet<String>();
		HashMap<String,String> productCategoryMap=new HashMap<String,String>();
		for(int i=1;i<=sheet.getLastRowNum();i++)
		{
			row=sheet.getRow(i);
			String productCategoryName=row.getCell(9).getStringCellValue();
			String productContainerName=row.getCell(11).getStringCellValue();
			String productSubCategoryName=row.getCell(10).getStringCellValue();
			
			
			productCategorySet.add(productCategoryName);
			productContainerSet.add(productContainerName);
			productCategoryMap.put(productSubCategoryName,productCategoryName);
		}
		
		saveProductCategory(productCategorySet);
		saveProductContainer(productContainerSet);
		saveProductSubCategory(productCategoryMap);
		
		
		return "Inserted Successfully";
		
		
	}
	
	
	
	
	@Override
	public String saveNon_MasterProductData(XSSFSheet sheet) {
		Row row;
		for(int i=1;i<=sheet.getLastRowNum();i++)
		{
			double productBaseMargin=0;
			double productUnitPrice=0;
			row=sheet.getRow(i);
			try {
			productBaseMargin=row.getCell(13).getNumericCellValue();
			}catch(NullPointerException e) {
				System.out.println(e);
			}
			try {
			productUnitPrice=row.getCell(3).getNumericCellValue();
			}catch(NullPointerException e) {
				System.out.println(e);
			}
			String productName=row.getCell(12).getStringCellValue();
			String productSubCategory=row.getCell(10).getStringCellValue();
			
			addProducts(productName, productBaseMargin, productUnitPrice, productSubCategory);
			
		
		}
		return "Successfully Added Changable Product";
		
	}


	
	
	
	
	
	@Override
	public Set<ProductCategory> saveProductCategory(HashSet<String> productCategorySet) {
			HashSet<ProductCategory> productCategories=new HashSet<ProductCategory>();
			
			for (String productCategoryName : productCategorySet) {
				ProductCategory productCategory=new ProductCategory();
					productCategory.setProductCategoryName(productCategoryName);
					productCategories.add(productCategory);
					}

			productCategoryRepository.saveAll(productCategories);
			return productCategories;
			
		}
	
	

	@Override
	public String saveProductSubCategory(HashMap<String,String> productCategoryMap)  {

			productCategoryMap.forEach((k, v) ->{ 
			ProductSubCategory productSubCategory=new ProductSubCategory();
	
			productSubCategory.setProductSubCategoryName(k);
			ProductCategory productCategory=productCategoryRepository.findByProductCategoryName(v);
			productSubCategory.setProductCategory(productCategory);	
			productSubCategoryRepository.saveAndFlush(productSubCategory);
				
			});
		
		return "successfully added product sub category";
		
	}
	
	

	@Override
	public Set<ProductContainer> saveProductContainer(HashSet<String> productContainerNameSet) {
		HashSet<ProductContainer> productContainerSet=new HashSet<ProductContainer>();
		
		for (String productContainerName : productContainerNameSet) {
				ProductContainer productContainer=new ProductContainer();
				productContainer.setProductContainerName(productContainerName);
				productContainerSet.add(productContainer);
			}
		productContainerRepository.saveAll(productContainerSet);
		
		return productContainerSet;
		
	}


	@Override
	public String addProducts(String productName,double product_Base_Margin,double product_Unit_Price,String product_Sub_Category) {
		
		
		
			Product product;
			double productBaseMargin=0;
			double productUnitPrice=0;
			if(productRepository.findByProductName(productName)==null) {
			
				
			productBaseMargin=product_Base_Margin;
			productUnitPrice=product_Unit_Price;
			
			String productSubCategoryName=product_Sub_Category;
			ProductSubCategory productSubCategory=productSubCategoryRepository.findByProductSubCategoryName(productSubCategoryName);
			product =new Product();
			product.setProductBaseMargin(productBaseMargin);
			product.setUnitPrice(productUnitPrice);
			product.setProductName(productName);
			product.setProductSubCategory(productSubCategory);
			productRepository.save(product);
			}
					
		return "Successfully Inserted Product Data";
		
	}

}
