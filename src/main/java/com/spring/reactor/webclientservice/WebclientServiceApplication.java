package com.spring.reactor.webclientservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebclientServiceApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpWebClient.class);

	public static void main(String[] args) {
		SpringApplication.run(WebclientServiceApplication.class, args);
		try {
			HttpWebClient httpWebClient = new HttpWebClient("http://localhost:8080");
			System.out.println(httpWebClient.wrappedGet("/incident/list"));

		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
	}

}
