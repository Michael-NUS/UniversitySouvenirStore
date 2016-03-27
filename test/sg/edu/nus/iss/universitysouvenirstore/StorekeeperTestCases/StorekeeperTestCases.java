/**
 * File name : StorekeeperTestCases.java
 * 
 * Description : Implementation of TestCases for Storekeeper.java & Customer.java.
 *               
 * @author : NayLA 
 * 
 * Date : 12/03/2016
 * 
 */

package sg.edu.nus.iss.universitysouvenirstore.StorekeeperTestCases;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class StorekeeperTestCases {
	
	private static Customer customer1,customer2;
	
	private static Storekeeper storekeeper1,storekeeper2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		System.out.println("Run @Before");
		
		storekeeper1 = new Storekeeper();
		storekeeper2 = new Storekeeper();
				
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		 System.out.println("Run @After");
		 System.out.println("Test End!");
	}

	@Test
	public void testGetterSetter() {
		 
		System.out.println("Run @Test testGetterSetter"); 
		 
		String storekeeper1_name = "Stephen";
		String storekeeper1_password = "12345";
		Boolean storekeeper1_right = false;/* Normal */
		 
		String storekeeper2_name = "Thomas";
		String storekeeper2_password = "54321";
		Boolean storekeeper2_right = true;/* Admin */
		 
		storekeeper1.setUsernameAndPassword(storekeeper1_name,storekeeper1_password);
		storekeeper1.setRightForStorekeeper(storekeeper1_right);
		
		storekeeper2.setUsernameAndPassword(storekeeper2_name,storekeeper2_password);		
		storekeeper2.setRightForStorekeeper(storekeeper2_right);
		
		
		assertEquals("error in getter/setter of storekeeper's name", storekeeper1_name, storekeeper1.getName());
		assertEquals("error in getter/setter of storekeeper's password", storekeeper1_password, storekeeper1.getPassword());
		assertEquals("error in getter/setter of storekeeper's right", storekeeper1_right, storekeeper1.getRightForStorekeeper());
		
		assertEquals("error in getter/setter of storekeeper's name", storekeeper2_name, storekeeper2.getName());
		assertEquals("error in getter/setter of storekeeper's password", storekeeper2_password, storekeeper2.getPassword());
		assertEquals("error in getter/setter of storekeeper's right", storekeeper2_right, storekeeper2.getRightForStorekeeper());
		
	}
	 
	@Test 
	public void FileWriteReadTest(){
	
		System.out.println("Run @Test FileWriteReadTest"); 
					 
		File dir = new File("./data/sg/edu/nus/iss/universitysouvenirstore/data");
  	    File filePath = new File(dir ,"Storekeepers.dat");
  	    
  	    String storekeeper1_name = "Peter";
		String storekeeper1_password = "0102";
		Boolean storekeeper1_right = false;/* Normal */
		
		System.out.println("Run @Test FileWrite"); 
		
		storekeeper1.setUsernameAndPassword(storekeeper1_name,storekeeper1_password);
		storekeeper1.setRightForStorekeeper(storekeeper1_right);
  	    
  	    assertEquals("error in FileWrite test", true,storekeeper1.FileWrite(filePath));
  	    
  	    
  	    System.out.println("Run @Test FileRead"); 
  	    ArrayList<String>list = new ArrayList<String>();
  	    	    
  	    try { 
  	    	
  	    	list = storekeeper1.FileRead(filePath);/* Read file and load the array list first. */
  	    	
  	    	assertEquals("error in FileRead test", list,storekeeper1.FileRead(filePath));
  	    	
	    } catch (IOException e){	    	
	    	e.printStackTrace();
	    }
  	    
  	   System.out.println("Run @Test readStorekeepersFile"); 
  	    try { 
  	    	list = storekeeper1.FileRead(filePath);/* Read file and load the array list first. */
  	    	
	    	assertEquals("error in readStorekeepersFile test", list,storekeeper1.readStorekeepersFile());
	    } catch (IOException e){	    	
	    	e.printStackTrace();
	    }
  	    
  	    System.out.println("Run @Test checkUserInfo");
  	    try { 
  	    	 assertEquals("error in checkUserInfo test", true,storekeeper1.checkUserInfo(storekeeper1_name, storekeeper1_password));
  	    } catch (IOException e){	    	
  	    	e.printStackTrace();
  	    }
  	    
  	    System.out.println("Run @Test removeStoreKeeper");
  	    assertEquals("error in removeStoreKeeper test", true, storekeeper1.removeStoreKeeper(storekeeper1_name));
  	          
	}
	
	
	@Test
	public void CustomerTest(){
		
		System.out.println("Run @Test getCustomerType"); 
		
		customer1 = new Customer();/* Override default contrustor and initialized. */
		assertEquals("error in getter/setter of customer1's type", "PUBLIC", customer1.getCustomerType());

		customer2 = new Customer();/* Override default contrustor and initialized. */
		assertEquals("error in getter/setter of customer2's type", "PUBLIC", customer2.getCustomerType());
	}
	
	@Test
	public void test() {
				
		//fail("Not yet implemented");
	}

}
