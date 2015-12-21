package com.wso2.services;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ws.rs.*;

import com.wso2.dto.BankAccount;
import com.wso2.dto.Customer;
import com.wso2.persistance.CustomerUtil;

@Path("/BankService")
public class BankService {
	
	@PostConstruct
    public void init() {
		System.out.println("BankService :: calling PostConstruct method");
		CustomerUtil.InitJPA();
    }
	
	@PreDestroy
    public void close() {
		System.out.println("BankService :: calling PreDestroy method");
		CustomerUtil.CloseJPA();
    }

	@GET
	@Produces({"application/xml", "application/json"})
	@Path("/Customer/{Id}")
	public com.wso2.dto.Customer getCustomer(@PathParam("Id") int id){
		Customer customer;
		customer = CustomerUtil.getCustomer(id); 
		if (customer == null && id == 100){
			customer = new Customer(100, "Bill", "Gates", 1000000);
			BankAccount bankAccount = new BankAccount(100100, "ABC001", 1000000, customer.getId());
			customer.addBankAccount(bankAccount);
		}
		return customer;
	}
	
	@GET
	@Produces({"application/json"})
	@Path("/Customer/all")
	public List<Customer> getAllCustomers(){
		List<Customer> customers = CustomerUtil.getAllCustomers(); 
		return customers;
	}
	
	@POST
	@Path("/Customer")
	@Consumes({"application/json"})
	public void saveCustomer(Customer customer){
		CustomerUtil.saveCustomer(customer);
	}
	
	@GET
	@Produces({"application/json"})
	@Path("/BankAccount/{Id}")
	public BankAccount getBankAccount(@PathParam("Id") int id){
		BankAccount bankaccount = CustomerUtil.getBankAccount(id); 
		return bankaccount;
	}
	
	@POST
	@Path("/BankAccount")
	@Consumes({"application/json"})
	public void saveBankAccount(BankAccount bankAccount){
		CustomerUtil.saveBankAccount(bankAccount);
	}
	
	@GET
	@Path("swagger")
	public String getSwagger(){
		return "{\"swagger\":\"2.0\",\"info\":{\"version\":\"1.0.0\",\"title\":\"BankService\"},\"host\":\"localhost:8080\","
				+ "\"basePath\":\"/\",\"schemas\":[\"http\"],\"paths\":{\"/BankService/BankAccount\":{\"post\":{\"consumes\""
				+ ":[\"application/json\"],\"produces\":[],\"parameters\":[{\"name\":\"body\",\"in\":\"body\",\"required\":t"
				+ "rue,\"schema\":{\"$ref\":\"BankAccount\"}}],\"responses\":{\"200\":{\"description\":\"OK\",\"headers\":{}"
				+ ",\"schema\":{\"$ref\":\"Void\"}}}}},\"/BankService/BankAccount/{Id}\":{\"get\":{\"consumes\":[],\"produce"
				+ "s\":[\"application/json\"],\"parameters\":[{\"name\":\"Id\",\"in\":\"path\",\"required\":true,\"type\":\""
				+ "integer\"}],\"responses\":{\"200\":{\"description\":\"OK\",\"headers\":{},\"schema\":{\"$ref\":\"BankAcco"
				+ "unt\"}}}}},\"/BankService/Customer\":{\"post\":{\"consumes\":[\"application/json\"],\"produces\":[],\"par"
				+ "ameters\":[{\"name\":\"body\",\"in\":\"body\",\"required\":true,\"schema\":{\"$ref\":\"Customer\"}}],\"re"
				+ "sponses\":{\"200\":{\"description\":\"OK\",\"headers\":{},\"schema\":{\"$ref\":\"Void\"}}}}},\"/BankServi"
				+ "ce/Customer/all\":{\"get\":{\"consumes\":[],\"produces\":[\"application/json\"],\"parameters\":[],\"respo"
				+ "nses\":{\"200\":{\"description\":\"OK\",\"headers\":{},\"schema\":{\"type\":\"array\",\"items\":{\"$ref\""
				+ ":\"Customer\"}}}}}},\"/BankService/Customer/{Id}\":{\"get\":{\"consumes\":[],\"produces\":[\"application/"
				+ "json\"],\"parameters\":[{\"name\":\"Id\",\"in\":\"path\",\"required\":true,\"type\":\"integer\"}],\"respo"
				+ "nses\":{\"200\":{\"description\":\"OK\",\"headers\":{},\"schema\":{\"$ref\":\"Customer\"}}}}}},\"definiti"
				+ "ons\":{\"BankAccount\":{\"properties\":{\"id\":{\"type\":\"number\"},\"accountNo\":{\"type\":\"string\"},"
				+ "\"creditBalance\":{\"type\":\"number\"},\"custId\":{\"type\":\"number\"}}},\"Customer\":{\"properties\":{"
				+ "\"id\":{\"type\":\"number\"},\"fName\":{\"type\":\"string\"},\"lName\":{\"type\":\"string\"},\"creditLimi"
				+ "t\":{\"type\":\"number\"},\"bankAccounts\":{\"type\":\"array\",\"items\":{\"$ref\":\"BankAccount\"}}}},\""
				+ "Void\":{\"properties\":{}}}}";
	}
}