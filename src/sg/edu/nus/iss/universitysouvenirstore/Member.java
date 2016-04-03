package sg.edu.nus.iss.universitysouvenirstore;

//import static java.lang.Math.*;

public class Member extends Customer {

	private String memberName;
	private String memberID;
	// protected static final double LOYALTY_POINT_TO_AMOUNT = 0.05;
	// protected static final double AMOUNT_TO_LOYALTY_POINT = 0.1;
	private int loyaltyPoint;

	public Member(String memberName, String memberID) {
		this.type = "M";
		this.memberID = memberID;
		this.loyaltyPoint = -1;
	}

	// for manipulating existing members from member.dat
	public Member(String memberName, String memberID, int loyaltyPoint) {
		this.type = "M";
		this.memberID = memberID;
		this.loyaltyPoint = loyaltyPoint;
	}

	public void updateLoyaltyPoint(int newLoyaltyPoint) {
		loyaltyPoint = newLoyaltyPoint;
	}

	public int getLoyaltyPoint() {
		return loyaltyPoint;
	}

	public String getMemberName() {
		return memberName;
	}

	// public void setMemberName(String newMemberName) {
	// memberName = newMemberName;
	// }

	public String getMemberID() {
		return memberID;
	}

	// public void setMemberID(String newMemberID) {
	// memberID = newMemberID;
	// }

}