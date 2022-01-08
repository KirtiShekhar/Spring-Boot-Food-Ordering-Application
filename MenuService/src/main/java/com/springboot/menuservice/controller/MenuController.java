package com.springboot.menuservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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

import com.springboot.menuservice.entity.Item;
import com.springboot.menuservice.dto.ItemDto;
import com.springboot.menuservice.service.ItemService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/menu")
public class MenuController 
{
	private static final Logger MenuLogger = LoggerFactory.getLogger(MenuController.class);
	
	@Autowired
	ItemService itemService;
	
	@Autowired
    Environment environment;
	
	@GetMapping("/port")
    public String getInfo() {
        String port = environment.getProperty("local.server.port");
        return "From server "+port;
    }
	
	@PostMapping("/items")
	@Operation(summary = "Insert the new item to the menu")
	public ResponseEntity<String> addItems(@RequestBody ItemDto itemDto)
	{
		boolean saveditem = itemService.saveItem(itemDto);
		if(saveditem)
		{
			MenuLogger.info("Item added to menu successfully");
			return new ResponseEntity<String>("Item added to menu successfully",HttpStatus.OK);
		}
		else
		{
			MenuLogger.info("Item added to menu unsuccessfully");
			return new ResponseEntity<String>("Item added to menu unsuccessfully",HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/items")
	@Operation(summary = "Get all the items added")
	public ResponseEntity<List<Item>> getAllItems()
	{
		MenuLogger.info("Get all the items added");
		List<Item> itemList=itemService.getAllItems();
		return new ResponseEntity<List<Item>>(itemList, HttpStatus.OK);
		
	}
	
	@GetMapping("/items/{itemName}")
	@Operation(summary = "Get the item details for the entered name")
	public ResponseEntity<Item> getItemByName(@PathVariable String itemName)
	{
		MenuLogger.info("Get the item details for the entered name");
		Item it=itemService.getItemByName(itemName);
		return new ResponseEntity<Item>(it,HttpStatus.OK);
		
	}
	
	@PutMapping("/items/{itemId}")
	@Operation(summary = "Update the existing items data")
	public ResponseEntity<Item> updateItemById(@RequestBody ItemDto itemDto,@PathVariable Integer itemId)
	{
		MenuLogger.info("Update the existing items data");
		Item it=itemService.updateItem(itemDto,itemId);
		return new ResponseEntity<Item>(it,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/items/{itemId}")
	@Operation(summary = "Delete the specific item for the given id")
	public ResponseEntity<String> deleteById(@PathVariable Integer itemId)
	{
		MenuLogger.info("Delete the specific item for the given id");
		itemService.deleteById(itemId);
		return new ResponseEntity<String>("Item deleted", HttpStatus.OK);
		
	}
	

}
