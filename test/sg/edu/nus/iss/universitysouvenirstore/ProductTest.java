/**
 * 
 */
package sg.edu.nus.iss.universitysouvenirstore;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author 2016Äê4ÔÂ3ÈÕlinwei
 *
 */
public class ProductTest {

	private Product product = new Product("ABC/1", "pen","this is a pen",10,10.11,5,"ABC",10);


	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Product#getReorderQuantity()}.
	 */
	@Test
	public void testGetReorderQuantity() {
		assertEquals(10, product.getReorderQuantity());
		
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Product#setReorderQuantity(int)}.
	 */
	@Test
	public void testSetReorderQuantity() {
		product.setReorderQuantity(15);
		assertEquals(15, product.getReorderQuantity());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Product#getProductId()}.
	 */
	@Test
	public void testGetProductId() {
		assertEquals("ABC/1", product.getProductId());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Product#setProductId(java.lang.String)}.
	 */
	@Test
	public void testSetProductId() {
		product.setProductId("ABC/3");
		assertEquals("ABC/3", product.getProductId());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Product#getCategoryId()}.
	 */
	@Test
	public void testGetCategoryId() {
		assertEquals("ABC", product.getCategoryId());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Product#setCategoryId(java.lang.String)}.
	 */
	@Test
	public void testSetCategoryId() {
		product.setCategoryId("BCD");
		assertEquals("BCD", product.getCategoryId());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Product#getProductName()}.
	 */
	@Test
	public void testGetProductName() {
		assertEquals("pen", product.getProductName());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Product#setProductName(java.lang.String)}.
	 */
	@Test
	public void testSetProductName() {
		product.setProductName("pen1");
		assertEquals("pen1", product.getProductName());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Product#getBriefDescription()}.
	 */
	@Test
	public void testGetBriefDescription() {
		assertEquals("this is a pen", product.getBriefDescription());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Product#setBriefDescription(java.lang.String)}.
	 */
	@Test
	public void testSetBriefDescription() {
		product.setBriefDescription("this is a pen1");
		assertEquals("this is a pen1", product.getBriefDescription());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Product#getAvailableQuantity()}.
	 */
	@Test
	public void testGetAvailableQuantity() {
		assertEquals(10, product.getAvailableQuantity());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Product#getReorderLevel()}.
	 */
	@Test
	public void testGetReorderLevel() {
		assertEquals(5, product.getReorderLevel());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Product#setReorderLevel(int)}.
	 */
	@Test
	public void testSetReorderLevel() {
		product.setReorderLevel(6);
		assertEquals(6, product.getReorderLevel());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Product#setAvailableQuantity(int)}.
	 */
	@Test
	public void testSetAvailableQuantity() {
		product.setAvailableQuantity(16);
		assertEquals(16, product.getAvailableQuantity());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Product#getPrice()}.
	 */
	@Test
	public void testGetPrice() {
		assertEquals(10.11, product.getPrice(),0.001);
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Product#setPrice(double)}.
	 */
	@Test
	public void testSetPrice() {
		product.setPrice(16);
		assertEquals(16, product.getPrice(),0.001);
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.Product#updateAvaliableQuantity(int, int)}.
	 */
	@Test
	public void testUpdateAvaliableQuantity() {
		product.setAvailableQuantity(16);
		assertEquals(16, product.getAvailableQuantity());
		product.updateAvaliableQuantity(19, 0);
		assertEquals(19, product.getAvailableQuantity());
		product.updateAvaliableQuantity(9, 1);
		assertEquals(10, product.getAvailableQuantity());
		product.updateAvaliableQuantity(7, 2);
		assertEquals(17, product.getAvailableQuantity());
		
	}

}
