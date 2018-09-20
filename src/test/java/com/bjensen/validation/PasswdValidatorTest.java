package com.bjensen.validation;

import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.bjensen.config.PasswdValidateConfig;
import com.bjensen.model.Password;
import com.bjensen.validation.PasswdValidator;

/**
 * Unit tests for for PasswdValidator.
 * 
 * @author bjensen
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { PasswdValidateConfig.class })
public class PasswdValidatorTest {

	@Autowired
	PasswdValidator passwdValidator;

	@Autowired
	MessageSource passwdRbMsgSrc;

	@Autowired
	Password passwdObject;

	Errors error;

	String passwdString;

	@Before
	public void setup() {
		error = new BeanPropertyBindingResult(passwdObject, "passwd");
	}

	/**
	 * Unit test for PasswdValidator.isPasswdLength(String passwdString, Errors
	 * error). Tests passwdString is between 5 and 12 in length.
	 * 
	 */
	@Test
	public void testCheckPasswdLength() {

		// this value will pass as-is, can manipulate to fail if desired.
		// This value intentionally violates other constraints for isolation.
		passwdString = "jsABgdft";

		assertTrue(passwdRbMsgSrc.getMessage("passwd.length", null, Locale.ENGLISH),
				passwdValidator.isPasswdLength(passwdString, error));
	}

	/**
	 * Unit test for PasswdValidator.isPasswdCase(String passwdString, Errors
	 * error). Tests passwdString does not contain an uppercase character.
	 * 
	 */
	@Test
	public void testCheckPasswdCase() {

		// this value will pass as-is, can manipulate to fail if desired.
		// This value intentionally violates other constraints for isolation.
		passwdString = "jabcdabcdsghlhyfrejiiklkfujmnfrty";

		assertTrue(passwdRbMsgSrc.getMessage("passwd.lowercase.only", null, Locale.ENGLISH),
				passwdValidator.isPasswdCase(passwdString, error));
	}

	/**
	 * Unit test for PasswdValidator.isPasswdCharAndInt(String passwdString, Errors
	 * error). Tests passwdString contains at least one character and one number.
	 * 
	 */
	@Test
	public void testCheckPasswdCharAndInt() {

		// this value will pass as-is, can manipulate to fail if desired.
		// This value intentionally violates other constraints for isolation.
		passwdString = "jsghABCABClhyfrejiiklkfujmnfrty9";

		assertTrue(passwdRbMsgSrc.getMessage("passwd.char.int", null, Locale.ENGLISH),
				passwdValidator.isPasswdCharAndInt(passwdString, error));
	}

	/**
	 * Unit test for PasswdValidator.isPasswdContainsSequence(String passwdString,
	 * Errors error). Tests passwdString does not contain a sequence of characters
	 * followed by the same sequence.
	 * 
	 */
	@Test
	public void testCheckPasswdSequence() {

		// this value will pass as-is, can manipulate to fail if desired.
		// This value intentionally violates other constraints for isolation.
		passwdString = "jsghALYlhyfrTRFeabajiiklkfPNBujmnfrty";

		assertTrue(passwdRbMsgSrc.getMessage("passwd.repeating.sequences", null, Locale.ENGLISH),
				passwdValidator.isPasswdContainsSequence(passwdString, error));
	}

}