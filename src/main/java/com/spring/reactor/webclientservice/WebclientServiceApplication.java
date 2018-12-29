package com.spring.reactor.webclientservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebclientServiceApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpWebClient.class);

	public static void main(String[] args) {
		TokenCacheManager tokenCache = new TokenCacheManager();
		SpringApplication.run(WebclientServiceApplication.class, args);
		
		
		try {
			
			LOGGER.info(tokenCache.getToken());

		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
	}

}
