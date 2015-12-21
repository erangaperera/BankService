package com.wso2.persistance;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BankAccount {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String accountNo;
	private double creditBalance;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="OWNER_ID")
	private Customer accountOwner;
	
	public BankAccount(com.wso2.dto.BankAccount dtoBankAccount) {
		this.id = dtoBankAccount.getId();
		this.accountNo = dtoBankAccount.getAccountNo();
		this.creditBalance = dtoBankAccount.getCreditBalance();
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
	public Customer getAccountOwner() {
		return accountOwner;
	}
	public void setAccountOwner(Customer accountOwner) {
		this.accountOwner = accountOwner;
	}
}
