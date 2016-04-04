package sg.edu.nus.iss.universitysouvenirstore;

import static org.junit.Assert.*;

import java.util.ArrayList;

import sg.edu.nus.iss.universitysouvenirstore.*;

import org.junit.Test;

public class TransactionTest {
	
	private Transaction transaction = new Transaction();
	private ArrayList<TransactionedItem> item1;
	@Test
	public void NewTransaction() {
		assertTrue(0 == transaction.GetTotalPrice());
	}
	
	@Test
	public void AddNewItem(){
		transaction.AddTransactionItem("CLO/1", 1, 21.45);
		transaction.IncreaseTransactionItem("CLO/1", 1);
		transaction.IncreaseTransactionItem("CLO/1", 5);
		item1 = transaction.GetTransactionItems();
		
		assertFalse(3 == item1.get(0).GetProductQuantity());
	}
	
	public void EditItem(){
		
		
	}
}
