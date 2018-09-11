package com.bjensen.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.bjensen.service.IPasswdValidateService;
import com.bjensen.service.PasswdValidateService;

/**
 * @author bjensen
 * 
 * Java based configuration for PasswdValidatorProject.
 */
@Configuration
@ComponentScan(basePackages = { "com.bjensen", "com.bjensen.model", "com.bjensen.service", "com.bjensen.controller",
		"com.bjensen.config", "com.bjensen.validation" })
public class PasswdValidateConfig {
	
	//Options: can also rely on ComponentScanning as I have as an example with PasswdValidator in PasswdValidateService.

	@Bean
	public IPasswdValidateService passwdValSvc() {
		return new PasswdValidateService();
	}

	@Bean
	public MessageSource passwdRbMsgSrc() {
		ResourceBundleMessageSource passwdRbMsgSrc = new ResourceBundleMessageSource();
		passwdRbMsgSrc.setBasename("messages");
		return passwdRbMsgSrc;
	}

}