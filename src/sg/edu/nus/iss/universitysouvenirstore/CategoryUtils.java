package sg.edu.nus.iss.universitysouvenirstore;

import java.util.ArrayList;
import java.util.Iterator;

public class CategoryUtils {
	private ArrayList<Category> categoryList;

	public CategoryUtils(){
		this.categoryList=new ArrayList<Category>();
	}

	public ArrayList<Category> getCategoryList(){
		return this.categoryList;
	}

	public Category getCategory(String categoryId){
		Iterator<Category> categoryIterate=this.categoryList.iterator();
		while(categoryIterate.hasNext()){
			Category c=categoryIterate.next();
			if(c.getCategoryId().equals(categoryId)){
				return c;
			}
		}
		return null;
	}
	public int getCategoryPosition(String categoryId){
		for(int i=0;i<this.categoryList.size();i++){
			if(this.categoryList.get(i).getCategoryId().equals(categoryId)){
				return i;
			}
		}
		return -1;
	}
	public Category addCategory(String id,String description){
		Category category=new Category(id,description);
		this.categoryList.add(category);
		return category;
	}
	public void replaceCategory(int position,Category c){
		this.categoryList.set(position, c);
	}
	public ArrayList<Category> removeCategory(String categoryId){
		Category c=this.getCategory(categoryId);
		if(c!=null){
			this.categoryList.remove(c);
		}
		return this.categoryList;
	}

	public ArrayList<Product> getProductListByCategory(String categoryId){
		Iterator<Product> productIterate=ProductUtils.getAllProducts().iterator();
		ArrayList<Product> resultProduct=new ArrayList<Product>();
		while(productIterate.hasNext()){
			Product p=productIterate.next();
			String categoryCode=p.getCategoryId().substring(0, 3);
			if(categoryCode.equals(categoryId)){
				resultProduct.add(p);
			}
		}
		return resultProduct;
	}
	public int getNewProductIdByCategory(String categoryId){
		ArrayList<Product> p=this.getProductListByCategory(categoryId);
		int largest=0;
		for(Product product:p){
			String splitString=product.getProductId().split("/");
			int splitProductId=Integer.parseInt(splitString[1]);
			if(largest=<splitProductId){
				largest=splitProductId;
			}
		}
		return largest+1;
	}
}
