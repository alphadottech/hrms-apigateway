package com.adt.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@SpringBootApplication
public class GatewayApplication {

	private static final Logger logger = LogManager.getLogger(GatewayApplication.class);

	public static void main(String[] args) {
		logger.debug("Debug Message Logged !!!");
		logger.info("Info Message Logged !!!");
		logger.trace("Info Message Logged !!!");
		SpringApplication.run(GatewayApplication.class, args);
		System.out.println("Gateway");
	}

}