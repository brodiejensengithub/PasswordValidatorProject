package com.bjensen.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bjensen.exception.ValidationResponseNullException;
import com.bjensen.model.Password;
import com.bjensen.model.ValidationResponse;
import com.bjensen.service.IValidateService;

/**
 * REST API for PasswdValidatorProject.
 * 
 * @author bjensen
 *
 */
@RestController
public class PasswdValidateController {

	private static final Logger logger = LoggerFactory.getLogger(PasswdValidateController.class);

	@Autowired
	IValidateService passwdValSvc;

	@Autowired
	MessageSource passwdRbMsgSrc;

	@Autowired
	ValidationResponse validationResponse;

	/**
	 * @param passwd Password parameter bound to a Password Model object for
	 * validation through the service layer and for returning to
	 * logical view respectively.
	 * 
	 * @return ResponseEntity with ValidationResponse parameterized type wrapping
	 * specific password constraint violations.
	 */
	@RequestMapping(value = "/validatePasswd")
	public ResponseEntity<ValidationResponse> validatePasswd(@ModelAttribute Password passwd) {

		Errors error = new BeanPropertyBindingResult(passwd, "passwd");

		validationResponse = passwdValSvc.validate(passwd, error);

		// Example of using a Spring @RestControllerAdvice and @ExceptionHandler to
		// handle specific Exceptions
		if (validationResponse == null) {
			logger.error(
					passwdRbMsgSrc.getMessage("exception.controller.validation.response.null", null, Locale.ENGLISH));
			// If a View was being resolved, this below might be encapsulated into the
			// ValidationResponse and a user-friendly message would be rendered within 
			//the View rather than returning this Exception raw, or alternatively that 
			//logic may be within the UI based on the ValidationResponse.
			throw new ValidationResponseNullException(
					passwdRbMsgSrc.getMessage("exception.controller.validation.response.null", null, Locale.ENGLISH));
		}

		return new ResponseEntity<>(validationResponse, HttpStatus.OK);
	}

}