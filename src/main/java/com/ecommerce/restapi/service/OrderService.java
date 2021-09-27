package com.ecommerce.restapi.service;

import java.util.List;

import com.ecommerce.restapi.dtos.OrderHistoryDTO;
import com.ecommerce.restapi.dtos.OrderProductDTO;

public interface OrderService
{
	String orderProducts(OrderProductDTO orderProdDto);
    List<OrderHistoryDTO> getOrderHistory(long userId);
}
