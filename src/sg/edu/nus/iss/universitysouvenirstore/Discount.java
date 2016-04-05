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

	public String discountType; // Applicable to Member (M) or All (A)

	public Discount(String discountType, String discountCode, String description, String startDate,
			String discountPeriod, int discountPercentage) {
		// if startDate is ALWAYS, then it's only logical that this discount is
		// ALWAYS available
		if (startDate == "ALWAYS") {
			discountPeriod = "ALWAYS";
		}

		this.discountType = discountType;
		this.startDate = startDate;
		this.discountPeriod = discountPeriod;
		this.discountCode = discountCode;
		this.description = description;
		this.discountPercentage = discountPercentage;
	}

	public void setCode(String newDiscountCode) {
		discountCode = newDiscountCode;
	}

	public void setDescription(String newDescription) {
		description = newDescription;
	}

	public void setPercentage(int newPercentage) {
		discountPercentage = newPercentage;
	}

	public void setType(String newDiscountType) {
		discountType = newDiscountType;
	}

	public void setStartDate(String newStartDate) {
		if (newStartDate == "ALWAYS") {
			// if startDate is ALWAYS, then it's only logical that this discount is
			// ALWAYS available
			setPeriod("ALWAYS");
		}
		startDate = newStartDate;
	}

	public void setPeriod(String newDiscountPeriod) {
		discountPeriod = newDiscountPeriod;
	}

	public int getPercentage() {
		return discountPercentage;
	}

	public String getType() {
		return discountType;
	}

	public String getCode() {
		return discountCode;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public String getPeriod() {
		return discountPeriod;
	}
	
	public String getDescription() {
		return description;
	}

}
