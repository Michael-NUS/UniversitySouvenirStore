package sg.edu.nus.iss.universitysouvenirstore;

import static org.junit.Assert.*;

import java.util.*;
import org.junit.*;

public class VendorTest {
	HashMap<String,ArrayList<Vendor>> vMap=new HashMap<String,ArrayList<Vendor>>();
	VendorUtils vUtils=new VendorUtils();
	
	@Before
	public void setUp()throws Exception{
		FileManagerUtils.fileDir="./data/sg/edu/nus/iss/universitysouvenirstore/unittestdata";
		ArrayList<Vendor> vlist=new ArrayList<Vendor>();
		vlist.add(new Vendor("Nancy","Nancy Clothing"));
		vlist.add(new Vendor("Mancy","Mancy Fashion"));
		vMap.put("CTO", vlist);
		vlist=new ArrayList<Vendor>();
		vlist.add(new Vendor("Milky","Milky & Bros"));
		vlist.add(new Vendor("Dairy","Dairy One"));
		vMap.put("DIA", vlist);
		for(String s:vMap.keySet()){
			vUtils.setVendorList(vMap.get(s), s);
		}
	}
	
	@After
	public void tearDown() throws Exception{

	}
	
	/**
	 * Get Vendor List Test
	 */
	@Test
	public void testGetVendorList(){
		assertTrue(vUtils.getVendorList("DIA").size()==2);
	}
	
	/**
	 * Get Vendor Object Test
	 */
	@Test
	public void testGetVendor(){
		assertTrue(vUtils.getVendor("Mommy", "CTO")==null);
		assertTrue(vUtils.getVendor("Nancy", "CTO").getVendorName().equals("Nancy"));
	}
	
	/**
	 * Setting Vendor List Test
	 */
	@Test
	public void testSetVendorList(){
		ArrayList<Vendor> vlist=new ArrayList<Vendor>();
		vlist.add(new Vendor("Owe","Owe Snacks"));
		vlist.add(new Vendor("Meggie","Meggie Noodles"));
		vMap.put("FOT",vlist);
		vUtils.setVendorList(vMap.get("FOT"), "FOT");
		assertTrue(vUtils.getVendorList("FOT").size()==2);
		assertTrue(vUtils.getVendor("Owe", "FOT").getVendorName().equals("Owe"));
	}
	
	/**
	 * Create New Vendor Test
	 * @throws CustomException
	 */
	@Test
	public void testAddVendorList() throws CustomException{
		vUtils.addVendor("Motto", "Motto Candy", "DIA");
		assertTrue(vUtils.getVendorList("DIA").size()==3);
		assertTrue(vUtils.getVendorPosition("Motto", "DIA")!=-1);
	}
	
	/**
	 * Get Vendor Position Test
	 */
	@Test
	public void testGetVendorPosition(){
		assertTrue(vUtils.getVendorPosition("Nancy", "CTO")!=-1);
	}
	
	/**
	 * Vendor Update Test
	 * @throws CustomException
	 */
	@Test
	public void testReplaceVendor() throws CustomException{
		Vendor v=new Vendor("One","One and only");
		vUtils.addVendor(v.getVendorName(), v.getVendorDecription(), "DIA");
		int originalPosition=vUtils.getVendorPosition("One", "DIA");
		vUtils.replaceVendor(originalPosition, new Vendor("Onne","Onne Yogurt"), "DIA");
		assertTrue(vUtils.getVendorPosition("Onne", "DIA")==originalPosition);
	}
	
	/**
	 * Vendor Remove Test Test
	 * @throws CustomException
	 */
	@Test
	public void testRemoveVendor() throws CustomException{
		Vendor v=new Vendor("Two","Two in one");
		vUtils.addVendor(v.getVendorName(), v.getVendorDecription(), "DIA");
		assertTrue(vUtils.getVendorPosition("Two", "DIA")!=-1);
		vUtils.removeVendor("Two", "DIA");
		assertTrue(vUtils.getVendorPosition("Two", "DIA")==-1);
	}
	
	
	
}
