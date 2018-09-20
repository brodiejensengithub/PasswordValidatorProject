package com.bjensen.model;

import org.springframework.stereotype.Component;

/**
 * Password Model/Resource object to bind and validate.
 * 
 * @author bjensen
 */
@Component
public class Password {
	
	private String passwd;
	
	public Password () {
	}
	
	public Password (String passwd) {
		this.passwd = passwd;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}