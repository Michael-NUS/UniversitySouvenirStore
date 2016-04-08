package sg.edu.nus.iss.universitysouvenirstore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Lim Hean Soon
 * Main Transaction class
 * house all the important information such as the Items, Members, MemberPoints, Discount info and also the Cashback
 * methods include all the manipulation of the items, cashback, update of the memberPoints as well as initate the updating of the transaction.dat file
 */
public class Transaction {
	private int transactionCount = 0; //get the last Transaction ID which is a running number
	private ArrayList<TransactionedItem> items = new ArrayList<TransactionedItem>(); //array list of the item to be added to this transaction
	private String memberID = "PUBLIC"; //default Member ID = PUBLIC which indicates non member
	private int memberPoints = 0; //memberPoints to keep track of the current member's point
	private boolean cashBack = false; //to indicate whether the member would like to convert the points to cash back
	private int highestDiscount = 0; //keep track of the highest applicable discount
	
	/**
	 * Constructor for Transaction will initiate the Transaction ID
	 */
	public Transaction(){	
		transactionCount = GetLastTransactionID() + 1;	
		//System.out.println("new TransactionID: " + transactionCount);
	}
	

	/**
	 * get the latest Transaction ID, will return the last transaction ID as an Int to the Constructor
	 * @return
	 */
	public int GetLastTransactionID(){
		int count = 0;
		
		ArrayList<Object> readTransact = FileManagerUtils.readDataFromDatFile(Transaction.class);
		//System.out.println("GetTransactionCount" + readTransact.size()); //debug
		count = readTransact.size();
		
		if(count >0)
			transactionCount = Integer.parseInt(readTransact.get(count-1).toString());
		else //in the case of the Transaction.dat is empty, means that this is the first Transaction to make
			transactionCount = 0;
			//System.out.println(test.get(count-1).toString()); //debug
			//get latest transaction number by checking the last trasaction.dat's transaction ID
			  
		//System.out.println("TransactionID " + count); //debug
		return transactionCount;
	}	
	
	/**
	 * Add an item into the ArrayList with the type of TransactionedItem
	 * @param productID
	 * @param quantity
	 * @param price
	 */
	public void AddTransactionItem (String productID, int quantity, double price){		
		//take in productID and quantity		
		items.add(new TransactionedItem(productID, quantity, price)); //create an object of TransactionedItem and add it into the ArrayList
	}
	
	/**
	 * Edit the TranactionedItem quantity
	 * @param productID
	 * @param quantity
	 * @throws CustomException
	 */
	public void EditTransactionItem (String productID, int quantity)throws CustomException 
	{
		int counter = 0; //counter to trap the address for the ArrayList
		//System.out.println("Transaction.EditTransactionItem.ProductID " + productID+"|"); //debug
		
		while (counter < items.size())
		{
			//System.out.println("items.get(counter).GetProductID()" + items.get(counter).GetProductID()+" compare " + productID ); //debug
			if(items.get(counter).GetProductID().equalsIgnoreCase(productID)) //when found the productID
			{
				items.get(counter).UpdateQuantity(quantity); //update the productID's quantity with the new quantity
				//System.out.println("New quantity" + items.get(counter).GetProductQuantity()); //debug
			}
			else
				throw new CustomException("Item not found"); //throw exception if the item to edit is not exist anymore
			
			counter ++;		
		}
	}
	
	/**
	 * Return the ArrayList containing the list of items for this transaction
	 * @return
	 */
	public ArrayList<TransactionedItem> GetTransactionItems() {
	    	//ArrayList<TransactionedItem> result = new ArrayList<TransactionedItem>(items);
	        return items;
	    }
	
	/**
	 * Remove the selected Item from this Transaction
	 * @param productID
	 * @throws CustomException
	 */
	public void RemoveTransactionItem(String productID) throws CustomException{		
		
		int counter = 0;
		//System.out.println("Transaction.EditTransactionItem.ProductID " + productID); //debug
		
		while (counter < items.size()) //loop thru the ArrayList to look for the Product ID
		{
			//System.out.println("items.get(counter).GetProductID()" + items.get(counter).GetProductID()+" compare " + productID ); //debug
			if(items.get(counter).GetProductID().equalsIgnoreCase(productID))//Product ID found
			{
				items.remove(counter); //Remove that entry from the ArrayList
			}
			else //Product ID not found
				throw new CustomException("Item not found");
				
			counter ++;		
		}
	}

	/**
	 * Get the highest discount applicable (either for Member or Non-Member)
	 * return the highest discount in Int
	 * @return
	 */
	public int GetHighestDiscount(){		
		if(!memberID.equals("PUBLIC")) //if it's member pass the memberID, this will help to indicate if the member is eligible for FirstTime Purchase discount
		{
		//call Discount Class's highest discount, will pass memberID or PUBLIC
			highestDiscount = DiscountManger.getHighestDiscount(memberID);
		}
		else //get the highest discount for non-member
			highestDiscount = DiscountManger.getHighestDiscount("PUBLIC");
		//System.out.println("Highest Discount = " + highestDiscount);//debug
		
		return highestDiscount;
	}
	
	/**
	 * Check out method, which will invoke when the user complete the transaction (after paying)
	 */
	public void CheckOut(){
		float amountPaid = 0;		
		Date date = new Date();
		SimpleDateFormat todayDate = new SimpleDateFormat("yyyy-MM-dd"); //get the latest date		
		String stringDate = String.valueOf(todayDate.format(date));
		//System.out.println(sdf.format(date)); //debug

		amountPaid = GetTotalPrice();
		//get Point to Cash
		int cashBackValue = 0;
		if(cashBack == true)
		{
			cashBackValue = memberPoints/100;
			memberPoints = memberPoints % 100;
			//System.out.println("memberPoints before new adjustment:" + memberPoints); //debug
		}
		
		amountPaid = (((amountPaid - cashBackValue) * (100-highestDiscount))/100);
		
		//calculate new Member Points
		memberPoints = (int) (memberPoints + (amountPaid/10));
		
		//Call Yakun's point update
		//System.out.println("MemberID: " + memberID + " - Points: " + memberPoints + "\n" + "Amount Paid: " + amountPaid); //debug
		MemberManager.updateMemberLoyaltyPoint(memberID,memberPoints);
		//end of Yakun's update
		
		//Call Lin Wei's update product
		ArrayList<Product> products = ProductUtils.getAllProducts();
		ProductUtils.updateTransctionQuantity(products,items);
		//end of Lin Wei's update product
		
		ArrayList<String>writeToFile = new ArrayList<String>(); //new ArrayList of string to keep track of the transaction information
		
		//structuring the transaction before writing to transaction.dat file
		String line;
		
		for (TransactionedItem item:items) //structure the transaction information for each item in the transaction list
		{
			line = String.valueOf(transactionCount) + "," + item.GetProductID() + "," + memberID + "," + String.valueOf(item.GetProductQuantity() + "," + stringDate);
			//System.out.println(line);
			writeToFile.add(line);
		}		
		
		//System.out.println(String.valueOf(memberPoints)); //debug
		
		FileManagerUtils.AppendDataToFile("Transactions", "Transactions", writeToFile); //call Myo's write to file method
		//write to Transaction.dat		
	}
	
	/**
	 * Check if the Product ID already existed
	 * @param productID
	 * @return
	 */
	public boolean ConflictItem(String productID) //check wether the same productID is already existing in the transaction list
	{
		boolean conflict = false;
		
		for(TransactionedItem item:items) //loop thru the ArrayList and compare the productID
		{
			if(item.GetProductID().equals(productID)) //Product ID found
				conflict = true;
		}
		return conflict;
	}

	/**
	 * Incraese existing Product ID's quantity instead of adding a new item
	 * @param productID
	 * @param quantity
	 * @throws CustomException
	 */
	public void IncreaseTransactionItem(String productID, int quantity) throws CustomException {//if the productID already existed, incraese the quantity with the new count
		int counter = 0;
		//System.out.println("Transaction.EditTransactionItem.ProductID " + productID+"|"); //debug		
		while (counter < items.size())
		{
			//System.out.println("items.get(counter).GetProductID()" + items.get(counter).GetProductID()+" compare " + productID ); //debug
			if(items.get(counter).GetProductID().equalsIgnoreCase(productID)) //found the Product ID
			{
				quantity = quantity + items.get(counter).GetProductQuantity(); //add the existing quantity with the new value
				items.get(counter).UpdateQuantity(quantity); //call the Update Quantity method
				//System.out.println("New quantity" + items.get(counter).GetProductQuantity()); //debug
			}
			else
				throw new CustomException("Item not found");
			
			counter ++;		
		}
	}
	
	/**
	 * Return the total price for the existing items
	 * @return
	 */
	public float GetTotalPrice(){//Total price with the existing items
		float total = 0;
		
		if(items != null)
		{
			for (TransactionedItem item:items)
			{
				total += item.GetPrice(); //add all the price of the items
			}
		}
		return total;
	}

	@Override
	public String toString() {
		return "Transaction [transactionCount=" + transactionCount + ", items=" + items + "]";
	}
	
	/**
	 * Set the member ID
	 * @param id
	 */
	public void SetMember(String id){
		this.memberID = id;
	}
	
	/**
	 * Set the member's point
	 * @param points
	 */
	public void SetMemberPoints(int points){
		this.memberPoints = points;
	}
	
	/**
	 * Set whether the member opt in to Cash back or not
	 * @param toogle
	 */
	public void SetCashBack(boolean toogle){
		cashBack = toogle;
	}
	
	/**
	 * Return current Transaction ID
	 * @return
	 */
	public int GetTransactionID(){
		return transactionCount;
	}
}
