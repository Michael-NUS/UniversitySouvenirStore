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

public class TransactionItemDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtProductName;
	private JTextField txtQuantity;
	private String title;
	private boolean isEditCase = false; 
	private Product curProduct = null;
	private JButton okButton = null;
	private ArrayList<Product> products =null;
	private TransactionDialog transactionDialog;

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
	 * @param transactionDialog 
	 */
	public TransactionItemDialog(TransactionDialog transactionDialog) {
		this.transactionDialog = transactionDialog;
		// contentPanel.setBackground(new Color(244, 164, 96));
		setTitle(title);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(244, 164, 96));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblProductName = new JLabel("Product Name:");
		lblProductName.setBounds(10, 54, 88, 27);
		contentPanel.add(lblProductName);

		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(20, 91, 59, 27);
		contentPanel.add(lblQuantity);

		txtProductName = new JTextField();
		txtProductName.setBounds(110, 56, 138, 21);
		contentPanel.add(txtProductName);
		txtProductName.setColumns(10);

		txtQuantity = new JTextField();
		txtQuantity.setBounds(108, 97, 66, 21);
		contentPanel.add(txtQuantity);
		txtQuantity.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(74, 152, 56, 16);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(74, 170, 56, 16);
		contentPanel.add(lblNewLabel_1);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			okButton = new JButton();
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					//String categoryId=lblProductId.getText();
					
					String productName= txtProductName.getText();
					
					int availableQuantity = Integer.valueOf(txtQuantity.getText());
					
					lblNewLabel.setText(txtProductName.getText());
					lblNewLabel_1.setText(txtQuantity.getText());
					transactionDialog.AddTransactionedItem(productName, availableQuantity);
					
					txtProductName.setText("");
					txtQuantity.setText("");
					//Double price = Double.valueOf(txtPrice.getText());
					//String barCodeNumber = lblBarCode.getText();
					//Integer reorderQuantity =Integer.valueOf(txtReorderQuantity.getText());
					//String vendorId = comboBoxVendor.getSelectedItem().toString();
					
					/*
					if(isEditCase == true){
						ProductUtils.editProduct(curProduct, categoryId, productName, briefDescription, availableQuantity, price, barCodeNumber, reorderQuantity,vendorId);
					}else{
						ProductUtils.addNewProduct(products, categoryId, productName, briefDescription, availableQuantity, price, barCodeNumber, reorderQuantity, vendorId);
					}
					
					*/
					
					
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
}
