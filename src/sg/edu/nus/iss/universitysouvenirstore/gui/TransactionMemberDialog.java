package sg.edu.nus.iss.universitysouvenirstore.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.universitysouvenirstore.*;


import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

public class TransactionMemberDialog extends JDialog {
	
	private String memberID = null;
	

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	/**
	 * Create the dialog.
	 */
	//public TransactionMemberDialog(Member member) {		
	public TransactionMemberDialog() {		
		setTitle("Transaction");
		setBounds(100, 100, 450, 427);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(244, 164, 96));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnRetrieveMember = new JButton("Retrieve Member");
		btnRetrieveMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		btnRetrieveMember.setBounds(220, 101, 115, 20);
		contentPanel.add(btnRetrieveMember);
		
		//label for static text display		
		JLabel lblNewLabel = new JLabel("Current Loyalty Points");
		lblNewLabel.setBounds(16, 157, 167, 16);
		contentPanel.add(lblNewLabel);
		
		JLabel lblDeductPoints = new JLabel("Redeemable Dollar");
		lblDeductPoints.setBounds(16, 184, 167, 16);
		contentPanel.add(lblDeductPoints);
		
		JLabel lblGrandTotal = new JLabel("Loyalty Points after Redeem");
		lblGrandTotal.setBounds(16, 226, 167, 16);
		contentPanel.add(lblGrandTotal);
		
		JCheckBox redeemCheckBox = new JCheckBox("Redeem");
		redeemCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		redeemCheckBox.setBounds(16, 201, 78, 18);
		contentPanel.add(redeemCheckBox);
		
		textField = new JTextField();
		textField.setBounds(16, 101, 167, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 353, 434, 35);
		contentPanel.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JButton btnDone = new JButton("Done");
		btnDone.setEnabled(false);
		
		btnDone.setActionCommand("Check Out");
		panel.add(btnDone);
		
		JButton button_1 = new JButton("Cancel");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setActionCommand("Cancel");
		panel.add(button_1);
		
		JLabel lblMemberId = new JLabel("Member ID");
		lblMemberId.setBounds(16, 85, 117, 16);
		contentPanel.add(lblMemberId);
		
		redeemCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//on check, need to prompt Transaction Member screen
				
				if(redeemCheckBox.isSelected())
				{
					//prompt 2nd screen that allow the store keeper to add the Memebr ID
					//in the 2nd screen once the Member ID is added, show the member's information
					//the member can select if they wanted to use the Points to deduct
					//lblPublic.setText("memberID");
				}
				else;
					//lblPublic.setText("PUBLIC");
			}
		});
		

	}
	
	public String GetMemberID ()
	{
		return memberID;
	}
}