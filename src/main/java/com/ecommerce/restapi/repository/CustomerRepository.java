package com.ecommerce.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.restapi.model.CustomerDetails;
import com.ecommerce.restapi.service.CustomerProjection;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDetails, Long>
{
	CustomerProjection findByCustomerId(long custId);
}
