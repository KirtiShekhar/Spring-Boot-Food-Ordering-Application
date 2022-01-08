package com.springboot.userservice.exception;

import java.util.HashMap;
import java.util.Map;

public class ValidationErrorResponse extends ErrorResponse 
{
	private Map<String , String> errors=new HashMap<String , String>();
	public Map<String, String> getErrors()
	{
		return errors;
	}
	public void setErrors(Map<String, String> errors)
	{
		this.errors=errors;
	}
		
}
