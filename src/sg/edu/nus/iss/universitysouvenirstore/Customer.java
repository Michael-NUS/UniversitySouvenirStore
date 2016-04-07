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

package sg.edu.nus.iss.universitysouvenirstore;

public class Customer {
	
	protected String type;/* Member="M" or Non-member="PUBLIC" */
	
	/**
	 *  Constructor for Customer and set as "PUBLIC" by default.
	 *  Member customer can override this for setting it as "Member".
	 */
	public Customer(){
		
		this.type = "PUBLIC";		
	}
	
	/**
	 * 
	 * @return   Return type of customer (PUBLIC or Member).
	 */
	
	public String getCustomerType(){
				
		return this.type;
	}
}
