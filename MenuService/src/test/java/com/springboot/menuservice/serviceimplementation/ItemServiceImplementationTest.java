package com.springboot.menuservice.serviceimplementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.springboot.menuservice.dto.ItemDto;
import com.springboot.menuservice.entity.Item;
import com.springboot.menuservice.repository.ItemRepository;
import com.springboot.menuservice.serviceimplementation.ItemServiceImplementation;

@ExtendWith(MockitoExtension.class)
class ItemServiceImplementationTest 
{
	@Mock
	ItemRepository menuRepository;

	@InjectMocks
	ItemServiceImplementation menuServiceImplementation;

	Item item;
	Optional<Item> item1;
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
		when(menuRepository.save(any(Item.class))).thenAnswer(i ->{
			Item item = i.getArgument(0);
			item.setItemId(1);
			return item;
		});
		//event
		boolean savednewitemresult = menuServiceImplementation.saveItem(itemDto);
		//outcome
		assertTrue(savednewitemresult);
	}

	@Test
	@DisplayName("Save Item : Negative")
	void SaveItemTest_Negative() 
	{
		// context
				when(menuRepository.save(any(Item.class))).thenReturn(null);
				//event
				boolean savednewitemresult = menuServiceImplementation.saveItem(itemDto);
				//outcome
				assertFalse(savednewitemresult);
	}

	@Test
	@DisplayName("Get all Items Added")
	void GetAllItemsItemTest()  
	{
		// context
		when(menuRepository.findAll()).thenReturn(itemList);
		//event 
		List<Item> savedItems = menuServiceImplementation.getAllItems(); 
		//outcome 
		assertEquals(itemList,savedItems); 
	}
	
	@Test
	@DisplayName("Get Item By Name Added")
	void GetItemByNameItemTest()  
	{
		// context
		when(menuRepository.findByItemName("Bada Pav")).thenReturn(Optional.of(item));
		//event 
		Item savedItems = menuServiceImplementation.getItemByName("Bada Pav"); 
		//outcome 
		assertEquals(savedItems,item);
	}

}
