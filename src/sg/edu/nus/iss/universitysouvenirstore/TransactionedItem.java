package sg.edu.nus.iss.universitysouvenirstore;

public class TransactionedItem {
	private String productID;
	private int productQty;
	private double price;
	private double individualPrice;
	
	public TransactionedItem(String id, int qty, double price){
		this.productID = id;
		this.productQty = qty;
		this.individualPrice = price;
		this.price = individualPrice * qty;
	}
	
	public boolean UpdateQuantity(int qty)
	{
		this.productQty = qty;
		this.price = qty * individualPrice;
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
	
	public double GetPrice()
	{
		return price;
	}
	
}
