package com.gm.infobus.entity.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class BaseValidator implements Validator {
	private final static String EMAIL_PATTERN = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	private final static String MOBILE_PATTERN = "^(13[4,5,6,7,8,9]|15[0,8,9,1,7]|188|187)\\d{8}$";

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {

	}

	protected boolean isValidEmail(String email) {
		if (email == null) {
			return false;
		}
		Pattern regex = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = regex.matcher(email);
		return matcher.matches();
	}

	protected boolean isValidMobileNumber(String mobile) {
		if (mobile == null) {
			return false;
		}
		Pattern regex = Pattern.compile(MOBILE_PATTERN);
		Matcher matcher = regex.matcher(mobile);
		return matcher.matches();
	}
}
