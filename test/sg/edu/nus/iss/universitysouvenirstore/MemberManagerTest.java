// LIU YAKUN

package sg.edu.nus.iss.universitysouvenirstore;

import static org.junit.Assert.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MemberManagerTest {

	private ArrayList<Member> memberList = new ArrayList<Member>();
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		String testDataDir ="./data/sg/edu/nus/iss/universitysouvenirstore/unittestdata";
		PrintWriter pw = new PrintWriter((testDataDir+"/Members.dat"));
		pw.close();
		
		FileManagerUtils.fileDir=testDataDir;
		
		MemberManager.addMember("Yan Martel", "F42563743156");
		MemberManager.addMember("Suraj Sharma", "X437F356");
		MemberManager.addMember("Ang Lee", "R64565FG4");
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.MemberManager#convertToMemberArraylist()}.
	 */
	@Test
	public void testConvertToMemberArraylist() {
		memberList = MemberManager.convertToMemberArraylist();
		assertEquals(3,memberList.size());
	}
	
	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.MemberManager#readExistingMembersFromDB()}.
	 */
	@Test
	public void testReadExistingMembersFromDB() {
		boolean status = MemberManager.readExistingMembersFromDB();
		assertTrue(status);
	}
	
	
	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.MemberManager#addMember(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAddMember() {		
		MemberManager.addMember("New Member 1", "MX001");
		memberList = MemberManager.convertToMemberArraylist();
		assertEquals(4,memberList.size());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.MemberManager#getMember(java.lang.String)}.
	 */
	@Test
	public void testGetMember() {		
		Member checkMember = new Member("Yan Martel", "F42563743156");
		assertEquals(checkMember.getID(), MemberManager.getMember("F42563743156").getID());
		assertEquals(checkMember.getName(), MemberManager.getMember("F42563743156").getName());
		assertEquals(checkMember.getLoyaltyPoint(), MemberManager.getMember("F42563743156").getLoyaltyPoint());
	}
	
	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.MemberManager#checkExistOfMember(java.lang.String)}.
	 */
	@Test
	public void testCheckExistOfMember() {
		Member checkMember=new Member("TEST M1", "F42563743156");
		boolean status=MemberManager.checkExistOfMember(checkMember.getID());
		assertTrue(status);
		
		checkMember=new Member("TEST M2", "X437F356");
		status=MemberManager.checkExistOfMember(checkMember.getID());
		assertTrue(status);

	}
	
	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.MemberManager#getMemberLoyaltyPoint(java.lang.String)}.
	 */
	@Test
	public void testGetMemberLoyaltyPoint() {
		assertEquals(-1, MemberManager.getMemberLoyaltyPoint("F42563743156"));
	}
	
	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.MemberManager#updateMemberLoyaltyPoint(java.lang.String, java.lang.Integer)}.
	 */
	@Test
	public void testUpdateMemberLoyaltyPoint() {
		MemberManager.updateMemberLoyaltyPoint("X437F356", 200);
		assertEquals(200, MemberManager.getMemberLoyaltyPoint("X437F356"));
	}
	
	
	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.MemberManager#clearMembersMap()}.
	 */
	@Test
	public void testClearMembersMap() {
		int status = MemberManager.clearMembersMap();
		assertEquals(0, status);		
	}
	
	
	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.MemberManager#removeMember(java.util.String)}.
	 */
	@Test
	public void testRemoveMember() {	
		boolean testStatus1 = MemberManager.removeMember("F42563743156");
		boolean testStatus2 = MemberManager.removeMember("X437F356");
		assertTrue(testStatus1);
		assertTrue(testStatus2);
	}
}
