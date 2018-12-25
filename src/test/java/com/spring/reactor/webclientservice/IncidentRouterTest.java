package com.spring.reactor.webclientservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IncidentRouterTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void testGetIncidents() {
		webTestClient
			.get().uri("/incidents")
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isOk()
			.expectBody(String.class).isEqualTo("{\"INC12345\":{\"title\": \"Auto Alert\",\"configurtion\": \"genesys\" }}");
	}
}
