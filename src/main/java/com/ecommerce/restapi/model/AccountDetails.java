package com.ecommerce.restapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="account_details")
public class AccountDetails 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="accountdetails_id")
	private long accountdetailsId;
	
    @Column(name="account_number")
    private long accountNumber;
    @Column(name="account_type")
    private String accountType;
    @Column(name="branch_address")
    private String branchAddress;
    @Column(name="ifsc_code")
    private String ifsccode;
    @Column(name="opening_balance")
    private double openingbalance;
    @Column(name="available_balance")
    private double avaialableBalance;
    
    
	@ManyToOne
	@JoinColumn(name="customer_id")
	private CustomerDetails customerDetails;

	public long getAccountdetailsId() {
		return accountdetailsId;
	}

	public void setAccountdetailsId(long accountdetailsId) {
		this.accountdetailsId = accountdetailsId;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public String getIfsccode() {
		return ifsccode;
	}

	public void setIfsccode(String ifsccode) {
		this.ifsccode = ifsccode;
	}

	public double getOpeningbalance() {
		return openingbalance;
	}

	public void setOpeningbalance(double openingbalance) {
		this.openingbalance = openingbalance;
	}

	public CustomerDetails getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(CustomerDetails customerDetails) {
		this.customerDetails = customerDetails;
	}

	public double getAvaialableBalance() {
		return avaialableBalance;
	}

	public void setAvaialableBalance(double avaialableBalance) {
		this.avaialableBalance = avaialableBalance;
	}
}
