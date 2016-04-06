package sg.edu.nus.iss.universitysouvenirstore.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.universitysouvenirstore.Discount;
import sg.edu.nus.iss.universitysouvenirstore.DiscountManger;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;

public class DiscountInfoMgrDialog extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTextField txtDiscountCode;
	private JTextField txtDiscountPeriod;
	private JTextField txtDiscountStartDate;
	
	public DiscountInfoMgrDialog() {
		setTitle("Discount Information Manger");
		setBounds(100, 100, 545, 498);
		contentPanel.setBackground(new Color(244, 164, 96));
		DiscountManger.readExistingDiscountsFromDB();
		ArrayList<Discount> d=DiscountManger.convertToDiscountArraylist();
		
		getContentPane().setLayout(null);
		
		JLabel discountCode = new JLabel("Discount Code :");
		JLabel discountDescription = new JLabel("Discount Description :");
		JLabel discountPeriod = new JLabel("Discount Period :");
		JLabel discountStartDate = new JLabel("Discount Start Date :");
		JLabel discountType = new JLabel("Discount Type :");
		discountCode.setBounds(15, 66, 163, 44);
		discountDescription.setBounds(15, 126, 163, 44);
		discountPeriod.setBounds(15, 237, 163, 44);
		discountStartDate.setBounds(15, 292, 163, 44);
		discountType.setBounds(26, 16, 282, 162);
		discountType.setBounds(0, 80, 119, -80);
		getContentPane().add(discountCode);
		getContentPane().add(discountDescription);
		getContentPane().add(discountPeriod);
		getContentPane().add(discountStartDate);
		getContentPane().add(discountType);
		
		JButton btnOK = new JButton("Save");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnOK.setBounds(75, 369, 192, 44);
		getContentPane().add(btnOK);
		
		JButton btnRemoveDiscount = new JButton("Cancel");
		btnRemoveDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRemoveDiscount.setBounds(282, 369, 192, 44);
		getContentPane().add(btnRemoveDiscount);
		
		JLabel lblNewLabel = new JLabel("Discount Type :");
		lblNewLabel.setBounds(15, 16, 163, 44);
		getContentPane().add(lblNewLabel);
		
		txtDiscountCode = new JTextField();
		txtDiscountCode.setBounds(195, 75, 279, 26);
		getContentPane().add(txtDiscountCode);
		txtDiscountCode.setColumns(10);
		
		JComboBox cboDiscountType = new JComboBox();
		cboDiscountType.setModel(new DefaultComboBoxModel(new String[] {"Members", "Public"}));
		cboDiscountType.setBounds(193, 25, 148, 26);
		getContentPane().add(cboDiscountType);
		
		txtDiscountPeriod = new JTextField();
		txtDiscountPeriod.setBounds(193, 246, 281, 26);
		getContentPane().add(txtDiscountPeriod);
		txtDiscountPeriod.setColumns(10);
		
		txtDiscountStartDate = new JTextField();
		txtDiscountStartDate.setBounds(195, 301, 279, 26);
		getContentPane().add(txtDiscountStartDate);
		txtDiscountStartDate.setColumns(10);
		
		JTextArea txtDiscountDescription = new JTextArea();
		txtDiscountDescription.setBounds(193, 138, 281, 73);
		getContentPane().add(txtDiscountDescription);
		contentPanel.setBackground(new Color(244, 164, 96));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	}
}
