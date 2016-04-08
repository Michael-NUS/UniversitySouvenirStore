/**
 * @author nyinyizin
 * @version 0.1
 */
package sg.edu.nus.iss.universitysouvenirstore;

import java.util.ArrayList;
import java.util.HashMap;

public class CategoryVendorMgr {
	//Dump Data
	public ArrayList<Category> categoryList=new ArrayList<Category>();
	public HashMap<String,ArrayList<Vendor>> vendorByCategoryList=new HashMap<String,ArrayList<Vendor>>();
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
		VendorUtils vUtils=new VendorUtils();
		ArrayList<Object> dbData=FileManagerUtils.ReadDataFromDatFile("Vendors"+categoryId, "vendors");
		ArrayList<Vendor> vArrayList=new ArrayList<Vendor>();
		for(Object vendor:dbData){
			Vendor v=(Vendor) vendor;
			vArrayList.add(new Vendor(v.getVendorName(), v.getVendorDecription()));
		}
		vUtils.setVendorList(vArrayList,categoryId);
		this.vendorByCategoryList.put(categoryId, vUtils.getVendorList(categoryId));
		return vUtils;
	}
	// Write Vendor List By Category
	public void setVendorByCategory(String categoryId,VendorUtils vendorUtils){

	}
	// Read Category
	public CategoryUtils getCategoryUtil() throws CustomException{
		//return new CategoryUtils();
		CategoryUtils cUtils=new CategoryUtils();
		ArrayList<Object> dbData=FileManagerUtils.readDataFromDatFile(Category.class);
		ArrayList<Category> cArrayList=new ArrayList<Category>();
		for (Object cItem : dbData) {
			Category c=(Category) cItem;
			cArrayList.add(new Category(c.getCategoryId(),c.getCategoryDescription()));
		}
		cUtils.setCategoryList(cArrayList);
		return cUtils;
	}
	// Write Category
	public void setCategoryUtils(ArrayList<Category> cList){
		FileManagerUtils.saveDataToDatFile(Category.class,cList);
	}
	public void setVendorByCategoryUtils(String categoryId,ArrayList<Vendor> vList){
		ArrayList<String> stringList=new ArrayList<String>();
		for(Vendor v:vList){
			stringList.add(v.toString());
		}
		FileManagerUtils.AppendDataToFile("Vendors"+categoryId, "vendors", stringList);
	}
	public void createNewVendorFile(String fileName){
		FileManagerUtils.CreateFile(fileName);
	}
}
