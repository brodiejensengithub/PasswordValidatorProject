package com.bjensen.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
@Service
public class PasswdValidateService implements IPasswdValidateService {
	
	//could also be another msg in msg props file
	public static String ISVALID = "Password is valid";

	@Autowired
	PasswdValidator passwdValidator;

	public List<ObjectError> validate(Object obj, Errors error) {
		
		List<ObjectError> errors = new ArrayList<>();

		DataBinder binder = new DataBinder(obj);
		binder.setValidator(passwdValidator); 
		binder.validate();

		BindingResult results = binder.getBindingResult();
		
		//Can also pull out specific properties of ObjectError (like just the code value) to return to View
		if (results.hasErrors()) {
			errors= results.getAllErrors();
		} else {
			errors.add(new ObjectError("obj", ISVALID));
		}

		return errors;

	}

}