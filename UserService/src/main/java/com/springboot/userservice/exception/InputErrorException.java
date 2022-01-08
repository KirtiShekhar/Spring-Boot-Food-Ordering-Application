package com.springboot.userservice.exception;

public class InputErrorException extends RuntimeException
{
	
	private static final long serialVersionUID=1L;
	
	public InputErrorException(String message)
	{
		super(message);
	}

}