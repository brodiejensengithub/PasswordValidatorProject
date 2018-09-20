package com.bjensen.model;

import org.springframework.stereotype.Component;

/**
 * This Response object encapsulates ObjectError attributes for ModelView return to UI.
 * Two attributes 'code' and 'defaultMessage' were chosen for this exercise.
 * 
 * @author bjensen
 *
 */
@Component
public class ValidationResponse {

	private String code;
	private String defaultMessage;
	
	public ValidationResponse () {}
	
	public ValidationResponse (String code,	String defaultMessage) {
		this.code = code;
		this.defaultMessage = defaultMessage;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDefaultMessage() {
		return defaultMessage;
	}
	public void setDefaultMessage(String defaultMessage) {
		this.defaultMessage = defaultMessage;
	}

}