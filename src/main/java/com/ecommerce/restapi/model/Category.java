package com.ecommerce.restapi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@Table(name="category")
public class Category
{
	@Id
	@GeneratedValue(generator="category_id",strategy = GenerationType.IDENTITY)
	//@SequenceGenerator(name="category_id" ,sequenceName = "categorySeq")
	@Column(name="category_id")
	private Long categoryId;
	
	@Column(name="category_name")
	private String categoryName;
	@Column(name="category_desc")
	private String categoryDescrption;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="category_id")
	private List<Product> prodList = new ArrayList<>();
	
	public Category()
	{
		
	}
	public Category(Long categoryId, String categoryName, String categoryDescrption) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryDescrption = categoryDescrption;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDescrption() {
		return categoryDescrption;
	}
	public void setCategoryDescrption(String categoryDescrption) {
		this.categoryDescrption = categoryDescrption;
	}
	
	public List<Product> getProdList() {
		return prodList;
	}
	public void setProdList(List<Product> prodList) {
		this.prodList = prodList;
	}
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", categoryDescrption="
				+ categoryDescrption + "]";
	}
	
}
