package com.accolite.au.onboarding.exceptions;

public class EntityInstanceNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "The requested entity instance does not exist";
	}
}
