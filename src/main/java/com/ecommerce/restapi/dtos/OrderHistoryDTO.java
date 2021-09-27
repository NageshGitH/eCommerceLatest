package com.ecommerce.restapi.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryDTO implements Serializable
{
	private static final long serialVersionUID = -929651754268912381L;
	private String userName;
	private double totalAmount;

	List<OrderHistoryDetailsDTO> orderDetails = new ArrayList<>();
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<OrderHistoryDetailsDTO> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderHistoryDetailsDTO> orderDetails) {
		this.orderDetails = orderDetails;
	}
	/**
	 * @return the totalAmount
	 */
	public double getTotalAmount() {
		return totalAmount;
	}
	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
}
