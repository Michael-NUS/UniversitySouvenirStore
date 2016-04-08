/**
 * @author LIU YAKUN
 */

package sg.edu.nus.iss.universitysouvenirstore;

public class Member extends Customer {

	private String memberName;
	private String memberID;
	private int loyaltyPoint;

	/**
	 * Constructor for this class
	 * @param memberName
	 * @param memberID
	 */
	public Member(String memberName, String memberID) {
		this.type = "M";
		this.memberName = memberName;
		this.memberID = memberID;
		this.loyaltyPoint = -1;
	}

	/**
	 * Constructor for the Member class
	 * @param memberName
	 * @param memberID
	 * @param loyaltyPoint
	 */
	public Member(String memberName, String memberID, int loyaltyPoint) {
		this.type = "M";
		this.memberName = memberName;
		this.memberID = memberID;
		this.loyaltyPoint = loyaltyPoint;
	}

	/** 
	 * update the member loyalty point
	 * @param newLoyaltyPoint
	 */
	public void updateLoyaltyPoint(int newLoyaltyPoint) {
		loyaltyPoint = newLoyaltyPoint;
	}
	
	/** 
	 * get the member loyalty point
	 */
	public int getLoyaltyPoint() {
		return loyaltyPoint;
	}
	
	/** 
	 * get the member name
	 */
	public String getName() {
		return memberName;
	}
	
	/** 
	 * get the member ID
	 */
	public String getID() {
		return memberID;
	}

}
