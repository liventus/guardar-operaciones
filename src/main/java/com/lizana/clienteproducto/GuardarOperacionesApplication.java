package com.lizana.clienteproducto;


import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GuardarOperacionesApplication {

	private static final Logger logger = Logger.getLogger(GuardarOperacionesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GuardarOperacionesApplication.class, args);
		logger.info("GuardarOperacionesApplication.class-1");

	}

}
