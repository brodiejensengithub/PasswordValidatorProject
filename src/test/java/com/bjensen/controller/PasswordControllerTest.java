package com.bjensen.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Locale;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.Errors;

import static org.mockito.BDDMockito.*;

import com.bjensen.model.Password;
import com.bjensen.model.ValidationResponse;
import com.bjensen.service.IValidateService;

/**
 * This test demonstrates a slice test on the controller component. This test
 * does not startup our Spring Boot container but only injects those components
 * required to test the Controller in isolation via @WebMvcTest and Mockito
 * MockMvc to directly test PasswordController based on expected
 * response/outcomes. This test demonstrates the use of Mockito mock for the
 * Service component.
 * 
 * @author bjensen
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PasswdValidateController.class)
public class PasswordControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(PasswordControllerTest.class);

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	MessageSource passwdRbMsgSrc;

	@MockBean
	IValidateService passwdValSvc;

	@MockBean
	ValidationResponse validationResponse;

	@Before
	public void before() {
		validationResponse = new ValidationResponse(passwdRbMsgSrc.getMessage("passwd.ok", null, Locale.ENGLISH),
				passwdRbMsgSrc.getMessage("passwd.valid", null, Locale.ENGLISH));
	}

	/**
	 * Controller slice test to ensure integrity of given and expected response
	 * through PasswdValidateController.
	 * 
	 * @exception NoSuchMessageException handled when a message cannot be resolved.
	 * 
	 */
	@Test
	public void testPasswdControllerShouldReturnValid() {

		String passwd = "jkh5fpghds";

		given(passwdValSvc.validate(any(Password.class), any(Errors.class))).willReturn(validationResponse);

		try {
			mockMvc.perform(get("/validatePasswd").param("passwd", passwd)).andExpect(status().isOk())
					// .andDo(print()) //for diagnostic purposes
					.andExpect(jsonPath("$.code").value(passwdRbMsgSrc.getMessage("passwd.ok", null, Locale.ENGLISH)))
					.andExpect(jsonPath("$.defaultMessage")
							.value(passwdRbMsgSrc.getMessage("passwd.valid", null, Locale.ENGLISH)));
		} catch (NoSuchMessageException nme) {
			logger.error("NoSuchMessageException in PasswordControllerTest.testPasswdControllerShouldReturnValid(): \n"
					+ nme.getMessage());
		} catch (Exception e) {
			logger.error(
					"Exception in PasswordControllerTest.testPasswdControllerShouldReturnValid(): \n" + e.getMessage());
		}
	}

}