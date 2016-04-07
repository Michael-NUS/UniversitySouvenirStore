/**
 * FileManagerUtils class for file read/write related functions
 */
package sg.edu.nus.iss.universitysouvenirstore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import sg.edu.nus.iss.universitysouvenirstore.Member;
import sg.edu.nus.iss.universitysouvenirstore.Product;

/**
 * @author 
 *
 */
public class FileManagerUtils {
	public static String fileDir = "./data/sg/edu/nus/iss/universitysouvenirstore/data";
	public static <T> boolean saveDataToDatFile(Object type, ArrayList<T> dataList) {
		String filePath = "";
		File dataFile = null;
		if (type.toString().contains("Product")) {
			filePath = fileDir + "/Products.dat";
		}

		else if (type.toString().contains("Member")) {
			filePath =fileDir + "/Members.dat";
		}else if(type.toString().contains("Discount")){
			filePath=fileDir+"/Discounts.dat";
		}else if(type.toString().contains("Category")){
			filePath=fileDir+"/Category.dat";
		}

		dataFile = new File(filePath);
		BufferedWriter bw = null;

		try {

			bw = new BufferedWriter(new FileWriter(dataFile, false));

			for (T one : dataList) {
				if (type.toString().contains("Product")) {
					Product item = (Product) one;
					bw.write(item.getProductId() + "," + item.getProductName() + "," + item.getBriefDescription() + ","
							+ item.getAvailableQuantity() + "," + item.getPrice() + "," + item.getBarCodeNumber() + ","
							+ item.getReorderLevel() + "," + item.getReorderQuantity() + "\r\n");

				}
				else if (type.toString().contains("Member")) {
					Member tmpMember = (Member) one;
					bw.write(tmpMember.getName() + "," + tmpMember.getID() + ","
							+ tmpMember.getLoyaltyPoint()
							+ "\r\n");/*
										 * Replace with text entered from
										 * textField.
										 */
				}
				else if (type.toString().contains("Discount")) {
					Discount tmpDiscount = (Discount) one;
					bw.write(tmpDiscount.getCode() + "," + tmpDiscount.getDescription() + ","
							+ tmpDiscount.getStartDate() + "," + tmpDiscount.getPeriod() + ","
							+ tmpDiscount.getPercentage() + "," + tmpDiscount.getType() + "\r\n");
										
				}else if(type.toString().contains("Category")){
					Category tmpCategory=(Category) one;
					bw.write(tmpCategory.getCategoryId()+","+tmpCategory.getCategoryDescription()+"\r\n");
				}
			}
			bw.flush();
		} catch (IOException ioe) {

			ioe.printStackTrace();
			return false;
		} finally {

			if (bw != null)
				try {

					bw.close();/* always close the file */
					return true;

				} catch (IOException ioe3) {

					ioe3.printStackTrace();
					return false;
				}
		}
		return false;
	}

	public static ArrayList<Object> readDataFromDatFile(Object type) {
		ArrayList<Object> dataList = new ArrayList<Object>();
		String filePath = "";
		File dataFile = null;

		if (type.toString().contains("Product")) {
			filePath =fileDir+ "/Products.dat";

		} else if (type.toString().contains("Transaction")) {
			filePath =fileDir+ "/Transactions.dat";
		} else if (type.toString().contains("Member")) {
			filePath =fileDir+ "/Members.dat";
		}else if(type.toString().contains("Category")){
			filePath=fileDir+"/Category.dat";
		}

		dataFile = new File(filePath);
		// read each line
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(dataFile.toString()));
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				if (type.toString().contains("Product")) {
					if (data.length == 8) {
						String[] tmp = data[0].trim().split("/");
						Product one = new Product(data[0].trim(), data[1].trim(), data[2].trim(),
								Integer.valueOf(data[3].trim()), Double.valueOf(data[4].trim()),
								Integer.valueOf(data[6].trim()), tmp[0].trim(), Integer.valueOf(data[7].trim()));
						dataList.add((Object) one);
					}
				} else if (type.toString().contains("Transaction")) {
					if (data.length == 5) {
						dataList.add(data[0]);
					}
				} else if (type.toString().contains("Member")) {
					if (data.length == 3) {
						Member tmpMember = new Member(data[0], data[1], Integer.valueOf(data[2]));
						dataList.add(tmpMember);
					}
				}else if(type.toString().contains("Category")){
					if(data.length==2){
						Category tmpCategory=new Category(data[0],data[1]);
						dataList.add(tmpCategory);
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

	/**
	 * Read data from .dat file 
	 *
	 */
	public static ArrayList<Object> ReadDataFromDatFile(String strFileName, String strFileType) {
		ArrayList<Object> dataList = new ArrayList<Object>();
		String filePath = "";
		File dataFile = null;
		try {
			//filePath = "./data/sg/edu/nus/iss/universitysouvenirstore/data/"; // to
																				// put
																				// under
																				// config
			if (!strFileName.isEmpty()) {
				filePath =fileDir+ "/"+strFileName + ".dat";
				dataFile = new File(filePath);
				if (dataFile.exists() && !dataFile.isDirectory()) {
					// read file
					String line = "";
					int intFileColumnCount = 0;
					BufferedReader br = new BufferedReader(new FileReader(dataFile.toString()));
					if ((br.ready())) {
						// read lines
						while ((line = br.readLine()) != null) {
							String[] data = line.split(",");
							intFileColumnCount = data.length;
							if (intFileColumnCount > 0) {
								switch (strFileType.toLowerCase()) {
								case "products":
									if (intFileColumnCount != 8) {
										// throw 'invalid product file format'
										// exception
									}else{
										String[] tmp = data[0].trim().split("/");
										Product one = new Product(data[0].trim(), data[1].trim(), data[2].trim(),
												Integer.valueOf(data[3].trim()), Double.valueOf(data[4].trim()),
												Integer.valueOf(data[6].trim()), tmp[0].trim(), Integer.valueOf(data[7].trim()));
										dataList.add((Object) one);
									}
									break;
								case "transactions":
									if (intFileColumnCount != 5) {
										// throw 'invalid transaction file
										// format' exception
									}else{
										dataList.add(data[0]);
									}
									break;
								case "members":
									if (intFileColumnCount != 3) {
										// throw 'invalid member file
										// format' exception
									}else{
										Member tmpMember = new Member(data[0], data[1], Integer.valueOf(data[2]));
										dataList.add(tmpMember);
									}
									break;
								case "discounts":
									if (intFileColumnCount != 6) {
										// throw 'invalid member file
										// format' exception
									}else{
										Discount tmpDiscount = new Discount(data[0], data[1], data[2], data[3], Integer.valueOf(data[4]), data[5]);
										dataList.add(tmpDiscount);
									}
									break;
								default:
									// throw 'invalid file type or file format'
									// exception
									break;
								}
								
							} else {
								// throw 'invalid file format' exception
							}
						}
					} else {
						// throw 'empty file' exception
					}

					br.close();
				} else {
					// throw 'file/directory not exist' exception
				}
			} else {
				// throw 'Invalid file name' exception
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// Do garbage collection
		}
		return dataList;
	}
	
	/**
	 * Write/Append a line of data to .dat file 
	 *
	 */
	public static Boolean AppendDataToFile(String strFileName, String strFileType, ArrayList<String> arrLstData){
		String filePath = "";
		File dataFile = null;
		try {
			//filePath = "./data/sg/edu/nus/iss/universitysouvenirstore/data/"; // to put under config																
			if (!strFileName.isEmpty()) {
				filePath =fileDir+ "/"+strFileName + ".dat";
				dataFile = new File(filePath);
				if (dataFile.exists() && !dataFile.isDirectory()) {
					int intFileColumnCount = 0;
					intFileColumnCount = arrLstData.get(0).split(",").length;
					if (intFileColumnCount > 0) {
						switch (strFileType.toLowerCase()) {
						case "products":
							if (intFileColumnCount != 8) {
								// throw 'invalid product file format'
								// exception
							}else{
								// write/append to the file
								for (int i = 0; i < arrLstData.size(); i++) 
						        {
						        	System.out.println(arrLstData.get(i));
						        	if(i == 0){
							        	Files.write(Paths.get(filePath),  arrLstData.get(i).getBytes(), StandardOpenOption.APPEND);

						        	}else{
							        	Files.write(Paths.get(filePath),  ("," + arrLstData.get(i)).getBytes(), StandardOpenOption.APPEND);
						        	}
						        }
								Files.write(Paths.get(filePath), "\r\n".getBytes(), StandardOpenOption.APPEND);
							}
							break;
						case "transactions":
							if (intFileColumnCount != 5) {
								// throw 'invalid transaction file
								// format' exception
							}else{
								// write/append to the file
								for (int i = 0; i < arrLstData.size(); i++) 
						        {
						        	System.out.println(arrLstData.get(i));
						        	if(i == 0){
							        	Files.write(Paths.get(filePath),  arrLstData.get(i).getBytes(), StandardOpenOption.APPEND);

						        	}else{
							        	Files.write(Paths.get(filePath),  ("\n" + arrLstData.get(i)).getBytes(), StandardOpenOption.APPEND);
						        	}
						        }
								Files.write(Paths.get(filePath), "\r\n".getBytes(), StandardOpenOption.APPEND);
							}
							break;
						case "members":
							if (intFileColumnCount != 3) {
								// throw 'invalid transaction file
								// format' exception
							}else{
								// write/append to the file
								for (int i = 0; i < arrLstData.size(); i++) 
						        {
						        	System.out.println(arrLstData.get(i));
						        	if(i == 0){
							        	Files.write(Paths.get(filePath),  arrLstData.get(i).getBytes(), StandardOpenOption.APPEND);

						        	}else{
							        	Files.write(Paths.get(filePath),  ("," + arrLstData.get(i)).getBytes(), StandardOpenOption.APPEND);
						        	}
						        }
								Files.write(Paths.get(filePath), "\r\n".getBytes(), StandardOpenOption.APPEND);
							}
							break;
						case "discounts":
							if (intFileColumnCount != 6) {
								// throw 'invalid transaction file
								// format' exception
							}else{
								// write/append to the file
								for (int i = 0; i < arrLstData.size(); i++) 
						        {
						        	System.out.println(arrLstData.get(i));
						        	if(i == 0){
							        	Files.write(Paths.get(filePath),  arrLstData.get(i).getBytes(), StandardOpenOption.APPEND);

						        	}else{
							        	Files.write(Paths.get(filePath),  ("," + arrLstData.get(i)).getBytes(), StandardOpenOption.APPEND);
						        	}
						        }
								Files.write(Paths.get(filePath), "\r\n".getBytes(), StandardOpenOption.APPEND);
							}
							break;
						case "vendors":
							if (intFileColumnCount != 2) {
								// throw 'invalid transaction file
								// format' exception
							}else{
								// write/append to the file
								for (int i = 0; i < arrLstData.size(); i++) 
						        {
						        	System.out.println(arrLstData.get(i));
						        	if(i == 0){
							        	Files.write(Paths.get(filePath),  arrLstData.get(i).getBytes(), StandardOpenOption.APPEND);

						        	}else{
							        	Files.write(Paths.get(filePath),  ("," + arrLstData.get(i)).getBytes(), StandardOpenOption.APPEND);
						        	}
						        }
								Files.write(Paths.get(filePath), "\r\n".getBytes(), StandardOpenOption.APPEND);
							}
							break;
						default:
							// throw 'invalid file type or file format'
							// exception
							break;
						}
						
					} else {
						// throw 'invalid file format' exception
					}
					
				} else {
					// throw 'file/directory not exist' exception
				}
			} else {
				// throw 'Invalid file name' exception
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			// Do garbage collection
		}
		return true;
	}

	/**
	 * Create a .dat file without data. 
	 *
	 */
	public static Boolean CreateFile(String strFileName){
		String filePath = "";
		File dataFile = null;
		try {
			//filePath = "./data/sg/edu/nus/iss/universitysouvenirstore/data/"; // to put under config																
			if (!strFileName.isEmpty()) {
				filePath =fileDir+ "/"+strFileName + ".dat";
				dataFile = new File(filePath);
				if (!dataFile.exists() && !dataFile.isDirectory()) {
					// Create file
					PrintWriter writer = new PrintWriter(dataFile, "UTF-8");
					writer.close();
					} else {
					// throw 'file already exists' exception
				}
			} else {
				// throw 'Invalid file name' exception
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			// Do garbage collection
		}
		return true;
	}
	/**
	 * Read transaction list from .dat file 
	 *
	 */
	public static ArrayList<String> getTransactionList(String strFromDate, String strToDate) {
		ArrayList<String> dataList = new ArrayList<String>();
		String filePath = "";
		File dataFile = null;
		try {
			
				filePath =fileDir+ "/"+ "Transactions" + ".dat";
				dataFile = new File(filePath);
				if (dataFile.exists() && !dataFile.isDirectory()) {
					// read file
					String line = "";
					int intFileColumnCount = 0;
					BufferedReader br = new BufferedReader(new FileReader(dataFile.toString()));
					if ((br.ready())) {
						// read lines
						while ((line = br.readLine()) != null) {
//							String[] data = line.split(",");
//							intFileColumnCount = data.length;
							if (line.length() > 0) {
								dataList.add(line);
							} else {
								// throw 'invalid file format' exception
							}
						}
					} else {
						// throw 'empty file' exception
					}

					br.close();
				} else {
					// throw 'file/directory not exist' exception
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// Do garbage collection
		}
		return dataList;
	}
	
	/**
	 * Read member list from .dat file 
	 *
	 */
	public static ArrayList<String> getMemberList() {
		ArrayList<String> dataList = new ArrayList<String>();
		String filePath = "";
		File dataFile = null;
		try {
			
				filePath =fileDir+ "/"+ "Members" + ".dat";
				dataFile = new File(filePath);
				if (dataFile.exists() && !dataFile.isDirectory()) {
					// read file
					String line = "";
					int intFileColumnCount = 0;
					BufferedReader br = new BufferedReader(new FileReader(dataFile.toString()));
					if ((br.ready())) {
						// read lines
						while ((line = br.readLine()) != null) {
//							String[] data = line.split(",");
//							intFileColumnCount = data.length;
							if (line.length() > 0) {
								dataList.add(line);
							} else {
								// throw 'invalid file format' exception
							}
						}
					} else {
						// throw 'empty file' exception
					}

					br.close();
				} else {
					// throw 'file/directory not exist' exception
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// Do garbage collection
		}
		return dataList;
	}
	
	/**
	 * Read category list from .dat file 
	 *
	 */
	public static ArrayList<String> getCategoryList() {
		ArrayList<String> dataList = new ArrayList<String>();
		String filePath = "";
		File dataFile = null;
		try {
			
				filePath =fileDir+ "/"+ "Category" + ".dat";
				dataFile = new File(filePath);
				if (dataFile.exists() && !dataFile.isDirectory()) {
					// read file
					String line = "";
					int intFileColumnCount = 0;
					BufferedReader br = new BufferedReader(new FileReader(dataFile.toString()));
					if ((br.ready())) {
						// read lines
						while ((line = br.readLine()) != null) {
//							String[] data = line.split(",");
//							intFileColumnCount = data.length;
							if (line.length() > 0) {
								dataList.add(line);
							} else {
								// throw 'invalid file format' exception
							}
						}
					} else {
						// throw 'empty file' exception
					}

					br.close();
				} else {
					// throw 'file/directory not exist' exception
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// Do garbage collection
		}
		return dataList;
	}
	
	/**
	 * Read product list from .dat file 
	 *
	 */
	public static ArrayList<String> getProductList() {
		ArrayList<String> dataList = new ArrayList<String>();
		String filePath = "";
		File dataFile = null;
		try {
			
				filePath =fileDir+ "/"+ "Products" + ".dat";
				dataFile = new File(filePath);
				if (dataFile.exists() && !dataFile.isDirectory()) {
					// read file
					String line = "";
					int intFileColumnCount = 0;
					BufferedReader br = new BufferedReader(new FileReader(dataFile.toString()));
					if ((br.ready())) {
						// read lines
						while ((line = br.readLine()) != null) {
//							String[] data = line.split(",");
//							intFileColumnCount = data.length;
							if (line.length() > 0) {
								dataList.add(line);
							} else {
								// throw 'invalid file format' exception
							}
						}
					} else {
						// throw 'empty file' exception
					}

					br.close();
				} else {
					// throw 'file/directory not exist' exception
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// Do garbage collection
		}
		return dataList;
	}
}

