package com.bjensen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bjensen.model.Password;
import com.bjensen.service.IPasswdValidateService;

/**
 * @author bjensen
 *
 * REST API for PasswdValidatorProject.
 *
 */
@RestController
public class PasswdValidateController {

	@Autowired
	IPasswdValidateService passwdValSvc;

	@RequestMapping("/validatePasswd")
	public List<ObjectError> validatePasswd(@ModelAttribute Password passwd) {

		Errors error = null;
		List<ObjectError> validStatus = passwdValSvc.validate(passwd, error);

		return validStatus;
	}

}
