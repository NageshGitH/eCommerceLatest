package com.ecommerce.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.restapi.model.OrderDetails;

@Repository
public interface OrderHistory extends JpaRepository<OrderDetails, Long>{

}
