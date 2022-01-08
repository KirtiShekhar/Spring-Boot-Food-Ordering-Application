package com.springboot.userservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "address")
public class Address 
{
	@Id
	@Column(name = "addressId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer addressId;
	@NotBlank(message = "State cannot be null")
	@NotEmpty(message = "State cannot be empty")
	private String state;
	@NotBlank(message = "City cannot be null")
	@NotEmpty(message = "City cannot be empty")
	private String city;
	private Long pinCode;
	@NotBlank(message = "Address Type cannot be null")
	@NotEmpty(message = "Address Type  cannot be empty")
	private String addressType;
	
	public Address() {}

	public Address(Integer addressId,String state,String city, Long pinCode,String addressType) {
		this.addressId = addressId;
		this.state = state;
		this.city = city;
		this.pinCode = pinCode;
		this.addressType = addressType;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getPinCode() {
		return pinCode;
	}

	public void setPinCode(Long pinCode) {
		this.pinCode = pinCode;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	
	
	
	

}
