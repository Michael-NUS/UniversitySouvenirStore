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
/**
 * 
 * @author Lim Hean Soon
 * Add Item into the transaction list
 */
public class TransactionItemDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtProductName;
	private IntegerTextField txtQuantity;
	private String title = "Transaction Item";
	private boolean isEditCase = false;
	private boolean isDeleteCase = false;
	private Product curProduct = null;
	private JButton okButton = null;
	private ArrayList<Product> products =null;
	JPanel buttonPane = new JPanel();
	JButton cancelButton = new JButton("Cancel");

	/**
	 * Set whether this is an Edit Window
	 * @param isEditCase
	 */
	public void setEditCase(boolean isEditCase) {
		this.isEditCase = isEditCase;
	}

	/**
	 * Receive Product object, and set the focus to that object
	 * @param curProduct
	 */
	public void setCurProduct(Product curProduct) {
		this.curProduct = curProduct;
	}
	
	/**
	 * Create the dialog.
	 * @param transactionDialog 
	 */
	public TransactionItemDialog(TransactionDialog transactionDialog) {		
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
		
		okButton.addActionListener(new ActionListener() { //OK button's action
			public void actionPerformed(ActionEvent e) {
				//String categoryId=lblProductId.getText();
				String productName = null;
				int availableQuantity = 0;				
				
				if(!txtProductName.getText().equals("") && !txtQuantity.getText().equals("")) //empty input throw exception
				{
					productName = txtProductName.getText();				
					availableQuantity = Integer.valueOf(txtQuantity.getText());
					//System.out.println("Quantity: " + availableQuantity);
					
					if(!isEditCase && !isDeleteCase)//Add new item case
					{				
						Product individualProduct = ProductUtils.getProductById(products, productName); //ProductUtils class;
						
						if (individualProduct != null) //make sure the item is existing
						{
							if(individualProduct.getAvailableQuantity() >= availableQuantity) //make sure the quantity entered is within availablity
								try {
									transactionDialog.AddTransactionedItem(productName, availableQuantity, individualProduct.getPrice()); //when all check pass, Add the item into the list
								} catch (CustomException e1) { //catch if there's any issue
									// TODO Auto-generated catch block
									String error ="";
									error = "Insufficient Quantity, current available quantity: " + individualProduct.getAvailableQuantity();
									JOptionPane.showMessageDialog(null,error, "Insufficient ProductID", JOptionPane.INFORMATION_MESSAGE);
								}
							else //in case of the existing item is insufficient, show the error message with the current available unit
							{				        
								String error ="";
								error = "Insufficient Quantity, current available quantity: " + individualProduct.getAvailableQuantity();
								JOptionPane.showMessageDialog(null,error, "Insufficient ProductID", JOptionPane.INFORMATION_MESSAGE);
							}	
						}
						else //in the case of Product ID does not exist
						{				        
							String error ="";
							error = "Invalid ProductID, please insert the correct ID";
							JOptionPane.showMessageDialog(null,error, "Invalid ProductID", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else if(isEditCase && !isDeleteCase)// this is edit case
					{
						setTitle("Edit "+ productName);
						try {
							transactionDialog.EditTransactionedItem(productName, availableQuantity);//Edit the Quantity
						} catch (CustomException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
				        setVisible (false);
				        dispose();
					}
					
					else//Remove selected item
					{
						setTitle("Remove " + productName);
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
					//error handling case, with the appropriate error messages
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
				
				txtProductName.setText(""); //Upon successful execution, clear the textbox
				txtQuantity.setText(""); //Upon successful execution, clear the textbox			
			}
		});
		
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		//Cancel Button
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}
	
	/**
	 * Redraw this screen
	 */
	public void Refresh()
	{		
		//System.out.println(title);
		setTitle(title);
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
	
	/**
	 * Edit the existing Item
	 * @param item
	 */
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

	/**
	 * Set whether this is a Delete case
	 * @param b
	 */
	public void setDeleteCase(boolean b) {
		isDeleteCase = b;
	}
}
