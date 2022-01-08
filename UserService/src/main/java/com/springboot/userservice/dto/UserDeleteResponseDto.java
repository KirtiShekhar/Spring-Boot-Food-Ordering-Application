package com.springboot.userservice.dto;

public class UserDeleteResponseDto {
	
	private Integer userId;
	private String message;
	public Integer getCustomerId() {
		return userId;
	}
	public void setCustomerId(Integer userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public UserDeleteResponseDto() {}
	
	public UserDeleteResponseDto(Integer userId, String message) {
		this.userId = userId;
		this.message = message;
	}
	
	
	


}