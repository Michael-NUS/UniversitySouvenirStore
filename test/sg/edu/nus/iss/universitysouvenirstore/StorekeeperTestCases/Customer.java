/**
 * File name : Customer.java
 * 
 * Description : Implementation of Customer class
 *               which is a parent class.
 * 
 * @author : NayLA 
 * 
 * Date :10/03/2016
 * 
 */

package sg.edu.nus.iss.universitysouvenirstore.StorekeeperTestCases;

public class Customer {
	
	protected String type;/* Member="M" or Non-member="PUBLIC" */
	
	public Customer(){
		
		this.type = "PUBLIC";		
	}

	public String getCustomerType(){
				
		return this.type;
	}
}
