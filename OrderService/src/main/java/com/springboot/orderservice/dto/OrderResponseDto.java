package com.springboot.orderservice.dto;

import java.sql.Date;
import java.time.LocalDate;

public class OrderResponseDto 
{
private Integer orderId;
private Integer userId;
private double amount;
private LocalDate date;

public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public LocalDate getDate() {
	return date;
}
public void setDate(LocalDate date) {
	this.date = date;
}


public Integer getOrderId() {
	return orderId;
}
public void setOrderId(Integer orderId) {
	this.orderId = orderId;
}
public Integer getUserId() {
	return userId;
}
public void setUserId(Integer userId) {
	this.userId = userId;
}

public OrderResponseDto() {}
public OrderResponseDto(Integer orderId, Integer userId, double amount, LocalDate date) {
	this.orderId = orderId;
	this.userId = userId;
	this.amount = amount;
	this.date = date;
}

}
