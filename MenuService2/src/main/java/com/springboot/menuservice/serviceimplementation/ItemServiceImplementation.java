package com.springboot.menuservice.serviceimplementation;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.menuservice.service.ItemService;
import com.springboot.menuservice.repository.ItemRepository;
import com.springboot.menuservice.dto.ItemDto;
import com.springboot.menuservice.entity.Item;
import com.springboot.menuservice.exception.InputErrorException;
import com.springboot.menuservice.exception.ItemNotFoundException;

@Service
public class ItemServiceImplementation implements ItemService {
	
	@Autowired
	ItemRepository itemRepository;

	@Override
	public void saveItem(ItemDto itemDto) {
		// TODO Auto-generated method stub
		Optional<Item> optionalItem=itemRepository.findByItemName(itemDto.getItemName());
		if(optionalItem.isPresent())
		{
			throw new InputErrorException("The item is already in menu");
		}
		Item item=new Item();
		BeanUtils.copyProperties(itemDto, item);
		itemRepository.save(item);
	}

	@Override
	public List<Item> getAllItems() {
		// TODO Auto-generated method stub
		List<Item> itemList=itemRepository.findAll();
		return itemList;
	}

	@Override
	public Item getItemByName(String itemName) {
		// TODO Auto-generated method stub
		Optional<Item> optionalItem=itemRepository.findByItemName(itemName);
		if(optionalItem.isPresent())
		{
			Item item=itemRepository.findByItemName(itemName).get();

			return item;
			
		}
		throw new ItemNotFoundException("There is no item by that name");
		
	}

	@Override
	public Item updateItem(ItemDto itemDto, Integer itemId) {
		// TODO Auto-generated method stub
		Optional<Item> optionalitem=itemRepository.findById(itemId);
		if(optionalitem.isPresent())
		{
			Item it=itemRepository.findById(itemId).get();
			BeanUtils.copyProperties(itemDto, it);
			itemRepository.save(it);
			return it;
		}
		throw new ItemNotFoundException("There are no items present on this item id"+itemId);
		
		
	}

	@Override
	public void deleteById(Integer itemId) {
		// TODO Auto-generated method stub
		Optional<Item> optionalitem=itemRepository.findById(itemId);
		if(optionalitem.isPresent())
		{
			itemRepository.deleteById(itemId);
		}
		throw new ItemNotFoundException("There are no items present on this item id"+itemId);

		
	}

}
