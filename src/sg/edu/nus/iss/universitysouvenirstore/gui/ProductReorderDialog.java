/**
 * linwei
 */
package sg.edu.nus.iss.universitysouvenirstore.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.universitysouvenirstore.Product;
import sg.edu.nus.iss.universitysouvenirstore.util.ConfirmDialog;
import sg.edu.nus.iss.universitysouvenirstore.util.FileManangerUtils;

public class ProductReorderDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ArrayList<Product> reorderProductList;
	private ArrayList<Product> allProducts;
	public ArrayList<Product> getAllProducts() {
		return allProducts;
	}
	public void setAllProducts(ArrayList<Product> allProducts) {
		this.allProducts = allProducts;
	}


	private DefaultListModel<String> productmodel;
	private JList<String> jlist;

	/**
	 * Create the dialog.
	 */
	public ProductReorderDialog() {
		//contentPanel.setBackground(new Color(244, 164, 96));
		setTitle("Reorder Product Manager");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(244, 164, 96));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnReorderNow = new JButton("Order Now");
		btnReorderNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String title = "Reorder Product";
		        String msg = "Do you want to reorder products ?";
		        ConfirmDialog d = new ConfirmDialog (null, title, msg) {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					protected boolean performOkAction () {
						for(Product one: reorderProductList){
							one.updateAvaliableQuantity(one.getReorderQuantity(),2);
						}
						dataInit();
						btnReorderNow.setEnabled(false);
						FileManangerUtils.saveDataToDatFile(Product.class, allProducts);
		                return true;
		            }
		        };
		        d.pack();
		        d.setVisible (true);
				
			}
		});
		btnReorderNow.setBounds(321, 54, 103, 23);
		contentPanel.add(btnReorderNow);
		jlist = new JList<String>();
		jlist.setBounds(5, 56, 282, 113);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 56, 282, 113);
		contentPanel.add(scrollPane);
		scrollPane.setViewportView(jlist);
		
		JLabel label = new JLabel("Reordering products");
		label.setBounds(10, 0, 138, 27);
		contentPanel.add(label);
		
		productmodel = new DefaultListModel<String>();
		jlist.setModel(productmodel);
		jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lblProductnameavaliableQuantity = new JLabel("Product Name-Avaliable Quantity-Reorder Quantity");
		lblProductnameavaliableQuantity.setBounds(5, 38, 306, 14);
		contentPanel.add(lblProductnameavaliableQuantity);
		
	
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

	}
	public void dataInit(){
		productmodel.removeAllElements();
		if(reorderProductList.size()>0){
			for(Product product : reorderProductList){
				productmodel.addElement( product.getProductName()+" - "+
				 String.valueOf(product.getAvailableQuantity())+" - "+
				 String.valueOf(product.getReorderQuantity()));
			}
		}
	}


	public ArrayList<Product> getReorderProductList() {
		return reorderProductList;
	}


	public void setReorderProductList(ArrayList<Product> reorderProductList) {
		this.reorderProductList = reorderProductList;
	}
}
