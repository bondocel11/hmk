package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;

import javax.swing.table.DefaultTableModel;

import models.Customer;
import models.OPDept;
import models.Order;
import models.Product;
import models.Warehouse;
import sample.CustomerSample;
import views.AdminView;
import views.LogIn;

public class IOStream {

	//WAREHOUSE
	public void SerializeWarehouse(Warehouse wh){
		try{
			FileOutputStream fileOut=new FileOutputStream("warehouse.ser");;
			ObjectOutputStream out=new ObjectOutputStream(fileOut);
		    out.writeObject(wh);
		    out.close();
		    fileOut.close();
		    System.out.printf("Serialized data is saved in warehouse.ser");
		}
		catch(IOException i){
			i.printStackTrace();
		}
	}
	public Warehouse deserializeWarehouse(){
		Warehouse warehouse=null;
		try{
			FileInputStream fileIn=new FileInputStream("warehouse.ser");
			ObjectInputStream in=new ObjectInputStream(fileIn);
		    warehouse=(Warehouse)in.readObject();
		    in.close();
		    fileIn.close();
		}
		catch(IOException i){
			i.printStackTrace();
		}
		catch(ClassNotFoundException c){
			System.out.println("Warehouse class not found");
			c.printStackTrace();
		}
		Iterator<Product> it=warehouse.getProducts().iterator();
		while (it.hasNext()){
			Product p=it.next();
			System.out.println(p.getItem() + " " +p.getStock()+"\n");
			if (LogIn.adminButton().isSelected()){ 
			Object[] row = { p.getItem(), p.getSize(), p.getColor(),p.getPrice(),p.getStock() };
			((DefaultTableModel) AdminView.getModel()).addRow(row);
			}
		}
		return warehouse;
	}
	
	//OPDept
	public void SerializeOPdept(OPDept op){
		try{
			FileOutputStream fileOut=new FileOutputStream("orders.ser");;
			ObjectOutputStream out=new ObjectOutputStream(fileOut);
		    out.writeObject(op);
		    out.close();
		    fileOut.close();
		    System.out.printf("Serialized data is saved in warehouse.ser");
		}
		catch(IOException i){
			i.printStackTrace();
		}
	}
	public void deserializeOPDept(){
		OPDept op=null;
		try{
			FileInputStream fileIn=new FileInputStream("orders.ser");
			ObjectInputStream in=new ObjectInputStream(fileIn);
		    op=(OPDept)in.readObject();
		    in.close();
		    fileIn.close();
		}
		catch(IOException i){
			i.printStackTrace();
		}
		catch(ClassNotFoundException c){
			System.out.println("OPDept class not found");
			c.printStackTrace();
		}
		Iterator<Order> it=op.getOrders().iterator();
		while (it.hasNext()){
			Order p=it.next();
			System.out.println(p.getCustomer().getName() + " " +p.toString()+"\n");
		}
	}
	//CUSTOMERS
	public void SerializeCustomerSample(CustomerSample sample){
		try{
			FileOutputStream fileOut=new FileOutputStream("customers.ser");;
			ObjectOutputStream out=new ObjectOutputStream(fileOut);
		    out.writeObject(sample);
		    out.close();
		    fileOut.close();
		    System.out.printf("Serialized data is saved in customers.ser");
		}
		catch(IOException i){
			i.printStackTrace();
		}
	}
	public void deserializeCustomerSample(){
		CustomerSample customers=null;
		try{
			FileInputStream fileIn=new FileInputStream("customers.ser");
			ObjectInputStream in=new ObjectInputStream(fileIn);
		    customers=(CustomerSample)in.readObject();
		    in.close();
		    fileIn.close();
		}
		catch(IOException i){
			i.printStackTrace();
		}
		catch(ClassNotFoundException c){
			System.out.println("CustomerSample class not found");
			c.printStackTrace();
		}
		Iterator<Customer> it=customers.getCustomers().iterator();
		while (it.hasNext()){
			Customer p=it.next();
			System.out.println(p.getName());
		}
	}
	
	
}
