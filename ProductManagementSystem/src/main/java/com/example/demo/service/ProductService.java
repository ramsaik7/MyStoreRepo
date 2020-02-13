package com.example.demo.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.example.demo.entity.ProductCategory;
import com.example.demo.entity.ProductContainer;

public interface ProductService {
	
	public Set<ProductCategory> saveProductCategory(HashSet<String> productCategorySet);
	
	public Set<ProductContainer> saveProductContainer(HashSet<String> productContainerSet);

	
	public String saveProductSubCategory(HashMap<String,String> productSubCategoryMap);
	
	
	public String saveNon_MasterProductData(XSSFSheet sheet);
	
	
	public String addProducts(String productName,double productBaseMargin,double productUnitPrice,String productSubCategory);
	
	public String saveMasterProduct(XSSFSheet sheet);
}
