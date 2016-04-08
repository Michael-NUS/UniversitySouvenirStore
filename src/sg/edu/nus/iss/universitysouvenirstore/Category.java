/**
 * @author nyinyizin
 * @version 1.0
 */
package sg.edu.nus.iss.universitysouvenirstore;


public class Category {
	private String categoryId;
	private String categoryDescription;

	/**
	 * Category Class Constructor
	 * @param id Category Code
	 * @param description Category Description
	 */
	public Category(String id,String description){
		this.categoryId=id;
		this.categoryDescription=description;
	}
	
	/**
	 * Getter for Category Code
	 * @return Category Code : String
	 */
	public String getCategoryId(){
		return this.categoryId;
	}
	
	/**
	 * Getter for Category Description
	 * @return Category Description : String
	 */
	public String getCategoryDescription(){
		return this.categoryDescription;
	}
	
	/**
	 * Return Category object in Comma Separated String
	 */
	@Override
	public String toString(){
		return this.categoryId+','+this.categoryDescription;
	}
}
