// LIU YAKUN

package sg.edu.nus.iss.universitysouvenirstore;

import static org.junit.Assert.*;

import org.junit.Test;

public class DiscountTest {

	private Discount testDiscount = new Discount("TDD_DISCOUNT", "for unit test", "2016-03-03", "55",
			23, "A");


	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Discount#getCode()}.
	 */
	@Test
	public void testGetCode() {
		assertEquals("TDD_DISCOUNT", testDiscount.getCode());
		
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Discount#setCode(java.lang.String)}.
	 */
	@Test
	public void testSetCode() {
		testDiscount.setCode("NEW_TDD_DISCOUNT");
		assertEquals("NEW_TDD_DISCOUNT", testDiscount.getCode());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Discount#getDescription()}.
	 */
	@Test
	public void testGetDescription() {
		assertEquals("for unit test", testDiscount.getDescription());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Discount#setDescription(java.lang.String)}.
	 */
	@Test
	public void testSetDescription() {
		testDiscount.setDescription("test");
		assertEquals("test", testDiscount.getDescription());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Discount#getStartDate()}.
	 */
	@Test
	public void testGetPeriod() {
		assertEquals("55", testDiscount.getPeriod());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Discount#setStartDate(java.lang.String)}.
	 */
	@Test
	public void testSetPeriod() {
		testDiscount.setPeriod("22");
		assertEquals("22", testDiscount.getStartDate());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Discount#getStartDate()}.
	 */
	@Test
	public void testgetStartDate() {
		assertEquals("ABC", testDiscount.getStartDate());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Discount#setStartDate(java.lang.String)}.
	 */
	@Test
	public void testSetStartDate() {
		testDiscount.setStartDate("2017-02-02");
		assertEquals("2017-02-02", testDiscount.getStartDate());
		
		testDiscount.setStartDate("ALWAYS");
		assertEquals("ALWAYS", testDiscount.getStartDate());
		assertEquals("ALWAYS", testDiscount.getPeriod());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Discount#getPercentage()}.
	 */
	@Test
	public void testGetPercentage() {
		assertEquals(23, testDiscount.getPercentage());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Discount#setBriefDescription(java.lang.Integer)}.
	 */
	@Test
	public void testSetPercentage() {
		testDiscount.setPercentage(32);
		assertEquals(32, testDiscount.getPercentage());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Discount#getAvailableQuantity()}.
	 */
	@Test
	public void testGetType() {
		assertEquals("A", testDiscount.getType());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Discount#getReorderLevel()}.
	 */
	@Test
	public void testSetType() {
		testDiscount.setType("M");
		assertEquals("M", testDiscount.getType());
	}

}
