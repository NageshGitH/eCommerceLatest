package com.ecommerce.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.restapi.model.AccountDetails;
import com.ecommerce.restapi.service.AccountDetailsProjections;

@Repository
public interface AccountRepository extends JpaRepository<AccountDetails, Long>{
	AccountDetails findByAccountNumber(long accountNo);
	@Query("select ad from AccountDetails ad where ad.accountNumber=:accountNo")
	AccountDetailsProjections getAccountNo(long accountNo);
}
