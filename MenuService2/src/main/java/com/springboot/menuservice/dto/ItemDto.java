package com.springboot.menuservice.dto;

import javax.validation.constraints.NotEmpty;

public class ItemDto {
	
	@NotEmpty(message ="itemName can't be empty")
	private String itemName;
	@NotEmpty(message = "price can;t be null")
	private double price;
	@NotEmpty(message ="description can't be empty")
	private String description;
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}