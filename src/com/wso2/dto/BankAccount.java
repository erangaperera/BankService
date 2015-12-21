package com.wso2.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="BankAccount")
public class BankAccount {
	
	private int id;
	private String accountNo;
	private double creditBalance;
	private int custId;
	
	
	
	public BankAccount(int id, String accountNo, double creditBalance, int custId) {
		super();
		this.id = id;
		this.accountNo = accountNo;
		this.creditBalance = creditBalance;
		this.custId = custId;
	}

	public BankAccount(com.wso2.persistance.BankAccount bankAccount) {
		super();
		this.id = bankAccount.getId();
		this.accountNo = bankAccount.getAccountNo();
		this.creditBalance = bankAccount.getCreditBalance();
		this.custId = bankAccount.getAccountOwner().getId();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public double getCreditBalance() {
		return creditBalance;
	}
	public void setCreditBalance(double creditBalance) {
		this.creditBalance = creditBalance;
	}
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
}
