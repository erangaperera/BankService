package com.wso2.persistance;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.wso2.dto.BankAccount;
import com.wso2.dto.Customer;

public class CustomerUtil {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;

	public static com.wso2.dto.Customer getCustomer(int id) {
		com.wso2.persistance.Customer customer = em.find(com.wso2.persistance.Customer.class, id);
		if (customer == null){
			return null;
		}
		return new com.wso2.dto.Customer(customer);
	}

	public static void saveCustomer(com.wso2.dto.Customer customer) {
		com.wso2.persistance.Customer dbCustomer = new com.wso2.persistance.Customer(customer);
		// Transaction
		em.getTransaction().begin();
		if (em.find(com.wso2.persistance.Customer.class, dbCustomer.getId()) == null) {
			em.persist(dbCustomer);
		}
		else
		{
			em.merge(dbCustomer);
		}
		
		for (com.wso2.persistance.BankAccount bankaccount : dbCustomer.getBankAccounts()){
			if (em.find(com.wso2.persistance.BankAccount.class, bankaccount.getId()) == null){
				em.persist(bankaccount);
			}
			else{
				em.merge(bankaccount);
			}
		}
		em.getTransaction().commit();
	}

	public static BankAccount getBankAccount(int id) {
		com.wso2.persistance.BankAccount dbAccount = em.find(com.wso2.persistance.BankAccount.class, id);
		BankAccount dtoBankAccount = null;
		if (dbAccount != null){
			dtoBankAccount = new BankAccount(dbAccount);
		}
		return dtoBankAccount;
	}

	public static void saveBankAccount(BankAccount bankAccount) {
		com.wso2.persistance.Customer customer = em.find(com.wso2.persistance.Customer.class, bankAccount.getCustId());
		com.wso2.persistance.BankAccount dbBankAccount = new com.wso2.persistance.BankAccount(bankAccount);
		dbBankAccount.setAccountOwner(customer);
		em.getTransaction().begin();
		if (em.find(com.wso2.persistance.BankAccount.class, bankAccount.getId()) != null){
			em.merge(dbBankAccount);
		}
		else {
			em.persist(dbBankAccount);
		}
		em.getTransaction().commit();
	}

	public static List<Customer> getAllCustomers() {
		Query custQuery = em.createQuery ("select cus from com.wso2.persistance.Customer cus");
		@SuppressWarnings("unchecked")
		List<com.wso2.persistance.Customer> customers = custQuery.getResultList ();
		List<Customer> dtoCustomers = new ArrayList<Customer>();
		for (com.wso2.persistance.Customer dbCustomer : customers){
			dtoCustomers.add(new Customer(dbCustomer.getId(),dbCustomer.getfName(), dbCustomer.getlName(), dbCustomer.getCreditLimit()));
		}
		return dtoCustomers;
	}
	

	public static void InitJPA() {
		emf = Persistence.createEntityManagerFactory("Customer.odb");
		em = emf.createEntityManager();
	}

	public static void CloseJPA() {
		em.close();
		emf.close();
	}
}
