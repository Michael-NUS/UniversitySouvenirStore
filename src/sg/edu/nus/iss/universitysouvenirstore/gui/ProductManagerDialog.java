/**
 * linwei
 */
package sg.edu.nus.iss.universitysouvenirstore.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.universitysouvenirstore.Product;
import sg.edu.nus.iss.universitysouvenirstore.ProductUtils;
import sg.edu.nus.iss.universitysouvenirstore.util.ConfirmDialog;
import sg.edu.nus.iss.universitysouvenirstore.util.FileManangerUtils;

import javax.swing.JScrollPane;

public class ProductManagerDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ProductInfoDialog productInfoDialog = new ProductInfoDialog();
	private Map<String, ArrayList<Product>> productList = new HashMap<String, ArrayList<Product>>();
	private ArrayList<Product> allProducts = new ArrayList<Product>();
	private String curCategoryType = "";
	private JComboBox<String> categoryList;
	private JList<String> jlist;
	private DefaultListModel<String> productmodel;
	
	/**
	 * Create the dialog.
	 */
	public ProductManagerDialog() {
		// contentPanel.setBackground(new Color(244, 164, 96));
	
		setTitle("Product manager");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(244, 164, 96));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		jlist = new JList<String>();
		jlist.setBounds(5, 56, 282, 113);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 56, 282, 113);
		contentPanel.add(scrollPane);
		scrollPane.setViewportView(jlist);
		//contentPanel.add(jlist);

		
		productInfoDialog.setProductManagerDialog(this);
		JButton btnAddNewProduct = new JButton("Add New Product");
		btnAddNewProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				productInfoDialog.setTitle("New Product");	
				productInfoDialog.setProducts(allProducts);
				productInfoDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				productInfoDialog.setEnabled(true);
				productInfoDialog.setVisible(true);
				productInfoDialog.setCurProduct(null);
				productInfoDialog.setEditCase(false);
				productInfoDialog.dataInit();

			}
		});
		btnAddNewProduct.setBounds(297, 10, 127, 37);
		contentPanel.add(btnAddNewProduct);

		JButton btnEditProduct = new JButton("Edit Product");
		btnEditProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get selected item

				String item = jlist.getSelectedValue();
				if (item != null) {
					String[] productInfo = item.split("-");
					Product product = ProductUtils.getProductById(allProducts, productInfo[0]);
					productInfoDialog.setCurProduct(product);
					productInfoDialog.setProducts(allProducts);
					productInfoDialog.setTitle("Edit Product");
					productInfoDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					productInfoDialog.setEnabled(true);
					productInfoDialog.setVisible(true);
					productInfoDialog.setEditCase(true);
					productInfoDialog.dataInit();
				}

			}
		});
		btnEditProduct.setBounds(297, 56, 127, 37);
		contentPanel.add(btnEditProduct);

		JButton btnRemove = new JButton("Remove Product");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String item = jlist.getSelectedValue();
				String[] productInfo = item.split("-");
				
				
		        String title = "Remove Product";
		        String msg = "Do you really want to remove product " + productInfo[1] + " ?";
		        ConfirmDialog d = new ConfirmDialog (null, title, msg) {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					protected boolean performOkAction () {
						ProductUtils.removeProduct(allProducts, productInfo[0]);
						updateItSelf();
		                return true;
		            }
		        };
		        d.pack();
		        d.setVisible (true);

			}
		});
		btnRemove.setBounds(297, 103, 127, 37);
		contentPanel.add(btnRemove);

		categoryList = new JComboBox<String>();
		categoryList.setBounds(93, 18, 162, 28);
		contentPanel.add(categoryList);

		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(10, 10, 73, 36);
		contentPanel.add(lblCategory);
	

		dataInit(ProductUtils.getAllProducts());
		for (Entry<String, ArrayList<Product>> one : productList.entrySet()) {
			String category = one.getKey();
			if (!category.isEmpty()) {
				categoryList.addItem(category);
			}
		}
		categoryList.setSelectedItem("All");
		ArrayList<Product> products = productList.get("All");
		productmodel = new DefaultListModel<String>();
		jlist.setModel(productmodel);
		jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		if (!products.isEmpty()) {
			for (Product one : products) {
				productmodel.addElement(one.getProductId() + "-" + one.getProductName());
			}
		}
		curCategoryType = "All";

		categoryList.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					JComboBox<?> jcb = (JComboBox<?>) e.getSource();
					curCategoryType = (String) jcb.getSelectedItem();
					ArrayList<Product> products = productList.get(curCategoryType);
					productmodel.removeAllElements();
					if (!products.isEmpty()) {
						for (Product one : products) {
							productmodel.addElement(one.getProductId() + "-" + one.getProductName());
						}
					}
				}
			}

		});

		// ArrayList<Product> products =

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
	public void updateItSelf(){
		dataInit(allProducts);
		ArrayList<Product> products = productList.get(curCategoryType);
		productmodel.removeAllElements();
		if (!products.isEmpty()) {
			for (Product one : products) {
				productmodel.addElement(one.getProductId() + "-" + one.getProductName());
			}
		}
		FileManangerUtils.saveDataToDatFile(Product.class, allProducts);
	}
}
