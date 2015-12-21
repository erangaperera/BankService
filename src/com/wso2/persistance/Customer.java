package com.wso2.persistance;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String fName;
	private String lName;
	private double creditLimit;
	@OneToMany(mappedBy="accountOwner")
	private List<BankAccount> bankAccounts = new ArrayList<BankAccount>();
	
	public Customer(com.wso2.dto.Customer customer){
		super();
		this.id = customer.getId();
		this.fName = customer.getfName();
		this.lName = customer.getlName();
		this.creditLimit = customer.getCreditLimit();
		for (com.wso2.dto.BankAccount dtoBankAccount : customer.getBankAccounts()){
			BankAccount bankAccount = new BankAccount(dtoBankAccount);
			this.addBankAccount(bankAccount);
		}
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
        if (bankAccount.getAccountOwner() != this) {
            bankAccount.setAccountOwner(this);
        }
    }

	public List<BankAccount> getBankAccounts() {
		return bankAccounts;
	}
}
