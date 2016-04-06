package sg.edu.nus.iss.universitysouvenirstore.gui;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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
		setBounds(100, 100, 450, 300);

		JLabel lblVendorName = new JLabel("Vendor Name");
		lblVendorName.setBounds(24, 27, 94, 16);
		getContentPane().add(lblVendorName);

		JLabel lblVendorDescription = new JLabel("Vendor Description");
		lblVendorDescription.setBounds(24, 80, 134, 16);
		getContentPane().add(lblVendorDescription);

		txtVendorName = new JTextField();
		txtVendorName.setBounds(188, 22, 130, 26);
		getContentPane().add(txtVendorName);
		txtVendorName.setColumns(10);

		txtVendorDescription = new JTextField();
		txtVendorDescription.setBounds(188, 75, 130, 66);
		getContentPane().add(txtVendorDescription);
		txtVendorDescription.setColumns(10);

		JButton btnOK = new JButton("Ok");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveDialog();
			}
		});
		btnOK.setBounds(74, 243, 117, 29);
		getContentPane().add(btnOK);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeDialog();
			}
		});
		btnCancel.setBounds(203, 243, 117, 29);
		getContentPane().add(btnCancel);

	}

	public void setCategoryManager(CategoryManagerDialog catMgr) {
		this.categoryManager = catMgr;
	}

	public void saveDialog() {
		this.categoryManager.updateVendorManager(this.txtVendorName.getText(), this.txtVendorDescription.getText(),
				position, this.categoryId);
		closeDialog();
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
