package sg.edu.nus.iss.universitysouvenirstore.product;

import java.util.List;

public class ProductManager {
	private List<Product> products;

	public int checkQuantity(String productId){	
		return 0;
	}
	public void removerProduct(String productId){
		
	}
	public void triggerReorder(){
		
	}
	public void addProduct(Product product){
		if(products!=null){
			products.add(product);
		}
	}
	public void removeProduct(Product product){
		if(products!=null){
			products.remove(product);
		}
	}
}
