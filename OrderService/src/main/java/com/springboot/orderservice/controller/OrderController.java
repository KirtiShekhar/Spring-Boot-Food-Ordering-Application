package com.springboot.orderservice.controller;

import java.util.List;
import java.sql.Date;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.orderservice.dto.OrderResponseDto;
import com.springboot.orderservice.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;

import com.springboot.orderservice.entity.Orders;

@RestController
@RequestMapping("/order")
public class OrderController 
{
	@Autowired
	OrderService orderService;
	
	private static final Logger OrderLogger = LoggerFactory.getLogger(OrderController.class);
	
	@PostMapping("/user/{userId}/amt/{amount}")
	@Operation(summary = "Order made by specific user")
	public ResponseEntity<String> makeOrder(@PathVariable Integer userId,@PathVariable double amount)
	{
		OrderLogger.info("Order made by specific user");
		orderService.makeOrder(userId,amount);
		return new ResponseEntity<String>("Order saved", HttpStatus.OK);
		
	}
	
	@GetMapping("/user/{userId}/sd/{startDate}/ed/{endDate}")
	@Operation(summary = "Get the order history between the gien dates")
	public ResponseEntity<List<Orders>> getOrdersBydate(@PathVariable Integer userId,@PathVariable String startDate,@PathVariable String endDate)
	{
		OrderLogger.info("Get the order history between the gien dates");
		List<Orders> list=orderService.getOrderByDate(userId,startDate,endDate);
		return new ResponseEntity<List<Orders>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	@Operation(summary = "Get the order history by month")
	public ResponseEntity<List<Orders>> getOrdersByMonth(@PathVariable Integer userId)
	{
		OrderLogger.info("Get the order history by month");
		List<Orders> list=orderService.getOrdersByMonthAndUserId(userId);
		return new ResponseEntity<List<Orders>>(list, HttpStatus.OK);
	}
}
