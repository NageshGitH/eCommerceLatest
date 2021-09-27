package com.ecommerce.restapi.dtos;

import java.io.Serializable;

public class SearchDTO implements Serializable
{

	private static final long serialVersionUID = 1L;
	private String categoryName;
	private String categoryDescrption;
	private long productId;
	private String productName;
	private double productPrice;
	private int productQuantity;
	
	
	public SearchDTO() {}
	
	
	


	public SearchDTO(String categoryName, String categoryDescrption, long productId, String productName,
			double productPrice, int productQuantity) {
		super();
		this.categoryName = categoryName;
		this.categoryDescrption = categoryDescrption;
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
	}





	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}


	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	/**
	 * @return the categoryDescrption
	 */
	public String getCategoryDescrption() {
		return categoryDescrption;
	}


	/**
	 * @param categoryDescrption the categoryDescrption to set
	 */
	public void setCategoryDescrption(String categoryDescrption) {
		this.categoryDescrption = categoryDescrption;
	}


	/**
	 * @return the productId
	 */
	public long getProductId() {
		return productId;
	}


	/**
	 * @param productId the productId to set
	 */
	public void setProductId(long productId) {
		this.productId = productId;
	}


	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}


	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}


	/**
	 * @return the productPrice
	 */
	public double getProductPrice() {
		return productPrice;
	}


	/**
	 * @param productPrice the productPrice to set
	 */
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}


	/**
	 * @return the productQuantity
	 */
	public int getProductQuantity() {
		return productQuantity;
	}


	/**
	 * @param productQuantity the productQuantity to set
	 */
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	
	
	  
}
