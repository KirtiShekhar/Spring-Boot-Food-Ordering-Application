package com.springboot.userservice.serviceimplementation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.springboot.userservice.dto.UserRequestDto;
import com.springboot.userservice.dto.UserResponseDto;
import com.springboot.userservice.dto.UserDeleteResponseDto;
import com.springboot.userservice.entity.Cart;
import com.springboot.userservice.entity.User;
import com.springboot.userservice.exception.InputErrorException;
import com.springboot.userservice.exception.UserNotFoundException;
import com.springboot.userservice.feign.FeignMenu;
import com.springboot.userservice.feign.FeignOrder;
import com.springboot.userservice.feignentity.Item;
import com.springboot.userservice.feignentity.Orders;
import com.springboot.userservice.repository.CartRepository;
import com.springboot.userservice.repository.UserRepository;
import com.springboot.userservice.service.UserService;

@Service
public class UserServiceImplementation implements UserService
{
	@Autowired
	UserRepository userRepository;

	@Autowired
	CartRepository cartRepository;

	@Autowired
	FeignMenu feignMenu;

	@Autowired
	FeignOrder feignOrder;

	@Override
	public boolean registerUser(UserRequestDto userRequestDto) 
	{
		User userContactNumber = userRepository.findByContactNumber(userRequestDto.getContactNumber());
		if(userContactNumber != null)
		{
			throw new InputErrorException("User already exist with this number");
		}
		User user = new User();
		BeanUtils.copyProperties(userRequestDto, user);
		User registeredUser =  userRepository.save(user);
		if(registeredUser != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public List<UserResponseDto> getAllUsersDetails() 
	{

		List<UserResponseDto> userResponseDtoList = new ArrayList<>();
		Iterator iterator = userRepository.findAll().iterator();
		while(iterator.hasNext())
		{
			UserResponseDto userResponseDto = new UserResponseDto();
			BeanUtils.copyProperties(iterator.next(),userResponseDto);
			userResponseDtoList.add(userResponseDto);
		}
		return userResponseDtoList;
	}

	@Override
	public UserResponseDto getUsersDetails(Integer userId) 
	{

		User user = new User();
		UserResponseDto userResponseDto = new UserResponseDto();
		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser.isPresent())
		{
			user = optionalUser.get();
		}
		else
		{
			throw new UserNotFoundException("User doesn't exist for this id"+" "+" "+userId);
		}
		BeanUtils.copyProperties(user, userResponseDto);
		return userResponseDto;
	}

	@Override
	public List<User> getAllUsersDetails(int pageNo, int pageSize) {

		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<User> pagedResult = userRepository.findAll(paging);

		return pagedResult.toList();
	}

	@Override
	public boolean updateUsersDetails(Integer userId, UserRequestDto userRequestDto) 
	{

		User user = userRepository.findById(userId).get();
		BeanUtils.copyProperties(userRequestDto,user);
		User updatedUser =  userRepository.save(user);
		if(updatedUser != null)
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	@Override
	public UserDeleteResponseDto deleteUsersDetails(Integer userId) 
	{
		if(userRepository.findById(userId).isPresent())
		{
			UserDeleteResponseDto userDeleteResponseDto = new UserDeleteResponseDto();
			userRepository.deleteById(userId);
			userDeleteResponseDto.setCustomerId(userId);
			userDeleteResponseDto.setMessage("User Deleted Successfully");
			return userDeleteResponseDto;
		}
		else
		{
			throw new UserNotFoundException("User doesn't exist for this id"+" "+" "+userId);  
		}
	}

	@Override
	public ResponseEntity<List<Item>> getAllItems() {
		// TODO Auto-generated method stub
		return feignMenu.getAllItems();
	}

	@Override
	public ResponseEntity<Item> getItemByName(String itemName) {
		// TODO Auto-generated method stub
		return feignMenu.getItemByName(itemName);
	}

	List<Item> cart = new ArrayList<Item>();

	public String addToCart(String itemName,Integer userId)
	{
		cart.add(getItemByName(itemName).getBody());

		Cart cart = new Cart();
		Item item = getItemByName(itemName).getBody();
		cart.setAmount(item.getPrice());
		cart.setItemName(itemName);
		cart.setUserId(userId);
		cartRepository.save(cart);		
		return "Item Added to the cart : " + itemName;	
	}

	@Transactional
	public String removeItemsOrdered(@PathVariable String itemName,@PathVariable Integer userId)
	{
		cartRepository.deleteCartByUserIdAndItemName(itemName,userId);
		return "Item removed successfully";
	}

	public List<Cart> showItemsInCart(Integer userId)
	{
		List<Cart> cartList = cartRepository.findCartDetailsByUserId(userId);
		return cartList;
	}

	@Override
	public String orderCart(Integer userId) {
		Optional<User> user =  userRepository.findById(userId);
		double currentAmount = user.get().getWalletAmount();
		double bill = processCart(cart);
		double remainingBalance = currentAmount - bill;
		user.get().setWalletAmount(remainingBalance);
		userRepository.save(user.get());
		makeOrder(userId, bill);
		return "Thank you for ordering and your remaining balance is : "+ remainingBalance;
	}

	@Override
	public ResponseEntity<String> makeOrder(@PathVariable Integer userId,@PathVariable double amount) 
	{
		return feignOrder.makeOrder(userId, amount);
	}

	public double processCart(List<Item> cart)
	{
		double amount = 0.0;
		if(!cart.isEmpty())
		{
			for(Item items:cart)
			{
				amount = amount + items.getPrice();
			}
		}
		return amount;
	}

	public ResponseEntity<List<Orders>> getOrdersBydate(@PathVariable Integer userId,@PathVariable String startDate,@PathVariable String endDate)
	{
		return feignOrder.getOrdersBydate(userId, startDate, endDate);
	}



}
