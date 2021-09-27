package com.ecommerce.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.restapi.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	Order findOrderByUserUserId(long userId);
}
