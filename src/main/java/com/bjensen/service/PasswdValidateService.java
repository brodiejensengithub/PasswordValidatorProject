package com.bjensen.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import com.bjensen.model.ValidationResponse;
import com.bjensen.validation.PasswdValidator;

/** 
 * Service layer implementation for PasswdValidator. This method uses a PasswdValidator 
 * Object via DI to perform validation of the password Object parameter.
 * 
 * @author bjensen
 */
@Service
public class PasswdValidateService implements IValidateService {
	
	@Autowired
	MessageSource passwdRbMsgSrc;

	@Autowired
	PasswdValidator passwdValidator;
	
	@Autowired
	ValidationResponse validationResponse;

	/**
	 * This method uses a PasswdValidator Object to validate the Password parameter
	 * through the use of Spring DataBinder to bind and use PasswdValidator to impose
	 * validation rules defined within it.
	 * 
	 * @param passwd Password Object to be validated.
	 * @param error Errors Object to contain data binding and validation errors.
	 * 
	 * @return Returns first FieldError containing a constraint violation on the Password
	 * Object in JSON response, or default for a valid password argument.
	 */
	public ValidationResponse validate(Object passwd, Errors error) {

		ObjectError objectError;

		DataBinder binder = new DataBinder(passwd);	

		binder.setValidator(passwdValidator); 
		binder.validate();

		BindingResult results = binder.getBindingResult();
		
		if (results.hasErrors()) {
			objectError = results.getFieldError();
			validationResponse = new ValidationResponse(objectError.getCode(), objectError.getDefaultMessage());
		} else {
			validationResponse = new ValidationResponse(passwdRbMsgSrc.getMessage
					("passwd.ok", null, Locale.ENGLISH), passwdRbMsgSrc.getMessage
					("passwd.valid", null, Locale.ENGLISH)); 
		}

		return validationResponse;
	}
	
}