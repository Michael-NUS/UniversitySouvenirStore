/**
 * @author nyinyizin
 * @version 1.0
 */
package sg.edu.nus.iss.universitysouvenirstore.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sg.edu.nus.iss.universitysouvenirstore.Category;
import sg.edu.nus.iss.universitysouvenirstore.CategoryUtils;
import sg.edu.nus.iss.universitysouvenirstore.CategoryVendorMgr;
import sg.edu.nus.iss.universitysouvenirstore.CustomException;
import sg.edu.nus.iss.universitysouvenirstore.Vendor;
import sg.edu.nus.iss.universitysouvenirstore.VendorUtils;

public class CategoryManagerDialog extends JDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private CategoryInfoDialog categoryInfoDialog=new CategoryInfoDialog();
	private VendorInfoDialog vendorInfoDialog=new VendorInfoDialog();
	private CategoryVendorMgr categoryVendorMgr=new CategoryVendorMgr();
	private CategoryUtils categoryUtils;
	private VendorUtils vendorUtils;
	private DefaultListModel<String> categoryListModel=new DefaultListModel<String>();
	JList<String> jlist = new JList<String>();
	JComboBox<String> comboBox = new JComboBox<String>();
	JButton btnAddNewCategory = new JButton("Add New Category");
	JButton btnEditCategory = new JButton("Edit Category");
	JButton btnRemoveCategory = new JButton("Remove Category");
	JButton btnEditVendor = new JButton("Edit Vendor");
	JButton btnAddVendor = new JButton("Add Vendor");
	JButton btnRemoveVendor = new JButton("Remove Vendor");
	JLabel lblCategoryDescription = new JLabel("");
	/**
	 * Create the dialog.
	 */
	public CategoryManagerDialog() {



		//scrollPane.setViewportView(jlist);

		try {
			categoryUtils=categoryVendorMgr.getCategoryUtil();
		} catch (CustomException e1) {
			JOptionPane.showMessageDialog(null, "Loading Data get Errors","Error",JOptionPane.ERROR_MESSAGE);
		}
		setTitle("Category manager");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(244, 164, 96));
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		btnEditVendor.setEnabled(false);
		btnRemoveVendor.setEnabled(false);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			jlist.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					btnEditVendor.setEnabled(true);
					btnRemoveVendor.setEnabled(true);
				}
			});
			jlist.setBounds(5, 87, 282, 162);
			contentPanel.add(jlist);
		}
		categoryInfoDialog.setCategoryMgr(this);
		vendorInfoDialog.setCategoryManager(this);
		btnAddNewCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				categoryInfoDialog.setTitle("Add New Category");
				categoryInfoDialog.clearEditData();
				categoryInfoDialog.showDialog();
			}
		});
		btnAddNewCategory.setBounds(296, 6, 134, 50);
		contentPanel.add(btnAddNewCategory);


		btnEditCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Category c=categoryUtils.getCategory(comboBox.getSelectedItem().toString());
				categoryInfoDialog.position=categoryUtils.getCategoryPosition(c.getCategoryId());
				categoryInfoDialog.setEditData(c.getCategoryId(),c.getCategoryDescription());
				categoryInfoDialog.setTitle("Edit Category");
				categoryInfoDialog.showDialog();
			}
		});
		btnEditCategory.setBounds(299, 61, 131, 50);
		contentPanel.add(btnEditCategory);


		btnRemoveCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String categoryId=comboBox.getSelectedItem().toString();
				categoryUtils.removeCategory(categoryId);
				loadCategoryData();
				if(categoryUtils.getCategoryList().size()==0){
					btnRemoveCategory.setEnabled(false);
				}
			}
		});
		btnRemoveCategory.setBounds(299, 119, 131, 50);
		contentPanel.add(btnRemoveCategory);


		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1){
					loadVendorData(e.getItem().toString());
					Category c=categoryUtils.getCategory(e.getItem().toString());
					lblCategoryDescription.setText(c.getCategoryDescription());
				}

			}
		});

		comboBox.setBounds(0, 34, 117, 27);
		contentPanel.add(comboBox);
		loadCategoryData();
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(5, 6, 61, 16);
		contentPanel.add(lblCategory);

		JLabel lblVendors = new JLabel("Vendors");
		lblVendors.setBounds(5, 67, 61, 16);
		contentPanel.add(lblVendors);


		btnAddVendor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vendorInfoDialog.setTitle("Add New Vendor");
				vendorInfoDialog.categoryId=comboBox.getSelectedItem().toString();
				vendorInfoDialog.clearEditData();
				vendorInfoDialog.showDialog();
			}
		});
		btnAddVendor.setBounds(5, 249, 117, 29);
		contentPanel.add(btnAddVendor);


		btnRemoveVendor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] vendor=jlist.getSelectedValue().split(",");
				String categoryId=comboBox.getSelectedItem().toString();
				vendorUtils.removeVendor(vendor[0], comboBox.getSelectedItem().toString());
				loadVendorData(comboBox.getSelectedItem().toString());
			}
		});
		btnRemoveVendor.setBounds(264, 249, 141, 29);
		contentPanel.add(btnRemoveVendor);


		btnEditVendor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vendorInfoDialog.setTitle("Edit Vendor");
				vendorInfoDialog.categoryId=comboBox.getSelectedItem().toString();
				String[] vendor=jlist.getSelectedValue().split(",");
				String selectedVendor=vendor[0];
				Vendor v=vendorUtils.getVendor(selectedVendor,vendorInfoDialog.categoryId);
				vendorInfoDialog.position=vendorUtils.getVendorPosition(v.getVendorName(),vendorInfoDialog.categoryId);
				vendorInfoDialog.setEditData(v.getVendorName(), v.getVendorDecription());
				vendorInfoDialog.showDialog();
			}
		});
		btnEditVendor.setBounds(135, 249, 117, 29);
		contentPanel.add(btnEditVendor);


		lblCategoryDescription.setBounds(129, 40, 150, 16);
		contentPanel.add(lblCategoryDescription);
	}
	public void loadCategoryData(){
		comboBox.removeAllItems();
		ArrayList<Category> clist=new ArrayList<Category>();
		try {
			clist = categoryVendorMgr.getCategoryUtil().getCategoryList();
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<clist.size();i++){
			comboBox.addItem(clist.get(i).getCategoryId());
		}
		if(clist.size()==0){
			btnEditCategory.setEnabled(false);
			btnRemoveCategory.setEnabled(false);
		}else{
			btnEditCategory.setEnabled(true);
			btnRemoveCategory.setEnabled(true);
		}
	}
	public void loadVendorData(String categoryId){
		categoryListModel.clear();
		 vendorUtils=categoryVendorMgr.getVendorByCategory(categoryId);
		 ArrayList<Vendor> vList=vendorUtils.getVendorList(categoryId);
		if(vList!=null){
			for(Vendor v:vList){
				categoryListModel.addElement(v.toString());
				jlist.setModel(categoryListModel);
			 }
			if(vList.size()==0 || jlist.isSelectionEmpty()){
				 btnEditVendor.setEnabled(false);
				 btnRemoveVendor.setEnabled(false);
			 }else{

				 btnEditVendor.setEnabled(true);
				 btnRemoveVendor.setEnabled(true);
			 }
		}

	}
	public void updateManager(String name,String description,int position) throws CustomException{
		System.out.println(name+' '+description+" position: "+position);
		if(position!=-1){
			categoryUtils.replaceCategory(position, new Category(name, description));
		}else{
			try{
			categoryUtils.addCategory(name, description);
			}catch(CustomException e){
				throw e;
			}
		}
		loadCategoryData();
	}
	public void updateVendorManager(String name,String description,int position,String categoryId) throws CustomException{
		System.out.println(name+' '+description+" position: "+position+"  -categoryid = "+categoryId);
		if(position!=-1){
			vendorUtils.replaceVendor(position, new Vendor(name,description), categoryId);
			loadVendorData(categoryId);
		}else{
			try{
				vendorUtils.addVendor(name, description, categoryId);
				loadVendorData(categoryId);
			}catch(CustomException e){
				throw e;
			}
		}
	}
}
