package sg.edu.nus.iss.universitysouvenirstore;

import java.util.ArrayList;

public class CategoryVendorMgr {
	//Dump Data
	public ArrayList<Category> categoryList=new ArrayList<Category>();
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
	public CategoryUtils getCategoryUtil() throws CustomException{
		//return new CategoryUtils();
		CategoryUtils cUtils=new CategoryUtils();
		ArrayList<Object> dbData=FileManagerUtils.readDataFromDatFile(Category.class);
		for (Object cItem : dbData) {
			Category c=(Category) cItem;
			cUtils.addCategory(c.getCategoryId(), c.getCategoryDescription());
		}
		return cUtils;
	}
	// Write Category
	public void setCategoryUtils(ArrayList<Category> cList){
		FileManagerUtils.saveDataToDatFile(Category.class,cList);
	}
}
