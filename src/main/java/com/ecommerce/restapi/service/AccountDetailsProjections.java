package com.ecommerce.restapi.service;

public interface AccountDetailsProjections 
{
	public long getAccountNumber() ;
	public String getAccountType();
	public double getAvaialableBalance() ;
	public CustomerDetails getCustomerDetails();
	
	interface CustomerDetails {
        String getfirstName();
        String getLastName();
    }
}
