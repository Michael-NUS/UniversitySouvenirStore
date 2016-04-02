package sg.edu.nus.iss.universitysouvenirstore.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.universitysouvenirstore.Product;
import sg.edu.nus.iss.universitysouvenirstore.ProductUtils;

public class ProductInfoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtProductName;
	private JTextField txtPrice;
	private JTextField txtQuantity;
	private JTextField txtReorderQuantity;
	private JTextField txtBrief;
	private String title;
	private JComboBox<String> comboBoxCategory;
	private JComboBox<String> comboBoxVendor;
	private JLabel lblBarCode;
	private JLabel lblProductId;
	private boolean isEditCase = false;
	private Product curProduct = null;
	private JButton okButton = null;
	private ArrayList<Product> products = null;
	private ProductManagerDialog productManagerDialog = null;


	public ProductManagerDialog getProductManagerDialog() {
		return productManagerDialog;
	}

	public void setProductManagerDialog(ProductManagerDialog productManagerDialog) {
		this.productManagerDialog = productManagerDialog;
	}

	public Product getCurProduct() {
		return curProduct;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

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
		lblCategory.setBounds(10, 39, 73, 27);
		contentPanel.add(lblCategory);

		JLabel lblProductName = new JLabel("Product Name:");
		lblProductName.setBounds(10, 67, 88, 27);
		contentPanel.add(lblProductName);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(234, 67, 59, 27);
		contentPanel.add(lblPrice);

		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(10, 94, 59, 27);
		contentPanel.add(lblQuantity);

		JLabel lblVendor = new JLabel("Vendor:");
		lblVendor.setBounds(234, 39, 48, 27);
		contentPanel.add(lblVendor);

		JLabel lblBriefDescription = new JLabel("Brief Description");
		lblBriefDescription.setBounds(10, 128, 138, 27);
		contentPanel.add(lblBriefDescription);

		JLabel label2 = new JLabel("BarCode:");
		label2.setBounds(234, 10, 59, 27);
		contentPanel.add(label2);

		JLabel lblReorder = new JLabel("Reorder Quantity:");
		lblReorder.setBounds(200, 94, 114, 27);
		contentPanel.add(lblReorder);

		txtProductName = new JTextField();
		txtProductName.setBounds(108, 70, 66, 21);
		contentPanel.add(txtProductName);
		txtProductName.setColumns(10);

		txtPrice = new JTextField();
		txtPrice.setBounds(312, 70, 66, 21);
		contentPanel.add(txtPrice);
		txtPrice.setColumns(10);

		txtQuantity = new JTextField();
		txtQuantity.setBounds(108, 97, 66, 21);
		contentPanel.add(txtQuantity);
		txtQuantity.setColumns(10);

		txtReorderQuantity = new JTextField();
		txtReorderQuantity.setBounds(312, 97, 66, 21);
		contentPanel.add(txtReorderQuantity);
		txtReorderQuantity.setColumns(10);

		txtBrief = new JTextField();
		txtBrief.setBounds(10, 154, 401, 46);
		contentPanel.add(txtBrief);
		txtBrief.setColumns(10);

		comboBoxCategory = new JComboBox<String>();
		comboBoxCategory.setBounds(108, 42, 66, 21);
		contentPanel.add(comboBoxCategory);

		comboBoxVendor = new JComboBox<String>();
		comboBoxVendor.setBounds(312, 42, 66, 21);
		contentPanel.add(comboBoxVendor);

		lblBarCode = new JLabel("New label");
		lblBarCode.setBounds(312, 16, 54, 15);
		contentPanel.add(lblBarCode);

		JLabel label1 = new JLabel("ProductId:");
		label1.setBounds(10, 10, 88, 27);
		contentPanel.add(label1);

		lblProductId = new JLabel("New label");
		lblProductId.setBounds(108, 16, 54, 15);
		contentPanel.add(lblProductId);

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
					String barCodeNumber = lblBarCode.getText();
					Integer reorderQuantity = txtReorderQuantity.getText().isEmpty()?0:Integer.valueOf(txtReorderQuantity.getText());
					String vendorId = comboBoxVendor.getSelectedItem()==null?"" :comboBoxVendor.getSelectedItem().toString();
					
					// need to check integer, double type
					if (productId.isEmpty() || productName.isEmpty() || barCodeNumber.isEmpty()||barCodeNumber.isEmpty() ||vendorId .isEmpty()||reorderQuantity<=0|| availableQuantity<=0 || price<=0 ){
						return;
					}
					int status =0;
					if (isEditCase == true) {
						status = ProductUtils.editProduct(curProduct, productId, productName, briefDescription,
								availableQuantity, price, barCodeNumber, reorderQuantity, vendorId);
					} else {
						status = ProductUtils.addNewProduct(products, productId, productName, briefDescription,
								availableQuantity, price, barCodeNumber, reorderQuantity, vendorId);
						
					}
					if(status == 0){
						// dialog show success
						
						// update the productManager
						productManagerDialog.updateItSelf();
						dispose();
						
					}else {
						//dialog show fail
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

	public void dataInit() {
		// data init
		String[] categeoryList = { "CLO", "MUG", "STA" };
		comboBoxCategory.removeAllItems();
		for (String one : categeoryList) {
			comboBoxCategory.addItem(one);
		}
		String[] vendorList = { "v1", "v2", "v3" };
		comboBoxVendor.removeAllItems();
		for (String one : vendorList) {
			comboBoxVendor.addItem(one);
		}

		if (curProduct != null) {
			if (curProduct.getProductId() != null && !curProduct.getProductId().isEmpty()) {
				lblProductId.setText(curProduct.getProductId());
			}
			if (curProduct.getBarCodeNumber() != null && !curProduct.getBarCodeNumber().isEmpty()) {
				lblBarCode.setText(curProduct.getBarCodeNumber());
			}
			if (curProduct.getProductName() != null && !curProduct.getProductName().isEmpty()) {
				txtProductName.setText(curProduct.getProductName());
			}

			txtPrice.setText(String.valueOf(curProduct.getPrice()));
			if (curProduct.getBriefDescription() != null && !curProduct.getBriefDescription().isEmpty()) {
				txtBrief.setText(curProduct.getBriefDescription());
			}
			txtQuantity.setText(String.valueOf(curProduct.getAvailableQuantity()));
			txtReorderQuantity.setText(String.valueOf(curProduct.getReorderQuantity()));
			comboBoxCategory.setSelectedItem(curProduct.getCategoryId());
			comboBoxVendor.setSelectedItem(curProduct.getVendorId());
		} else {
			comboBoxCategory.setSelectedIndex(0);
			comboBoxVendor.setSelectedIndex(0);
			lblProductId.setText(ProductUtils.productIdGenerator(comboBoxCategory.getSelectedItem().toString()));
			lblBarCode.setText(ProductUtils.barCodeGenerator(comboBoxCategory.getSelectedItem().toString()));
			txtProductName.setText("");
			txtPrice.setText("");
			txtBrief.setText("");
			txtQuantity.setText("");
			txtReorderQuantity.setText("");
		}
		if (isEditCase == true) {
			okButton.setText("Save");
		} else {
			okButton.setText("Add New");
		}
	}

}
