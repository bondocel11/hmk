package sample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import models.Customer;

public class CustomerSample implements Serializable{
	List<Customer> customers;
	
	public CustomerSample(){
		customers=new ArrayList<Customer>();
	}
	public void addCustomer(Customer customer){
		customers.add(customer);
	}
	public List<Customer> getCustomers(){
		return customers;
	}
}
