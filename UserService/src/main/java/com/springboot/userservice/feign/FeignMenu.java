package com.springboot.userservice.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.springboot.userservice.feignentity.Item;

@FeignClient("MenuService")
public interface FeignMenu 
{
	@GetMapping("/menu/port")
    public String getInfo();
	
	@GetMapping("/menu/items")
	public ResponseEntity<List<Item>> getAllItems();
	
	@GetMapping("/menu/items/{itemName}")
	public ResponseEntity<Item> getItemByName(@PathVariable String itemName);

}
