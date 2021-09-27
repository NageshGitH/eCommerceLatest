package com.ecommerce.restapi.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_EMPTY)
public class OrderProductDTO implements Serializable 
{

	private static final long serialVersionUID = 1L;
	@NotEmpty(message="Please provide user id")
	private String userName;
	@NotEmpty(message="Please provide account number")
	private long accountNo;
	
	private List<ProductDTO> productDto = new ArrayList<>();
	
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	/**
	 * @return the productDto
	 */
	public List<ProductDTO> getProductDto() {
		return productDto;
	}
	/**
	 * @param productDto the productDto to set
	 */
	public void setProductDto(List<ProductDTO> productDto) {
		this.productDto = productDto;
	}
	
}
