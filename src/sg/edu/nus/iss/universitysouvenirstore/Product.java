package sg.edu.nus.iss.universitysouvenirstore;

public class Product {
	private String productId;
	private String productName;
	private String briefDescription;
	private int availableQuantity;
	private double price;
	private String barCodeNumber;
	private int reorderQuantity;
	private int orderQuantity;
	private String categoryId;
	private String vendorId;
	
	
	
	public Product(String productId, String productName, String briefDescription, int availableQuantity, double price,
			String barCodeNumber, int reorderQuantity, String categoryId, String vendorId) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.briefDescription = briefDescription;
		this.availableQuantity = availableQuantity;
		this.price = price;
		this.barCodeNumber = barCodeNumber;
		this.reorderQuantity = reorderQuantity;
		this.categoryId = categoryId;
		this.vendorId = vendorId;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
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
	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getBarCodeNumber() {
		return barCodeNumber;
	}
	public void setBarCodeNumber(String barCodeNumber) {
		this.barCodeNumber = barCodeNumber;
	}
	public int getReorderQuantity() {
		return reorderQuantity;
	}
	public void setReorderQuantity(int reorderQuantity) {
		this.reorderQuantity = reorderQuantity;
	}
	public int getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public void updateProduct(){
		
	}

}

