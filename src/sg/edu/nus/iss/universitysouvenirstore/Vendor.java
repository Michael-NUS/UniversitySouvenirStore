/**
 * @author nyinyizin
 * @version 1.0
 */
package sg.edu.nus.iss.universitysouvenirstore;

public class Vendor {
	private String vendorName;
	private String vendorDescription;
	
	/**
	 * Vendor Constructor
	 * @param name Vendor Name
	 * @param description Vendor Description
	 */
	public Vendor(String name, String description){
		this.vendorName=name;
		this.vendorDescription=description;
	}
	
	/**
	 * Getter for Vendor Name
	 * @return Vendor Name : String
	 */
	public String getVendorName(){
		return this.vendorName;
	}
	
	/**
	 * Getter for Vendor Description
	 * @return Vendor Description : String
	 */
	public String getVendorDecription(){
		return this.vendorDescription;
	}
	/**
	 * Return Vendor in Comma Separated String : String
	 */
	public String toString(){
		return this.vendorName+','+this.vendorDescription;
	}
}
