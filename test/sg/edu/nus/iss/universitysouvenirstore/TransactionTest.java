package sg.edu.nus.iss.universitysouvenirstore;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.*;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author Lim Hean Soon
 * Unit test for Transaction Class
 */
public class TransactionTest {
	InputStream in;
	private Transaction transaction = new Transaction();
	private ArrayList<TransactionedItem> item1;
	
	@Before
	public void setUp() throws Exception {
		FileManagerUtils.fileDir="./data/sg/edu/nus/iss/universitysouvenirstore/unittestdata";		
		//readTransact = FileManagerUtils.readDataFromDatFile(Transaction.class);
	}
	
	/**
	 * After each test cases, replace the Transactions.dat with the TransactionsBackup.dat to reset
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {		
		in = new FileInputStream(new File("./data/sg/edu/nus/iss/universitysouvenirstore/unittestdata/TransactionsBackup.dat"));
		OutputStream out = new FileOutputStream(new File("./data/sg/edu/nus/iss/universitysouvenirstore/unittestdata/Transactions.dat"));
		byte[] buffer = new byte[1024];
		int length;
		while ((length = in.read(buffer)) > 0) {
			out.write(buffer, 0, length);
		}
		in.close();
		out.close();			     
	}
	
	/**
	 * Each transaction starts with $0
	 */
	@Test
	public void NewTransaction() { //transaction should always starts with $0
		assertTrue(0 == transaction.GetTotalPrice());
	}
	
	/**
	 * Attempt to read from test/Transaction.dat to get the last Transaction ID
	 */
	@Test
	public void TestReadLatestTranasctionID(){	
		//System.out.println(transaction.GetTransactionID());
		assertTrue(6==transaction.GetTransactionID());
	}
	
	@Test
	/**
	 * Test writing to the Transaciton.dat file and ensure the transactionID increased went up
	 */
	public void WriteTransactionFile(){
		int oldTransactionID = 0;
		oldTransactionID = transaction.GetTransactionID();
		transaction.AddTransactionItem("CLO/1", 1, 21.45);
		transaction.CheckOut();
		
		Transaction transaction2 = new Transaction();
		//System.out.println("Transaction2: " + transaction2.GetTransactionID());
		assertTrue((oldTransactionID + 1)==transaction2.GetTransactionID());
	}
}
