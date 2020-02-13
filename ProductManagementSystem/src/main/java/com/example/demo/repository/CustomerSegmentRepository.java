package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CustomerSegment;

@Repository
public interface CustomerSegmentRepository extends JpaRepository<CustomerSegment, Integer>{

	public CustomerSegment findByCustomerSegmentName(String customerSegmentName);
	
}
