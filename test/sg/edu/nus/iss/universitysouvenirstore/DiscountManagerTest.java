// LIU YAKUN

package sg.edu.nus.iss.universitysouvenirstore;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DiscountManagerTest {

	private ArrayList<Discount> discountList = new ArrayList<Discount>();
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		String testDataDir ="./data/sg/edu/nus/iss/universitysouvenirstore/unittestdata";
		PrintWriter pw = new PrintWriter((testDataDir+"/Discounts.dat"));
		pw.close();
		
		FileManagerUtils.fileDir=testDataDir;
		
		DiscountManger.addDiscount("TEST_D1", "Discount Test 1","ALWAYS", "ALWAYS", 12, "M");
		DiscountManger.addDiscount("TEST_D2", "Discount Test 2","2016-05-01", "53", 22, "M");
		DiscountManger.addDiscount("TEST_D3", "Discount Test 3","2016-02-17", "180", 32, "A");
		DiscountManger.addDiscount("TEST_D4", "Discount Test 4","ALWAYS", "ALWAYS", 42, "A");
		DiscountManger.addDiscount("TEST_D5", "Discount Test 5","2016-01-21", "ALWAYS", 52, "A");
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.DiscountManger#ConvertToDiscountArraylist()}.
	 */
	@Test
	public void testConvertToDiscountArraylist() {
		discountList = DiscountManger.convertToDiscountArraylist();
		assertEquals(5,discountList.size());
	}
	
	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.DiscountManger#readExistingDiscountsFromDB()}.
	 */
	@Test
	public void testReadExistingDiscountsFromDB() {
		boolean status = DiscountManger.readExistingDiscountsFromDB();
		assertTrue(status);
	}
	
	
	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.DiscountManger#addNewProduct(java.lang.String, java.lang.String, java.lang.String, java.lang.String,
			java.lang.Integer, java.lang.String)}.
	 */
	@Test
	public void testAddDiscount() {		
		DiscountManger.addDiscount("TEST_D6", "Discount Test 6","2016-03-15", "ALWAYS", 33, "M");
		discountList = DiscountManger.convertToDiscountArraylist();
		assertEquals(6,discountList.size());
	}
	
	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.DiscountManger#getProduct(java.lang.String)}.
	 */
	@Test
	public void testGetDiscount() {		
		Discount checkDiscount = new Discount("TEST_D1", "Discount Test 1","ALWAYS", "ALWAYS", 12, "M");
		assertEquals(checkDiscount.getCode(), DiscountManger.getDiscount("TEST_D1").getCode());
		assertEquals(checkDiscount.getDescription(), DiscountManger.getDiscount("TEST_D1").getDescription());
		assertEquals(checkDiscount.getPercentage(), DiscountManger.getDiscount("TEST_D1").getPercentage());
		assertEquals(checkDiscount.getPeriod(), DiscountManger.getDiscount("TEST_D1").getPeriod());
		assertEquals(checkDiscount.getStartDate(), DiscountManger.getDiscount("TEST_D1").getStartDate());
		assertEquals(checkDiscount.getType(), DiscountManger.getDiscount("TEST_D1").getType());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.DiscountManger#checkExistOfDiscount(java.lang.String)}.
	 */
	@Test
	public void testCheckExistOfDiscount() {
		Discount checkDiscount=new Discount("TEST_D1", "Discount Test 1","ALWAYS", "ALWAYS", 3, "M");
		boolean status=DiscountManger.checkExistOfDiscount(checkDiscount.getCode());
		assertTrue(status);
		
		checkDiscount=new Discount("TEST_D2", "Discount Test 2","ALWAYS", "ALWAYS", 2, "M");
		status=DiscountManger.checkExistOfDiscount(checkDiscount.getCode());
		assertTrue(status);

	}


	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.DiscountManger#getDiscountDescription(java.lang.String)}.
	 */
	@Test
	public void testGetDiscountDescription() {
		assertEquals("Discount Test 1", DiscountManger.getDiscountDescription("TEST_D1"));
	}
	
	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.DiscountManger#updateDiscountDescription(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testUpdateDiscountDescription() {
		String testString = "check description";
		DiscountManger.updateDiscountDescription("TEST_D1", "check description");
		assertEquals(testString, DiscountManger.getDiscountDescription("TEST_D1"));
	}

	
	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.DiscountManger#getDiscountType(java.lang.String)}.
	 */
	@Test
	public void testGetDiscountType() {
		assertEquals("M", DiscountManger.getDiscountType("TEST_D2"));
	}
	
	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.DiscountManger#updateDiscountType(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testUpdateDiscountType() {
		String testString = "A";
		DiscountManger.updateDiscountType("TEST_D2", "A");
		assertEquals(testString, DiscountManger.getDiscountType("TEST_D2"));
	}
	
	
	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.DiscountManger#getDetDiscountPeriod(java.lang.String)}.
	 */
	@Test
	public void testGetDiscountPeriod() {
		assertEquals("180", DiscountManger.getDiscountPeriod("TEST_D3"));
		assertEquals("ALWAYS", DiscountManger.getDiscountPeriod("TEST_D4"));
	}
	
	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.DiscountManger#updateDiscountPeriod(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testUpdateDiscountPeriod() {
		String testString1 = "ALWAYS";
		String testString2 = "180";

		DiscountManger.updateDiscountPeriod("TEST_D3", "ALWAYS");
		assertEquals(testString1, DiscountManger.getDiscountPeriod("TEST_D1"));
		
		DiscountManger.updateDiscountPeriod("TEST_D4", "180");
		assertEquals(testString2, DiscountManger.getDiscountPeriod("TEST_D1"));
	}
	
	
	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.DiscountManger#getDiscountStartDate(java.lang.String)}.
	 */
	@Test
	public void testGetDiscountStartDate() {
		assertEquals("ALWAYS", DiscountManger.getDiscountStartDate("TEST_D4"));
		assertEquals("2016-01-21", DiscountManger.getDiscountStartDate("TEST_D5"));
	}
	
	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.DiscountManger#updateDiscountStartDate(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testUpdateDiscountStartDate() {
		String testString1 = "2016-01-18";
		String testString2 = "ALWAYS";

		DiscountManger.updateDiscountStartDate("TEST_D4", "2016-01-18");
		assertEquals(testString1, DiscountManger.getDiscountStartDate("TEST_D4"));
		
		DiscountManger.updateDiscountStartDate("TEST_D5", "ALWAYS");
		assertEquals(testString2, DiscountManger.getDiscountStartDate("TEST_D5"));
		assertEquals(testString2, DiscountManger.getDiscountPeriod("TEST_D5"));
	}
	
	
	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.DiscountManger#getDiscountPercentage(java.lang.String)}.
	 */
	@Test
	public void testGetDiscountPercentage() {
		assertEquals(12, DiscountManger.getDiscountPercentage("TEST_D1"));
	}
	
	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.DiscountManger#updateDiscountPercentage(java.lang.String, java.lang.Integer)}.
	 */
	@Test
	public void testUpdateDiscountPercentage() {
		DiscountManger.updateDiscountPercentage("TEST_D1", 18);
		assertEquals(18, DiscountManger.getDiscountPercentage("TEST_D1"));
	}
	

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.DiscountManger#getHighestDiscount(java.lang.String)}.
	 */
	@Test
	public void testGetHighestDiscount() {
		DiscountManger.removeDiscount("TEST_D1");
		DiscountManger.removeDiscount("TEST_D2");
		DiscountManger.removeDiscount("TEST_D3");
		DiscountManger.removeDiscount("TEST_D4");
		DiscountManger.removeDiscount("TEST_D5");
		DiscountManger.removeDiscount("TEST_D6");
				
		DiscountManger.addDiscount("TEST_D1", "Discount Test 1","ALWAYS", "ALWAYS", 10, "M");
		DiscountManger.addDiscount("TEST_D2", "Discount Test 2","2016-01-01", "180", 30, "M");
		DiscountManger.addDiscount("TEST_D3", "Discount Test 3","2016-02-17", "365", 20, "A");
		DiscountManger.addDiscount("TEST_D4", "Discount Test 4","ALWAYS", "ALWAYS", 15, "A");
		DiscountManger.addDiscount("TEST_D5", "Discount Test 5","2016-08-21", "ALWAYS", 8, "A");
		DiscountManger.addDiscount("MEMBER_FIRST", "MEMBER FIRST TIME DISCOUNT","ALWAYS", "ALWAYS", 33, "M");
		
		int highDiscount1 = DiscountManger.getHighestDiscount("X437F356"); // regular member case
		int highDiscount2 = DiscountManger.getHighestDiscount("R64565FG4"); // 1st-time member case
		int highDiscount3 = DiscountManger.getHighestDiscount("PUBLIC"); // non-member case

		assertEquals(30, highDiscount1);
		assertEquals(33, highDiscount2);
		assertEquals(20, highDiscount3);		
	}

	
	
	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.DiscountManger#clearDiscountsMap()}.
	 */
	@Test
	public void testClearDiscountsMap() {
		int status = DiscountManger.clearDiscountsMap();
		assertEquals(0, status);		
	}
	
	
	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.DiscountManger#convertToDate(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testConvertToDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				
		String testStartDate = "2016-03-20";
		String offset = "10";
		String testEndDate = "2016-03-30";
		Date startDate = DiscountManger.convertToDate(testStartDate, "0");
		Date endDate = DiscountManger.convertToDate(testStartDate, offset);
		
		String checkStartDate = sdf.format(startDate);
		String checkEndDate = sdf.format(endDate);

		assertEquals(checkStartDate, testStartDate);
		assertEquals(checkEndDate, testEndDate);
	}


	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.DiscountManger#removeDiscount(java.util.String)}.
	 */
	@Test
	public void testRemoveDiscount() {	
		boolean testStatus1 = DiscountManger.removeDiscount("TEST_D1");
		boolean testStatus2 = DiscountManger.removeDiscount("TEST_D99");
		assertTrue(testStatus1);
		assertTrue(testStatus2);
	}
}
