package com.bjensen.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.bjensen.model.Password;
import com.bjensen.model.ValidationResponse;
import com.bjensen.service.IValidateService;
import com.bjensen.service.PasswdValidateService;
import com.bjensen.validation.PasswdValidator;

/**
 * Java based configuration for PasswdValidatorProject.
 *  
 * @author bjensen
 * 
 */

@Configuration
@ComponentScan(basePackages = { "com.bjensen", "com.bjensen.model", "com.bjensen.service",
		"com.bjensen.controller", "com.bjensen.config", "com.bjensen.validation" })
public class PasswdValidateConfig {

	@Bean
	public ValidationResponse validationResponse() {
		return new ValidationResponse();
	}
	
	@Bean
	public Password passwdObject() {
		return new Password();
	}
	
	@Bean
	public IValidateService passwdValSvc() {
		return new PasswdValidateService();
	}
	
	@Bean
	public PasswdValidator passwdValidator() {
		return new PasswdValidator();
	}

	@Bean
	public MessageSource passwdRbMsgSrc() {
		ResourceBundleMessageSource passwdRbMsgSrc = new ResourceBundleMessageSource();
		passwdRbMsgSrc.setBasename("messages");
		return passwdRbMsgSrc;
	}

}