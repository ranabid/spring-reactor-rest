package com.spring.reactor.webclientservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebclientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebclientServiceApplication.class, args);
		
		IncidentWebClient iwc = new IncidentWebClient();
		System.out.println(iwc.getResult());
	}

}

