/**
 * @author nyinyizin
 * @version 0.1
 */
package sg.edu.nus.iss.universitysouvenirstore;

import java.util.ArrayList;
import java.util.HashMap;

public class CategoryVendorMgr {
	public ArrayList<Category> categoryList=new ArrayList<Category>();
	public HashMap<String,ArrayList<Vendor>> vendorByCategoryList=new HashMap<String,ArrayList<Vendor>>();

	/**
	 * Read Vendor List related to Category Code
	 * @param categoryId Category Code
	 * @return 	 {@link VendorUtils}
	 */
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

	/**
	 * Read Category List
	 * @return Category Array List
	 * @throws CustomException 
	 */
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
	
	/**
	 * Save Category List to File or other data sources
	 * @param cList Category List
	 */
	public void setCategoryUtils(ArrayList<Category> cList){
		FileManagerUtils.saveDataToDatFile(Category.class,cList);
	}
	
	/**
	 * Save Vendor List related to provided Category Code
	 * @param categoryId Category Code
	 * @param vList Vendor List
	 */
	public void setVendorByCategoryUtils(String categoryId,ArrayList<Vendor> vList){
		ArrayList<String> stringList=new ArrayList<String>();
		for(Vendor v:vList){
			stringList.add(v.toString());
		}
		FileManagerUtils.AppendDataToFile("Vendors"+categoryId, "vendors", stringList);
	}
	
	/**
	 * Create Vendor File by calling {@link FileManagerUtils}
	 * @param fileName Name of Vendor File
	 */
	public void createNewVendorFile(String fileName){
		FileManagerUtils.CreateFile(fileName);
	}
}
