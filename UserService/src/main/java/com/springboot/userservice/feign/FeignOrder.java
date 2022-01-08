package com.springboot.userservice.feign;

import java.sql.Date;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.springboot.userservice.feignentity.Orders;

@FeignClient("OrderService")
public interface FeignOrder 
{
	@PostMapping("/order/user/{userId}/amt/{amount}")
	public ResponseEntity<String> makeOrder(@PathVariable Integer userId,@PathVariable double amount);
	
	@GetMapping("/order/user/{userId}/sd/{startDate}/ed/{endDate}")
	public ResponseEntity<List<Orders>> getOrdersBydate(@PathVariable Integer userId,@PathVariable String startDate,@PathVariable String endDate);
}
