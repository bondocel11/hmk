package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Order implements Entity,Serializable{
	private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);
	private int ID;
	private Customer customer;
	private List<Product> shoppingCart;
	
	public Order(Customer customer){
		this.setID(ID_GENERATOR.getAndIncrement());
		shoppingCart=new ArrayList<Product>();
		this.customer=customer;
	}
	public int getID(){
		return ID;
	}
	public void setID(int ID){
		this.ID=ID;
	}
	public Customer getCustomer(){
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<Product> getShoppingCart() {
		return shoppingCart;
	}
	public void setShoppingCart(ArrayList<Product> shoppingCart){
		this.shoppingCart=shoppingCart;
	}
	public void addProduct(Product product){
		shoppingCart.add(product);
	}
	private double getTotal(){
		double total=0;
		for (Product p:shoppingCart){
			total+=p.getPrice();
		}
		return total;
	}
	public String toString() {
		String string="";
		for(Product p: shoppingCart){
			string = string + "\n"+p.getItem();
		}
		return string;
	}
	
}
