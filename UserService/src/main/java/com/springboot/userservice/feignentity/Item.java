package com.springboot.userservice.feignentity;

public class Item 
{
	
	private Integer itemId;
	private String itemName;
	private double price;
	private String description;
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
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
	public Item(Integer itemId, String itemName, double price, String description) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.price = price;
		this.description = description;
	}
	
	
}

