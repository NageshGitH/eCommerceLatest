package com.ecommerce.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.restapi.dtos.SearchDTO;
import com.ecommerce.restapi.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>
{
	@Query("select new com.ecommerce.restapi.dtos.SearchDTO(catgry.categoryName,catgry.categoryDescrption,pro.productId,pro.productName,pro.productPrice,pro.productQuantity) from "
			+ "Category catgry join catgry.prodList  pro on  catgry.categoryId = pro.category.categoryId and "
			+ " (pro.productName=:productName or catgry.categoryName=:catgryName)")
	List<SearchDTO> getCategoryProductList(String catgryName, String productName);
}
