package com.springboot.orderservice.serviceimplementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
import com.springboot.orderservice.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplementationTest 
{
	@Mock
	OrderRepository orderRepository;

	@InjectMocks
	OrderServiceImplementation orderServiceImplementation;

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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		LocalDate startDate = LocalDate.parse("2022-01-01", formatter);
		LocalDate endDate = LocalDate.parse("2022-01-06", formatter);
		// context
		when(orderRepository.getByDateAndUserId(startDate,endDate,1)).thenReturn(ordersList);
		//event
		List<Orders> orderhistoryresultresult = orderServiceImplementation.getOrderByDate(1,"2022-01-01","2022-01-06");
		//outcome
		assertEquals(ordersList, orderhistoryresultresult);
	}

	@Test
	@DisplayName("GetOrder History By Month")
	void GetOrdersByMonth_Test() 
	{
		// context
		when(orderRepository.getByMonthAndUserId(1)).thenReturn(ordersList);
		//event
		List<Orders> orderhistoryresultresult = orderServiceImplementation.getOrdersByMonthAndUserId(1);
		//outcome
		assertEquals(ordersList, orderhistoryresultresult);
	}

}
