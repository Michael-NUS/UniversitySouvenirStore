package sg.edu.nus.iss.universitysouvenirstore;

import static org.junit.Assert.*;

import java.util.ArrayList;

import sg.edu.nus.iss.universitysouvenirstore.*;

import org.junit.Test;
/**
 * 
 * @author Michael
 * Unit test for Transaction Class
 */
public class TransactionTest {
	
	private Transaction transaction = new Transaction();
	private ArrayList<TransactionedItem> item1;
	
	/**
	 * Each transaction starts with $0
	 */
	@Test
	public void NewTransaction() { //tranasction should always starts with $0
		assertTrue(0 == transaction.GetTotalPrice());
	}
	
	/**
	 * Adding a new Item, 
	 * expected result: item added properly
	 * @throws CustomException
	 */
	@Test
	public void AddItem() throws CustomException{ //value should be increased, instead of replaced
		transaction.AddTransactionItem("CLO/1", 1, 21.45);
		item1 = transaction.GetTransactionItems();
		assertTrue(item1.get(0).GetProductID().equals("CLO/1"));
	}
	
	/**
	 * Increase existing productID's quantity if it's already existing 
	 * expected result: quantity is increased properly
	 * @throws CustomException
	 */
	@Test
	public void AddDuplicateItem() throws CustomException{ //value should be increased, instead of replaced
		transaction.AddTransactionItem("CLO/1", 1, 21.45);
		transaction.IncreaseTransactionItem("CLO/1", 1); //value should be increased, instead of replaced
		transaction.IncreaseTransactionItem("CLO/1", 5);
		item1 = transaction.GetTransactionItems();
		
		assertFalse(5 == item1.get(0).GetProductQuantity());
	}
	
	/**
	 * Edit existing productID's quantity
	 * expected result: quantity edited properly
	 * @throws CustomException
	 */
	@Test	
	public void EditItem() throws CustomException{	//edit need to replace the existing value
		transaction.AddTransactionItem("CLO/1", 1, 21.45);
		transaction.IncreaseTransactionItem("CLO/1", 1);
		transaction.IncreaseTransactionItem("CLO/1", 1);
		transaction.EditTransactionItem("CLO/1", 5); //edit need to replace the existing value
		item1 = transaction.GetTransactionItems();
		
		//System.out.println(String.valueOf(item1.get(0).GetProductQuantity()));			
		assertTrue(5==item1.get(0).GetProductQuantity());		
	}
	
	/**
	 * Add 2 different items into the list, pop them and make comparison
	 * expected result: 2 different productID
	 */
	@Test
	public void TestDifferentItemsAdded(){
		transaction.AddTransactionItem("CLO/1", 1, 21.45);
		transaction.AddTransactionItem("MUG/1", 1, 10.25);
		item1 = transaction.GetTransactionItems();
		assertFalse(item1.get(0).GetProductID().equals(item1.get(1).GetProductID()));
	}
	
	/**
	 * Attempt to remove non-existing productID
	 * throw the non-existing productID exception
	 * @throws CustomException
	 */
	@Test(expected=CustomException.class)
	public void TestRemoveNonExistence() throws CustomException{ //remove item that are not existing
		transaction.AddTransactionItem("CLO/1", 1, 21.45);
		transaction.RemoveTransactionItem("CLO/2");
	}
	
	/**
	 * Attempt to edit non-existing productID
	 * throw the non-existing productID exception
	 * @throws CustomException
	 */
	@Test(expected=CustomException.class)
	public void TestEditNonExistenceItems() throws CustomException{ //remove item that are not existing
		transaction.AddTransactionItem("CLO/1", 1, 21.45);
		transaction.EditTransactionItem("CLO/2",5);
	}
	
	
}
