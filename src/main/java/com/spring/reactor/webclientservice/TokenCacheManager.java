package com.spring.reactor.webclientservice;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TokenCacheManager {

	private final LocalDateTime currentDateTimeUTC;
	private static final Logger LOGGER = LoggerFactory.getLogger(TokenCacheManager.class);
	private Map<String, String> tokenCache;
	private final DateTimeFormatter formatter;
	private final HttpWebClient httpWebClient;

	public TokenCacheManager(final String tokenProviderUrl) {
		this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		this.currentDateTimeUTC = LocalDateTime.now(Clock.systemUTC());
		this.tokenCache = new HashMap<String, String>();
		this.httpWebClient = new HttpWebClient(tokenProviderUrl);

	}

	private Boolean isTokenvalid() {
		boolean isValid = false;

		LocalDateTime tokenTTL = LocalDateTime.parse(tokenCache.get("ttl"), formatter);
		if (tokenTTL.compareTo(currentDateTimeUTC) <= 0) {
			LOGGER.info("token is invalid");
			isValid = false;
		} else {
			isValid = true;
		}
		return isValid;
	}

	private String generateNewTokenAndCache(final String tokenUri, final String requestBody) throws Exception {

		try {
			String newTokenJson = this.httpWebClient.wrappedPost(tokenUri, requestBody);
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();

			map = mapper.readValue(newTokenJson, new TypeReference<Map<String, String>>() {
			});

			tokenCache.put("token", map.get("token").toString());
			tokenCache.put("ttl", getCurrentDateTimeUTC());
			return tokenCache.get("token");
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			throw new Exception(ex.getMessage());
		}
	}

	public String getCurrentDateTimeUTC() {
		return this.formatter.format(currentDateTimeUTC);
	}

	public String getToken(final String tokenUri, final String requestBody) throws Exception {
		try {
			if (!this.tokenCache.isEmpty() && isTokenvalid()) {
				return tokenCache.get("token");
			} else {
				return this.generateNewTokenAndCache(tokenUri, requestBody);
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			throw new Exception(ex.getMessage());
		}
	}

}
