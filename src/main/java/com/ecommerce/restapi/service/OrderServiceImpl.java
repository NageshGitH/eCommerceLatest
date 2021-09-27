package com.ecommerce.restapi.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.restapi.dtos.OrderHistoryDTO;
import com.ecommerce.restapi.dtos.OrderHistoryDetailsDTO;
import com.ecommerce.restapi.dtos.OrderProductDTO;
import com.ecommerce.restapi.dtos.ProductDTO;
import com.ecommerce.restapi.exception.InSufficientBalanceException;
import com.ecommerce.restapi.exception.OrderNotFoundException;
import com.ecommerce.restapi.exception.OutOfStockException;
import com.ecommerce.restapi.exception.ResourceNotFoundException;
import com.ecommerce.restapi.model.AccountDetails;
import com.ecommerce.restapi.model.Order;
import com.ecommerce.restapi.model.OrderDetails;
import com.ecommerce.restapi.model.Product;
import com.ecommerce.restapi.model.TransactionDetails;
import com.ecommerce.restapi.model.User;
import com.ecommerce.restapi.repository.AccountRepository;
import com.ecommerce.restapi.repository.OrderHistory;
import com.ecommerce.restapi.repository.OrderRepository;
import com.ecommerce.restapi.repository.ProductRepository;
import com.ecommerce.restapi.repository.TransactionRepository;
import com.ecommerce.restapi.repository.UserRepository;
import com.google.common.util.concurrent.AtomicDouble;

@Service
public class OrderServiceImpl implements OrderService
{
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
    OrderRepository orderRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	AccountRepository accountRepo;
	
	@Autowired
	ProductRepository prodRepo;
	
	@Autowired
	OrderHistory orderHistoryRepo;
	
	@Autowired
	TransactionRepository transRepo;

	
	public String orderProducts(OrderProductDTO orderProdDto) throws InSufficientBalanceException
	{
		Date date = new Date();
		List<ProductDTO> productDtoList= new ArrayList<>();
		
		// Check is the user exists, if does not exists return error message else continue .
		User user= Optional.ofNullable(userRepo.findUserByUserName(orderProdDto.getUserName()))
				.orElseThrow(()->new ResourceNotFoundException("User", "User Name", orderProdDto.getUserName()));
		
		// Check is the account number exists, if does not exists return error message  else continue .
		AccountDetails fromAccDetails =  Optional.ofNullable(accountRepo.findByAccountNumber(orderProdDto.getAccountNo()))
				 .orElseThrow(()->new ResourceNotFoundException("Account Details", "Account Number", orderProdDto.getAccountNo()));

		AccountDetails toAccDetails =  Optional.ofNullable(accountRepo.findByAccountNumber(Long.valueOf("31787544391")))
				 .orElseThrow(()->new ResourceNotFoundException("Account Details", "Account Number", orderProdDto.getAccountNo()));
		
		
		
		double availableBal = fromAccDetails.getAvaialableBalance();
		
		orderProdDto.getProductDto().stream().map(prod->
		{
			 ProductDTO dto = new ProductDTO();
			// Check is the product with the product id exists, if does not exists return error message  else continue .
			 Product product = prodRepo.findById(prod.getProductId()).
					orElseThrow(()->new ResourceNotFoundException("Product", "Product Id", prod.getProductId()));	
			 
			 if(prod.getQuantity() > product.getProductQuantity())
			 {
				 throw new OutOfStockException("requested quantity for the product :: "+
			               product.getProductName()+"("+product.getProductId()+") "
				 		+ " not available in the stock, available stock is:: "+product.getProductQuantity());
			 }
			 dto.setProductId(product.getProductId());
			 dto.setQuantity(prod.getQuantity());
			 dto.setTotalPrice(product.getProductPrice()*prod.getQuantity());
			 productDtoList.add(dto);
			 return productDtoList;
		}).collect(Collectors.toList());
		
		Double totalAmount = productDtoList.stream().map(totPrice->totPrice.getTotalPrice())
				  .collect(Collectors.summingDouble(Double::doubleValue));
		
		logger.info("totalAmount:::"+totalAmount);
		logger.info("availableBal:::"+availableBal);
		
		// Checks if the total purchase amount is less that available balance 
		if(totalAmount > availableBal)
		{
			throw new InSufficientBalanceException("InSufficent balance for the account number::"+fromAccDetails.getAccountNumber());
		}
		// if all the validations are success then only proceed with placing the order
		Order order= new Order();
		try
		{
			List<OrderDetails> orderDetailsList = new ArrayList<>();
			order.setDatetime(date);
			order.setUser(user);
			order.setTotalPrice(totalAmount);
			for(ProductDTO dto : productDtoList)
			{
				Product product = prodRepo.findById(dto.getProductId()).get();
				product.setProductQuantity(product.getProductQuantity()-dto.getQuantity());
				OrderDetails orderDetails = new OrderDetails();
				orderDetails.setOrders(order);
				orderDetails.setPrice(product.getProductPrice());
				orderDetails.setQuantity(dto.getQuantity());
				orderDetails.setProduct(product);
				orderDetailsList.add(orderDetails);
			}
			order.setOrderList(orderDetailsList);
			orderRepo.save(order);
		}catch (Exception exception) {
			logger.error("Exception occurred while saving the order", exception);
			return exception.getMessage();
		}
		if(Optional.ofNullable(order).isPresent())
		{
				try
				{
					TransactionDetails sourceAcc= new TransactionDetails();
					TransactionDetails targetAcc= new TransactionDetails();
					
					//inserting records to transactions tables for source account transaction
					Timestamp ts = new Timestamp(date.getTime());
					sourceAcc.setAmount(totalAmount);
					sourceAcc.setFromAccount(fromAccDetails.getAccountNumber());
					sourceAcc.setTransactionTime(ts);
					sourceAcc.setTransactionType("Debit");
					sourceAcc.setRemarks("Dedit Transaction");
					transRepo.save(sourceAcc);
					
					//inserting records to transactions tables for target account transaction
					targetAcc.setAmount(totalAmount);
					targetAcc.setToAccount(toAccDetails.getAccountNumber());
					targetAcc.setTransactionTime(ts);
					targetAcc.setTransactionType("Credit");
					targetAcc.setRemarks("Credit Transaction");
					transRepo.save(targetAcc);
					
					// updating the account details for the given account number
					if(Optional.ofNullable(transRepo).isPresent())
					{
						fromAccDetails.setAvaialableBalance(fromAccDetails.getAvaialableBalance()-totalAmount);
						fromAccDetails.setOpeningbalance(fromAccDetails.getOpeningbalance()-totalAmount);
						accountRepo.save(fromAccDetails);
						
						toAccDetails.setAvaialableBalance(toAccDetails.getAvaialableBalance()+totalAmount);
						toAccDetails.setOpeningbalance(toAccDetails.getOpeningbalance()+totalAmount);
						accountRepo.save(toAccDetails);
						
					}
				}catch (Exception exception) {
					logger.error("Exception occurred while saving the transaction details", exception);
					return exception.getMessage();
				}
		 }
		return "Order placed successfully with order id:: "+order.getOrderId();
	}
	
	public List<OrderHistoryDTO> getOrderHistory(long userId) throws OrderNotFoundException
	{
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
		
		List<Order> orders = user.getOrderId();
		
		List<OrderHistoryDTO> orderDetails = new ArrayList<>();
		if(orders.size()> 0)
		{
			OrderHistoryDTO orderHistory = new OrderHistoryDTO();			
			List<OrderHistoryDetailsDTO> prodList = new ArrayList<>();
			orderHistory.setUserName(user.getUserName());
			
			AtomicDouble totalAmt = new AtomicDouble(0);
			orders.stream().forEach((order)->
			{
				totalAmt.getAndAdd(order.getTotalPrice());
				order.getOrderList().stream().map(ordr->
				{
					OrderHistoryDetailsDTO dto = new OrderHistoryDetailsDTO();
					dto.setProductName(ordr.getProduct().getProductName());
					dto.setPrice(ordr.getProduct().getProductPrice());
					dto.setQuantity(ordr.getQuantity());
					dto.setTotalPrice(ordr.getPrice()*ordr.getQuantity());
					dto.setOrderDate(ordr.getOrders().getDatetime().toString().substring(0, ordr.getOrders().getDatetime().toString().length()-2));
					prodList.add(dto);
					return prodList;
				}).collect(Collectors.toList());
			});
			orderHistory.setTotalAmount(totalAmt.get());
			orderHistory.setOrderDetails(prodList);
			orderDetails.add(orderHistory);			
		}else
		{
			throw new OrderNotFoundException("Order", "User Id", userId);
		}
		return orderDetails;
	}
}
