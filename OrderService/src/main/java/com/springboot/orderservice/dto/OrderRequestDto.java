package com.springboot.orderservice.dto;

import java.time.LocalDate;

public class OrderRequestDto 
{
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public OrderRequestDto() {}
	public OrderRequestDto(Integer userId, double amount, LocalDate date) {
		this.userId = userId;
		this.amount = amount;
		this.date = date;
	}
}