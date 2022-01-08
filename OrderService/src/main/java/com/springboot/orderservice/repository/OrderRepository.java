package com.springboot.orderservice.repository;

import java.util.List;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.springboot.orderservice.entity.Orders;
import com.springboot.orderservice.dto.OrderResponseDto;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> 
{
	@Query("SELECT t FROM Orders t WHERE (t.date BETWEEN :startDate AND :endDate) AND (t.userId = :userId)")
    List<Orders> getByDateAndUserId(@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate,@Param("userId") Integer userId);
	
	@Query("SELECT t FROM Orders t WHERE MONTH(t.date) = MONTH(now()) AND (t.userId = :userId)")
    List<Orders> getByMonthAndUserId(@Param("userId") Integer userId);
}