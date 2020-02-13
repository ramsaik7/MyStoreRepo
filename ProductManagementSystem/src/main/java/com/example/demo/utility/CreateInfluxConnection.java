package com.example.demo.utility;

import java.util.List;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Pong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class CreateInfluxConnection {
	 private static Logger LOG = LoggerFactory.getLogger(CreateInfluxConnection.class);
	
	public InfluxDB connectDB() {
			boolean connected= false;
			InfluxDB influxDB=null;
		    influxDB = (InfluxDB) InfluxDBFactory.connect("http://localhost:8086/","root","root");
		    //if(!influxDB.databaseExists("My_InfluxDB"))
		    //	influxDB.createDatabase("My_InfluxDB");
		    try {
		        Pong response = influxDB.ping();
		        if (!response.getVersion().equalsIgnoreCase("unknown")) {
		            connected = true;
		            LOG.info("Connected to database server InfluxDB");
		        } else {
		            LOG.error("Database server InfluxDB not responding. Please check your configuration and/or connection");
		            connected = false;
		        }
		    } catch (Exception e) {
		        connected = false;
		        LOG.error("Impossible to connect to database server InfluxDB for {}", e.getMessage());
		    }
		    return influxDB;
		}
	 
	 
	 
	 public String createInfluxDataBase(InfluxDB influxDB) {
			
		 	String dbName="NewInfluxDB";
			if(!influxDB.databaseExists(dbName)) {
				influxDB.createDatabase(dbName);
			}
			influxDB.createRetentionPolicy("defaultPolicy", dbName, "5000d", 1, true);
			return  dbName;
			
		}
		
		public void getLoggingLevel(InfluxDB influxDB) {
			influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
			}
}
	
