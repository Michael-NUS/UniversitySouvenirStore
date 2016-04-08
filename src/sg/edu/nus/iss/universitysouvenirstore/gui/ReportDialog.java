package sg.edu.nus.iss.universitysouvenirstore.gui;

import sg.edu.nus.iss.universitysouvenirstore.*;
import sg.edu.nus.iss.universitysouvenirstore.util.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;

/**
 * @author Aung Myo
 *
 */
public class ReportDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Map<String, ArrayList<Product>> productList = new HashMap<String, ArrayList<Product>>();
	private ArrayList<Product> allProducts = new ArrayList<Product>();
	private String curCategoryType = "";
	private DefaultListModel<String> productmodel;
	private JTable tblList;
	private JComboBox<String> cbbType = new JComboBox<String>();
	private JScrollPane scrollPane = new JScrollPane();
	private ArrayList<Product> products;
	private JLabel lblDateFrom;
	private DateFormattedTextField dftxtFrom;
	private JLabel lblDateTo;
	private DateFormattedTextField dftxtTo ;
	private JLabel lblDateFormatFrom;
	private JLabel lblDateFromTo ;
	
	private ArrayList<String> objItems = new ArrayList<String>();
	private StringBuilder fromYYYYMMDD;
	private StringBuilder toYYYYMMDD;
	
	/**
	 * Create the dialog.
	 */
	public ReportDialog() {
		setTitle("Report");
		setBounds(100, 100, 1103, 867);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(244, 164, 96));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(14, 31, 41, 36);
		contentPanel.add(lblType);
		
		lblDateFrom = new JLabel("Date From:");
		lblDateFrom.setBounds(362, 31, 80, 36);
		contentPanel.add(lblDateFrom);
		
		dftxtFrom = new DateFormattedTextField();
		
		Date dateNow = new Date ();
        SimpleDateFormat dateformatYYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
        toYYYYMMDD = new StringBuilder( dateformatYYYYMMDD.format( dateNow ) );
        
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -30);
        Date dateBefore30Days = cal.getTime();
        
        fromYYYYMMDD = new StringBuilder( dateformatYYYYMMDD.format( dateBefore30Days ) );
		
		dftxtFrom.setText(fromYYYYMMDD.toString());
		dftxtFrom.setBounds(445, 31, 100, 36);
		contentPanel.add(dftxtFrom);
		
		lblDateTo = new JLabel("Date To:");
		lblDateTo.setBounds(693, 31, 62, 36);
		contentPanel.add(lblDateTo);
		
		dftxtTo = new DateFormattedTextField();
		dftxtTo.setText(toYYYYMMDD.toString());
		dftxtTo.setBounds(758, 31, 100, 36);
		contentPanel.add(dftxtTo);
		
		lblDateFormatFrom = new JLabel(" (yyyy-mm-dd)");
		lblDateFormatFrom.setBounds(545, 31, 113, 36);
		contentPanel.add(lblDateFormatFrom);
		
		lblDateFromTo = new JLabel(" (yyyy-mm-dd)");
		lblDateFromTo.setBounds(859, 31, 113, 36);
		contentPanel.add(lblDateFromTo);
		
		
		
		cbbType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					JComboBox<?> jcb = (JComboBox<?>) e.getSource();
					String getSelectedItem = (String) jcb.getSelectedItem();
					if(getSelectedItem.equals("Transactions")){
						showHideDateFilters(true);
					}else{
						showHideDateFilters(false);
					}
					clearReport();
				}
			}
		});
		
//		JComboBox<String> cbbType = new JComboBox<String>();
		cbbType.setBounds(58, 31, 269, 36);
		contentPanel.add(cbbType);
		
		cbbType.removeAllItems();
		cbbType.addItem("Categories");
		cbbType.addItem("Products");
		cbbType.addItem("Transactions");
		cbbType.addItem("Members");
		
		cbbType.setSelectedItem("Transactions");
		
		dataInit(ProductUtils.getAllProducts());
		ArrayList<Product> products = productList.get("All");
		productmodel = new DefaultListModel<String>();
		
		if ( products != null && !products.isEmpty()) {
			for (Product one : products) {
				if(one.getAvailableQuantity()>-1){
					productmodel.addElement(one.getProductId() + "-" + one.getProductName());
				}
			}
		}
		
//		curCategoryType = "Products";

//		cbbType.addItemListener(new ItemListener() {
//
//			@Override
//			public void itemStateChanged(ItemEvent e) {
//				// TODO Auto-generated method stub
//				if (e.getStateChange() == ItemEvent.SELECTED) {
//					JComboBox<?> jcb = (JComboBox<?>) e.getSource();
//					curCategoryType = (String) jcb.getSelectedItem();
//					ArrayList<Product> products = productList.get(curCategoryType);
//					productmodel.removeAllElements();
//					if (!products.isEmpty()) {
//						for (Product one : products) {
//							if(one.getAvailableQuantity()>-1){
//								productmodel.addElement(one.getProductId() + "-" + one.getProductName());
//							}
//						}
//					}
//				}
//			}
//
//		});
		
		JButton btnShowReport = new JButton("Generate Report");
		
		btnShowReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showReport(cbbType.getSelectedItem().toString());
			}
		});
		
		btnShowReport.setBounds(14, 83, 163, 37);
		contentPanel.add(btnShowReport);
		
		
		
		

		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnClose = new JButton("Close");
				btnClose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible (false);
				        dispose();
					}
				});
				btnClose.setActionCommand("Cancel");
				buttonPane.add(btnClose);
			}
		}
		
		
		
		showReport(cbbType.getSelectedItem().toString());
		
	}
	
	private void showReport(String strType){
		String[] columnNames = null;
		Object[][] data= null;
		
		switch(strType){
			case"Categories":
				columnNames = new String [3];
				columnNames[0] = "#";
				columnNames[1] = "Category Code";
				columnNames[2]= "Category Name";
				
				getCategoryList();
				
				if ( objItems != null && !objItems.isEmpty()) {
					data = new Object [objItems.size()][3];
					for (int i = 0; i < objItems.size(); i++) { 
						String[] strArr = objItems.get(i).toString().split(",");
						for (int j = 0; j <= strArr.length; j++) {
							if(j== 0){
								data[i][j] = i + 1;
							}else{
								data[i][j] = strArr[j-1];
							}
				        }
					}
				
				}else{
					InfoBox.showInfoBox(this, "No records available to be shown!", "No Records");
				}
				break;
			case "Products": 
				columnNames = new String [9];
				columnNames[0] = "#";
				columnNames[1] = "ID";
				columnNames[2]= "Name";
				columnNames[3] = "Description";
				columnNames[4] = "Qty Available";
				columnNames[5]= "Price";
				columnNames[6] = "Bar Code #";
				columnNames[7]= "Threshold";
				columnNames[8]= "Order Qty";
				
				getProductList();
				
				if ( objItems != null && !objItems.isEmpty()) {
					data = new Object [objItems.size()][9];
					for (int i = 0; i < objItems.size(); i++) { 
						String[] strArr = objItems.get(i).toString().split(",");
						for (int j = 0; j <= strArr.length; j++) {
							if(j== 0){
								data[i][j] = i + 1;
							}else{
								data[i][j] = strArr[j-1];
							}
				        }
					}
				
				}else{
					InfoBox.showInfoBox(this, "No records available to be shown!", "No Records");
				}
				break;
			case"Transactions":
				columnNames = new String [6];
				columnNames[0] = "#";
				columnNames[1] = "Transaction ID";
				columnNames[2]= "Product ID";
				columnNames[3]= "Member ID";
				columnNames[4]= "Quantity Purchased";
				columnNames[5]= "Transaction Date";
				
				getTransactionList(dftxtFrom.getText(), dftxtTo.getText());
				
				if ( objItems != null && !objItems.isEmpty()) {
					data = new Object [objItems.size()][6];
					for (int i = 0; i < objItems.size(); i++) { 
						String[] strArr = objItems.get(i).toString().split(",");
						for (int j = 0; j <= strArr.length; j++) {
							if(j== 0){
								data[i][j] = i + 1;
							}else{
								data[i][j] = strArr[j-1];
							}
				        }
					}
				
				}else{
					InfoBox.showInfoBox(this, "No records available to be shown!", "No Records");
				}
				break;
			case"Members":
				columnNames = new String [4];
				columnNames[0] = "#";
				columnNames[1] = "Name";
				columnNames[2]= "Member ID";
				columnNames[3]= "Loyalty Points";
				
				getMemberList();
				
				if ( objItems != null && !objItems.isEmpty()) {
					data = new Object [objItems.size()][4];
					for (int i = 0; i < objItems.size(); i++) { 
						String[] strArr = objItems.get(i).toString().split(",");
						for (int j = 0; j <= strArr.length; j++) {
							if(j== 0){
								data[i][j] = i + 1;
							}else{
								data[i][j] = strArr[j-1];
							}
				        }
					}
				
				}else{
					InfoBox.showInfoBox(this, "No records available to be shown!", "No Records");
				}
				break;
			default:
				break;
					
		}
	

		if(data == null){
			tblList = new JTable();
		}else{
			tblList = new JTable(data, columnNames);
		}
		
		scrollPane.setBounds(14, 136, 1052, 620);
		contentPanel.add(scrollPane);
		scrollPane.setViewportView(tblList);
		
		
	}
	
	private void clearReport(){
		tblList = new JTable();
		scrollPane.setBounds(14, 136, 1052, 620);
		contentPanel.add(scrollPane);
		scrollPane.setViewportView(tblList);
	}
	
	private void showHideDateFilters(Boolean isShow){
		lblDateFrom.setVisible(isShow);
		dftxtFrom.setVisible(isShow);
		lblDateTo.setVisible(isShow);
		dftxtTo.setVisible(isShow);
		lblDateFormatFrom.setVisible(isShow);
		lblDateFromTo.setVisible(isShow);
	}
	
	private void dataInit(ArrayList<Product> products) {
		productList.clear();
		productList.put("All", products);
		allProducts = products;
		// get the category list from category;
		String[] category = { "CLO", "MUG", "STA" };
		for (String one : category) {
			ArrayList<Product> items = ProductUtils.getProductsForCategory( allProducts,one);
			if (!products.isEmpty()) {
				productList.put(one, items);
			}
		}
	}
	
	private void getTransactionList(String strFromDate, String strToDate) {
		if((strFromDate == null || strFromDate.isEmpty() || strFromDate.contains("_")) ||
				(strToDate == null || strToDate.isEmpty() || strFromDate.contains("_"))){
			objItems.clear();
		} else {
			objItems.clear();
			// get the transaction list from file;
			objItems = FileManagerUtils.getTransactionList("", "");
	
			ArrayList<String> tempObjItems = (ArrayList<String>) objItems.clone();
			String strTransactionDate = "";
			
			
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			Date dtFromDate=null;
			Date dtToDate=null;
			try {
				dtFromDate = dateformat.parse(strFromDate);
				dtToDate = dateformat.parse(strToDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try{
				for(String strLine : tempObjItems){
					strTransactionDate = strLine.split(",")[4];
					Date dtTransactionDate = dateformat.parse(strTransactionDate);
					if(dtTransactionDate.after(dtFromDate) && dtTransactionDate.before(dtToDate)) {
					    // In between
						
					}else{
						objItems.set(objItems.indexOf(strLine),null);
					}
					
				}
				objItems.removeAll(Collections.singleton(null));
			}catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private void getMemberList() {
		objItems.clear();
		objItems = FileManagerUtils.getMemberList();
	}
	
	private void getCategoryList() {
		objItems.clear();
		objItems = FileManagerUtils.getCategoryList();
	}
	
	private void getProductList() {
		objItems.clear();
		objItems = FileManagerUtils.getProductList();
	}
	
}
