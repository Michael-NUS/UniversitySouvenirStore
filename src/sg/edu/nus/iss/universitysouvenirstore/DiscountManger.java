/**
 * @author LIU YAKUN
 */

package sg.edu.nus.iss.universitysouvenirstore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DiscountManger {

	private static HashMap<String, Discount> discounts = new HashMap<String, Discount>();

	
	/**
	 * convert the HashMap discounts to an ArrayList discountList
	 * @return the discountList
	 */	
	public static ArrayList<Discount> convertToDiscountArraylist() {
		ArrayList<Discount> discountList = new ArrayList<Discount>();

		Iterator iterator = discounts.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry discountEntry = (Map.Entry) iterator.next();
			discountList.add((Discount) discountEntry.getValue());
		}

		return discountList;
	}

	
	/**
	 * fetch the existing discounts from discounts.dat to the HashMap discounts
	 * @return false if the HashMap discounts is empty, true if not
	 */	
	public static boolean readExistingDiscountsFromDB() {
		clearDiscountsMap();

		// get the discounts from io
		ArrayList<Object> objects = FileManagerUtils.ReadDataFromDatFile("discounts", "discounts");
		for (Object one : objects) {
			discounts.put(((Discount) one).getCode(), (Discount) one);
		}
		
		return (!discounts.isEmpty());
	}

	/**
	 * clear the HashMap discounts
	 * @return the size of the HashMap discounts
	 */	
	public static int clearDiscountsMap() {
		// clear the discounts map
		discounts.clear();
		return discounts.size();
	}

	/**
	 * add new discount to the discounts.dat
	 * @param discountCode
	 * @param description
	 * @param startDate
	 * @param discountPeriod
	 * @param discountPercentage
	 * @param discountType
	 * @return boolean : true if added, false if the given discountCode already exists
	 */	
	public static boolean addDiscount(String discountCode, String description, String startDate, String discountPeriod,
			int discountPercentage, String discountType) {
		discountType = discountType.toUpperCase();
		discountCode = discountCode.toUpperCase();
		startDate = startDate.toUpperCase();
		discountPeriod = discountPeriod.toUpperCase();

		clearDiscountsMap();
		readExistingDiscountsFromDB();
		if (!checkExistOfDiscount(discountCode)) {
			Discount tmpDiscount = new Discount(discountCode, description, startDate, discountPeriod,
					discountPercentage, discountType);
			discounts.put(discountCode, tmpDiscount);

			ArrayList<Discount> discountList = convertToDiscountArraylist();
			discountList.add(tmpDiscount);
			FileManagerUtils.saveDataToDatFile(Discount.class, discountList);

			return true;
		} else {
			return false;
		}
	}

	
	/**
	 * check whether the discount already exists, by the discountCode
	 * @param discountCode
	 * @return boolean : true if it already exists, false if not
	 */
	public static boolean checkExistOfDiscount(String discountCode) {
		discountCode = discountCode.toUpperCase();
		clearDiscountsMap();
		readExistingDiscountsFromDB();
		if (discounts.containsKey(discountCode)) {
			return true;
		} else
			return false;
	}

	/**
	 * delete the discount from discounts.dat 
	 * @param discountCode
	 * @return boolean : true if it is deleted, false if it doesn't exist
	 */
	public static boolean removeDiscount(String discountCode) {
		discountCode = discountCode.toUpperCase();
		clearDiscountsMap();
		readExistingDiscountsFromDB();
		if (checkExistOfDiscount(discountCode)) {
			discounts.remove(discountCode);

			ArrayList<Discount> discountList = convertToDiscountArraylist();
			FileManagerUtils.saveDataToDatFile(Discount.class, discountList);

			clearDiscountsMap();
			return true;
		} else {
			
			clearDiscountsMap();
			return false;
		}
	}

	
	/**
	 * get discount by discountCode 
	 * @param discountCode
	 * @return Discount : the discount object if it exists, null if not
	 */
	public static Discount getDiscount(String discountCode) {
		discountCode = discountCode.toUpperCase();
		clearDiscountsMap();
		readExistingDiscountsFromDB();
		if (checkExistOfDiscount(discountCode)) {
			return discounts.get(discountCode);
		} else {
			return null;
			}
	}

	
	/**
	 * convert the Strings of a start date and an offset into a Date object
	 * @param startDate
	 * @param offset
	 * @return Date of the startDate plus the offset 
	 */
	public static Date convertToDate(String startDate, String offset) {

		String[] tmpDate = startDate.split("-");
		int years = Integer.valueOf(tmpDate[0]);
		int months = Integer.valueOf(tmpDate[1]) - 1;
		int days = Integer.valueOf(tmpDate[2]);
		int offsetDays = Integer.valueOf(offset);

		Calendar cal = Calendar.getInstance();

		cal.set(years, months, days);
		cal.add(Calendar.DATE, offsetDays);

		Date firstDate = cal.getTime();
		return firstDate;
	}
	
	
	/**
	 * get the highest discount percentage given the customer ID at the moment of transaction
	 * customerID= memberID for member; customerID ="PUBLIC" for public customer
	 * 
	 * @param customerID
	 * @return the highest discount percentage if there are available discounts
	 * at the moment of transaction, 0 if no discounts available
	 */	
	public static int getHighestDiscount(String customerID) {
		clearDiscountsMap();
		readExistingDiscountsFromDB();

		ArrayList<Integer> availableDiscountList = new ArrayList<Integer>();

		customerID = customerID.toUpperCase();
		Date currentDate = new Date();

		Iterator iterator = discounts.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry discountEntry = (Map.Entry) iterator.next();

			String tmpDiscountStartDate = ((Discount) discountEntry.getValue()).getStartDate();
			String currentDiscountPeriod = ((Discount) discountEntry.getValue()).getPeriod();

			Date currentDiscountStartDate;
			Date currentDiscountEndDate;

			// Member discounts
			if (customerID != "PUBLIC") {

				// add discount to list if the date checks out
				if (!((Discount) discountEntry.getValue()).getCode().equals("MEMBER_FIRST")) {
					if (!tmpDiscountStartDate.equals("ALWAYS") && !currentDiscountPeriod.equals("ALWAYS")) {
						currentDiscountStartDate = convertToDate(tmpDiscountStartDate, "0");
						currentDiscountEndDate = convertToDate(tmpDiscountStartDate, currentDiscountPeriod);

						if (currentDate.after(currentDiscountStartDate) && currentDate.before(currentDiscountEndDate)) {
							availableDiscountList.add(((Discount) discountEntry.getValue()).getPercentage());
						}

					} else if (!tmpDiscountStartDate.equals("ALWAYS") && currentDiscountPeriod.equals("ALWAYS")) {
						currentDiscountStartDate = convertToDate(tmpDiscountStartDate, "0");

						if (currentDate.after(currentDiscountStartDate)) {
							availableDiscountList.add(((Discount) discountEntry.getValue()).getPercentage());
						}

					} else {
						availableDiscountList.add(((Discount) discountEntry.getValue()).getPercentage());
					}
				}

				// add 1st time discount to list for member's 1st purchase
				if (MemberManager.getMemberLoyaltyPoint(customerID) == -1
						&& ((Discount) discountEntry.getValue()).getCode().equals("MEMBER_FIRST")) {
					availableDiscountList.add(((Discount) discountEntry.getValue()).getPercentage());
				}

			}

			// add discount to list if the date checks out
			// exclude member discounts
			if (customerID.equals("PUBLIC") && ((Discount) discountEntry.getValue()).getType().equals("A")) {

				if (!tmpDiscountStartDate.equals("ALWAYS") && !currentDiscountPeriod.equals("ALWAYS")) {
					currentDiscountStartDate = convertToDate(tmpDiscountStartDate, "0");
					currentDiscountEndDate = convertToDate(tmpDiscountStartDate, currentDiscountPeriod);

					if (currentDate.after(currentDiscountStartDate) && currentDate.before(currentDiscountEndDate)) {
						availableDiscountList.add(((Discount) discountEntry.getValue()).getPercentage());
					}

				} else if (!tmpDiscountStartDate.equals("ALWAYS") && currentDiscountPeriod.equals("ALWAYS")) {
					currentDiscountStartDate = convertToDate(tmpDiscountStartDate, "0");

					if (currentDate.after(currentDiscountStartDate)) {
						availableDiscountList.add(((Discount) discountEntry.getValue()).getPercentage());
					}

				} else {
					availableDiscountList.add(((Discount) discountEntry.getValue()).getPercentage());
				}
			}
		}

		// in case no discount is available at the moment
		// return 0% discount
		if (availableDiscountList.size() == 0) {
			return 0;
		}

		//System.out.println("availableDiscountList " + availableDiscountList);
		//System.out.println("max of availableDiscountList " + Collections.max(availableDiscountList));
		return Collections.max(availableDiscountList);

	}

	/**
	 * get discount percentage by discountCode 
	 * @param discountCode
	 * @return discount percentage if the discount exists, 0 if not
	 */	
	public static int getDiscountPercentage(String discountCode) {
		discountCode = discountCode.toUpperCase();
		clearDiscountsMap();
		readExistingDiscountsFromDB();
		if (checkExistOfDiscount(discountCode)) {
			Discount tmpDiscount = discounts.get(discountCode);
			discounts.replace(discountCode, tmpDiscount);

			return (discounts.get(discountCode)).getPercentage();
		} else {
			return 0;
		}
	}

	
	/**
	 * get discount description by discountCode 
	 * @param discountCode
	 * @return discount description if the discount exists, null if not
	 */	
	public static String getDiscountDescription(String discountCode) {
		discountCode = discountCode.toUpperCase();
		clearDiscountsMap();
		readExistingDiscountsFromDB();
		if (checkExistOfDiscount(discountCode)) {
			return getDiscount(discountCode).getDescription();
		} else {
			return null;
		}
	}

	
	/**
	 * get discount type by discountCode 
	 * @param discountCode
	 * @return discount type if the discount exists, null if not
	 */	
	public static String getDiscountType(String discountCode) {
		discountCode = discountCode.toUpperCase();
		clearDiscountsMap();
		readExistingDiscountsFromDB();
		if (checkExistOfDiscount(discountCode)) {
			return getDiscount(discountCode).getType();
		} else {
			return null;
		}
	}

	
	/**
	 * get discount start date by discountCode 
	 * @param discountCode
	 * @return discount start date if the discount exists, null if not
	 */	
	public static String getDiscountStartDate(String discountCode) {
		discountCode = discountCode.toUpperCase();
		clearDiscountsMap();
		readExistingDiscountsFromDB();
		if (checkExistOfDiscount(discountCode)) {
			return getDiscount(discountCode).getStartDate();
		} else {
			return null;
		}
	}

	
	/**
	 * get discount period by discountCode 
	 * @param discountCode
	 * @return discount period if the discount exists, null if not
	 */	
	public static String getDiscountPeriod(String discountCode) {
		discountCode = discountCode.toUpperCase();
		clearDiscountsMap();
		readExistingDiscountsFromDB();
		if (checkExistOfDiscount(discountCode)) {
			return getDiscount(discountCode).getPeriod();
		} else {
			return null;
		}
	}

	
	/** 
	 * update the discount description by discountCode
	 * @param discountCode
	 * @param newDescription
	 * @return boolean : true if updated, false if the discount doesn't exist
	 */
	public static boolean updateDiscountDescription(String discountCode, String newDescription) {
		discountCode = discountCode.toUpperCase();
		clearDiscountsMap();
		readExistingDiscountsFromDB();
		if (checkExistOfDiscount(discountCode)) {
			Discount tmpDiscount = discounts.get(discountCode);
			tmpDiscount.setDescription(newDescription);
			discounts.replace(discountCode, tmpDiscount);

			ArrayList<Discount> discountList = convertToDiscountArraylist();
			FileManagerUtils.saveDataToDatFile(Discount.class, discountList);

			return true;
		} else {
			return false;
		}
	}

	
	/** 
	 * update the discount star date by discountCode
	 * @param discountCode
	 * @param newDiscountStartDate
	 * @return boolean : true if updated, false if the discount doesn't exist
	 */
	public static boolean updateDiscountStartDate(String discountCode, String newDiscountStartDate) {
		discountCode = discountCode.toUpperCase();
		newDiscountStartDate = newDiscountStartDate.toUpperCase();
		clearDiscountsMap();
		readExistingDiscountsFromDB();
		if (checkExistOfDiscount(discountCode)) {
			Discount tmpDiscount = discounts.get(discountCode);
			tmpDiscount.setStartDate(newDiscountStartDate);
			discounts.replace(discountCode, tmpDiscount);

			ArrayList<Discount> discountList = convertToDiscountArraylist();
			FileManagerUtils.saveDataToDatFile(Discount.class, discountList);

			return true;
		} else {
			return false;
		}
	}

	
	/** 
	 * update the discount period by discountCode
	 * @param discountCode
	 * @param newDiscountPeriod
	 * @return boolean : true if updated, false if the discount doesn't exist
	 */
	public static boolean updateDiscountPeriod(String discountCode, String newDiscountPeriod) {
		discountCode = discountCode.toUpperCase();
		newDiscountPeriod = newDiscountPeriod.toUpperCase();
		clearDiscountsMap();
		readExistingDiscountsFromDB();
		if (checkExistOfDiscount(discountCode)) {
			Discount tmpDiscount = discounts.get(discountCode);
			tmpDiscount.setPeriod(newDiscountPeriod);
			discounts.replace(discountCode, tmpDiscount);

			ArrayList<Discount> discountList = convertToDiscountArraylist();

			FileManagerUtils.saveDataToDatFile(Discount.class, discountList);

			return true;
		} else {
			return false;
		}
	}

	
	/** 
	 * update the discount percentage by discountCode
	 * @param discountCode
	 * @param newDiscountPercentage
	 * @return boolean : true if updated, false if the discount doesn't exist
	 */
	public static boolean updateDiscountPercentage(String discountCode, int newDiscountPercentage) {
		discountCode = discountCode.toUpperCase();
		clearDiscountsMap();
		readExistingDiscountsFromDB();
		if (checkExistOfDiscount(discountCode)) {
			Discount tmpDiscount = discounts.get(discountCode);
			tmpDiscount.setPercentage(newDiscountPercentage);
			discounts.replace(discountCode, tmpDiscount);

			ArrayList<Discount> discountList = convertToDiscountArraylist();
			FileManagerUtils.saveDataToDatFile(Discount.class, discountList);

			return true;
		} else {
			return false;
		}
	}

	
	/** 
	 * update the discount type by discountCode
	 * @param discountCode
	 * @param newDiscountType
	 * @return boolean : true if updated, false if the discount doesn't exist
	 */
	public static boolean updateDiscountType(String discountCode, String newDiscountType) {
		discountCode = discountCode.toUpperCase();
		newDiscountType = newDiscountType.toUpperCase();
		clearDiscountsMap();
		readExistingDiscountsFromDB();
		if (checkExistOfDiscount(discountCode)) {
			Discount tmpDiscount = discounts.get(discountCode);
			tmpDiscount.setType(newDiscountType);
			discounts.replace(discountCode, tmpDiscount);

			ArrayList<Discount> discountList = convertToDiscountArraylist();
			FileManagerUtils.saveDataToDatFile(Discount.class, discountList);

			return true;
		} else {
			return false;
		}
	}

}
