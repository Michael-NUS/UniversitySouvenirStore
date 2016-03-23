package sg.edu.nus.iss.universitysouvenirstore;

import java.util.ArrayList;

public class Transaction {

	private ArrayList<TransactionedItem> items = new ArrayList<TransactionedItem>();
	private ArrayList<Product> product = new ArrayList<Product>();
	private int transactionCount = 0;
	
	//private Member member = new Member();
	
	Transaction(){
		
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
	
	public void AddTransactionItem (){
		String productID = null;
		int quantity = 0;
		
		//take in productID and quantity
		//check it exist
		
		//add the item
		items.add(new TransactionedItem(productID, quantity));
	}
	
	public ArrayList<TransactionedItem> GetTransactionItems(){
		return items;		
	}
	
	public void RemoveTransactionItem(int productID){
		items.remove(productID);
	}
	
	public void CancelTransaction(){
		
	}
	
	public void UpdateInventory(TransactionedItem item){
		item.GetProductID();
		item.GetProductQuantity();
		//update the product's array list;
		
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
		for (TransactionedItem item:items){
			UpdateInventory(item);
		}
		
		//write into the products.dat
		//call Product's Update (arraylist product);
		
		//write to Transaction.dat
		return amountPaid;
	}
	
}
