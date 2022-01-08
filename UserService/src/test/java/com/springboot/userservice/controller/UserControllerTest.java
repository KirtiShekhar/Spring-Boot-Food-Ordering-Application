package com.springboot.userservice.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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

import com.springboot.userservice.dto.UserDeleteResponseDto;
import com.springboot.userservice.dto.UserRequestDto;
import com.springboot.userservice.dto.UserResponseDto;
import com.springboot.userservice.entity.Address;
import com.springboot.userservice.entity.Cart;
import com.springboot.userservice.entity.User;
import com.springboot.userservice.feignentity.Item;
import com.springboot.userservice.service.UserService;



@ExtendWith(MockitoExtension.class)
class UserControllerTest 
{
	@Mock
	UserService userService;

	@InjectMocks
	UserController userController;

	UserRequestDto userRequestDto;
	UserResponseDto userResponseDto;
	UserDeleteResponseDto userDeleteResponseDto; 
	List<Address> addresses,addresses1;
	User user;
	Item items,items1;
	List<Item> itemList;
	Cart cart,cart1;
	List<Cart> cartList;

	@BeforeEach
	void setUp() throws Exception 
	{
		addresses = new ArrayList<Address>();
		addresses1 = new ArrayList<Address>();
		addresses.add(new Address(1,"Haryana","Gurugram",110093L,"Current"));
		addresses.add(new Address(2,"New Delhi","Dilshad Colony",110095L,"Permanent"));
		addresses1.add(new Address(3,"Uttar Pradesh","Allahabad",520152L,"Current"));
		addresses1.add(new Address(4,"New Delhi","Janakpuri",110096L,"Permanent"));

		userRequestDto = new UserRequestDto();
		userRequestDto.setUserFullName("Nidhi Updhyay");
		userRequestDto.setAadharNumber("757385263524");
		userRequestDto.setContactNumber("9864517852");
		userRequestDto.setEmailAddress("nidhiup@gmail.com");
		userRequestDto.setPassword("Nidhi2511");
		userRequestDto.setRoles("USER");
		userRequestDto.setUserFullName("Nidhi Updhyay");
		userRequestDto.setUserName("Nidhi");
		userRequestDto.setWalletAmount(2000.00);
		userRequestDto.setAddress(addresses);

		userResponseDto = new UserResponseDto();
		userResponseDto.setUserId(1);
		userResponseDto.setUserFullName("Mihir Pandey");
		userResponseDto.setAadharNumber("757385269966");
		userResponseDto.setContactNumber("9864516644");
		userResponseDto.setEmailAddress("mihircool@gmail.com");
		userResponseDto.setPassword("Mihir2511");
		userResponseDto.setRoles("USER");
		userResponseDto.setUserFullName("Mihir Pandey");
		userResponseDto.setUserName("Mihir");
		userResponseDto.setWalletAmount(6000.00);
		userResponseDto.setAddress(addresses1);
		
		itemList = new ArrayList<>();
		items = new Item(1,"Samsung S6",64000.00,"Electronic Device");
		items1 =  new Item(2,"Hp Laptop",75000.00,"Electronic Device");
		itemList.add(items);
		itemList.add(items1);
		
		userDeleteResponseDto = new UserDeleteResponseDto(1,"User Deleted Successfully");
		cart = new Cart();
		cart1 = new Cart();
		cart.setCartId(1);
		cart.setUserId(2);
		cart.setItemName("pav bhaji");
		cart.setAmount(100.0);
		cart1.setCartId(2);
		cart1.setUserId(2);
		cart1.setItemName("Chappati");
		cart1.setAmount(200.0);
//		cartList.add(new Cart(1,2,"biryani",200.0));
//		cartList.add(new Cart(2,2,"dosa",250.0));
	}

	@Test
	@DisplayName("Register User : Positive")
	void RegisterUserTest_Positive() 
	{
		// context
		when(userService.registerUser(userRequestDto)).thenReturn(true);
		//event
		ResponseEntity<String> registereduserresult = userController.saveCustomerDetails(userRequestDto);
		//outcome
		assertEquals("User Registered Successfully", registereduserresult.getBody());
		assertEquals(HttpStatus.ACCEPTED, registereduserresult.getStatusCode());
	}

	@Test
	@DisplayName("Register User : Negative")
	void RegisterUserTest_Negative() 
	{
		// context
		when(userService.registerUser(userRequestDto)).thenReturn(false);
		//event
		ResponseEntity<String> registereduserresult = userController.saveCustomerDetails(userRequestDto);
		//outcome
		assertEquals("User Registered Unsuccessfully", registereduserresult.getBody());
		assertEquals(HttpStatus.BAD_REQUEST, registereduserresult.getStatusCode());
	}
	@Test 
	@DisplayName("Get All User Details") 
	void GetAllUsersDetailsTest() 
	{
		//context
		when(userService.getAllUsersDetails()).thenReturn(Stream.of(new UserResponseDto(2,"Vinay Teja","vinay@gmail.com","9955632511","956235241412","Vinay","Vinay1234","User",2500.00,addresses),new UserResponseDto(3,"Manik Tanwar","manik@gmail.com","9865321471","241789632541","manik","manik1234","User",66000.00,addresses1)).collect(Collectors.toList()));
		//event 
		List<UserResponseDto> userdetails = userController.getUserDetails(); 
		//outcome 
		assertEquals(2,userdetails.size()); 
	}

	@Test 
	@DisplayName("Get User Details on Given UserId") 
	void UserDetailsByIdTest() 
	{
		// context  CustomerResponse(String emailAddress, String contactNumber)
		when(userService.getUsersDetails(1)).thenReturn(userResponseDto);
		//event 
		UserResponseDto UserDetailsById = userController.getUserDetails(1);
		//outcome
		assertEquals(userResponseDto, UserDetailsById);
	}
	
	@Test 
	@DisplayName("Delete User Details For the Given UserId") 
	void DeleteUserByIdTest() 
	{
		// context 
		when(userService.deleteUsersDetails(1)).thenReturn(userDeleteResponseDto);
		//event 
		ResponseEntity<String> deletedUserResult = userController.deleteUserDetails(1);
		//outcome
		assertEquals("Specified User with Id Deleted Successfully", deletedUserResult.getBody());
	}
	
	@Test 
	@DisplayName("Add items to the cart Test") 
	void AddItemsToTheCartTest() 
	{
		// context 
		when(userService.addToCart("pav bhaji",2)).thenReturn("Item Added to the cart : " + cart.getItemName());
		//event 
		String itemsaddedResult = userController.addToCart("pav bhaji",2);
		//outcome
		assertEquals("Item Added to the cart : " + cart.getItemName(),itemsaddedResult);
	}
	
	@Test 
	@DisplayName("Getting all the items in the cart Test") 
	void GetAllItemsIoTheCartTest() 
	{
		// context 
		when(userService.showItemsInCart(2)).thenReturn(Stream.of(new Cart(1,2,"biryani",200.0),new Cart(2,2,"dosa",250.0)).collect(Collectors.toList()));;
		//event 
		List<Cart> cartListResult = userController.showItemsOrdered(2);
		//outcome
		assertEquals(cartListResult.size(),2);
	}
	
	@Test 
	@DisplayName("Removing the items in the cart Test") 
	void RemovingItemsInCartTest() 
	{
		// context 
		when(userService.removeItemsOrdered("pav bhaji",2)).thenReturn("Item removed successfully");
		//event 
		String deleteditemsResult = userController.removeItemsOrdered("pav bhaji",2);
		//outcome
		assertEquals("Item removed successfully",deleteditemsResult);
	}
	
	

}
