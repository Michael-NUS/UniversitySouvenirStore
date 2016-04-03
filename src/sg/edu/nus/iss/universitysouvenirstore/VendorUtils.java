package sg.edu.nus.iss.universitysouvenirstore;

import java.util.*;

public class VendorUtils {
	private ArrayList<Vendor> vendorList;

	public VendorUtils(){
		vendorList=new ArrayList<Vendor>();
	}
	public ArrayList<Vendor> getVendorList(){
		return this.vendorList;
	}
	public Vendor getVendor(String vendorName){
		Iterator<Vendor> vIterate=this.vendorList.iterator();
		while(vIterate.hasNext()){
			Vendor v=vIterate.next();
			if(v.getVendorName().equals(vendorName)){
				return v;
			}
		}
		return null;
	}
	public ArrayList<Vendor> addVendor(String vendorName,String vendorDescription){
		Vendor v=new Vendor(vendorName,vendorDescription);
		this.vendorList.add(v);
		return this.vendorList;
	}
	public int getVendorPosition(String vendorName){
		for(int i=0;i<this.vendorList.size();i++){
			if(this.vendorList.get(i).getVendorName().equals(vendorName)){
				return i;
			}
		}
		return -1;
	}
	public void replaceVendor(int position,Vendor v){
		this.vendorList.set(position,v);
	}
	public void removeVendor(String vendorName){
		Vendor v=this.getVendor(vendorName);
		if(v!=null){
			this.vendorList.remove(v);
		}
	}
}
