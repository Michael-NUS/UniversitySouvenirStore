/**
 * @author nyinyizin
 * @version 1.0
 */
package sg.edu.nus.iss.universitysouvenirstore;

public class Vendor {
	private String vendorName;
	private String vendorDescription;
	
	public Vendor(String name, String description){
		this.vendorName=name;
		this.vendorDescription=description;
	}
	public String getVendorName(){
		return this.vendorName;
	}
	public String getVendorDecription(){
		return this.vendorDescription;
	}
	public String toString(){
		return this.vendorName+','+this.vendorDescription;
	}
}
