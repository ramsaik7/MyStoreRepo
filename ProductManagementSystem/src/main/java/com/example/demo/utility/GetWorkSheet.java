package com.example.demo.utility;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exception.FileNotFoundException;
	
public class GetWorkSheet {
		
	public XSSFSheet getSheet(MultipartFile multipartFile,int index) throws FileNotFoundException{
		
		
		InputStream inputStream;
		XSSFSheet sheet;
		try {
			inputStream=multipartFile.getInputStream();
				
		}catch (IOException ioe) {
			throw new FileNotFoundException("File Not Found");
			}
	
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			sheet=workbook.getSheetAt(index);
				
		}catch (IOException e) {
			throw new FileNotFoundException("File Not Found");
		}
		return sheet;		
	}
}



