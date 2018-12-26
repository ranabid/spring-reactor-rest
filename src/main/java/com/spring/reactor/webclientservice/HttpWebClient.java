package com.spring.reactor.webclientservice;

import java.net.URI;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

public class HttpWebClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpWebClient.class);
	private final WebClient webClient;

	public HttpWebClient(final String baseUrl) {

		this.webClient = WebClient.builder().baseUrl(baseUrl)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}

	public String wrappedGet(final String uri) throws Exception {
		try {
			return this.webClient.get().uri(uri).accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(String.class)
					.block();

		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			throw new Exception(ex);
		}
	}

	public String wrappedPost(final String uri, final String requestBody) throws Exception {

		try {
			String response = this.webClient.post().uri(URI.create(uri)).contentType(MediaType.APPLICATION_JSON)
					.syncBody(requestBody).retrieve().bodyToMono(String.class).block();
			return response;

		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			throw new Exception(ex);
		}
	}

	public String wrappedPost(final String uri, final String requestBody, final Map<String, String> headerList)
			throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.setAll(headerList);
		String response = "";
		try {
			response = this.webClient.post().uri(URI.create(uri)).contentType(MediaType.APPLICATION_JSON)
					.headers(headers -> {
						headers.addAll(params);
					}).syncBody(requestBody).retrieve().bodyToMono(String.class).block();
			return response;

		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			throw new Exception(ex);
		}
	}

}
