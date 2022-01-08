package com.springboot.userservice.dto;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.springboot.userservice.entity.Address;

public class UserRequestDto 
{
	@NotBlank(message = "userFullName cannot be null")
	@NotEmpty(message = "userFullName cannot be empty")
	private String userFullName;
	@NotBlank(message = "emailAddress cannot be null")
	@NotEmpty(message = "emailAddress cannot be empty")
	private String emailAddress;
	@NotBlank(message = "contactNumber cannot be null")
	@NotEmpty(message = "contactNumber cannot be empty")
	private String contactNumber;
	@NotBlank(message = "aadharNumber cannot be null")
	@NotEmpty(message = "aadharNumber cannot be empty")
	private String aadharNumber;
	@NotBlank(message = "userName cannot be null")
	@NotEmpty(message = "userName cannot be empty")
	private String userName;
	@NotBlank(message = "password cannot be null")
	@NotEmpty(message = "password cannot be empty")
	private String password;
	@NotBlank(message = "roles cannot be null")
	@NotEmpty(message = "roles cannot be empty")
	private String roles;
	@Min(value = 100,message = "Wallet amount cannot be less than 100")
	private double walletAmount;
	@NotNull(message = "Address cannot be null")
	@NotEmpty(message = "Address cannot be empty")
	private List<Address> address;
	
	public UserRequestDto() {}

	public UserRequestDto(String userFullName, String emailAddress, String contactNumber, String aadharNumber,
			String userName, String password, String roles, double walletAmount, List<Address> address) {
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
