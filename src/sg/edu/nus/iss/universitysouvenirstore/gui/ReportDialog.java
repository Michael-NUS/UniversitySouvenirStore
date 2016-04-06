package sg.edu.nus.iss.universitysouvenirstore.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.universitysouvenirstore.Product;
import sg.edu.nus.iss.universitysouvenirstore.ProductUtils;

/**
 * @author Aung Myo
 *
 */
public class ReportDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Map<String, ArrayList<Product>> productList = new HashMap<String, ArrayList<Product>>();
	private ArrayList<Product> allProducts = new ArrayList<Product>();
	private DefaultListModel<String> productmodel;
	private JTable tblList;
	private JComboBox<String> cbbType = new JComboBox<String>();
	private JScrollPane scrollPane = new JScrollPane();
	private ArrayList<Product> products;
	

	/**
	 * Create the dialog.
	 */
	public ReportDialog() {
		setTitle("Report");
		setBounds(100, 100, 934, 867);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(244, 164, 96));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(4, 31, 73, 36);
		contentPanel.add(lblType);
		
//		JComboBox<String> cbbType = new JComboBox<String>();
		cbbType.setBounds(51, 31, 198, 28);
		contentPanel.add(cbbType);
		
		cbbType.removeAllItems();
		cbbType.addItem("Categories");
		cbbType.addItem("Products");
		cbbType.addItem("Transactions");
		cbbType.addItem("Members");
		
		cbbType.setSelectedItem("Products");
		
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
		
		JButton btnShowReport = new JButton("Show Report");
		
		btnShowReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showReport(cbbType.getSelectedItem().toString());
			}
		});
		
		btnShowReport.setBounds(291, 31, 139, 37);
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
	private void showReport(String strType){
		String[] columnNames = null;
		Object[][] data= null;
		
		switch(strType){
			case"Categories":
				columnNames = new String [3];
				columnNames[0] = "Sr.";
				columnNames[1] = "Code";
				columnNames[2]= "Name";
				
	//			dataInit(ProductUtils.getAllProducts());
	//			products = productList.get("All");
				
	//			if ( products != null && !products.isEmpty()) {
	//				data = new Object [products.size()][3];
	//				for (int i = 0; i < products.size(); i++) 
	//		        {
	//		        	data[i][0] = i + 1;
	//		        	data[i][1] = products.get(i).getProductId();
	//		        	data[i][2] = products.get(i).getProductName();
	//		        }
	//			};
	//			
				data = new Object [1][3];
				data[0][0] = "";
	        	data[0][1] = "";
	        	data[0][2] = "";
				break;
			case "Products": 
				columnNames = new String [3];
				columnNames[0] = "Sr.";
				columnNames[1] = "ID";
				columnNames[2]= "Name";
				
				dataInit(ProductUtils.getAllProducts());
				products = productList.get("All");
				
				if ( products != null && !products.isEmpty()) {
					data = new Object [products.size()][3];
					for (int i = 0; i < products.size(); i++) 
			        {
			        	data[i][0] = i + 1;
			        	data[i][1] = products.get(i).getProductId();
			        	data[i][2] = products.get(i).getProductName();
			        }
				};
				break;
			case"Transactions":
				columnNames = new String [3];
				columnNames[0] = "Sr.";
				columnNames[1] = "Name";
				columnNames[2]= "Description";
				
//				dataInit(ProductUtils.getAllProducts());
//				products = productList.get("All");
				
//				if ( products != null && !products.isEmpty()) {
//					data = new Object [products.size()][3];
//					for (int i = 0; i < products.size(); i++) 
//			        {
//			        	data[i][0] = i + 1;
//			        	data[i][1] = products.get(i).getProductId();
//			        	data[i][2] = products.get(i).getProductName();
//			        }
//				};
//				
				data = new Object [1][3];
				data[0][0] = "";
	        	data[0][1] = "";
	        	data[0][2] = "";
				break;
			case"Members":
				columnNames = new String [4];
				columnNames[0] = "Sr.";
				columnNames[1] = "Name";
				columnNames[2]= "Member ID";
				columnNames[3]= "Loyalty Points";
				
//				dataInit(ProductUtils.getAllProducts());
//				products = productList.get("All");
				
//				if ( products != null && !products.isEmpty()) {
//					data = new Object [products.size()][3];
//					for (int i = 0; i < products.size(); i++) 
//			        {
//			        	data[i][0] = i + 1;
//			        	data[i][1] = products.get(i).getProductId();
//			        	data[i][2] = products.get(i).getProductName();
//			        }
//				};
//				
				data = new Object [1][4];
				data[0][0] = "";
	        	data[0][1] = "";
	        	data[0][2] = "";
	        	data[0][3] = "";
				break;
			default:
				break;
					
		}
	
//		tblList = new JTable();
		tblList = new JTable(data, columnNames);
		scrollPane.setBounds(14, 87, 883, 669);
		contentPanel.add(scrollPane);
		scrollPane.setViewportView(tblList);
	}
}
