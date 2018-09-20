package com.bjensen.service;

import org.springframework.validation.Errors;
import com.bjensen.model.ValidationResponse;

/**
 * ValidateService interface for proper Type DI and extensibility if needed.
 * 
 * @author bjensen
 */
public interface IValidateService {

	public ValidationResponse validate(Object obj, Errors error);
	
}