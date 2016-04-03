/**
 * linwei
 */
package sg.edu.nus.iss.universitysouvenirstore;

public class Product {
	private String productId;
	private String productName;
	private String briefDescription;
	private int availableQuantity;
	private double price;
	private int reorderLevel;
	private int reorderQuantity;
	private String categoryId;
	private int barCodeNumber;
	
	
	
	
	

	public Product(String productId, String productName, String briefDescription, Integer availableQuantity, double price,
			 Integer reorderLevel, String categoryId, Integer reorderQuantity) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.briefDescription = briefDescription;
		this.availableQuantity = availableQuantity;
		this.price = price;
		this.barCodeNumber = this.productId.hashCode();
		this.reorderLevel = reorderLevel;
		this.categoryId = categoryId;
		this.reorderQuantity = reorderQuantity;

	}
	
	public int getReorderQuantity() {
		return reorderQuantity;
	}

	public void setReorderQuantity(int reorderQuantity) {
		this.reorderQuantity = reorderQuantity;
	}

	public int getBarCodeNumber() {
		return barCodeNumber;
	}

	public void setBarCodeNumber(int barCodeNumber) {
		this.barCodeNumber = barCodeNumber;
	}

	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBriefDescription() {
		return briefDescription;
	}
	public void setBriefDescription(String briefDescription) {
		this.briefDescription = briefDescription;
	}
	public int getAvailableQuantity() {
		return availableQuantity;
	}
	public int getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(int reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public void updateAvaliableQuantity(int quantity, int type){
		if(type==1){
			this.availableQuantity -= quantity;
		}else if (type ==2){
			this.availableQuantity += quantity;
		}else if(type ==0){
			this.availableQuantity = quantity;
		}
	}
	

}

