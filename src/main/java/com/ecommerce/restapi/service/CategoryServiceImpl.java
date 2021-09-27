package com.ecommerce.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.restapi.model.Category;
import com.ecommerce.restapi.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService
{

	@Autowired
	CategoryRepository catRepository;
	
	@Override
	public Category saveCategory(Category catgry) 
	{
		return catRepository.save(catgry);
		
	}
	
}
