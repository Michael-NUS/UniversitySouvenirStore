/**
 * @author nyinyizin
 * @version 1.0
 */
package sg.edu.nus.iss.universitysouvenirstore;


public class Category {
	private String categoryId;
	private String categoryDescription;

	public Category(String id,String description){
		this.categoryId=id;
		this.categoryDescription=description;
	}
	public String getCategoryId(){
		return this.categoryId;
	}
	public String getCategoryDescription(){
		return this.categoryDescription;
	}
	public String toString(){
		return this.categoryId+','+this.categoryDescription;
	}
}
