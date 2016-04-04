package sg.edu.nus.iss.universitysouvenirstore;

import java.util.ArrayList;
import java.util.Collections;
import java.text.SimpleDateFormat;
import java.util.*;

import sg.edu.nus.iss.universitysouvenirstore.*;
import sg.edu.nus.iss.universitysouvenirstore.gui.TransactionDialog;
import sg.edu.nus.iss.universitysouvenirstore.util.FileManangerUtils;

public class Transaction {
	private int transactionCount = 0;
	private ArrayList<TransactionedItem> items = new ArrayList<TransactionedItem>();
	private String memberID = "PUBLIC";
	private int memberPoints = 0;
	private boolean cashBack = false;
	
	//private ArrayList<Product> product = new ArrayList<Product>();

	private FileManangerUtils fileManager = new FileManangerUtils();
	//private Member member = new Member();
	
	public Transaction(){	
		transactionCount = GetTransactionCount() + 1;	
	}
	
	public int GetTransactionCount(){
		int count = 0;
		
		//ArrayList<Object> test = fileManager.ReadDataFromDatFile("Transactions", "Transactions");
		ArrayList<Object> readTransact = FileManagerUtils.readDataFromDatFile(Transaction.class);
		//System.out.println("HERE" + test.size());
		count = readTransact.size();
		
		transactionCount = Integer.parseInt(readTransact.get(count-1).toString());
		//System.out.println(test.get(count-1).toString());
		//get latest transaction number by checking the last trasaction.dat's number/count?	
			  
		System.out.println("TransactionID " + count);
		//memberPoints = 150 % 100;
		System.out.println(memberPoints);
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
	
	public void CheckOut(){
		float amountPaid = 0;
		float discount = 0;
		int conversionRation = 100;
		
		//date
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String stringDate = String.valueOf(sdf.format(date));
		//System.out.println(sdf.format(date)); //debug

		//Discount class's return the highest discount available
		amountPaid = GetTotalPrice();
		discount = GetHighestDiscount();


		//get Point to Cash
		int cashBackValue = 0;
		if(cashBack == true)
		{
			cashBackValue = memberPoints/100;
			memberPoints = memberPoints % 100;
			System.out.println("memberPoints before new adjustment:" + memberPoints);
		}

		amountPaid = (amountPaid - cashBackValue * (1 - (discount/100)));
		
		//new Member Points
		memberPoints = (int) (memberPoints + (amountPaid/10));
		
		//Yakun's point update
		System.out.println("MemberID: " + memberID + " - Points: " + memberPoints + "\n" + "Amount Paid: " + amountPaid);
		MemberManager.updateMemberLoyaltyPoint(memberID,memberPoints);
		//end of Yakun's update
		
		//Major's update product
		ArrayList<Product> products = ProductUtils.getAllProducts();
		ProductUtils.updateTransctionQuantity(products,items);
		//end of Major's update product
		
		ArrayList<String>writeToFile = new ArrayList<String>();
		String line;
		
		for (TransactionedItem item:items)
		{
			line = String.valueOf(transactionCount) + "," + item.GetProductID() + "," + memberID + "," + String.valueOf(item.GetProductQuantity() + "," + stringDate);
			System.out.println(line);
			writeToFile.add(line);
		}		
		
		System.out.println(String.valueOf(memberPoints));
		
		FileManagerUtils.AppendDataToFile("Transactions", "Transactions", writeToFile);
		//write to Transaction.dat		
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
	
	public float GetTotalPrice(){
		float total = 0;
		
		if(items != null)
		{
			for (TransactionedItem item:items)
			{
				total += item.GetPrice();
			}
		}
		return total;
	}

	@Override
	public String toString() {
		return "Transaction [transactionCount=" + transactionCount + ", items=" + items + "]";
	}
	
	public void SetMember(String id){
		this.memberID = id;
	}
	
	public void SetMemberPoints(int points){
		this.memberPoints = points;
	}
	
	public void SetCashBack(boolean toogle){
		cashBack = toogle;
	}
	
}
