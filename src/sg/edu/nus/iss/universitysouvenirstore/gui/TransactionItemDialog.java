package sg.edu.nus.iss.universitysouvenirstore.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.universitysouvenirstore.CustomException;
import sg.edu.nus.iss.universitysouvenirstore.Product;
import sg.edu.nus.iss.universitysouvenirstore.ProductUtils;
import sg.edu.nus.iss.universitysouvenirstore.util.IntegerTextField;

public class TransactionItemDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtProductName;
	private IntegerTextField txtQuantity;
	private String title;
	private boolean isEditCase = false;
	private boolean isDeleteCase = false;
	private Product curProduct = null;
	private JButton okButton = null;
	private ArrayList<Product> products =null;
	private TransactionDialog transactionDialog;
	
	
	JPanel buttonPane = new JPanel();
	JButton cancelButton = new JButton("Cancel");
	
	//JLabel lblNewLabel = new JLabel("New label");
	//JLabel lblNewLabel_1 = new JLabel("New label");


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
		//lblNewLabel.setBounds(74, 152, 56, 16); //label debug
		//contentPanel.add(lblNewLabel);//label debug

		//lblNewLabel_1.setBounds(74, 170, 56, 16);//label debug
		//contentPanel.add(lblNewLabel_1);//label debug
		
		
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

		txtQuantity = new IntegerTextField();
		txtQuantity.setBounds(108, 97, 66, 21);
		contentPanel.add(txtQuantity);
		txtQuantity.setColumns(10);
		



		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		products = ProductUtils.getAllProducts();
		okButton = new JButton();
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String categoryId=lblProductId.getText();
				String productName = null;
				int availableQuantity = 0;
				
				
				if(!txtProductName.getText().equals("") && !txtQuantity.getText().equals("")) //empty input throw exception
				{
					productName = txtProductName.getText();				
					availableQuantity = Integer.valueOf(txtQuantity.getText());
					//System.out.println("Quantity: " + availableQuantity);
					
					if(!isEditCase && !isDeleteCase)
					{
						
						Product individualProduct = ProductUtils.getProductById(products, productName); //ProductUtils class;
						
						if (individualProduct != null)
						{
							if(individualProduct.getAvailableQuantity() >= availableQuantity)
								try {
									transactionDialog.AddTransactionedItem(productName, availableQuantity, individualProduct.getPrice());
								} catch (CustomException e1) {
									// TODO Auto-generated catch block
									String error ="";
									error = "Unable to add product";
									JOptionPane.showMessageDialog(null,error, "Error!", JOptionPane.INFORMATION_MESSAGE);
								}
							else
							{				        
								String error ="";
								error = "Insufficient Quantity, current available quantity: " + individualProduct.getAvailableQuantity();
								JOptionPane.showMessageDialog(null,error, "Insufficient ProductID", JOptionPane.INFORMATION_MESSAGE);
							}	
						}
						else
						{				        
							String error ="";
							error = "Invalid ProductID, please insert the correct ID";
							JOptionPane.showMessageDialog(null,error, "Invalid ProductID", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else if(isEditCase && !isDeleteCase)
					{
						try {
							transactionDialog.EditTransactionedItem(productName, availableQuantity);
						} catch (CustomException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
				        setVisible (false);
				        dispose();
					}
					
					else
					{
						try {
							transactionDialog.RemoveTransactionedItem(productName);
						} catch (CustomException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
				        setVisible (false);
				        dispose();
					}
				}
				else
				{
					String error ="";
					
					if(txtProductName.getText().equals("") && txtQuantity.getText().equals(""))
						error = "Missing Product ID AND Quantity";
					else if(txtQuantity.getText().equals(""))
						error = "Missing Quantity";
					else if(txtProductName.getText().equals(""))
						error = "Missing Product ID";
					//error = "Missing ProductID or Quantity";
					JOptionPane.showMessageDialog(null,error, "Missing Fields", JOptionPane.INFORMATION_MESSAGE);
				}
				//lblNewLabel.setText(txtProductName.getText()); //debug
				//lblNewLabel_1.setText(txtQuantity.getText()); //debug
				
				
				
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
		
		//Cancel Button
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}
	
	public void Refresh()
	{		
		if(isEditCase == true){
			okButton.setText("Save");
		}
		else if(!isEditCase && !isDeleteCase){
			okButton.setText("Add New");
		}
		else{
			okButton.setText("Delete");			
		}
		
		txtProductName.setEnabled(true);
		txtQuantity.setEnabled(true);
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        setVisible (false);
		        dispose();
			}
		});

	}
	
	public void EditItem(String item)
	{
		int counter = 0;
		String productID = "";
		int quantity = 0;

	      for (String retval: item.split(" ")){
	    	 if(counter == 0)
	    		 productID = retval;
	    	 
	    	 if(counter == 2)
	    		 quantity = Integer.parseInt(retval);
	    	 
	    	 counter++;
	      }
	      
		   //System.out.println("ProductID:" + productID + "Quantity:" + quantity ); //debug
		
		   txtProductName.setText(productID);
		   txtQuantity.setText(Integer.toString(quantity));
		   
		   txtProductName.setEnabled(false);
		   txtQuantity.setEnabled(true);
		   
		   if(isDeleteCase)
			   txtQuantity.setEnabled(false);

		   
	}

	public void setDeleteCase(boolean b) {
		isDeleteCase = b;
	}
}
