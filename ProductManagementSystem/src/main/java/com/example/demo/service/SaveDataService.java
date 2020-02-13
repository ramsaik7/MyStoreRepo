package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exception.FileNotFoundException;

public interface SaveDataService {
	
	public String SaveMasterData(MultipartFile multiPartFile) throws FileNotFoundException;
	
	public String saveNonMasterData(MultipartFile multiPartFile) throws FileNotFoundException, Exception;
	
	public String saveData(MultipartFile multiPartFile) throws FileNotFoundException, Exception;

}
