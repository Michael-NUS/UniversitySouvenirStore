
package sg.edu.nus.iss.universitysouvenirstore.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.universitysouvenirstore.Member;
import sg.edu.nus.iss.universitysouvenirstore.Product;

public class FileManangerUtils {
	public static <T> boolean saveDataToDatFile(Object type, ArrayList<T> dataList) {
		String filePath = "./data/sg/edu/nus/iss/universitysouvenirstore/data";
		File dataFile = null;
		if (type.toString().contains("Product")) {
			filePath += "/Products.dat";
		}

		else if (type.toString().contains("Member")) {
			filePath += "/Members.dat";
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
					Member member = (Member) one;
					bw.write(member.getMemberID() + "," + member.getMemberName() + "," + member.getLoyaltyPoint()
							+ "\r\n");/*
										 * Replace with text entered from
										 * textField.
										 */
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
		String filePath = "./data/sg/edu/nus/iss/universitysouvenirstore/data";
		File dataFile = null;

		if (type.toString().contains("Product")) {
			filePath += "/Products.dat";
			dataFile = new File(filePath);
		}

		else if (type.toString().contains("Member")) {
			filePath += "/Members.dat";
			dataFile = new File(filePath);
		}

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
				}

				else if (type.toString().contains("Member")) {
					Member one = new Member(data[0], data[1], Integer.valueOf(data[3]));
				}
			}
			br.close();

		} catch (IOException e) {

			e.printStackTrace();
			return null;
		}
		return dataList;
	}

	public static ArrayList<Object> ReadDataFromDatFile(String strFileName, String strFileType) {
		ArrayList<Object> dataList = new ArrayList<Object>();
		String filePath = "";
		File dataFile = null;
		try {
			filePath = "./data/sg/edu/nus/iss/universitysouvenirstore/data/"; // to
																				// put
																				// under
																				// config
			if (!strFileName.isEmpty()) {
				filePath += strFileName + ".dat";
				dataFile = new File(filePath);
				if (dataFile.exists() && !dataFile.isDirectory()) {
					// read file
					String line = "";
					int intFileColumnCount = 0;
					BufferedReader br = new BufferedReader(new FileReader(dataFile.toString()));
					if ((br != null) && (br.lines() != null) && (br.lines().count() > 0)) {
						// read lines
						while ((line = br.readLine()) != null) {
							String[] data = line.split(",");
							intFileColumnCount = data.length;
							if (intFileColumnCount > 0) {
								switch (strFileType.toLowerCase()) {
								case "product":
									if (intFileColumnCount != 8) {
										// throw 'invalid product file format'
										// exception
									}
									break;
								case "transaction":
									if (intFileColumnCount != 4) {
										// throw 'invalid transaction file
										// format' exception
									}
									break;
								default:
									// throw 'invalid file type or file format'
									// exception
									break;
								}
								dataList.add(data);
								break;
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
}
