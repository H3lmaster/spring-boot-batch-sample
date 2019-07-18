package com.ef.parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	
	public static void main(String[] args) throws Exception {
		
		if(args.length < 4) throw new Exception("Incorrect number of input params passed.");
			
		SpringApplication.run(Application.class, args);
	}
}
