package sg.edu.nus.iss.universitysouvenirstore;

public class Discount {

	private String discountCode;
	private String description;
	private String startDate; // where applicable or “ALWAYS”
	private String discountPeriod; // where applicable or “ALWAYS”

	private int discountPercentage; /*
									 * Percentage discount without the
									 * percentage symbol e.g. “10” for 10%
									 * discount
									 */

	public char discountType; // Applicable to Member (M) or All (A)

	public Discount(char discountType, String discountCode, String description, String startDate, String discountPeriod,
			int discountPercentage) {
		this.discountType = discountType;
		this.startDate = startDate;
		this.discountPeriod = discountPeriod;
		this.discountCode = discountCode;
		this.description = description;
		this.discountPercentage = discountPercentage;
	}

	public void setDiscountCode(String newDiscountCode) {
		discountCode = newDiscountCode;
	}

	public void setDiscountDescription(String newDescription) {
		description = newDescription;
	}

	public void setDiscountPercentage(int newPercentage) {
		discountPercentage = newPercentage;
	}

	public void setDiscountType(char newDiscountType) {
		discountType = newDiscountType;
	}

	public void setStartDate(String newStartDate) {
		startDate = newStartDate;
	}

	public void setDiscountPeriod(String newDiscountPeriod) {
		discountPeriod = newDiscountPeriod;
	}

	public int getDiscountPercentage() {
		return discountPercentage;
	}

	public char getDiscountType() {
		return discountType;
	}
	
	public String getDiscountCode() {
		return discountCode;
	}

}
