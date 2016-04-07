/**
 * File name : Storekeeper.java
 * 
 * Description : Implementation of Storekeeper class
 *               which inherits from Customer class 
 *               because Storekeeper can be a customer at some point.
 * 
 * @author : NayLA 
 * 
 * Date : 10/03/2016
 * 
 */

package sg.edu.nus.iss.universitysouvenirstore.StorekeeperTestCases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storekeeper extends Customer{
	
	private String name;
	private String password;
	private Boolean right;
	
	private File dir = new File("./data/sg/edu/nus/iss/universitysouvenirstore/data");/* Instantiation of directory object*/
	private File filePath = new File(dir ,"Storekeepers.dat");/* Instantiation of file object*/

	/**
	 *  Constructor for Storekeeper class
	 */	
	public Storekeeper(){
		
		this.name = null;
		this.password = null;
		this.right = null;
	}
	
	/**
	 * Set user's right for instance of Storekeeper.
	 * @param right Storekeeper's access right
	 *  
	 */
	public void setRightForStorekeeper(Boolean right){
		
		this.right = right;
	}
	
	/**
	 * Get and return user's right of instance of Storekeeper.
	 * @return  Return user's right.
	 */
	public Boolean getRightForStorekeeper(){
			
		return this.right;
	}
	
	/**
	 * Set user name and password for instance of Storekeeper.
	 *  @param name  Set user name
	 *  @param password  Set password
	 */
	public void setUsernameAndPassword(String name ,String password){
		
		this.name =name;
		this.password = password;		
	}
	
	/**
	 * Get and return name of instance of Storekeeper.
	 * @return  Return name
	 */
	public String getName(){
		
		return this.name;
	}
	
	/**
	 * Get and return password of instance of Storekeeper.
	 * @return  Return password
	 */
	public String getPassword(){
		
		return this.password;
	}
	/**
	 * Check whether data file already exists or not , and 
	 * update Storekeeper.dat file at specified location and return write status.
	 * @return  Return file write status.
	 */
	public Boolean updateStoreKeeperInfo(){
		 
	      try {
	    	  
	    	  File dir = new File("./data");/* Instantiation of directory object*/
	    	  File file = new File(dir ,"Storekeepers.dat");/* Instantiation of file object*/
	    	  
	    	  if(!dir.exists()){
	    		  
	    		/* Create directory now. */
	    		  dir.mkdirs();
	    		  
	    		  if (! filePath.exists( ) )
		          {
		    		  /* Create new file now. */
	    			  filePath.createNewFile( );		              
		              /* Write file at the specified path.*/
		              FileWrite(filePath);		              					     
					  return true;	              
		              
		          }else{
		    	  		       
		        	  FileWrite(filePath);		        		        	  
				      return true;
		          }	    	
	    		  
	    	  }else{
	    	      	  
		    	  if (! filePath.exists( ) )
		          {
		    		  /* Create new file now. */
		    		  filePath.createNewFile( );
		              /* Write file at the specified path.*/
		              FileWrite(filePath);		              				     
					  return true;	              
		              
		          }else{
		    	  			   
		        	  /* Write file at the specified path.*/
		        	  FileWrite(filePath);	        	  
				      return true;
		          }
	    	  }
		     
	      } catch (IOException ioe) {
	    	  
	    	  ioe.printStackTrace();	    	  
	    	  return false;
	    	  
	      } finally { 
	    	  
		    /* Do nothing for now. */
	      } 
		
	}
	
	/** 
	 * Input parameter as file path. 
	 * @param file  File path for writing.
	 * @return  Return file write status.
	 */
	public Boolean FileWrite(File file){
		
		 BufferedWriter bw = null;
		
		 try{
			
			 bw = new BufferedWriter(new FileWriter(file, true));
			 bw.write(this.name +","+this.password +"\r\n");/* Replace with text entered from textField.*/
			 bw.flush();
			 		  
			 return true;
		  
		 } catch (IOException ioe) {
	    	  
	    	  ioe.printStackTrace();
	    	  
	    	  return false;
	    	  
	      } finally { 
	    	  
		     if (bw != null) try {
		    	 
		        bw.close();/* always close the file */
		        return true;
		        
		     } catch (IOException ioe3) {
		    	 
		    	 ioe3.printStackTrace();
		     }
	    } 
	}
	
	/**
	 * Read data file from specified path and return as ArrayList.
	 * (filePath as parameter)
	 * @param filePath  File path for reading
	 * @return   Return file read data as ArrayList.
	 * @throws java.io.IOException  Throw IO exception.
	 */
	public ArrayList<String> FileRead(File filePath)throws IOException{
		
		String line = new String();
		ArrayList<String>list =new ArrayList<String> ();
		
		try {			
			BufferedReader br = new BufferedReader(new FileReader(filePath.toString()));
		    
		    while((line = br.readLine()) != null) {
	  	    	  
		    	  list.add(line);/* Add the line (which was read) into the list.*/	              	    
		      }
		      
		    br.close();
		      
			return list;
			
		} catch (IOException e) {
	    	
	        e.printStackTrace();
	    }
				
		return list;
	}
	
	/**
	 * Read data file from specified path and return as ArrayList.
	 * @return  Read Storekeeper's file read data as ArrayList.
	 * @throws java.io.IOException  Throw IO exception.
	 */
	public ArrayList<String> readStorekeepersFile() throws IOException{	
		String line = new String();
		ArrayList<String>list =new ArrayList<String> ();
			
		try {
		     
	      BufferedReader in = new BufferedReader(new FileReader(filePath));
	      	      
	      while((line = in.readLine()) != null) {
	    	  	    	  
	    	  list.add(line);/* Add the line (which was read) into the list.*/	              	    
	      }
	      
	      in.close();
	        
	    } catch (IOException e) {
	    	
	        e.printStackTrace();
	    }
		
		return list;
	}
	
	/**
	 *  Check Storekeeper's information from the data file.
	 *  @param usrname  Name to be checked.
	 *  @param pw       Password to be checked.
	 *  @return         Return status in checking whether it is correct user or not.
	 *  @throws java.io.IOException   Throw IO exception.
	 */
	public Boolean checkUserInfo(String usrname,String pw) throws IOException
	{		
		String userInfo = usrname + "," + pw;/* Combine both textFields and add "," in between.*/
		
		ArrayList<String> listRead = readStorekeepersFile();
		
		/* Check whether userInfo is in the list. */
		if(listRead.contains(userInfo))
		{
			return true;
		}
		else{
						
			return false;
		}
			
	}
	
	/**
	 * Remove Storekeeper from the data file.
	 * @param usrname  Name to be removed.
	 *  @return        Return file removal status.
	 */
	public Boolean removeStoreKeeper(String usrname){
		
		String userInfo = usrname;
		ArrayList<String> listRead = new ArrayList<String>();
		
		try{
		    /* Read all from data file into array-list. */
			listRead = readStorekeepersFile();
		
		}catch (IOException e) {
	    	
	        e.printStackTrace();
	    }
			
		int index = 0;  
		int IndexToBeRemoved = 0;
		Boolean UserExistNotExist = false;
		Boolean FileRemovalStatus = false;
		String RemovedUser = null;
		int listsize = listRead.size();
				
	    /* Loop the whole list and check whether userInfo is in the list.*/      
	    for (String name : listRead) {
	            		    
	    	/* Compare list's element with userInfo. */
	        if(name.contains(userInfo)){
	        	    
	        	IndexToBeRemoved = index;
	        	UserExistNotExist = true;
	        }
	        
	        index++;
	    }
			
	    if(UserExistNotExist == true){
	    		    
		    /* Remove list-element at the specified index and return removed element. */
		    RemovedUser = listRead.remove(IndexToBeRemoved);
		    
		    if(RemovedUser.contains(userInfo))
		    {	    
		  	    try{
		  	    		    	  	    	
				    File tmp = File.createTempFile("tmp", "");
				    BufferedReader br = new BufferedReader(new FileReader(filePath));
				    BufferedWriter bw = new BufferedWriter(new FileWriter(tmp));
				    
				    for (int i = 0; i < IndexToBeRemoved; i++)
				        bw.write(String.format("%s%n", br.readLine()));
		
				    br.readLine();
				    
				    String lineRead;
				    while (null != (lineRead = br.readLine()))
				        bw.write(String.format("%s%n", lineRead));
		
				    br.close();
				    bw.close();
				    
				    File oldFile = filePath;
				    if (oldFile.delete())
				        tmp.renameTo(oldFile);
				    
				    FileRemovalStatus = true;
			    
		  	    } catch (IOException e) {
			    	
			        e.printStackTrace();
			    }
	  	    
			}
	   			
	    }else{
	    	
	    	FileRemovalStatus = false;
	    }
	    
	    return FileRemovalStatus;
	}
		
}
