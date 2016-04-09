/**
 * linwei
 **/
package sg.edu.nus.iss.universitysouvenirstore.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.universitysouvenirstore.Category;
import sg.edu.nus.iss.universitysouvenirstore.CategoryVendorMgr;
import sg.edu.nus.iss.universitysouvenirstore.CustomException;
import sg.edu.nus.iss.universitysouvenirstore.Product;
import sg.edu.nus.iss.universitysouvenirstore.ProductUtils;
import sg.edu.nus.iss.universitysouvenirstore.util.DoubleTextField;
import sg.edu.nus.iss.universitysouvenirstore.util.IntegerTextField;

public class ProductInfoDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtProductName;
	private DoubleTextField txtPrice;
	private IntegerTextField txtQuantity;
	private IntegerTextField txtReorderLevel;
	private JTextField txtBrief;
	private String title;
	private JComboBox<String> comboBoxCategory;
	private JLabel lblProductId;
	private boolean isEditCase = false;
	private Product curProduct = null;
	private JButton okButton = null;
	private ArrayList<Product> products = null;
	private ProductManagerDialog productManagerDialog = null;
	private IntegerTextField txtReorderQuantity;

	/**
	 * get the  ProductManagerDialog
	 * @return productManagerDialog
	 */
	public ProductManagerDialog getProductManagerDialog() {
		return productManagerDialog;
	}
	/**
	 * setProductManagerDialog
	 * @param productManagerDialog
	 */
	public void setProductManagerDialog(ProductManagerDialog productManagerDialog) {
		this.productManagerDialog = productManagerDialog;
	}

	/**
	 * get current product
	 * @return product
	 */
	public Product getCurProduct() {
		return curProduct;
	}
	/**
	 * get all products
	 * @return arrayList of product
	 */
	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	/**
	 * flag to show it is in edit product mode or add new product mode
	 * @return boolean
	 */
	public boolean isEditCase() {
		return isEditCase;
	}

	public void setEditCase(boolean isEditCase) {
		this.isEditCase = isEditCase;
	}

	public void setCurProduct(Product curProduct) {
		this.curProduct = curProduct;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Create the dialog.
	 */
	public ProductInfoDialog() {
		// contentPanel.setBackground(new Color(244, 164, 96));
		setTitle(title);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(244, 164, 96));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setBounds(215, 10, 73, 27);
		contentPanel.add(lblCategory);

		JLabel lblProductName = new JLabel("Product Name:");
		lblProductName.setBounds(10, 39, 88, 27);
		contentPanel.add(lblProductName);

		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(10, 93, 59, 27);
		contentPanel.add(lblPrice);

		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(10, 67, 59, 27);
		contentPanel.add(lblQuantity);

		JLabel lblBriefDescription = new JLabel("Brief Description");
		lblBriefDescription.setBounds(10, 116, 138, 27);
		contentPanel.add(lblBriefDescription);

		JLabel lblReorder = new JLabel("Reorder Level:");
		lblReorder.setBounds(193, 42, 106, 27);
		contentPanel.add(lblReorder);

		txtProductName = new JTextField();
		txtProductName.setBounds(108, 42, 66, 21);
		contentPanel.add(txtProductName);
		txtProductName.setColumns(10);

		txtPrice = new DoubleTextField();
		txtPrice.setBounds(108, 96, 66, 21);
		contentPanel.add(txtPrice);
		txtPrice.setColumns(10);

		txtQuantity = new IntegerTextField();
		txtQuantity.setBounds(108, 70, 66, 21);
		contentPanel.add(txtQuantity);
		txtQuantity.setColumns(10);

		txtReorderLevel = new IntegerTextField();
		txtReorderLevel.setBounds(328, 42, 66, 21);
		contentPanel.add(txtReorderLevel);
		txtReorderLevel.setColumns(10);

		txtBrief = new JTextField();
		txtBrief.setBounds(10, 143, 401, 57);
		contentPanel.add(txtBrief);
		txtBrief.setColumns(10);

		comboBoxCategory = new JComboBox<String>();
		comboBoxCategory.setBounds(328, 13, 66, 21);
		contentPanel.add(comboBoxCategory);

		JLabel label1 = new JLabel("ProductId:");
		label1.setBounds(10, 10, 88, 27);
		contentPanel.add(label1);

		lblProductId = new JLabel("New label");
		lblProductId.setBounds(108, 16, 54, 15);
		contentPanel.add(lblProductId);
		
		JLabel lblReorderQuantity = new JLabel("Reorder Quantity:");
		lblReorderQuantity.setBounds(193, 73, 106, 27);
		contentPanel.add(lblReorderQuantity);
		
		txtReorderQuantity = new IntegerTextField();
		txtReorderQuantity.setColumns(10);
		txtReorderQuantity.setBounds(328, 70, 66, 21);
		contentPanel.add(txtReorderQuantity);
	

		comboBoxCategory.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					JComboBox<?> jcb = (JComboBox<?>) e.getSource();
					String curCategory = (String) jcb.getSelectedItem();

					String newProductId = ProductUtils.productIdGenerator(curCategory); 
					lblProductId.setText(newProductId);

				}
			}

		});
	
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			okButton = new JButton();
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String productId = lblProductId.getText();
					String productName = txtProductName.getText();
					String briefDescription = txtBrief.getText();
					Integer availableQuantity = txtQuantity.getText().isEmpty()?0 :Integer.valueOf(txtQuantity.getText());
					Double price = txtPrice.getText().isEmpty()?0:Double.valueOf(txtPrice.getText());
					Integer reorderLevel = txtReorderLevel.getText().isEmpty()?0:Integer.valueOf(txtReorderLevel.getText());
					Integer reorderQuantity= txtReorderQuantity.getText().isEmpty()?0:Integer.valueOf(txtReorderQuantity.getText());
					// need to check integer, double type
					if (productId.isEmpty() || productName.isEmpty() || reorderQuantity<=0|| availableQuantity<=0 || price<=0 ){
						JOptionPane.showMessageDialog(null, "Please fill in all the information", "Error", JOptionPane.INFORMATION_MESSAGE);
					}else{
						int status = 0;
						if (isEditCase == true) {
							if (curProduct.getCategoryId().equals(comboBoxCategory.getSelectedItem().toString())) {
								status = ProductUtils.editProduct(curProduct, productId, productName, briefDescription,
										availableQuantity, price, reorderLevel, reorderQuantity);
							} else {
								status = ProductUtils.editProduct(curProduct, curProduct.getProductId(), productName,
										briefDescription, -1, price, reorderLevel, reorderQuantity);
								status = ProductUtils.addNewProduct(products, productId, productName, briefDescription,
										availableQuantity, price, reorderLevel, reorderQuantity);

							}

						} else {
							status = ProductUtils.addNewProduct(products, productId, productName, briefDescription,
									availableQuantity, price, reorderLevel, reorderQuantity);

						}
						if (status == 0) {
							// update the productManager
							productManagerDialog.updateItSelf();
							dispose();

						} else {
							// dialog show fail
							String error ="";
							
							if(status ==-1){
								error = "product id duplicate";
							}else{
								error = "product name duplicate";
							}
							JOptionPane.showMessageDialog(null,error, "Error", JOptionPane.INFORMATION_MESSAGE);

						}

					}
				}
			});

			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);
		}

	}
	/**
	 * data initialization 
	 */
	public void dataInit() {
		// data init
		
		CategoryVendorMgr categoryVendorMgr = new CategoryVendorMgr();
		ArrayList<Category> categeoryList=new ArrayList<Category>();
		try {
			categeoryList = categoryVendorMgr.getCategoryUtil().getCategoryList();
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comboBoxCategory.removeAllItems();
		for (Category one : categeoryList) {
			comboBoxCategory.addItem(one.getCategoryId());
		}

		if (curProduct != null) {
			if (curProduct.getProductId() != null && !curProduct.getProductId().isEmpty()) {
				lblProductId.setText(curProduct.getProductId());
			}
		
			if (curProduct.getProductName() != null && !curProduct.getProductName().isEmpty()) {
				txtProductName.setText(curProduct.getProductName());
			}

			txtPrice.setText(String.valueOf(curProduct.getPrice()));
			if (curProduct.getBriefDescription() != null && !curProduct.getBriefDescription().isEmpty()) {
				txtBrief.setText(curProduct.getBriefDescription());
			}
			txtQuantity.setText(String.valueOf(curProduct.getAvailableQuantity()));
			txtReorderLevel.setText(String.valueOf(curProduct.getReorderLevel()));
			txtReorderQuantity.setText(String.valueOf(curProduct.getReorderQuantity()));
			comboBoxCategory.setSelectedItem(curProduct.getCategoryId());
			
		} else {
			comboBoxCategory.setSelectedIndex(0);
			lblProductId.setText(ProductUtils.productIdGenerator(comboBoxCategory.getSelectedItem().toString()));
			txtProductName.setText("");
			txtPrice.setText("");
			txtBrief.setText("");
			txtQuantity.setText("");
			txtReorderLevel.setText("");
			txtReorderQuantity.setText("");
		}
		if (isEditCase == true) {
			okButton.setText("Save");
		} else {
			okButton.setText("Add New");
		}
	}
}
