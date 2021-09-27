package com.ecommerce.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.restapi.model.TransactionDetails;
@Repository
public interface TransactionRepository extends JpaRepository<TransactionDetails, Long>{

}
