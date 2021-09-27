package com.ecommerce.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.restapi.model.Category;
import com.ecommerce.restapi.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController
{

	@Autowired
	CategoryService catService;
	
	@PostMapping("/save")
	public String saveCategory(@RequestBody Category category)
	{
		Category ctgry = catService.saveCategory(category);
		return "Category created successfully with category Id:"+ctgry.getCategoryId();
	}
	
	
}
