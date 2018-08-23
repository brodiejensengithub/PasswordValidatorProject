package com.bjensen.model;

import org.springframework.stereotype.Component;

/**
 * @author bjensen
 *
 * Model object to validate. 
 */
@Component
public class Password {

	private String passwd;

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}