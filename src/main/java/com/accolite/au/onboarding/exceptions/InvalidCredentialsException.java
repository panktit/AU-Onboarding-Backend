package com.accolite.au.onboarding.exceptions;

public class InvalidCredentialsException extends Exception {
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "The credentials provided are invalid";
	}
}
