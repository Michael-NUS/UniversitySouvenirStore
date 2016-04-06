/**
 * 
 */
package sg.edu.nus.iss.universitysouvenirstore;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author 2016Äê4ÔÂ3ÈÕlinwei
 *
 */
public class ProductUtilsTest {

	private ArrayList<Product> products = new ArrayList<Product>();
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		FileManagerUtils.fileDir="./data/sg/edu/nus/iss/universitysouvenirstore/unittestdata";
		
		products.add(new Product("ABC/1", "pen1","this is a pen1",4,10,5,"ABC",10));
		products.add(new Product("ABC/2", "pen2","this is a pen2",4,10,5,"ABC",10));
		products.add(new Product("ABC/3", "pen3","this is a pen3",10,10,5,"ABC",10));
		products.add(new Product("CBD/1", "pencil1","this is a pencil1",10,10,5,"CBD",10));
		products.add(new Product("CBD/2", "pencil2","this is a pencil2",10,10,5,"CBD",10));

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.ProductUtils#addNewProduct(java.util.ArrayList, java.lang.String, java.lang.String, java.lang.String, int, double, int, int)}.
	 */
	@Test
	public void testAddNewProduct() {
		ProductUtils.addNewProduct(products, "CBD/3","pencil3","this is a pencil3",10,10.0,10,5);
		assertEquals(6,products.size());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.ProductUtils#checkExistOfProduct(java.util.ArrayList, sg.edu.nus.iss.universitysouvenirstore.Product)}.
	 */
	@Test
	public void testCheckExistOfProduct() {
		Product checkProduct=new Product("ABC/1", "pen13","this is a pen1",10,10,5,"ABC",10);
		int status=ProductUtils.checkExistOfProduct(products,checkProduct);
		assertEquals(-1, status);
		
		checkProduct=new Product("ABC/13", "pen1","this is a pen1",10,10,5,"ABC",10);
		status=ProductUtils.checkExistOfProduct(products,checkProduct);
		assertEquals(-2, status);

	}



	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.ProductUtils#productIdGenerator(java.lang.String)}.
	 */
	@Test
	public void testProductIdGenerator() {
		
		String id = ProductUtils.productIdGenerator("CLO");
		assertEquals(id,"CLO/2");
	}


	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.ProductUtils#editProduct(sg.edu.nus.iss.universitysouvenirstore.Product, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Double, java.lang.Integer, java.lang.Integer)}.
	 */
	@Test
	public void testEditProduct() {

		Product product=new Product("ABC/16", "pen16","this is a pen16",11,11,6,"ABC",11);
		ProductUtils.editProduct(products.get(0), "ABC/16", "pen16","this is a pen16",11,11.0,6,11);
		assertEquals(product, products.get(0));
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.ProductUtils#getAllProducts()}.
	 */
	@Test
	public void testGetAllProducts() {
		//fail("Not yet implemented");
		ArrayList<Product> products = ProductUtils.getAllProducts();
		assertEquals(3,products.size());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.ProductUtils#getProductsForCategory(java.util.ArrayList, java.lang.String)}.
	 */
	@Test
	public void testGetProductsForCategory() {
	
		ArrayList<Product> productList1= ProductUtils.getProductsForCategory(products, "CBD");
		assertEquals(2, productList1.size());
		
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.ProductUtils#getProductById(java.util.ArrayList, java.lang.String)}.
	 */
	@Test
	public void testGetProductById() {
		Product test =new Product("CBD/1", "pencil1","this is a pencil1",10,10,5,"CBD",10);
		Product product = ProductUtils.getProductById(products, "CBD/1");
		assertEquals(test,product);
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.ProductUtils#getReorderProductList(java.util.ArrayList)}.
	 */
	@Test
	public void testGetReorderProductList() {
		ArrayList<Product> productList = new ArrayList<Product>();
		ArrayList<Product> productList1=null;
		productList.add(new Product("ABC/1", "pen1","this is a pen1",4,10,5,"ABC",10));
		productList.add(new Product("ABC/2", "pen2","this is a pen2",4,10,5,"ABC",10));
		productList1 = ProductUtils.getReorderProductList(products);
		assertArrayEquals(productList.toArray(),productList1.toArray());
	}



	/**
	 * Test method for {@link sg.edu.nus.iss.universitysouvenirstore.ProductUtils#removeProduct(java.util.ArrayList, sg.edu.nus.iss.universitysouvenirstore.Product)}.
	 */
	@Test
	public void testRemoveProduct() {
	
		ProductUtils.removeProduct( products, "ABC/1");
		assertEquals(-1, products.get(0).getAvailableQuantity());
	}
}
