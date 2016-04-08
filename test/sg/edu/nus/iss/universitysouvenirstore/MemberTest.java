// LIU YAKUN

package sg.edu.nus.iss.universitysouvenirstore;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author LIU YAKUN
 * Unit test for Member Class
 */

public class MemberTest {
	
	private Member testMember1 = new Member("Member 1", "MXXXX1");
	
	private Member testMember2 = new Member("Member 2", "MXXXX2", 100);
	
	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Member#getLoyaltyPoint()}.
	 */
	@Test
	public void testGetLoyaltyPoint() {
		assertEquals(-1, testMember1.getLoyaltyPoint());
		assertEquals(100, testMember2.getLoyaltyPoint());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Member#getName()}.
	 */
	@Test
	public void testGetName() {
		assertEquals("Member 1", testMember1.getName());
		assertEquals("Member 2", testMember2.getName());
	}
	
	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Member#getID()}.
	 */
	@Test
	public void testGetID() {
		assertEquals("MXXXX1", testMember1.getID());
		assertEquals("MXXXX2", testMember2.getID());
	}
	
	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Member#updateLoyaltyPoint(java.lang.Integer)}.
	 */
	@Test
	public void testUpdateLoyaltyPoint() {
		testMember1.updateLoyaltyPoint(200);;
		assertEquals(200, testMember1.getLoyaltyPoint());
	}

}
