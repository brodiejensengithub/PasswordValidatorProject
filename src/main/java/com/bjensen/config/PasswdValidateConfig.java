package com.bjensen.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.bjensen.service.IPasswdValidateService;
import com.bjensen.service.PasswdValidateService;
import com.bjensen.validation.PasswdValidator;

/**
 * @author bjensen
 * 
 * Java based configuration for PasswdValidatorProject.
 */
@Configuration
@ComponentScan(basePackages = { "com.bjensen", "com.bjensen.model", "com.bjensen.service", "com.bjensen.controller",
		"com.bjensen.config", "com.bjensen.validation" })
public class PasswdValidateConfig {

	@Bean
	public IPasswdValidateService passwdValSvc() {
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