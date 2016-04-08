/**
 * @author Nyi Nyi Zin
 * @version 1.0
 */
package sg.edu.nus.iss.universitysouvenirstore;

import java.util.ArrayList;
import java.util.Iterator;

public class CategoryUtils {
	private ArrayList<Category> categoryList;
	private CategoryVendorMgr cvMgr=new CategoryVendorMgr();
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
	public void setCategoryList(ArrayList<Category> clist){
		this.categoryList=clist;
	}
	public Category addCategory(String id,String description) throws CustomException{
		if(!id.matches("[A-Z]{3}")){
			throw new CustomException("Category_Code_Error");
		}
		if(getCategoryPosition(id)!=-1){
			throw new CustomException("Already_Exist_Error");
		}
		Category category=new Category(id,description);
		this.categoryList.add(category);
		cvMgr.setCategoryUtils(this.categoryList);
		cvMgr.createNewVendorFile("Vendors"+category.getCategoryId());
		return category;
	}
	public void replaceCategory(int position,Category c) throws CustomException{
		if(getCategoryPosition(c.getCategoryId())!=-1 && getCategoryPosition(c.getCategoryId())!=position){
			throw new CustomException("Already_Exist_Error");
		}
		this.categoryList.set(position, c);
		cvMgr.setCategoryUtils(this.categoryList);
	}
	public ArrayList<Category> removeCategory(String categoryId){
		Category c=this.getCategory(categoryId);
		if(c!=null){
			this.categoryList.remove(c);
		}
		cvMgr.setCategoryUtils(this.categoryList);
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
	public int getLargestNumByCategory(String categoryId){
		ArrayList<Product> p=this.getProductListByCategory(categoryId);
		int largest=0;
		for(Product product:p){
			String[] splitString=product.getProductId().split("/");
			int splitProductId=Integer.parseInt(splitString[1]);
			if(largest<=splitProductId){
				largest=splitProductId;
			}
		}
		return largest+1;
	}
}
