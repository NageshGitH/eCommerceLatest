package com.ecommerce.restapi.dtos;

public class AccountDetailsDTO 
{
    private String customerName;
    private long accountNo;
    private String accountType;
    private double availableBalance;
    
    public AccountDetailsDTO(String customerName, long accountNo, String accountType, double availableBalance) {
		super();
		this.customerName = customerName;
		this.accountNo = accountNo;
		this.accountType = accountType;
		this.availableBalance = availableBalance;
	}

	public AccountDetailsDTO()
    {
    	
    }

	/**
	 * @return the accountNo
	 */
	public long getAccountNo() {
		return accountNo;
	}

	/**
	 * @param accountNo the accountNo to set
	 */
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * @return the accountType
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	/**
	 * @return the availableBalance
	 */
	public double getAvailableBalance() {
		return availableBalance;
	}

	/**
	 * @param availableBalance the availableBalance to set
	 */
	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
    
	
    
}
