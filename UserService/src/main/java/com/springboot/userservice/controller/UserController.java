package com.springboot.userservice.controller;

import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.userservice.dto.UserRequestDto;
import com.springboot.userservice.dto.UserResponseDto;
import com.springboot.userservice.service.UserService;
import com.springboot.userservice.entity.Cart;
import com.springboot.userservice.entity.User;
import com.springboot.userservice.feign.FeignMenu;
import com.springboot.userservice.feignentity.Item;
import com.springboot.userservice.feignentity.Orders;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/user")
public class UserController 
{
	private static final Logger UserDetailsLogger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	FeignMenu feignMenu;
	
	@PostMapping("/users")
	@Operation(summary = "Register a specific user")
	public ResponseEntity<String> saveCustomerDetails(@Valid @RequestBody UserRequestDto userRequestDto)
	{
		boolean registerUserResponse = userService.registerUser(userRequestDto);
		if(registerUserResponse)
		{
			UserDetailsLogger.info("User Registered Successfully");
			return new ResponseEntity<String>("User Registered Successfully",HttpStatus.ACCEPTED);
		}
		else
		{
			UserDetailsLogger.info("User Registered Unsuccessfully");
			return new ResponseEntity<String>("User Registered Unsuccessfully",HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PutMapping("/users/{userId}")
	@Operation(summary = "Update the existing user details for the given id")
	public ResponseEntity<String> updateUserDetails(@PathVariable Integer userId,@RequestBody UserRequestDto userRequestDto)
	{
		boolean updateUserResponse = userService.updateUsersDetails(userId, userRequestDto);
		if(updateUserResponse)
		{
			UserDetailsLogger.info("User Details Updated Successfully");
			return new ResponseEntity<String>("User Details Updated Successfully",HttpStatus.ACCEPTED);
		}
		else
		{
			UserDetailsLogger.info("User Details Updated Unsuccessfully");
			return new ResponseEntity<String>("User Details Updated Unsuccessfully",HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/users/{userId}")
	@Operation(summary = "Get the user details for the given id")
	public UserResponseDto getUserDetails(@PathVariable Integer userId)
	{
		UserDetailsLogger.info("Fetching User Data for the entered Id");
		return userService.getUsersDetails(userId);
	}

	@GetMapping("/users")
	@Operation(summary = "Get the list of registered user details")
	public List <UserResponseDto>getUserDetails()
	{
		List<UserResponseDto> userResponseList = userService.getAllUsersDetails();
		UserDetailsLogger.info("Fetching All Stored User Data");
		return userResponseList;
	}
	
	@GetMapping("/users/{pageNo}/{pageSize}")
	@Operation(summary = "Get the list of registered user details with pagination")
    public List<User> getPaginatedUsers(@PathVariable int pageNo, 
            @PathVariable int pageSize) {

        return userService.getAllUsersDetails(pageNo, pageSize);
    }

	@DeleteMapping(value="/users/{userId}")
	@Operation(summary = "Delete the existing user details for the given id")
	public ResponseEntity<String> deleteUserDetails(@PathVariable Integer userId) 
	{
		userService.deleteUsersDetails(userId);
		UserDetailsLogger.info("Specified User with Id Deleted Successfully");
		return new ResponseEntity<String>("Specified User with Id Deleted Successfully",HttpStatus.OK);
	}
	
	@GetMapping("/menu/items")
	@Operation(summary = "Get the list of items in user service using feignclient")
	public ResponseEntity<List<Item>> getAllItems()
	{
		UserDetailsLogger.info("Get the list of items in user service using feignclient");
		return userService.getAllItems();
	}
	
	@GetMapping("/menu/items/{itemName}")
	@Operation(summary = "Get the item data for the entered name in user service using feignclient")
	public ResponseEntity<Item> getItemByName(@PathVariable String itemName)
	{
		UserDetailsLogger.info("Get the item detail of given name");
		return userService.getItemByName(itemName);
	}
	
	@PostMapping("/menu/cart/{itemName}/user/{userId}")
	@Operation(summary = "Adding the items to the cart")
	public String addToCart(@PathVariable String itemName,@PathVariable Integer userId)
	{
		UserDetailsLogger.info("Adding the items to the cart");
		return userService.addToCart(itemName,userId);
	}
	
	@DeleteMapping("/menu/cart/removeitems/{itemName}/user/{userId}")
	@Operation(summary = "Removing the item to the cart")
    public String removeItemsOrdered(@PathVariable String itemName,@PathVariable Integer userId)
    {
		UserDetailsLogger.info("Removing the items from the cart");
        return userService.removeItemsOrdered(itemName, userId); 
    }
	
	@GetMapping("/menu/cart/items/user/{userId}")
	@Operation(summary = "Show all the items available in the cart")
    public List<Cart> showItemsOrdered(Integer userId)
    {
		UserDetailsLogger.info("Showing the items in the cart for specific userid");
		List<Cart>	cartItems = userService.showItemsInCart(userId);	
        return cartItems;
    }
	
	@PostMapping("/cart/order/user/{userId}")
	@Operation(summary = "Order the stored item by specific user")
	public String orderCart(@PathVariable Integer userId)
	{
		UserDetailsLogger.info("Order the stored item by specific user");
		return userService.orderCart(userId);
	}
	
	@GetMapping("/order/user/{userId}/sd/{startDate}/ed/{endDate}")
	@Operation(summary = "Get the history of orders between the entered dates")
	public ResponseEntity<List<Orders>> getOrdersBydate(@PathVariable Integer userId,@PathVariable String startDate,@PathVariable String endDate)
	{
		UserDetailsLogger.info("Get the history of orders between the entered dates");
		return userService.getOrdersBydate(userId, startDate, endDate);
	}
	
	@GetMapping("/menuport")
	@Operation(summary = "Showing different ports on which menu service is running")
    public String getPortInfo()
    {
		UserDetailsLogger.info("Showing different ports on which menu service is running");
		return feignMenu.getInfo();
    }
	
}
