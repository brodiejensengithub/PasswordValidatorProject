package com.bjensen.validation;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bjensen.model.Password;

/**
 * @author bjensen
 *
 *         Validator class to enforce rules on passwd attribute of Password
 *         object.
 */
public class PasswdValidator implements Validator {

	private static final int MINIMUM_PASSWORD_LENGTH = 5;
	private static final int MAXIMUM_PASSWORD_LENGTH = 12;

	@Autowired
	MessageSource passwdRbMsgSrc;

	/**
	 * Spring Validator implementation.
	 * 
	 * @param obj to be validated.
	 * @param error contain data binding and validation errors
	 */
	public void validate(Object obj, Errors error) {

		Password passwd = (Password) obj;
		String passwdString = passwd.getPasswd().trim();

		Matcher caseMatcher = Pattern.compile("[A-Z]").matcher(passwdString); // no uppercase chars
		Matcher seqMatcher = Pattern.compile("(\\w{2,})\\1").matcher(passwdString); // no repeated char seq
		Matcher charIntMatcher = Pattern.compile("[a-z]+\\d+|\\d+[a-z]+").matcher(passwdString); // at least one char and int																								

		ValidationUtils.rejectIfEmpty(error, "passwd", passwdRbMsgSrc.getMessage("passwd.empty", null, Locale.ENGLISH));

		if (passwdString.length() < MINIMUM_PASSWORD_LENGTH || passwdString.length() > MAXIMUM_PASSWORD_LENGTH) {
			error.rejectValue("passwd", "passwd violates length criteria.");
		} else if (caseMatcher.find()) {
			error.rejectValue("passwd", passwdRbMsgSrc.getMessage("passwd.lowercase.only", null, Locale.ENGLISH));
		} else if (!charIntMatcher.find()) {
			error.rejectValue("passwd", passwdRbMsgSrc.getMessage("passwd.char.int", null, Locale.ENGLISH));
		} else if (seqMatcher.find()) {
			error.rejectValue("passwd", passwdRbMsgSrc.getMessage("passwd.repeating.sequences", null, Locale.ENGLISH));
		}

	}

	/**
	 * Describes which Objects this Validator can validate against.
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return Password.class.isAssignableFrom(clazz);
	}

}