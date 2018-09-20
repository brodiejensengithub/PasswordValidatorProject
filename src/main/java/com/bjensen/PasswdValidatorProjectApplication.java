package com.bjensen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot execution class for development and testing using external tools 
 * on this API such as PostMan.
 * 
 * @author bjensen
 */
@SpringBootApplication
public class PasswdValidatorProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PasswdValidatorProjectApplication.class, args);
	}
}