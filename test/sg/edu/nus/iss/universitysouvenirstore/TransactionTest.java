package sg.edu.nus.iss.universitysouvenirstore;

import static org.junit.Assert.*;

import java.util.ArrayList;

import sg.edu.nus.iss.universitysouvenirstore.*;

import org.junit.Test;

public class TransactionTest {
	
	private Transaction transaction = new Transaction();
	private ArrayList<TransactionedItem> item1;
	
	@Test
	public void NewTransaction() { //tranasction should always starts with $0
		assertTrue(0 == transaction.GetTotalPrice());
	}
	
	@Test
	public void AddNewItem(){ //value should be increased, instead of replaced
		transaction.AddTransactionItem("CLO/1", 1, 21.45);
		transaction.IncreaseTransactionItem("CLO/1", 1); //value should be increased, instead of replaced
		transaction.IncreaseTransactionItem("CLO/1", 5);
		item1 = transaction.GetTransactionItems();
		
		assertFalse(5 == item1.get(0).GetProductQuantity());
	}
	
	@Test	
	public void EditItem(){	//edit need to replace the existing value
		transaction.AddTransactionItem("CLO/1", 1, 21.45);
		transaction.IncreaseTransactionItem("CLO/1", 1);
		transaction.IncreaseTransactionItem("CLO/1", 1);
		transaction.EditTransactionItem("CLO/1", 5); //edit need to replace the existing value
		item1 = transaction.GetTransactionItems();
		
		//System.out.println(String.valueOf(item1.get(0).GetProductQuantity()));			
		assertTrue(5==item1.get(0).GetProductQuantity());		
	}
}
