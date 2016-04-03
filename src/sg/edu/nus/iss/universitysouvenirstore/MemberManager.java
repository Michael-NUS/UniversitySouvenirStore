package sg.edu.nus.iss.universitysouvenirstore;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import sg.edu.nus.iss.universitysouvenirstore.util.FileManangerUtils;

public class MemberManager {

	private static HashMap<String, Member> members = new HashMap<String, Member>();

	public static ArrayList<Member> convertToMemberArraylist() {
		ArrayList<Member> memberList = new ArrayList<Member>();

		// Iterator avoids a ConcurrentModificationException,
		// in case we need to remove entry
		Iterator it = members.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Member tmpMember = (Member) pair.getValue();
		}
		return memberList;
	}

	public static void readExistingMembersFromDB() {
		clearMembersMap();
		Member tmpMember = null;

		// get the members from io
		ArrayList<Object> objects = FileManangerUtils.readDataFromDatFile(tmpMember);
		for (Object one : objects) {
			members.put(((Member) one).getMemberID(), (Member) one);
		}
	}

	public static int clearMembersMap() {
		// clear the members map
		members.clear();
		return 0;
	}

	public static boolean checkExistOfMember(String memberID) {
		if (members.containsKey(memberID)) {
			return true;
		} else
			return false;
	}

	public static boolean addMember(String memberName, String memberID) {
		readExistingMembersFromDB();
		if (!checkExistOfMember(memberID)) {
			Member m = new Member(memberName, memberID);
			members.put(memberID, m);
			Member tmpMember = null;

			ArrayList<Member> memberList = convertToMemberArraylist();
			memberList.add(tmpMember);
			FileManangerUtils.saveDataToDatFile(tmpMember, memberList);

			clearMembersMap();
			return true;
		} else {
			clearMembersMap();
			return false;
		}
	}

	public static boolean removeMember(String memberID) {
		readExistingMembersFromDB();
		if (checkExistOfMember(memberID)) {
			members.remove(memberID);
			Member tmpMember = null;

			ArrayList<Member> memberList = convertToMemberArraylist();
			memberList.add(tmpMember);
			FileManangerUtils.saveDataToDatFile(tmpMember, memberList);

			clearMembersMap();
			return true;
		} else {
			clearMembersMap();
			return false;
		}
	}

	public static Member getMember(String memberID) {
		readExistingMembersFromDB();
		return members.get(memberID);
	}

	// public static void updateMemberName(String memberID, String
	// newMemberName) {
	// Member tmpMember = members.get(memberID);
	// tmpMember.setMemberName(newMemberName);
	// members.replace(memberID, tmpMember);
	// }

	// public static void setMemberID(String oldMemberID, String newMemberID) {
	// Member tmpMember = members.get(oldMemberID);
	// tmpMember.setMemberID(newMemberID);
	// members.remove(oldMemberID);
	// members.put(newMemberID, tmpMember);
	// }

	public static void updateMemberLoyaltyPoint(String memberID, int newLoyaltyPoint) {
		readExistingMembersFromDB();
		if (checkExistOfMember(memberID)) {
			Member tmpMember = members.get(memberID);
			tmpMember.updateLoyaltyPoint(newLoyaltyPoint);
			members.replace(memberID, tmpMember);

			ArrayList<Member> memberList = convertToMemberArraylist();
			memberList.add(tmpMember);
			FileManangerUtils.saveDataToDatFile(tmpMember, memberList);

			clearMembersMap();
		} else {
			clearMembersMap();
		}
	}

	public static int getMemberLoyaltyPoint(String memberID) {
		readExistingMembersFromDB();
		if (checkExistOfMember(memberID)) {
			Member tmpMember = members.get(memberID);
			members.replace(memberID, tmpMember);

			return (members.get(memberID)).getLoyaltyPoint() + clearMembersMap();
		} else {
			return -99 + clearMembersMap();
		}
	}

	// public static void setMemberLoyaltyPoint(String memberID, int newPoint) {
	// Member tmpMember = members.get(memberID);
	// tmpMember.setLoyaltyPoint(newPoint);
	// members.replace(memberID, tmpMember);
	// }

}
