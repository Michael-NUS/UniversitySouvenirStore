package sg.edu.nus.iss.universitysouvenirstore;

public class TransactionedItem {
	private String productID;
	private int productQty;
	
	public TransactionedItem(String id, int qty){
		this.productID = id;
		this.productQty = qty;
	}
	
	public boolean UpdateQuantity(int qty)
	{
		
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
		return productID + " * " + productQty;
	}
	
}
