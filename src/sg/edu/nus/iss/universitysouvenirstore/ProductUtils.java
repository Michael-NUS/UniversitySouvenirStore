package sg.edu.nus.iss.universitysouvenirstore;

import java.util.ArrayList;

public class ProductUtils {
	
	   
	public static void addNewProduct(ArrayList<Product> products, String categoryId, String productName, String briefDesc, int availableQuantity, double price, String barCodeNumber, int reOrderQuantity , String vendorId){
		String productId = categoryId + "/" + "12";	 // get sizeof content in the category 	
		Product product = new Product(productId,productName,  briefDesc,  availableQuantity,  price, barCodeNumber, reOrderQuantity, categoryId,vendorId) ;
		products.add(product);
	}
	public static void removeProduct(ArrayList<Product> products, Product product){
		products.remove(product);	
	}
	public static void editProduct(Product original,String categoryId, String productName, String briefDescription, Integer availableQuantity, Double price, String barCodeNumber, Integer reorderQuantity, String vendorId){
		if(categoryId!=null){
			String productId = categoryId +"/" +"12"; //need to get the actual id from the category
			original.setProductId(productId);
		}
		if(productName!=null){
			original.setProductName(productName);			
		}
		if(briefDescription!=null){
			original.setBriefDescription(briefDescription);
		}
		if(availableQuantity!=null){
			original.setAvailableQuantity(availableQuantity);
		}
		if(price!=null){
			original.setPrice(price);
		}
		if(barCodeNumber!=null){
			original.setBarCodeNumber(barCodeNumber);
		}
		if(reorderQuantity!=null){
			original.setReorderQuantity(reorderQuantity);
		}
		if(vendorId!= null){
			original.setVendorId(vendorId);
		}
		
	}
	public static ArrayList<Product> readExistingProductFromDB(){
		ArrayList<Product>  products= new ArrayList<Product>();
		//get the products from the io
		
		products.add(new Product("qwe1", "product1", "buythings", 0, 0, null, 0, null, null));
		products.add(new Product("qwe2", "product2", "buythings", 0, 0, null, 0, null, null));
		products.add(new Product("qwe3", "product3", "buythings", 0, 0, null, 0, null, null));
		products.add(new Product("qwe4", "product4", "buythings", 0, 0, null, 0, null, null));
		products.add(new Product("qwe5", "product5", "buythings", 0, 0, null, 0, null, null));
		
		return products;
	}
	public static ArrayList<Product> getAllProducts(){
		return readExistingProductFromDB();
	}
	public static ArrayList<Product> getProductsForCategory(String categoryId){
		ArrayList<Product>  products= new ArrayList<Product>();
		//get the products from categoryu
		
		if(categoryId.equals("lin1")){
		products.add(new Product("qwe1", "product1", "buythings", 0, 0, null, 0, null, null));
		products.add(new Product("qwe2", "product2", "buythings", 0, 0, null, 0, null, null));
		}
		else if(categoryId.equals("lin2")){
		products.add(new Product("qwe3", "product3", "buythings", 0, 0, null, 0, null, null));
		products.add(new Product("qwe4", "product4", "buythings", 0, 0, null, 0, null, null));
		}
		else{
		products.add(new Product("qwe5", "product5", "buythings", 0, 0, null, 0, null, null));
		}
		
		return products;
		
	}
	public static void saveProductToDB(Product product){
		// get the IO for product saving 
	}
	public static void saveProductsToDB(ArrayList<Product> products){
		for(Product one: products){
			saveProductToDB(one);
		}
	}
	
	public static void checkProductQuantity(Product product){
		
	}
	public static void checkProductsQuantity(ArrayList<Product> products){
		for (Product one: products){
			checkProductQuantity(one);
		}
	}
	public static void reOrderProcess(ArrayList<Product> products){
		
	}
	public static Product getProductById(ArrayList<Product> products, String productId){
		Product product = null;
		for(Product one : products){
			if(one.getProductId().equals(productId)){
				product = one;
				break;
			}
		}
		
		return product;
	}
	
	public static boolean removeProductFromCategory(String categoryId, String productId){
		boolean status = true;
		return status;
	}
	
	

}
