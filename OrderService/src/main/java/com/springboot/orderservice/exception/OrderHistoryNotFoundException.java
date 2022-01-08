package com.springboot.orderservice.exception;

public class OrderHistoryNotFoundException extends RuntimeException
{
	
	private static final long serialVersionUID=1L;
	
	public OrderHistoryNotFoundException(String message)
	{
		super(message);
	}

}