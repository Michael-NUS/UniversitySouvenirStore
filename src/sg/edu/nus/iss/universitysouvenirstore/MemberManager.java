// LIU YAKUN

package sg.edu.nus.iss.universitysouvenirstore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MemberManager {

	private static HashMap<String, Member> members = new HashMap<String, Member>();

	public static ArrayList<Member> convertToMemberArraylist() {
		ArrayList<Member> memberList = new ArrayList<Member>();

		Iterator iterator = members.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry memberEntry = (Map.Entry) iterator.next();
			memberList.add((Member) memberEntry.getValue());
		}

		return memberList;
	}

	public static boolean readExistingMembersFromDB() {
		clearMembersMap();

		// get the members from io
		ArrayList<Object> objects = FileManagerUtils.readDataFromDatFile(Member.class);
		for (Object one : objects) {
			members.put(((Member) one).getID(), (Member) one);
		}
		
		return (!members.isEmpty());
	}

	public static int clearMembersMap() {
		// clear the members map
		members.clear();
		return members.size();
	}

	public static boolean checkExistOfMember(String memberID) {
		if (members.containsKey(memberID.toUpperCase())) {
			return true;
		} else
			return false;
	}

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

	public static Member getMember(String memberID) {
		memberID = memberID.toUpperCase();
		readExistingMembersFromDB();
		return members.get(memberID);
	}

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
