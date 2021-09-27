package com.ecommerce.restapi.service;

import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.restapi.dtos.SearchDTO;
import com.ecommerce.restapi.exception.InavalidArgumentException;
import com.ecommerce.restapi.exception.SearchResultsNotFoundException;
import com.ecommerce.restapi.repository.AccountRepository;
import com.ecommerce.restapi.repository.CustomerRepository;
import com.ecommerce.restapi.repository.ProductRepository;

@Service
public class SearchServiceImpl implements SearchService
{
	
	private static final Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);
	
	@Autowired
	ProductRepository prodRepository;
	
	@Autowired
	AccountRepository accountRepo;
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Override
	public List<SearchDTO> findProductDetails(String productName,String catName) throws InavalidArgumentException,SearchResultsNotFoundException
	{
		if((Strings.isBlank(productName) && Strings.isBlank(catName)))
			throw new InavalidArgumentException("Invalid Input");
		if((Strings.isNotBlank(productName) && Strings.isNotBlank(catName)))   
			return prodRepository.getProductNameAndCategoryName(productName,catName);
		else
		{
			List<SearchDTO> searchDTo = prodRepository.getProductNameOrCategoryName(productName,catName);
			if(searchDTo.size() ==0 )
			{
				String searchName = Strings.isBlank(productName) ? catName : productName;
				throw new SearchResultsNotFoundException("No Results found with :: "+ searchName);
			}else
			return searchDTo;
		}
	}

	@Override
	public AccountDetailsProjections getAccountDetais(long accountNo) 
	{
		return accountRepo.getAccountNo(accountNo);
		
	}

	@Override
	public CustomerProjection getCustomerById(long custId) {
		// TODO Auto-generated method stub
		return customerRepo.findByCustomerId(custId);
	}
}



