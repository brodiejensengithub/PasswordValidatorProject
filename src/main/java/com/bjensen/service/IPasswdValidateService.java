package com.bjensen.service;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

/**
 * @author bjensen
 *
 * PasswdValidateService interface for proper DI.
 */
public interface IPasswdValidateService {

	public List<ObjectError> validate(Object obj, Errors error);

}
