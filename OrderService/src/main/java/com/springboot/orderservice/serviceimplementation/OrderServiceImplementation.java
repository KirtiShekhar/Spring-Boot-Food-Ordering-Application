package com.springboot.orderservice.serviceimplementation;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.orderservice.dto.OrderRequestDto;
import com.springboot.orderservice.dto.OrderResponseDto;
import com.springboot.orderservice.entity.Orders;
import com.springboot.orderservice.exception.OrderHistoryNotFoundException;
import com.springboot.orderservice.repository.OrderRepository;
import com.springboot.orderservice.service.OrderService;

@Service
public class OrderServiceImplementation implements OrderService 
{
	@Autowired
	OrderRepository orderRepository;  

	@Override
	public void makeOrder(Integer userId, double amount) 
	{
		Orders order = new Orders();
		order.setUserId(userId);
		order.setAmount(amount);
		order.setDate(LocalDate.now());
		orderRepository.save(order);

	}

	@Override
	public List<Orders> getOrderByDate(Integer userId,String fromDate,String toDate) 
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		LocalDate startDate = LocalDate.parse(fromDate, formatter);
		LocalDate endDate = LocalDate.parse(toDate, formatter);
		List<Orders> list=orderRepository.getByDateAndUserId(startDate, endDate, userId);
		if(!list.isEmpty())
		{
			return list;
		}
		else
		{
			throw new OrderHistoryNotFoundException("No History between the given dates");
		}
	}
	
	public List<Orders> getOrdersByMonthAndUserId(Integer userId)
	{
		List<Orders> list=orderRepository.getByMonthAndUserId(userId);
		if(!list.isEmpty())
		{
			return list;
		}
		else
		{
			throw new OrderHistoryNotFoundException("No History exist for the given month");
		}
	}
}