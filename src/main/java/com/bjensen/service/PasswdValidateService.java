/**
 * 
 */
package com.bjensen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import com.bjensen.validation.PasswdValidator;

/**
 * @author bjensen
 * 
 * Service layer implementation for PasswdValidator.
 *
 */
public class PasswdValidateService implements IPasswdValidateService {

	@Autowired
	PasswdValidator passwdValidator;

	public List<ObjectError> validate(Object obj, Errors error) {

		DataBinder binder = new DataBinder(obj);
		binder.addValidators(passwdValidator);
		binder.validate();

		BindingResult results = binder.getBindingResult();

		if (results.hasErrors()) {
			binder.getBindingResult().getAllErrors().stream()
					.forEach(e -> System.out.println("Validation Error: " + e.getCode()));
		}

		return results.getAllErrors();

	}

}