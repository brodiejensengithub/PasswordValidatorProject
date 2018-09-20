package com.bjensen;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import com.bjensen.controller.PasswdValidateController;
import com.bjensen.exception.ValidationResponseNullException;
import com.bjensen.model.ValidationResponse;

/**
 * Integration test case on the API endpoint. This is a complete top-down happy
 * path test. This test will startup Spring Boot, provide the runtime container,
 * and test the endpoint down through its dependencies as expected for this IT.
 * 
 * @author bjensen
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

	private static final Logger logger = LoggerFactory.getLogger(IntegrationTest.class);

	@Autowired
	MessageSource passwdRbMsgSrc;

	@Autowired
	TestRestTemplate restTemplate;

	/**
	 * Integration test from API down through Validator implementation.
	 */
	@Test
	public void testApiShouldReturnValid() {

		String passwd = "hjyfd4srdx";
		ResponseEntity<ValidationResponse> response = null;

		try {
			response = restTemplate.getForEntity("/validatePasswd?passwd=" + passwd, ValidationResponse.class);
		} catch (RestClientException rce) {
			logger.error("RestClientException in IntegrationTest. Check to see if ValidationResponse"
					+ " is null in Controller either using debug or external tool such as Postman: \n"
					+ rce.getMessage());

		}

		assertThat(response.getBody().getDefaultMessage())
				.isEqualTo(passwdRbMsgSrc.getMessage("passwd.valid", null, Locale.ENGLISH));
		assertThat(response.getBody().getCode())
				.isEqualTo(passwdRbMsgSrc.getMessage("passwd.ok", null, Locale.ENGLISH));

	}

}