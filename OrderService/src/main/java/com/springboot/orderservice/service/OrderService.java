package com.springboot.orderservice.service;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.springboot.orderservice.dto.OrderResponseDto;
import com.springboot.orderservice.entity.Orders;

public interface OrderService 
{
	public void makeOrder(Integer userId, double amount);

	public List<Orders> getOrderByDate(Integer userId, String startDate, String endDate);
	
	public List<Orders> getOrdersByMonthAndUserId(Integer userId);
}
