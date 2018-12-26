package com.spring.reactor.webclientservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class IncidentRouter {

	@Bean
	public RouterFunction<ServerResponse> route(IncidentHandler incidentHandler) {

		return RouterFunctions.route(
				RequestPredicates.GET("/incidents").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
				incidentHandler::getIncidents);
	}
}
