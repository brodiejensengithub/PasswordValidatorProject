package com.bjensen.advice;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bjensen.exception.ValidationResponseNullException;

/**
 * This ControllerAdvice class demonstrates the use of RestControllerAdvice and
 * ExceptionHandler to handle Exceptions within PasswdValidateController.
 * 
 * @author bjensen
 *
 */
@RestControllerAdvice
public class PasswdValidateControllerAdvice {

	@Autowired
	MessageSource passwdRbMsgSrc;

	@ExceptionHandler(ValidationResponseNullException.class)
	public String handleValidationResponseNullException(ValidationResponseNullException ex) {
		return passwdRbMsgSrc.getMessage("exception.controller.validation.response.null", null, Locale.ENGLISH);

	}
}