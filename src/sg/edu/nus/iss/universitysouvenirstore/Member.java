// LIU YAKUN

package sg.edu.nus.iss.universitysouvenirstore;

public class Member extends Customer {

	private String memberName;
	private String memberID;
	private int loyaltyPoint;

	public Member(String memberName, String memberID) {
		this.type = "M";
		this.memberName = memberName;
		this.memberID = memberID;
		this.loyaltyPoint = -1;
	}

	// for manipulating on existing members from member.dat
	public Member(String memberName, String memberID, int loyaltyPoint) {
		this.type = "M";
		this.memberName = memberName;
		this.memberID = memberID;
		this.loyaltyPoint = loyaltyPoint;
	}

	public void updateLoyaltyPoint(int newLoyaltyPoint) {
		loyaltyPoint = newLoyaltyPoint;
	}

	public int getLoyaltyPoint() {
		return loyaltyPoint;
	}

	public String getName() {
		return memberName;
	}

	public String getID() {
		return memberID;
	}

}
