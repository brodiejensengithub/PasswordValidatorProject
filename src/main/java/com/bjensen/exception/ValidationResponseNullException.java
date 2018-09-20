package com.bjensen.exception;

/**
 * This Exception class is provided for demonsration purposes for the use
 * of Spring @RestControllerAdvice and Exception handling in MVC on the Controller.
 * 
 * @author bjensen
 *
 */
public class ValidationResponseNullException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidationResponseNullException(String msg) {
		super(msg);
	}

}
