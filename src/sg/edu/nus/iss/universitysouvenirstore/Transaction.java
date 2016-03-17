package sg.edu.nus.iss.universitysouvenirstore;

import java.util.ArrayList;

public class Transaction {

	private ArrayList<TransactionedItem> items = new ArrayList<TransactionedItem>();
	private ArrayList<Product> product = new ArrayList<Product>();
	private int transactionCount = 0;
	
	Transaction(){
		//get latest transaction number by reading the trasaction.dat
		
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
	
	public void UpdateInventory(){
		
		
	}
	
	
}
