package com.springboot.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.springboot.userservice.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> 
{
	List<Cart> findCartDetailsByUserId(Integer userId);

	@Modifying
	@Query("delete from Cart c  where c.itemName = :itemName AND c.userId = :userId")
	void deleteCartByUserIdAndItemName(@Param("itemName") String itemName,@Param("userId") Integer userId);
}