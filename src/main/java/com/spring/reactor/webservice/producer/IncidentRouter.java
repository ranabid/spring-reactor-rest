package com.spring.reactor.webservice.producer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.spring.reactor.webservice.consumer.HttpWebClient;

import static org.springframework.web.reactive.function.server.RouterFunctions.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class IncidentRouter {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpWebClient.class);

//	@Bean
//	public RouterFunction<ServerResponse> route(IncidentHandler incidentHandler) {
//
//		return RouterFunctions.route(
//				RequestPredicates.GET("/incidents").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
//				incidentHandler::getIncidents);
//	}

	@Bean
	RouterFunction<ServerResponse> getIncidents(IncidentHandler incidentHandler) {
		LOGGER.info("Inside getIncidents router");
		return route(GET("/incidents").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
				incidentHandler::getIncidents);
	}
	
	@Bean
	RouterFunction<ServerResponse> getIncidentList(IncidentHandler incidentHandler) {
		LOGGER.info("Inside getIncidentList router");
		return route(GET("/incident/list").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
				incidentHandler::getIncidents);
	}
	
}
