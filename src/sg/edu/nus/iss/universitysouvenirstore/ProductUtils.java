/**
 * linwei
 */
package sg.edu.nus.iss.universitysouvenirstore;

import java.util.ArrayList;

import sg.edu.nus.iss.universitysouvenirstore.util.FileManangerUtils;

public class ProductUtils {
	
	   
	public static int addNewProduct(ArrayList<Product> products, String productId, String productName, String briefDesc, int availableQuantity, double price, String barCodeNumber, int reOrderQuantity , String vendorId){
		String []temp = productId.split("/");
		Product product = new Product(productId,productName,  briefDesc,  availableQuantity,  price, barCodeNumber, reOrderQuantity, temp[0],vendorId) ;
		int status = checkExistOfProduct(products, product);
		if(status ==0){
			products.add(product);
		}
		return status;
	}
	public static int checkExistOfProduct(ArrayList<Product> products, Product checkProduct){
		int status =0;
		for(Product one :products ){
			if(one.getProductId().equals(checkProduct.getProductId())) {
				status=-1;
				break;
			}
			else if(one.getProductName().equals(checkProduct.getProductName())){
				status=-2;
				break;
			}	
		}
		return status;
	}
	public static void removeProduct(ArrayList<Product> products, Product product){
		products.remove(product);	
	}
	public static String productIdGenerator(String categoryId){
		return categoryId + "/" + "12";
	}

	public static String barCodeGenerator(String categoryId){
		return categoryId + "/" + "12";
	}
		
	public static int editProduct(Product original,String productId, String productName, String briefDescription, Integer availableQuantity, Double price, String barCodeNumber, Integer reorderQuantity, String vendorId){
		
		if(productId!=null){
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
		return 0;
	}
	public static ArrayList<Product> readExistingProductFromDB(){
		ArrayList<Product>  products= new ArrayList<Product>();
		//get the products from the io
		ArrayList<Object>  objects= FileManangerUtils.readDataFromDatFile(Product.class);
		for(Object one: objects){
			products.add((Product)one);
		}

		return products;
	}
	public static ArrayList<Product> getAllProducts(){
		return readExistingProductFromDB();
	}
	public static ArrayList<Product> getProductsForCategory(ArrayList<Product> allProducts,String categoryId){
		ArrayList<Product>  products= new ArrayList<Product>();
		//get the products from categoryu
		for(Product one : allProducts){
			if(one.getCategoryId().equals(categoryId)){
				products.add(one);
			}
		}
		
		return products;
		
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
	
	public static boolean removeProduct(ArrayList<Product> products, String productId){
		boolean status = false;
		Product item =null;
		for(Product one : products){
			if(one.getProductId().equals(productId)){
				item = one;
				break;
			}
		}
		if(item!=null){
			products.remove(item);
			status = true;
		}
		return status;
	}
	
	

}
