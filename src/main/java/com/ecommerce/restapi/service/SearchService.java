package com.ecommerce.restapi.service;

import java.util.List;

import com.ecommerce.restapi.dtos.SearchDTO;

public interface SearchService 
{
	List<SearchDTO> findProductDetails(String productName,String catName);
	
	AccountDetailsProjections getAccountDetais(long accountNo);
	
	CustomerProjection getCustomerById(long custId);
}

