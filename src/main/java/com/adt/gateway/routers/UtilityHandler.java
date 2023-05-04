package com.adt.gateway.routers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.adt.gateway.entity.UriValidation;

@Component
public class UtilityHandler {

	@Value("${service.token.path}")
	private String url;
	
	@Value("${service.sample.path}")
	private String apiValidUrl;

	private static final Logger LOGGER = LogManager.getLogger(UtilityHandler.class);

	public boolean isInvalid(String token) {

		try {

			ResponseEntity<String> response = null;
			RestTemplate restTemplate = new RestTemplate();

			String tokens = token.substring(7, token.length());

			HttpHeaders headers = new HttpHeaders();

			headers.setBearerAuth(tokens);

			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity httpEntity = new HttpEntity(headers);

			LOGGER.info("tokenValidationPath - " + url);

			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

			int respons = response.getStatusCodeValue();

			LOGGER.info("StatusCode of Third Party Api - " + respons);

			if (respons == 200) {
				
				return false;
				
			} else {
				return true;
			}

		} catch (Exception e) {
			LOGGER.error(e);
			return true;
		}

	}
	
	public boolean isApiValid(String apiUrl ,String methodType,String token) {

		try {

			ResponseEntity<String> response = null;
			RestTemplate restTemplate = new RestTemplate();

			String tokens = token.substring(7, token.length());

			HttpHeaders headers = new HttpHeaders();

			headers.setBearerAuth(tokens);

			headers.setContentType(MediaType.APPLICATION_JSON);
			
			UriValidation uriValidation = new UriValidation();

			uriValidation.setUri(apiUrl);
			
			uriValidation.setMethedType(methodType);
			
			HttpEntity httpEntity = new HttpEntity(uriValidation,headers);

			LOGGER.info("tokenValidationPath - " + url);

			response = restTemplate.exchange(apiValidUrl, HttpMethod.POST, httpEntity, String.class);

			int respons = response.getStatusCodeValue();

			LOGGER.info("StatusCode of Third Party Api - " + respons);

			if (respons == 200) {
				
				return false;
				
			} else {
				return true;
			}

		} catch (Exception e) {
			LOGGER.error(e);
			return true;
		}

	}

}
