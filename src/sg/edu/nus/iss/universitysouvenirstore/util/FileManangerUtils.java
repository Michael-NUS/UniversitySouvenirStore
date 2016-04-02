/**
 * linwei
 */
package sg.edu.nus.iss.universitysouvenirstore.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.universitysouvenirstore.Product;

public class FileManangerUtils {
	public static <T> boolean saveDataToDatFile(Object type, ArrayList<T> dataList){
		String filePath ="./data/sg/edu/nus/iss/universitysouvenirstore/data";
		File dataFile = null;
		if(type.toString().contains("Product")){
			filePath +="/Products.dat";
		}
		dataFile = new File(filePath);
		BufferedWriter bw = null;
		
		 try{
			
			 bw = new BufferedWriter(new FileWriter(dataFile, false));
			
			for (T one : dataList) {
				if (type.toString().contains("Product")) {
					Product item = (Product) one;
					bw.write(item.getProductId() + "," + item.getProductName() + "," + item.getBriefDescription() + ","
							+ item.getAvailableQuantity() + "," + item.getPrice() + "," + item.getBarCodeNumber() + ","
							+ item.getReorderLevel() + "," + item.getReorderQuantity() + "\r\n");
								
				}
			}
			 bw.flush();	  
		 } catch (IOException ioe) {
	    	  
	    	  ioe.printStackTrace();
	    	  return false;
	      } finally { 
	    	  
		     if (bw != null) try {
		    	 
		        bw.close();/* always close the file */
		        return true;
		        
		     } catch (IOException ioe3) {
		    	 
		    	 ioe3.printStackTrace();
		    	 return false; 
		     }
	      }
		return false;
	}

	
	public static ArrayList<Object> readDataFromDatFile( Object type){
		ArrayList <Object> dataList = new ArrayList<Object>();
		String filePath ="./data/sg/edu/nus/iss/universitysouvenirstore/data";
		File dataFile = null;

		if(type.toString().contains("Product")){
			filePath +="/Products.dat";
			dataFile = new File(filePath);
		}

		// read each line
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(dataFile.toString()));
			while ((line = br.readLine()) != null) {
				String []data= line.split(",");
				if (type.toString().contains("Product")) {
					if(data.length == 8 ){
						String [] tmp = data[0].trim().split("/");
						Product one = new Product(data[0].trim(),data[1].trim(),data[2].trim(),Integer.valueOf(data[3].trim()), Double.valueOf(data[4].trim()),Integer.valueOf(data[6].trim()),tmp[0].trim() ,Integer.valueOf(data[7].trim()));
						dataList.add((Object) one);
					}
				}
			}
			br.close();

		} catch (IOException e) {

			e.printStackTrace();
			return null;
		}
		return dataList;
	}
}
