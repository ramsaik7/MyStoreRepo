package com.example.demo.service.serviceImpl;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exception.FileNotFoundException;
import com.example.demo.repository.CountryRepository;
import com.example.demo.service.AddressService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.SaveDataService;
import com.example.demo.service.ShippingService;
import com.example.demo.utility.GetWorkSheet;

@Service
public class SaveDataServiceImpl implements SaveDataService {

	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ShippingService shippingService;
	
	
	@Override
	public String SaveMasterData(MultipartFile multiPartFile) throws FileNotFoundException{
		
		GetWorkSheet getWorkSheet=new GetWorkSheet();
		XSSFSheet sheet= getWorkSheet.getSheet(multiPartFile,0);
		XSSFSheet sheet1=getWorkSheet.getSheet(multiPartFile,2);
		
		addressService.addAddress(sheet);
		addressService.saveManger(sheet1);
		customerService.saveMasterCustomer(sheet);
		productService.saveMasterProduct(sheet);
		orderService.saveOrderMasterData(sheet);
		
		shippingService.saveMasterShippingData(sheet);
		
		return "Saved Master Data Successfully";
	}


	@Override
	public String saveNonMasterData(MultipartFile multiPartFile) throws FileNotFoundException  {
		
		GetWorkSheet getWorkSheet=new GetWorkSheet();
		XSSFSheet sheet= getWorkSheet.getSheet(multiPartFile,0);
		XSSFSheet sheet1= getWorkSheet.getSheet(multiPartFile,1);
		customerService.save_NonMaster_Customer(sheet);
		productService.saveNon_MasterProductData(sheet);
		orderService.saveNonMasterData(sheet);
		shippingService.saveNonMasterShippingData(sheet);
		
		orderService.saveOrderStatus(sheet1);
		
		return "Saved Non-Master Data Successfully";
	}

	
	
	

	@Override
	public String saveData(MultipartFile multiPartFile) throws FileNotFoundException  {
		if(countryRepository.findAll().size()==0)
		{
			SaveMasterData(multiPartFile);
			saveNonMasterData(multiPartFile);
		}
		else {
			saveNonMasterData(multiPartFile);
		}
		return "SuucessFully Inserted";
	}
	
	
	

	
}
