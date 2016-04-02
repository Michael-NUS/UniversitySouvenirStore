package sg.edu.nus.iss.universitysouvenirstore;

public class TransactionedItem {
	private String productID;
	private int productQty;
	private double price;
	
	public TransactionedItem(String id, int qty, double price){
		this.productID = id;
		this.productQty = qty;
		this.price = price;
	}
	
	public boolean UpdateQuantity(int qty)
	{
		this.productQty = qty;
		this.price = qty * price;
		return true;
	}
	
	public String GetProductID(){
		return productID;
	}
	
	public int GetProductQuantity(){
		return productQty;
	}

	@Override
	public String toString() {
		return productID + " * " + productQty + "      " + price;
	}
	
}
