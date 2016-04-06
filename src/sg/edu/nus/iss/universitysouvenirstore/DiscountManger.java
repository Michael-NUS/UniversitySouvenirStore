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

	public static ArrayList<Discount> convertToDiscountArraylist() {
		ArrayList<Discount> discountList = new ArrayList<Discount>();

		// Iterator avoids a ConcurrentModificationException,
		// in case we need to remove entry

		Iterator iterator = discounts.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry discountEntry = (Map.Entry) iterator.next();
			discountList.add((Discount) discountEntry.getValue());
		}

		return discountList;
	}

	public static void readExistingDiscountsFromDB() {
		clearDiscountsMap();

		// get the discounts from io
		ArrayList<Object> objects = FileManagerUtils.ReadDataFromDatFile("discounts", "discounts");
		for (Object one : objects) {
			discounts.put(((Discount) one).getCode(), (Discount) one);
		}
	}

	public static Date convertToDate(Date date, String days) {
		int actualDays = Integer.parseInt(days);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, actualDays);
		return cal.getTime();
	}

	public static int clearDiscountsMap() {
		// clear the discounts map
		discounts.clear();
		return 0;
	}

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

	public static boolean checkExistOfDiscount(String discountCode) {
		discountCode = discountCode.toUpperCase();
		if (discounts.containsKey(discountCode)) {
			return true;
		} else
			return false;
	}

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

	public static Discount getDiscount(String discountCode) {
		discountCode = discountCode.toUpperCase();
		clearDiscountsMap();
		readExistingDiscountsFromDB();
		return discounts.get(discountCode);
	}

	public static Date convertToDate(String startDate, String offset) {

		String[] tmpDate = startDate.split("-");
		int years = Integer.valueOf(tmpDate[0]);
		int months = Integer.valueOf(tmpDate[1]) - 1;
		int days = Integer.valueOf(tmpDate[2]);

		Calendar cal = Calendar.getInstance();

		cal.set(years, months, days);
		cal.add(Calendar.DATE, Integer.valueOf(offset));

		Date firstDate = cal.getTime();
		return firstDate;

	}

	/* customerID="MemberID" for member or ="PUBLIC" for public customer */
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
						currentDiscountEndDate = convertToDate(currentDiscountStartDate, currentDiscountPeriod);

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
					currentDiscountEndDate = convertToDate(currentDiscountStartDate, currentDiscountPeriod);

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

		System.out.println("availableDiscountList " + availableDiscountList);
		return Collections.max(availableDiscountList);

	}

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
