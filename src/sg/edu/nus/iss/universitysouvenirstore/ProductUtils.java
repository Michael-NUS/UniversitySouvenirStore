/**
 * linwei
 */
package sg.edu.nus.iss.universitysouvenirstore;

import java.util.ArrayList;

import javax.swing.JDialog;

import sg.edu.nus.iss.universitysouvenirstore.gui.ProductReorderDialog;

public class ProductUtils {
	

	public static int addNewProduct(ArrayList<Product> products, String productId, String productName, String briefDesc, int availableQuantity, double price,  int reorderQuantity, int reorderLevel ){
		String []temp = productId.split("/");
		Product product = new Product(productId,productName,  briefDesc,  availableQuantity,  price,  reorderLevel, temp[0],reorderQuantity) ;
		int status = checkExistOfProduct(products, product);
		if(status ==0){
			products.add(product);
		}
		return status;
	}
	public static int checkExistOfProduct(ArrayList<Product> products, Product checkProduct){
		int status =0;
	
		for(Product one :products ){
			if(one.getAvailableQuantity()>-1){
				if(one.getProductId().equals(checkProduct.getProductId())) {
					status=-1;
					break;
				}
				else if(one.getProductName().equals(checkProduct.getProductName())){
					status=-2;
					break;
				}
			}
		}
		return status;
	}

	public static String productIdGenerator(String categoryId){
		CategoryUtils  utils = new CategoryUtils();
		int largest = utils.getLargestNumByCategory(categoryId);
		return categoryId + "/" + largest;
	}

	public static String barCodeGenerator(String categoryId){
		return categoryId + "/" + "12";
	}
		
	public static int editProduct(Product original,String productId, String productName, String briefDescription, Integer availableQuantity, Double price, Integer reorderLevel, Integer reorderQuantity){
		
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
	
		if(reorderQuantity!=null){
			original.setReorderQuantity(reorderQuantity);
		}
		if(reorderLevel!=null){
			original.setReorderLevel(reorderLevel);
		}
	
		return 0;
	}
	public static ArrayList<Product> readExistingProductFromDB(){
		ArrayList<Product>  products= new ArrayList<Product>();
		//get the products from the io
		ArrayList<Object>  objects= FileManagerUtils.readDataFromDatFile(Product.class);
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

	
	public static ArrayList<Product> getReorderProductList(ArrayList<Product> products){
		ArrayList<Product> reorderlist = new ArrayList<Product>();
		for(Product product: products ){
			if(product.getAvailableQuantity()< product.getReorderLevel() && product.getAvailableQuantity()>-1){
				reorderlist.add(product);
			}
		}
		return reorderlist;
	}
	public static boolean  updateTransctionQuantity(ArrayList<Product> products,ArrayList<TransactionedItem> items){
		ArrayList<Product> reOrderProducts= new ArrayList<Product>();
		for(Product product: products ){
			for( TransactionedItem item:items){
				if(item.GetProductID().equals(product.getProductId())){
					product.updateAvaliableQuantity(item.GetProductQuantity(),1);
					if(product.getAvailableQuantity()< product.getReorderLevel() && product.getAvailableQuantity()>-1){
						reOrderProducts.add(product);
					}
					break;
				}
			}
		}
		
		FileManagerUtils.saveDataToDatFile(Product.class, products);
		ProductReorderDialog productRorderDialog = new ProductReorderDialog();
		if(reOrderProducts.size()>0){
			productRorderDialog.setAllProducts(products);
			productRorderDialog.setReorderProductList(reOrderProducts);
			productRorderDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			productRorderDialog.setEnabled(true);
			productRorderDialog.setVisible(true);
			productRorderDialog.dataInit();
		}
		
		
		
		
		return false ;
	}

	public static boolean removeProduct(ArrayList<Product> products, String productId){
		boolean status = false;
		for(Product one : products){
			if(one.getProductId().equals(productId)){
				one.setAvailableQuantity(-1);
				status = true;
				break;
			}
		}
		return status;
	}
	
	

}
