package com.ecommerce.restapi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.restapi.dtos.SearchDTO;
import com.ecommerce.restapi.request.SearchRequest;
import com.ecommerce.restapi.service.AccountDetailsProjections;
import com.ecommerce.restapi.service.CustomerProjection;
import com.ecommerce.restapi.service.SearchService;

@RestController
@RequestMapping("/api/")
public class SearchController 
{
	
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	 @Autowired
	 SearchService searchService;
     @PostMapping("products/searchproducts")
     public ResponseEntity<List<SearchDTO>> getCategoryProductDetails(
    		 @RequestBody SearchRequest request)
     {
    		 return new ResponseEntity<List<SearchDTO>>(searchService.findProductDetails(request.getProductName(),request.getCategoryName()), HttpStatus.OK);
     }
     
     
 	@GetMapping("/searchaccount/{accountNo}")
 	public ResponseEntity<?> getAccountDetails(@PathVariable("accountNo") long accountNo) throws Exception
 	{
 		 return new ResponseEntity<AccountDetailsProjections>(searchService.getAccountDetais(accountNo), HttpStatus.OK);
 	}
     
 	 
 	@GetMapping("/searchcustomer/{custId}")
 	public ResponseEntity<?> getCustomerDetails(@PathVariable("custId") long custId) throws Exception
 	{
 		 return new ResponseEntity<CustomerProjection>(searchService.getCustomerById(custId), HttpStatus.OK);
 	}
     
}
