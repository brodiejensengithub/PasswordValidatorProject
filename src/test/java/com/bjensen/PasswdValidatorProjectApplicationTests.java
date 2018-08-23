package com.bjensen;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.Errors;

import com.bjensen.config.PasswdValidateConfig;
import com.bjensen.model.Password;
import com.bjensen.service.IPasswdValidateService;

/**
 * @author bjensen
 * 
 * Test cases for for PasswdValidatorProject.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = PasswdValidateConfig.class)
public class PasswdValidatorProjectApplicationTests {

	@Autowired
	private Password passwd;

	@Autowired
	IPasswdValidateService passwdValSvc;

	/**
	 * Test case for null passwd.
	 */
	@Test
	public void checkPasswdExists() {
		assertNotNull(passwd);
	}

	/**
	 * Test case for PasswdValidateService
	 */
	@Test
	public void testPasswdValidatorService() {
		String password = "sdjgkh"; // modify this value to test each validation rule.
		Errors error = null;
		passwd.setPasswd(password);
		passwdValSvc.validate(passwd, error);
	}

}