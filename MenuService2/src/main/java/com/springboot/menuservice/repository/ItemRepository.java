package com.springboot.menuservice.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springboot.menuservice.entity.Item;

@Repository
public interface ItemRepository  extends JpaRepository<Item, Integer> {

	Optional<Item> findByItemName(String itemName);



}

