package com.wso2.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Customer")
public class Customer {
	
	private int id;
	private String fName;
	private String lName;
	private double creditLimit;
	private List<BankAccount> bankAccounts = new ArrayList<BankAccount>();
	
	public Customer(com.wso2.persistance.Customer customer) {
		super();
		this.id = customer.getId();
		this.fName = customer.getfName();
		this.lName = customer.getlName();
		this.creditLimit = customer.getCreditLimit();
		for (com.wso2.persistance.BankAccount dbBankAccount : customer.getBankAccounts()){
			addBankAccount(new BankAccount(dbBankAccount));
		}
	}

	public Customer(int id, String fName, String lName, double creditLimit) {
		super();
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.creditLimit = creditLimit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}
	
    public void addBankAccount(BankAccount bankAccount) {
        this.bankAccounts.add(bankAccount);
        if (bankAccount.getCustId() != this.id) {
            bankAccount.setCustId(this.id);
        }
    }

	public List<BankAccount> getBankAccounts() {
		return bankAccounts;
	}
}
