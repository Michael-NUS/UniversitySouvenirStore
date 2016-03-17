package sg.edu.nus.iss.universitysouvenirstore;

import java.util.ArrayList;

public class Transaction {

	private ArrayList<TransactionedItem> items = new ArrayList<TransactionedItem>();
	private ArrayList<Product> product = new ArrayList<Product>();
	private int transactionCount = 0;
	
	Transaction(){
		//get latest transaction number by checking the last trasaction.dat's number/count?		
		//transactionCount = last ID
		
		
	}
	
	public void ReadProductList(){
		
	}
	
	public void AddTransactionItem (){
		String productID = null;
		int quantity = 0;
		
		//take in productID and quantity
		//check it exist
		
		//add the item
		items.add(new TransactionedItem(productID, quantity));
	}
	
	public void GetTransaction(){
		
	}
	
	public void RemoveTransaction(){
		
	}
	
	public void CancelTransaction(){
		
	}
	
	public void UpdateInventory(TransactionedItem item){
		item.GetProductID();
		//update the product's arraylist;
		
	}
	
	public float CheckOut(){
		float amountPaid = 0;
		int discount = 0;
		//Discount class's return the highest discount available
		
		
		for (TransactionedItem item:items){
			UpdateInventory(item);
		}
		
		//write into the products.dat
		return amountPaid;
	}
	
}
