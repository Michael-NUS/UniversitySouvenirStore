package sg.edu.nus.iss.universitysouvenirstore;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

public class CategoryTest {

	private ArrayList<Category> categoryList=new ArrayList<Category>();
	CategoryUtils cUtils=new CategoryUtils();

	@Before
	public void setUp() throws Exception{
		FileManagerUtils.fileDir="./data/sg/edu/nus/iss/universitysouvenirstore/unittestdata";
		categoryList.add(new Category("CLO","Clothing"));
		categoryList.add(new Category("MUG","Mugs"));
		categoryList.add(new Category("STA","Stationary"));
		categoryList.add(new Category("DIA","Diary"));
		cUtils.setCategoryList(categoryList);
	}
	@After
	public void tearDown() throws Exception{

	}
	@Test
	public void testGetCategoryList(){
		assertTrue(categoryList.size()==cUtils.getCategoryList().size());
	}
	@Test
	public void testAddCategory() throws CustomException{
		cUtils.addCategory("CTT", "Testing Category");
		assertTrue(cUtils.getCategoryList().size()==5);
		
	}
	@Test
	public void testReplaceCategory() throws CustomException{
		Category c=new Category("CLO","Cooling");
		cUtils.replaceCategory(0, c);
		assertTrue(cUtils.getCategoryList().get(0).getCategoryDescription().equals("Cooling"));
	}
	@Test
	public void testRemoveCategory(){
		cUtils.removeCategory("CLO");
		assertTrue(cUtils.getCategoryPosition("CLO")==-1);
	}
	@Test
	public void testGetPosition(){
		assertTrue(cUtils.getCategoryPosition("CLO")==0);
		assertTrue(cUtils.getCategoryPosition("MUG")==1);
	}

}
