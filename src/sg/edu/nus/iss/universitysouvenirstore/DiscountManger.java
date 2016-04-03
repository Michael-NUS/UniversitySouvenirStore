package sg.edu.nus.iss.universitysouvenirstore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import sg.edu.nus.iss.universitysouvenirstore.util.FileManangerUtils;

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
		ArrayList<Object> objects = FileManangerUtils.readDataFromDatFile(Discount.class);
		for (Object one : objects) {
			discounts.put(((Discount) one).getDiscountCode(), (Discount) one);
		}
	}

	public static int clearDiscountsMap() {
		// clear the discounts map
		discounts.clear();
		return 0;
	}

	public static boolean addDiscount(char discountType, String discountCode, String description, String startDate,
			String discountPeriod, int discountPercentage) {
		readExistingDiscountsFromDB();
		if (!checkExistOfDiscount(discountCode)) {
			Discount tmpDiscount = new Discount(discountType, discountCode, description, startDate, discountPeriod,
					discountPercentage);
			discounts.put(discountCode, tmpDiscount);

			ArrayList<Discount> discountList = convertToDiscountArraylist();
			discountList.add(tmpDiscount);
			FileManangerUtils.saveDataToDatFile(Discount.class, discountList);

			return true;
		} else {
			return false;
		}
	}

	public static boolean checkExistOfDiscount(String discountCode) {
		if (discounts.containsKey(discountCode)) {
			return true;
		} else
			return false;
	}

	public static boolean removeDiscount(String discountCode) {
		readExistingDiscountsFromDB();
		if (checkExistOfDiscount(discountCode)) {
			discounts.remove(discountCode);

			ArrayList<Discount> discountList = convertToDiscountArraylist();
			FileManangerUtils.saveDataToDatFile(Discount.class, discountList);

			clearDiscountsMap();
			return true;
		} else {
			clearDiscountsMap();
			return false;
		}
	}

	public static Discount getDiscount(String discountCode) {
		readExistingDiscountsFromDB();
		return discounts.get(discountCode);
	}

	public static int getDiscountPercentage(String discountCode) {
		readExistingDiscountsFromDB();
		if (checkExistOfDiscount(discountCode)) {
			Discount tmpDiscount = discounts.get(discountCode);
			discounts.replace(discountCode, tmpDiscount);

			return (discounts.get(discountCode)).getDiscountPercentage() + clearDiscountsMap();
		} else {
			return -1 + clearDiscountsMap();
		}
	}
	
	
	public static void updateDiscountDescription(String discountCode, String newDescription) {
		readExistingDiscountsFromDB();
		if (checkExistOfDiscount(discountCode)) {
			Discount tmpDiscount = discounts.get(discountCode);
			tmpDiscount.setDiscountDescription(newDescription);
			discounts.replace(discountCode, tmpDiscount);

			ArrayList<Discount> discountList = convertToDiscountArraylist();
			discountList.add(tmpDiscount);
			FileManangerUtils.saveDataToDatFile(Discount.class, discountList);

			clearDiscountsMap();
		} else {
			clearDiscountsMap();
		}
	}

	public static void updateDiscountStartDate(String discountCode, String newDiscountStartDate) {
		readExistingDiscountsFromDB();
		if (checkExistOfDiscount(discountCode)) {
			Discount tmpDiscount = discounts.get(discountCode);
			tmpDiscount.setDiscountDescription(newDiscountStartDate);
			discounts.replace(discountCode, tmpDiscount);

			ArrayList<Discount> discountList = convertToDiscountArraylist();
			discountList.add(tmpDiscount);
			FileManangerUtils.saveDataToDatFile(Discount.class, discountList);

			clearDiscountsMap();
		} else {
			clearDiscountsMap();
		}
	}

	public static void updateDiscountPeriod(String discountCode, String newDiscountPeriod) {
		readExistingDiscountsFromDB();
		if (checkExistOfDiscount(discountCode)) {
			Discount tmpDiscount = discounts.get(discountCode);
			tmpDiscount.setDiscountPeriod(newDiscountPeriod);
			discounts.replace(discountCode, tmpDiscount);

			ArrayList<Discount> discountList = convertToDiscountArraylist();
			discountList.add(tmpDiscount);
			FileManangerUtils.saveDataToDatFile(Discount.class, discountList);

			clearDiscountsMap();
		} else {
			clearDiscountsMap();
		}
	}

	public static void updateDiscountPercentage(String discountCode, int newDiscountPercentage) {
		readExistingDiscountsFromDB();
		if (checkExistOfDiscount(discountCode)) {
			Discount tmpDiscount = discounts.get(discountCode);
			tmpDiscount.setDiscountPercentage(newDiscountPercentage);
			discounts.replace(discountCode, tmpDiscount);

			ArrayList<Discount> discountList = convertToDiscountArraylist();
			discountList.add(tmpDiscount);
			FileManangerUtils.saveDataToDatFile(Discount.class, discountList);

			clearDiscountsMap();
		} else {
			clearDiscountsMap();
		}
	}
	
	public static void updateDiscountType(String discountCode, char newDiscountType) {
		readExistingDiscountsFromDB();
		if (checkExistOfDiscount(discountCode)) {
			Discount tmpDiscount = discounts.get(discountCode);
			tmpDiscount.setDiscountType(newDiscountType);
			discounts.replace(discountCode, tmpDiscount);

			ArrayList<Discount> discountList = convertToDiscountArraylist();
			discountList.add(tmpDiscount);
			FileManangerUtils.saveDataToDatFile(Discount.class, discountList);

			clearDiscountsMap();
		} else {
			clearDiscountsMap();
		}
	}

}
