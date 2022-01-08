package com.springboot.orderservice.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.springboot.orderservice.entity.Orders;
import com.springboot.orderservice.service.OrderService;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest 
{
	@Mock
	OrderService orderService;
	
	@InjectMocks
	OrderController orderController;
	
	Orders orders;
	List<Orders> ordersList;

	@BeforeEach
	void setUp() throws Exception 
	{
		orders = new Orders();
		orders.setOrderId(1);
		orders.setUserId(1);
		orders.setAmount(200.00);
		orders.setDate(LocalDate.now());
		ordersList = new ArrayList<Orders>();
		ordersList.add(orders);
	}

	@Test
	@DisplayName("GetOrder History By Date")
	void GetOrdersBydate_Test() 
	{
		// context
		when(orderService.getOrderByDate(1,"2022-01-01","2022-01-06")).thenReturn(ordersList);
		//event
		ResponseEntity<List<Orders>> orderhistoryresultresult = orderController.getOrdersBydate(1,"2022-01-01","2022-01-06");
		//outcome
		assertEquals(ordersList, orderhistoryresultresult.getBody());
		assertEquals(HttpStatus.OK, orderhistoryresultresult.getStatusCode());
	}

	@Test
	@DisplayName("GetOrder History By Month")
	void GetOrdersByMonth_Test() 
	{
		// context
		when(orderService.getOrdersByMonthAndUserId(1)).thenReturn(ordersList);
		//event
		ResponseEntity<List<Orders>> orderhistoryresultresult = orderController.getOrdersByMonth(1);
		//outcome
		assertEquals(ordersList, orderhistoryresultresult.getBody());
		assertEquals(HttpStatus.OK, orderhistoryresultresult.getStatusCode());
	}

}
