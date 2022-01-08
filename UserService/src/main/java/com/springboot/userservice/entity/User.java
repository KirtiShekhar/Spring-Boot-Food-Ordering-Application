package com.springboot.userservice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private Integer userId;
	@Column(name = "userFullName")
	private String userFullName;
	@Column(name = "emailAddress")
	private String emailAddress;
	@Column(name = "contactNumber")
	private String contactNumber;
	@Column(name = "aadharNumber")
	private String aadharNumber;
	@Column(name = "userName")
	private String userName;
	@Column(name = "password")
	private String password;
	@Column(name = "roles")
	private String roles;
	@Column(name = "walletAmount")
	private double walletAmount;
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_userId",referencedColumnName = "userId")
	private List<Address> address;
	
	public User() {}

	public User(Integer userId, String userFullName, String emailAddress, String contactNumber, String aadharNumber,
			String userName, String password, String roles, double walletAmount, List<Address> address) {
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
