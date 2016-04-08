/**
 * @author LIU YAKUN
 */

package sg.edu.nus.iss.universitysouvenirstore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MemberManager {

	private static HashMap<String, Member> members = new HashMap<String, Member>();

	
	/**
	 * convert the HashMap members to an ArrayList memberList
	 * @return the memberList
	 */	
	public static ArrayList<Member> convertToMemberArraylist() {
		ArrayList<Member> memberList = new ArrayList<Member>();

		Iterator iterator = members.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry memberEntry = (Map.Entry) iterator.next();
			memberList.add((Member) memberEntry.getValue());
		}

		return memberList;
	}

	
	/**
	 * fetch the existing members from discounts.dat to the HashMap discounts
	 * @return false if the HashMap members is empty, true if not
	 */	
	public static boolean readExistingMembersFromDB() {
		clearMembersMap();

		// get the members from io
		ArrayList<Object> objects = FileManagerUtils.readDataFromDatFile(Member.class);
		for (Object one : objects) {
			members.put(((Member) one).getID(), (Member) one);
		}
		
		return (!members.isEmpty());
	}

	
	/**
	 * clear the HashMap members
	 * @return the size of the HashMap members
	 */	
	public static int clearMembersMap() {
		// clear the members map
		members.clear();
		return members.size();
	}

	
	/**
	 * check whether the member already exists, by the memberID
	 * @param memberID
	 * @return boolean : true if it already exists, false if not
	 */
	public static boolean checkExistOfMember(String memberID) {
		if (members.containsKey(memberID.toUpperCase())) {
			return true;
		} else
			return false;
	}

	
	/**
	 * add new member to the members.dat
	 * @param memberName 
	 * @param memberID
	 * @return boolean : true if added, false if the given memberID already exists
	 */	
	public static boolean addMember(String memberName, String memberID) {
		memberName = memberName.toUpperCase();
		memberID = memberID.toUpperCase();
		readExistingMembersFromDB();
		if (!checkExistOfMember(memberID)) {
			Member tmpMember = new Member(memberName, memberID);
			members.put(memberID, tmpMember);

			ArrayList<Member> memberList = convertToMemberArraylist();
			memberList.add(tmpMember);
			FileManagerUtils.saveDataToDatFile(Member.class, memberList);

			return true;
		} else {
			return false;
		}
	}

	
	/**
	 * delete the member from discounts.dat 
	 * @param memberID
	 * @return boolean : true if it is deleted, false if it doesn't exist
	 */
	public static boolean removeMember(String memberID) {
		memberID = memberID.toUpperCase();
		readExistingMembersFromDB();
		if (checkExistOfMember(memberID)) {
			members.remove(memberID);
			Member tmpMember = null;

			ArrayList<Member> memberList = convertToMemberArraylist();
			memberList.add(tmpMember);
			FileManagerUtils.saveDataToDatFile(Member.class, memberList);

			clearMembersMap();
			return true;
		} else {
			// the member does not exist
			clearMembersMap();
			return false;
		}
	}

	
	/**
	 * get member by memberID 
	 * @param memberID
	 * @return Member : the Member object if it exists, null if not
	 */
	public static Member getMember(String memberID) {
		memberID = memberID.toUpperCase();
		readExistingMembersFromDB();
		
		if (checkExistOfMember(memberID)) {
			return members.get(memberID);
		} else {
			return null;
			}
	}

	
	/** 
	 * update the member loyalty point by memberID
	 * @param memberID
	 * @param newLoyaltyPoint
	 */
	public static void updateMemberLoyaltyPoint(String memberID, int newLoyaltyPoint) {
		memberID = memberID.toUpperCase();
		readExistingMembersFromDB();
		if (checkExistOfMember(memberID)) {
			Member tmpMember = members.get(memberID);
			tmpMember.updateLoyaltyPoint(newLoyaltyPoint);
			members.replace(memberID, tmpMember);

			ArrayList<Member> memberList = convertToMemberArraylist();
			FileManagerUtils.saveDataToDatFile(Member.class, memberList);

			clearMembersMap();
		} else {
			clearMembersMap();
		}
	}

	
	/**
	 * get member loyalty point by memberID 
	 * @param memberID
	 * @return member loyalty point if the member exists, -99 if not
	 */	
	public static int getMemberLoyaltyPoint(String memberID) {
		memberID = memberID.toUpperCase();
		readExistingMembersFromDB();
		if (checkExistOfMember(memberID)) {
			return getMember(memberID).getLoyaltyPoint() + clearMembersMap();
		} else {
			return -99 + clearMembersMap();
		}
	}

}
