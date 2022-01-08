package com.springboot.userservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.springboot.userservice.dto.UserDeleteResponseDto;
import com.springboot.userservice.dto.UserRequestDto;
import com.springboot.userservice.dto.UserResponseDto;
import com.springboot.userservice.entity.Cart;
import com.springboot.userservice.entity.User;
import com.springboot.userservice.feignentity.Item;
import com.springboot.userservice.feignentity.Orders;

public interface UserService 
{
	boolean registerUser(UserRequestDto userRequestDto);
	List<UserResponseDto> getAllUsersDetails();
	List<User> getAllUsersDetails(int pageNo, int pageSize);
	UserResponseDto getUsersDetails(Integer userId);
	boolean updateUsersDetails(Integer userId, UserRequestDto customerRequestDto);
	UserDeleteResponseDto deleteUsersDetails(Integer userId);
	
	public ResponseEntity<List<Item>> getAllItems();
	public ResponseEntity<Item> getItemByName(@PathVariable String itemName);
	public String addToCart(String itemName,Integer userId);
	public String orderCart(Integer userId);
	public ResponseEntity<String> makeOrder(@PathVariable Integer userId,@PathVariable double amount);
	public ResponseEntity<List<Orders>> getOrdersBydate(@PathVariable Integer userId,@PathVariable String startDate,@PathVariable String endDate);
	public String removeItemsOrdered(String itemName,Integer userId);
	public List<Cart> showItemsInCart(Integer userId);
}
