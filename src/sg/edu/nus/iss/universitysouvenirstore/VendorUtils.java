/**
 * @author nyinyizin
 * @version 1.0
 */
package sg.edu.nus.iss.universitysouvenirstore;

import java.util.*;

public class VendorUtils {
	//private ArrayList<Vendor> vendorList;
	private CategoryVendorMgr vMgr;

	public VendorUtils(){
		//vendorList=new ArrayList<Vendor>();
		vMgr=new CategoryVendorMgr();
	}
	public ArrayList<Vendor> getVendorList(String categoryId){
		//this.vendorList=vMgr.vendorByCategoryList.get(categoryId);
		return this.vMgr.vendorByCategoryList.get(categoryId);
	}
	public Vendor getVendor(String vendorName,String categoryId){
		Iterator<Vendor> vIterate=this.vMgr.vendorByCategoryList.get(categoryId).iterator();
		while(vIterate.hasNext()){
			Vendor v=vIterate.next();
			if(v.getVendorName().equals(vendorName)){
				return v;
			}
		}
		return null;
	}
	public void setVendorList(ArrayList<Vendor> vlist,String categoryId){
		//this.vendorList=vlist;
		this.vMgr.vendorByCategoryList.put(categoryId, vlist);
	}
	public ArrayList<Vendor> addVendor(String vendorName,String vendorDescription,String categoryId)throws CustomException{
		if(getVendorPosition(vendorName, categoryId)!=-1){
			throw new CustomException("Already_Exist_Error");
		}
		Vendor v=new Vendor(vendorName,vendorDescription);
		this.vMgr.vendorByCategoryList.get(categoryId).add(v);
		this.vMgr.setVendorByCategoryUtils(categoryId, this.vMgr.vendorByCategoryList.get(categoryId));
		return this.vMgr.vendorByCategoryList.get(categoryId);
	}
	public int getVendorPosition(String vendorName,String categoryId){
		for(int i=0;i<this.vMgr.vendorByCategoryList.get(categoryId).size();i++){
			if(this.vMgr.vendorByCategoryList.get(categoryId).get(i).getVendorName().equals(vendorName)){
				return i;
			}
		}
		return -1;
	}
	public void replaceVendor(int position,Vendor v,String categoryId) throws CustomException{
		if(getVendorPosition(v.getVendorName(), categoryId)!=-1 && getVendorPosition(v.getVendorName(),categoryId)!=position){
			throw new CustomException("Already_Exist_Error");
		}
		this.vMgr.vendorByCategoryList.get(categoryId).set(position, v);
		this.vMgr.vendorByCategoryList.put(categoryId, this.vMgr.vendorByCategoryList.get(categoryId));
		this.vMgr.setVendorByCategoryUtils(categoryId, this.vMgr.vendorByCategoryList.get(categoryId));
	}
	public void removeVendor(String vendorName,String categoryId){
		Vendor v=this.getVendor(vendorName,categoryId);
		if(v!=null){
			this.vMgr.vendorByCategoryList.get(categoryId).remove(v);
			this.vMgr.vendorByCategoryList.put(categoryId, this.vMgr.vendorByCategoryList.get(categoryId));
			this.vMgr.setVendorByCategoryUtils(categoryId, this.vMgr.vendorByCategoryList.get(categoryId));
		}
	}
}
