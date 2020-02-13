package com.example.demo.service.serviceImpl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Country;
import com.example.demo.entity.OrderEntity;
import com.example.demo.entity.Shipment;
import com.example.demo.repository.CountryRepository;
import com.example.demo.repository.OrderEntityRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ShipmentRepository;
import com.example.demo.service.AddInfluxDB;
import com.example.demo.utility.CreateInfluxConnection;


@Service
public class AddInfluxDBImpl implements AddInfluxDB {

	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	ShipmentRepository shipmentRepository;
	
	@Autowired
	OrderEntityRepository orderRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	CreateInfluxConnection connectionInflux=new CreateInfluxConnection();
	
	InfluxDB influxDB=connectionInflux.connectDB();
	
	String dbName=connectionInflux.createInfluxDataBase(influxDB);
	@Override
	public String addCountryIntoInflux() {
		
		List<Country> countryList=countryRepository.findAll();
		BatchPoints batchPoints = BatchPoints
				  .database(dbName)
				  .retentionPolicy("defaultPolicy")
				  .build();
		
		for(Country country:countryList) {
		Point point = Point.measurement("country")
				  .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
				  .addField("countryName", country.getCountryName())
				  .addField("countryId",country.getCountryId())
				  .build();
		batchPoints.point(point);
		
		}
		influxDB.write(batchPoints);
		influxDB.disableBatch();
		influxDB.close();
		
		return "Country";
		
	}
	@Override
	public String addShipmentsIntoInflux() {
		BatchPoints batchPoints = BatchPoints
				  .database(dbName)
				  .retentionPolicy("defaultPolicy")
				  .build();
		
		List<Shipment> shipmentList=shipmentRepository.findAll();
		for(Shipment shipment:shipmentList) {
			Point point = Point.measurement("shipment_new_live")
					  //.time(generateRandomTimeStamp().getTime(), TimeUnit.MILLISECONDS)
					  .time(setRandomTime(shipment.getShipDate().toInstant()).toEpochMilli(),TimeUnit.MILLISECONDS)
					  .addField("ShippingId",shipment.getShipmentId())		  
					  .addField("ShippingCost",shipment.getShippingCost())
					  .addField("ShippingMode",shipment.getShippingMode().getShippingMode())
					  .addField("Discount",shipment.getDiscount())
					  .addField("Profit", shipment.getProfit())
					  .addField("Sales",shipment.getSales())
					  .addField("Quantity Ordered New",shipment.getQuantityOrderedNew())
					  .addField("Customer",shipment.getOrder().getCustomer().getCustomerName())
					  .addField("Region",shipment.getOrder().getCustomer().getPostalcode().getCity().getState().getRegion().getRegionName())
					  .build();
			batchPoints.point(point);
		
		
		
		}
		influxDB.write(batchPoints);
		influxDB.disableBatch();
		influxDB.close();
		return "Shipment Data";
		
		
	}
	
	@Override
	public String addOrderIntoInflux() {
		BatchPoints batchPoints = BatchPoints
				  .database(dbName)
				  .retentionPolicy("defaultPolicy")
				  .build();
		
		List<OrderEntity> orderEntityList=orderRepository.findAll();
		for(OrderEntity order:orderEntityList) {
			Point point = Point.measurement("orders")
						
					  //.time(generateRandomTimeStamp().getTime(), TimeUnit.MILLISECONDS)
					  .addField("orderId",order.getOrderId())
					  .addField("orderDate",order.getOrderDate().toString())
					  .addField("orderPriority",order.getOrderPriority().getOrderPriorityName())
					  .build();
			batchPoints.point(point);
			
		}
		influxDB.write(batchPoints);
		influxDB.disableBatch();
		influxDB.close();
		return "Order Data";
		
	}
	/*
	public Timestamp generateRandomTimeStamp() {
		long offset = Timestamp.valueOf("2010-01-01 00:00:00").getTime();
		long end = System.currentTimeMillis();//Timestamp.valueOf("2013-01-01 00:00:00").getTime();
		long diff = end - offset + 1;
		Timestamp rand = new Timestamp(offset + (long)(Math.random() * diff));
		
		return rand;
	}*/
	
	private Instant setRandomTime(Instant timeInstant) {
	    
        Random rand = new Random();
           long seconds = rand.nextInt(60);
           long minutes = rand.nextInt(60);
           long hours = rand.nextInt(12);
           timeInstant = timeInstant.plus(hours, ChronoUnit.HOURS).plus(minutes, ChronoUnit.MINUTES).plus(seconds,
                   ChronoUnit.SECONDS);
           return timeInstant;
   }
	

}
