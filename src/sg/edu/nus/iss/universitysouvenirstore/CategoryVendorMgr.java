package sg.edu.nus.iss.universitysouvenirstore;

import java.util.ArrayList;

public class CategoryVendorMgr {
	//Dump Data
	public ArrayList<Category> dumbData(){
		ArrayList<Category> c=new ArrayList<Category>();
		c.add(new Category("CLO","Clothing"));
		c.add(new Category("MUG","Mugs"));
		c.add(new Category("STA","Stationary"));
		c.add(new Category("DIA","Diary"));
		return c;
	}
	// Dump Vendor Data
	public ArrayList<Vendor> dumbVendor(String categoryId){
		ArrayList<Vendor> c=new ArrayList<Vendor>();
		if(categoryId.equals("CLO")){
			c.add(new Vendor("Nancy","Nancy's Design"));
			c.add(new Vendor("Marcy","Marcy's Design"));
			c.add(new Vendor("Union","Union Selection Group"));
			return c;
		}else if(categoryId.equals("MUG")){
			c.add(new Vendor("Muggy","Muggy Mugs"));
			c.add(new Vendor("Naggy","Naggy Nags"));
			c.add(new Vendor("Vaggy","Vaggy Vags"));
			return c;
		}
		return c;
	}
	// Read & Return Vendor List Specify By Category
	public VendorUtils getVendorByCategory(String categoryId){
		VendorUtils v=new VendorUtils();
		for(Vendor vendor:this.dumbVendor(categoryId)){
			v.addVendor(vendor.getVendorName(), vendor.getVendorDecription());
		}
		return v;
	}
	// Write Vendor List By Category
	public void setVendorByCategory(String categoryId,VendorUtils vendorUtils){
		
	}
	// Read Category
	public CategoryUtils getCategoryUtil(){
		//return new CategoryUtils();
		CategoryUtils c=new CategoryUtils();
		for(Category category:this.dumbData()){
			c.addCategory(category.getCategoryId(), category.getCategoryDescription());
		}
		return c;
	}
	// Write Category
	public void setCategoryUtils(){
		
	}
}
