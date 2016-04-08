package sg.edu.nus.iss.universitysouvenirstore;

/**
 * 
 * @author Lim Hean Soon
 *Transactioned Item Class
 *each transactioned item will represent 1 item entry in the Checkout screen.
 *the item is indicated by the product ID and also the quantity
 */
public class TransactionedItem {
	private String productID; //productID which will be used to identify this item
	private int productQty; //number of items for this productID
	private double individualPrice; //single item's price
	private double price; //total price for particular item
	
	/**
	 * Constructor for this class
	 * will accept the following params:
	 * @param id
	 * @param qty
	 * @param individualPrice
	 * price is calculated based on how many units for this item multiply by the individual price
	 */
	public TransactionedItem(String id, int qty, double individualPrice){
		this.productID = id;
		this.productQty = qty;
		this.individualPrice = individualPrice;
		this.price = individualPrice * qty;
	}
	
	/**
	 * Update the existing's quantity
	 * @param qty
	 */
	public void UpdateQuantity(int qty)
	{
		this.productQty = qty;
		this.price = qty * individualPrice * 100 / 100;
	}
	
	/**
	 * Return the productID for this item
	 * @return
	 */
	public String GetProductID(){
		return productID;
	}
	
	/**
	 * Return the quantity for this particular product
	 * @return
	 */
	public int GetProductQuantity(){
		return productQty;
	}
	
	@Override
	public String toString() {
		return productID + " * " + productQty + "      " + "$" + price;
	}
	
	/**
	 * Return the total price for this item multiply it's unit
	 * @return
	 */
	public double GetPrice()
	{
		return price;
	}
	
}
