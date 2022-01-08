package com.springboot.menuservice.service;

import com.springboot.menuservice.dto.ItemDto;
import com.springboot.menuservice.entity.Item;
import java.util.List;

public interface ItemService {

	void saveItem(ItemDto itemDto);

	List<Item> getAllItems();

	Item getItemByName(String itemName);

	Item updateItem(ItemDto itemDto, Integer itemId);

	void deleteById(Integer itemId);

}
