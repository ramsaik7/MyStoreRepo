package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PostalCode;


@Repository
public interface PostalCodeRepository extends JpaRepository<PostalCode, Integer>{

	
	public PostalCode findByPostalCode(long postalCode);
}
