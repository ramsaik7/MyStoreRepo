package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.OrderEntity;


@Repository
public interface OrderEntityRepository extends JpaRepository<OrderEntity, Integer> {

	
}
