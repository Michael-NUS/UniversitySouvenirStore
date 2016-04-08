/**
 * @author LIU YAKUN
 */

package sg.edu.nus.iss.universitysouvenirstore;

public class Discount {

	private String discountCode;
	private String description;
	private String startDate; // where applicable or “ALWAYS”
	private String discountPeriod; // where applicable or “ALWAYS”

	private int discountPercentage; /*
									 * Percentage of the discount without the
									 * percentage symbol e.g. “10” for 10%
									 * discount
									 */

	public String discountType; // Applicable to Member (M) or All (A)

	
	/**
	 * Constructor for the Discount class
	 * @param discountCode
	 * @param description
	 * @param startDate
	 * @param discountPeriod
	 * @param discountPercentage
	 * @param discountType
	 */
	public Discount(String discountCode, String description, String startDate, String discountPeriod,
			int discountPercentage, String discountType) {
		// if startDate is ALWAYS, it's only logical that this discount is
		// ALWAYS available
		if (startDate.equalsIgnoreCase("ALWAYS")) {
			discountPeriod = "ALWAYS";
		}

		this.discountType = discountType;
		this.startDate = startDate;
		this.discountPeriod = discountPeriod;
		this.discountCode = discountCode;
		this.description = description;
		this.discountPercentage = discountPercentage;
	}

	
	/** 
	 * set the discount code
	 * @param newDiscountCode
	 */
	public void setCode(String newDiscountCode) {
		discountCode = newDiscountCode;
	}
	
	/** 
	 * set the discount description
	 * @param newDescription
	 */
	public void setDescription(String newDescription) {
		description = newDescription;
	}

	/** 
	 * set the discount percentage
	 * @param newPercentage
	 */
	public void setPercentage(int newPercentage) {
		discountPercentage = newPercentage;
	}

	/** 
	 * set the discount type
	 * @param newDiscountType
	 */
	public void setType(String newDiscountType) {
		discountType = newDiscountType;
	}

	/** 
	 * set the discount start date
	 * @param newStartDate
	 */
	public void setStartDate(String newStartDate) {
		if (newStartDate.equalsIgnoreCase("ALWAYS")) {
			// if startDate is ALWAYS, it's only logical that this discount is
			// ALWAYS available
			setPeriod("ALWAYS");
		}
		startDate = newStartDate;
	}

	/** 
	 * set the discount period
	 * @param newDiscountPeriod
	 */
	public void setPeriod(String newDiscountPeriod) {
		discountPeriod = newDiscountPeriod;
	}

	/** 
	 * get the discount percentage
	 */
	public int getPercentage() {
		return discountPercentage;
	}

	/** 
	 * get the discount type
	 */
	public String getType() {
		return discountType;
	}

	/** 
	 * get the discount code
	 */
	public String getCode() {
		return discountCode;
	}

	/** 
	 * get the discount start date
	 */
	public String getStartDate() {
		return startDate;
	}

	/** 
	 * get the discount period
	 */
	public String getPeriod() {
		return discountPeriod;
	}

	/** 
	 * get the discount description
	 */
	public String getDescription() {
		return description;
	}

}
