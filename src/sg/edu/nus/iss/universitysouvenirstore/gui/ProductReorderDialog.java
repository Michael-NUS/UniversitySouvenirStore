package sg.edu.nus.iss.universitysouvenirstore.gui;

import sg.edu.nus.iss.universitysouvenirstore.*;


import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ProductReorderDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPrice;
	private JTextField txtReorderQuantity;


	/**
	 * Create the dialog.
	 */
	public ProductReorderDialog() {
		//contentPanel.setBackground(new Color(244, 164, 96));
		setTitle("Product manager");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(244, 164, 96));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCategory = new JLabel("Product:");
		lblCategory.setBounds(10, 10, 73, 27);
		contentPanel.add(lblCategory);
		{
			JLabel lblPrice = new JLabel("Current Quantity:");
			lblPrice.setBounds(10, 47, 114, 27);
			contentPanel.add(lblPrice);
		}
		{
			JLabel lblBriefDescription = new JLabel("Reordering products");
			lblBriefDescription.setBounds(10, 110, 138, 27);
			contentPanel.add(lblBriefDescription);
		}
		{
			JLabel lblReorder = new JLabel("Reorder Quantity:");
			lblReorder.setBounds(10, 77, 114, 27);
			contentPanel.add(lblReorder);
		}
		
		txtPrice = new JTextField();
		txtPrice.setBounds(147, 50, 66, 21);
		contentPanel.add(txtPrice);
		txtPrice.setColumns(10);
		
		txtReorderQuantity = new JTextField();
		txtReorderQuantity.setBounds(147, 80, 66, 21);
		contentPanel.add(txtReorderQuantity);
		txtReorderQuantity.setColumns(10);
		
		JComboBox comboBoxCategory = new JComboBox();
		comboBoxCategory.setBounds(147, 13, 66, 21);
		contentPanel.add(comboBoxCategory);
		
		JList list = new JList();
		list.setBounds(10, 137, 301, 67);
		contentPanel.add(list);
		
		JButton btnAdd = new JButton("Add ");
		btnAdd.setBounds(247, 49, 93, 23);
		contentPanel.add(btnAdd);
		
		JButton button = new JButton("Add ");
		button.setBounds(321, 134, 93, 23);
		contentPanel.add(button);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(321, 181, 93, 23);
		contentPanel.add(btnRemove);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				okButton.setActionCommand("OK");
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
}
