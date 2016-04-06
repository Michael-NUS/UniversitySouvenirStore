package sg.edu.nus.iss.universitysouvenirstore;

import java.util.ArrayList;

import org.junit.*;

public class CategoryTest {
	
	private ArrayList<Category> categoryList=new ArrayList<Category>();
	
	@Before
	public void setUp() throws Exception{
		categoryList.add(new Category("CLO","Clothing"));
		categoryList.add(new Category("MUG","Mugs"));
		categoryList.add(new Category("STA","Stationary"));
		categoryList.add(new Category("DIA","Diary"));
	}
	@After
	public void tearDown() throws Exception{
		
	}
	@Test
	public void testGetCategoryList(){
		
	}
	@Test
	public void testAddCategory(){
		
	}
	@Test
	public void testReplaceCategory(){
		
	}
	@Test
	public void testRemoveCategory(){
		
	}
	@Test
	public void testGetPosition(){
		
	}

}
