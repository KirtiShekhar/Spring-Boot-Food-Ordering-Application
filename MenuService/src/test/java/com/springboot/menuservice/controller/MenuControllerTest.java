package com.springboot.menuservice.controller;

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

import com.springboot.menuservice.MenuServiceApplication;
import com.springboot.menuservice.entity.Item;
import com.springboot.menuservice.dto.ItemDto;
import com.springboot.menuservice.service.ItemService;

@ExtendWith(MockitoExtension.class)
class MenuControllerTest 
{
	@Mock
	ItemService menuService;

	@InjectMocks
	MenuController menuController;

	Item item;
	ItemDto itemDto;
	List<Item> itemList;

	@BeforeEach
	void setUp() throws Exception 
	{
		item = new Item();
		itemDto = new ItemDto();
		itemList = new ArrayList<Item>();

		itemDto.setItemName("Idli with Sambar");
		itemDto.setPrice(400.00);
		itemDto.setDescription("South Indian Food Item");

		item.setItemId(1);
		item.setItemName("Bada Pav");
		item.setDescription("Food Item popular in Mahastra");
		item.setPrice(60.00);
		itemList.add(item);
	}

	@Test
	@DisplayName("Save Item : Positive")
	void SaveItemTest_Positive() 
	{
		// context
		when(menuService.saveItem(itemDto)).thenReturn(true);
		//event
		ResponseEntity<String> savednewitemresult = menuController.addItems(itemDto);
		//outcome
		assertEquals("Item added to menu successfully", savednewitemresult.getBody());
		assertEquals(HttpStatus.OK, savednewitemresult.getStatusCode());
	}

	@Test
	@DisplayName("Save Item : Negative")
	void SaveItemTest_Negative() 
	{
		// context
		when(menuService.saveItem(itemDto)).thenReturn(false);
		//event
		ResponseEntity<String> savednewitemresult = menuController.addItems(itemDto);
		//outcome
		assertEquals("Item added to menu unsuccessfully", savednewitemresult.getBody());
		assertEquals(HttpStatus.BAD_REQUEST, savednewitemresult.getStatusCode());
	}

	@Test
	@DisplayName("Get all Items Added")
	void GetAllItemsItemTest()  
	{
		// context
		when(menuService.getAllItems()).thenReturn(itemList);
		//event 
		ResponseEntity<List<Item>> savedItems = menuController.getAllItems(); 
		//outcome 
		assertEquals(itemList,savedItems.getBody()); 
	}
	
	@Test
	@DisplayName("Get Item By Name Added")
	void GetItemByNameItemTest()  
	{
		// context
		when(menuService.getItemByName("Bada Pav")).thenReturn(item);
		//event 
		ResponseEntity<Item> savedItems = menuController.getItemByName("Bada Pav"); 
		//outcome 
		assertEquals(item,savedItems.getBody()); 
	}
	
	@Test
	@DisplayName("Delete Item By Item Id")
	void DeleteByItemIdTest()  
	{
		// context
		when(menuService.deleteById(1)).thenReturn("Item Deleted Successfully");
		//event 
		ResponseEntity<String> savedItems = menuController.deleteById(1); 
		//outcome 
		assertEquals("Item Deleted Successfully",savedItems); 
	}

}
