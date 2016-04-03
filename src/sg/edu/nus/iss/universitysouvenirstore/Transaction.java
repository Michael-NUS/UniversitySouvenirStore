package sg.edu.nus.iss.universitysouvenirstore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

import sg.edu.nus.iss.universitysouvenirstore.*;

public class Transaction {

	private ArrayList<TransactionedItem> items = new ArrayList<TransactionedItem>();
	private ArrayList<Product> product = new ArrayList<Product>();
	private int transactionCount = 0;
	
	//private Member member = new Member();
	
	public Transaction(){
		
		//transactionCount = GetTransactionCount();
		transactionCount = 5;		
	}
	
	/*
	Transaction(Member memberCustomer){
	
		//transactionCount = GetTransactionCount();
		member = memberCustomer;
		transactionCount = 5;
		
	}
	*/
	
	public int GetTransactionCount(){
		int count = 0;
		
		//get latest transaction number by checking the last trasaction.dat's number/count?	
		
		return count;
	}
	
	public void GetProductList(){
		//get from Product's method
	}
	
	public void AddTransactionItem (String productID, int quantity, double price){
		//String productID = null;
		//int quantity = 0;
		
		//take in productID and quantity
		//check it exist
		
		//add the item
		items.add(new TransactionedItem(productID, quantity, price));
	}
	
	public void EditTransactionItem (String productID, int quantity)
	{
		int counter = 0;
		//System.out.println("Transaction.EditTransactionItem.ProductID " + productID+"|"); //debug
		
		while (counter < items.size())
		{
			//System.out.println("items.get(counter).GetProductID()" + items.get(counter).GetProductID()+" compare " + productID ); //debug

			if(items.get(counter).GetProductID().equalsIgnoreCase(productID))
			{
				items.get(counter).UpdateQuantity(quantity);
				System.out.println("New quantity" + items.get(counter).GetProductQuantity());
			}
			else
				System.out.println("nope");
			
			counter ++;		
		}
	}
	
	/*
	public ArrayList<TransactionedItem> GetTransactionItems(){
		return items;		
	}
	*/
	public ArrayList<TransactionedItem> GetTransactionItems() {
	    	ArrayList<TransactionedItem> result = new ArrayList<TransactionedItem>(items);
	        //Collections.sort (result);
	        return (result);
	    }
	
	public void RemoveTransactionItem(String productID){
		
		
		int counter = 0;
		//System.out.println("Transaction.EditTransactionItem.ProductID " + productID); //debug
		
		while (counter < items.size())
		{
			//System.out.println("items.get(counter).GetProductID()" + items.get(counter).GetProductID()+" compare " + productID ); //debug

			if(items.get(counter).GetProductID().equalsIgnoreCase(productID))
			{
				items.remove(counter);
			}
			else
				System.out.println("nope");
			
			counter ++;		
		}
	}
	
	public void CancelTransaction(){
		
	}
	
	
	public float GetHighestDiscount(){
		float highestDiscount = 0;
		
		//call Discount Class's highest discount, will pass member or not
		
		return highestDiscount;
	}
	
	public float CheckOut(){
		float amountPaid = 0;
		float discount = 0;
		//Discount class's return the highest discount available
		discount = GetHighestDiscount();
	
		amountPaid = amountPaid * (discount/100);

		//get Point to Cash
		
		//amountPaid - Points
		//new amountPaid
		
		//successful transaction
		//update the Member Point
		
		//write into the products.dat
		//call Product's Update (arraylist product);
		ArrayList<Product> products = ProductUtils.getAllProducts();
		ProductUtils.updateTransctionQuantity(products,items);
		
		//write to Transaction.dat
		return amountPaid;
	}
	
	public boolean ConflictItem(String productID)
	{
		boolean conflict = false;
		
		for(TransactionedItem item:items)
		{
			if(item.GetProductID().equals(productID))
				conflict = true;
		}
		return conflict;
	}

	public void IncreaseTransactionItem(String productID, int quantity) {
		int counter = 0;
		//System.out.println("Transaction.EditTransactionItem.ProductID " + productID+"|"); //debug
		
		while (counter < items.size())
		{
			//System.out.println("items.get(counter).GetProductID()" + items.get(counter).GetProductID()+" compare " + productID ); //debug

			if(items.get(counter).GetProductID().equalsIgnoreCase(productID))
			{
				quantity = quantity + items.get(counter).GetProductQuantity();
				items.get(counter).UpdateQuantity(quantity);
				//System.out.println("New quantity" + items.get(counter).GetProductQuantity()); //debug
			}
			else
				System.out.println("nope");
			
			counter ++;		
		}
	}
	
	public double GetTotalPrice(){
		double total = 0;
		
		if(items != null)
		{
			for (TransactionedItem item:items)
			{
				total += item.GetPrice();
			}
		}
		return total;
	}
	
}
