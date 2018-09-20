package com.bjensen.validation;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bjensen.model.Password;

/**
 * Validator class used to enforce constraints on a Password object. This class
 * implements the Spring Validator Interface methods as defined per the API.
 * 
 * @author bjensen
 * 
 */
@Component
public class PasswdValidator implements Validator {

	public static final int MINIMUM_PASSWORD_LENGTH = 5;
	public static final int MAXIMUM_PASSWORD_LENGTH = 12;

	@Autowired
	MessageSource passwdRbMsgSrc;

	/**
	 * Spring Validator Interface method implementation.
	 * 
	 * @param passwd Password Object to be validated.
	 * @param error  Errors Object to contain data binding and validation errors.
	 */
	public void validate(Object passwd, Errors error) {

		Password passwdObject = (Password) passwd;
		String passwdString = passwdObject.getPasswd().trim();

		ValidationUtils.rejectIfEmpty(error, "passwd",
				passwdRbMsgSrc.getMessage("passwd.bad.request", null, Locale.ENGLISH),
				passwdRbMsgSrc.getMessage("passwd.empty", null, Locale.ENGLISH));

		isPasswdLength(passwdString, error);

		isPasswdCase(passwdString, error);

		isPasswdCharAndInt(passwdString, error);

		isPasswdContainsSequence(passwdString, error);
	}

	/**
	 * Test length constraints on the password parameter.
	 * 
	 * @param passwdString is the password value being passed for length evaluation.
	 * @param error Errors Object to contain data binding and validation
	 * errors if any.
	 * 
	 * @return boolean true if passwdString is between 5 and 12 in length.
	 */
	public boolean isPasswdLength(String passwdString, Errors error) {

		if (passwdString.length() < MINIMUM_PASSWORD_LENGTH || passwdString.length() > MAXIMUM_PASSWORD_LENGTH) {
			error.rejectValue("passwd", passwdRbMsgSrc.getMessage("passwd.bad.request", null, Locale.ENGLISH), null,
					passwdRbMsgSrc.getMessage("passwd.length", null, Locale.ENGLISH));
			return false;
		}
		return true;
	}

	/**
	 * Check password value for presence of upper-alpha characters.
	 * 
	 * @param passwdString String is the password value being passed for evaluation.
	 * @param error Errors Object to contain data binding and validation
	 * errors if any.
	 * 
	 * @return boolean true if passwdString does not contain an uppercase character.
	 */
	public boolean isPasswdCase(String passwdString, Errors error) {

		final Matcher caseMatcher = Pattern.compile("[A-Z]").matcher(passwdString);

		if (caseMatcher.find()) {
			error.rejectValue("passwd", passwdRbMsgSrc.getMessage("passwd.bad.request", null, Locale.ENGLISH), null,
					passwdRbMsgSrc.getMessage("passwd.lowercase.only", null, Locale.ENGLISH));
			return false;
		}
		return true;
	}

	/**
	 * Check password value for presence of at least one character and one number.
	 * 
	 * @param passwdString String is the password value being passed for evaluation.
	 * @param error Errors Object to contain data binding and validation
	 * errors if any.
	 * 
	 * @return boolean true if passwdString contains at least one character and one
	 * number.
	 */
	public boolean isPasswdCharAndInt(String passwdString, Errors error) {

		final Matcher charIntMatcher = Pattern.compile("[a-z]+\\d+|\\d+[a-z]+").matcher(passwdString);

		if (!charIntMatcher.find()) {
			error.rejectValue("passwd", passwdRbMsgSrc.getMessage("passwd.bad.request", null, Locale.ENGLISH), null,
					passwdRbMsgSrc.getMessage("passwd.char.int", null, Locale.ENGLISH));
			return false;
		}
		return true;
	}

	/**
	 * Check password value for presence of repeating sequential characters.
	 * 
	 * @param passwdString String is the password value being passed for evaluation.
	 * @param error Errors Object to contain data binding and validation
	 * errors if any.
	 * 
	 * @return boolean true if passwdString does not contain a sequence of
	 * characters followed by the same sequence.
	 */
	public boolean isPasswdContainsSequence(String passwdString, Errors error) {

		final Matcher seqMatcher = Pattern.compile("(\\w{2,})\\1").matcher(passwdString);

		if (seqMatcher.find()) {
			error.rejectValue("passwd", passwdRbMsgSrc.getMessage("passwd.bad.request", null, Locale.ENGLISH), null,
					passwdRbMsgSrc.getMessage("passwd.repeating.sequences", null, Locale.ENGLISH));
			return false;
		}
		return true;
	}

	/**
	 * Describes which Objects this Validator can validate against.
	 * 
	 * @param clazz Class this Validator implementation supports as per the Spring
	 * Validator Interface.
	 * @return Returns a boolean if this Validator can support validation for the
	 * provided Class.
	 */
	@Override
	public boolean supports(Class<?> clazz) {

		return Password.class.isAssignableFrom(clazz);
	}

}