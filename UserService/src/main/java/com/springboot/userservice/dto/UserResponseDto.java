package com.springboot.userservice.dto;

import java.util.List;
import com.springboot.userservice.entity.Address;

public class UserResponseDto 
{
	private Integer userId;
	private String userFullName;
	private String emailAddress;
	private String contactNumber;
	private String aadharNumber;
	private String userName;
	private String password;
	private String roles;
	private double walletAmount;
	private List<Address> address;
	
	public UserResponseDto() {}

	public UserResponseDto(Integer userId, String userFullName, String emailAddress, String contactNumber,
			String aadharNumber, String userName, String password, String roles, double walletAmount,
			List<Address> address) {
		this.userId = userId;
		this.userFullName = userFullName;
		this.emailAddress = emailAddress;
		this.contactNumber = contactNumber;
		this.aadharNumber = aadharNumber;
		this.userName = userName;
		this.password = password;
		this.roles = roles;
		this.walletAmount = walletAmount;
		this.address = address;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public double getWalletAmount() {
		return walletAmount;
	}

	public void setWalletAmount(double walletAmount) {
		this.walletAmount = walletAmount;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	
	
}
