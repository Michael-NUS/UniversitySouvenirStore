/**
 * linwei
 */
package sg.edu.nus.iss.universitysouvenirstore;

import java.util.ArrayList;

import javax.swing.JDialog;

import sg.edu.nus.iss.universitysouvenirstore.gui.ProductReorderDialog;

public class ProductUtils {
	
	/**
	 * add new product to the arraylist
	 * @param products  : the list of product 
	 * @param productId : productId 
	 * @param productName: productName
	 * @param briefDesc: briefDesc
	 * @param availableQuantity:availableQuantity
	 * @param price:price
	 * @param reorderQuantity: reorderQuantity
	 * @param reorderLevel:reorderLevel 
	 * @return integer : -1 : same id, -2: same product name
	 */
	public static int addNewProduct(ArrayList<Product> products, String productId, String productName, String briefDesc, int availableQuantity, double price,  int reorderQuantity, int reorderLevel ){
		String []temp = productId.split("/");
		Product product = new Product(productId,productName,  briefDesc,  availableQuantity,  price,  reorderLevel, temp[0],reorderQuantity) ;
		int status = checkExistOfProduct(products, product);
		if(status ==0){
			products.add(product);
		}
		return status;
	}
	/**
	 * check the product is existed or not , check the id and productname
	 * @param products
	 * @param checkProduct
	 * @return
	 */
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

	/**
	 * gereate the product id by category 
	 * @param categoryId
	 * @return product id 
	 */
	public static String productIdGenerator(String categoryId){
		CategoryUtils  utils = new CategoryUtils();
		int largest = utils.getLargestNumByCategory(categoryId);
		return categoryId + "/" + largest;
	}

	/** 
	 * edit the product 
	 * @param original
	 * @param productId
	 * @param productName
	 * @param briefDescription
	 * @param availableQuantity
	 * @param price
	 * @param reorderLevel
	 * @param reorderQuantity
	 * @return
	 */
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
	/**
	 * read existing product form the product.dat file 
	 * @return
	 */
	private static ArrayList<Product> readExistingProductFromDB(){
		ArrayList<Product>  products= new ArrayList<Product>();
		//get the products from the io
		ArrayList<Object>  objects= FileManagerUtils.readDataFromDatFile(Product.class);
		for(Object one: objects){
			products.add((Product)one);
		}

		return products;
	}
	/**
	 * get all products
	 * @return
	 */
	public static ArrayList<Product> getAllProducts(){
		return readExistingProductFromDB();
	}
	/**
	 * getProducts for Category
	 * @param allProducts
	 * @param categoryId
	 * @return arraylast of product 
	 */
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
	/**
	 * get product by id 
	 * @param products
	 * @param productId
	 * @return product
	 */
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

	/**
	 * get products that need to be reorder 
	 * @param products
	 * @return ArrayList product
	 */
	public static ArrayList<Product> getReorderProductList(ArrayList<Product> products){
		ArrayList<Product> reorderlist = new ArrayList<Product>();
		for(Product product: products ){
			if(product.getAvailableQuantity()< product.getReorderLevel() && product.getAvailableQuantity()>-1){
				reorderlist.add(product);
			}
		}
		return reorderlist;
	}
	/**
	 * updateTransctionQuantity
	 * @param products:products
	 * @param items:items
	 * @return boolean
	 */
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

	/**
	 * delete the product from the product list 
	 * @param products
	 * @param productId
	 * @return
	 */
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
