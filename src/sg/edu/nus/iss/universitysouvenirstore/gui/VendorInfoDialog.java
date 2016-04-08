/**
 * @author nyinyizin
 * @version 1.0
 */
package sg.edu.nus.iss.universitysouvenirstore.gui;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import sg.edu.nus.iss.universitysouvenirstore.CustomException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class VendorInfoDialog extends JDialog {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtVendorName;
	private JTextField txtVendorDescription;
	public CategoryManagerDialog categoryManager;
	public int position = -1;
	public String categoryId;

	public VendorInfoDialog() {
		getContentPane().setLayout(null);
		setBounds(100, 100, 450, 258);
		getContentPane().setBackground(new Color(244,164,96));
		JLabel lblVendorName = new JLabel("Vendor Name");
		lblVendorName.setBounds(30, 64, 94, 16);
		getContentPane().add(lblVendorName);

		JLabel lblVendorDescription = new JLabel("Vendor Description");
		lblVendorDescription.setBounds(30, 115, 134, 16);
		getContentPane().add(lblVendorDescription);

		txtVendorName = new JTextField();
		txtVendorName.setBounds(176, 59, 236, 26);
		getContentPane().add(txtVendorName);
		txtVendorName.setColumns(10);

		txtVendorDescription = new JTextField();
		txtVendorDescription.setBounds(176, 110, 236, 26);
		getContentPane().add(txtVendorDescription);
		txtVendorDescription.setColumns(10);

		JButton btnOK = new JButton("Ok");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveDialog();
			}
		});
		btnOK.setBounds(176, 148, 117, 29);
		getContentPane().add(btnOK);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeDialog();
			}
		});
		btnCancel.setBounds(295, 148, 117, 29);
		getContentPane().add(btnCancel);

	}

	public void setCategoryManager(CategoryManagerDialog catMgr) {
		this.categoryManager = catMgr;
	}

	public void saveDialog() {
		try{
			if(txtVendorName.getText().isEmpty()||txtVendorDescription.getText().isEmpty()){
				throw new CustomException("Empty_Data");
			}else if(txtVendorName.getText().contains(",")||txtVendorDescription.getText().contains(",")){
				throw new CustomException("Comma_in_descritpion");
			}
			this.categoryManager.updateVendorManager(this.txtVendorName.getText(), this.txtVendorDescription.getText(),
				position, this.categoryId);
		closeDialog();
		}catch(CustomException e){
			if(e.getMessage().equals("Empty_Data")){
				JOptionPane.showMessageDialog(null, "Vendor name or vendor description cannot be empty!","Error",JOptionPane.ERROR_MESSAGE);
			}else if(e.getMessage().equals("Comma_in_descritpion")){
				JOptionPane.showMessageDialog(null, "Vendor name and vendor description cannot contain comma (,) !","Error",JOptionPane.ERROR_MESSAGE);
			}else if(e.getMessage().equals("Already_Exist_Error")){
				JOptionPane.showMessageDialog(null, "Vendor name already exists!","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void closeDialog() {
		clearEditData();
		this.setVisible(false);
		this.dispose();
	}

	public void showDialog() {
		this.setVisible(true);
	}

	public void clearEditData() {
		this.txtVendorName.setText("");
		this.txtVendorDescription.setText("");
	}

	public void setEditData(String vendorName, String vendorDescription) {
		this.txtVendorName.setText(vendorName);
		this.txtVendorDescription.setText(vendorDescription);
	}
}
