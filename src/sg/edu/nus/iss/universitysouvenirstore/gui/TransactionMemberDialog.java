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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.universitysouvenirstore.*;

import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

public class TransactionMemberDialog extends JDialog {
	
	private String memberID = null;
	private int memberPoints = 0;

	private final JPanel contentPanel = new JPanel();
	private JTextField memberField;
	TransactionDialog transactionDialog;

	JButton btnRetrieveMember = new JButton("Retrieve Member");
	JLabel memberPoints_lbl = new JLabel("");
	JLabel cashback_Lbl = new JLabel("");
	JButton btnDone = new JButton("Done");
	JLabel lblMemberId = new JLabel("Member ID");
	//public TransactionMemberDialog(Member member) {		
	public TransactionMemberDialog(TransactionDialog transactionDialog) {	
		this.transactionDialog = transactionDialog;
		
		setTitle("Member Info");
		setBounds(100, 100, 450, 427);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(244, 164, 96));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);		

		btnRetrieveMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!memberField.getText().equals("")){
					if(MemberManager.getMemberLoyaltyPoint(memberField.getText()) != -99){						//call MemberUtils to get the loyalty point
						memberPoints = MemberManager.getMemberLoyaltyPoint(memberField.getText());
						memberID = memberField.getText();

						System.out.println("member points: " + memberPoints);
						btnDone.setEnabled(true); //turn on Done button
						
						Refresh(); //redraw this screen				
					}

					
					else{
						String error ="";
						error = "Member ID not found";
						JOptionPane.showMessageDialog(null,error, "Error", JOptionPane.INFORMATION_MESSAGE);
						
						memberID = "PUBLIC";
						memberPoints = 0;
						btnDone.setEnabled(false); //disable Done button
						
						Refresh();
					}
				}
				else //empty memberID
				{
					String error ="";
					error = "Member ID field Empty";
					JOptionPane.showMessageDialog(null,error, "Error", JOptionPane.INFORMATION_MESSAGE);
				}					
			}
		});
		//}
		


		btnRetrieveMember.setBounds(220, 101, 143, 20);
		contentPanel.add(btnRetrieveMember);
		
		//label for static text display		
		JLabel lblNewLabel = new JLabel("Current Loyalty Points");
		lblNewLabel.setBounds(16, 157, 167, 16);
		contentPanel.add(lblNewLabel);
		
		JLabel lblDeductPoints = new JLabel("Redeemable Dollar");
		lblDeductPoints.setBounds(16, 184, 167, 16);
		contentPanel.add(lblDeductPoints);
		
		JCheckBox redeemCheckBox = new JCheckBox("Redeem");
		redeemCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		redeemCheckBox.setBounds(16, 201, 78, 18);
		contentPanel.add(redeemCheckBox);
		
		memberField = new JTextField();
		memberField.setBounds(16, 101, 167, 20);
		contentPanel.add(memberField);
		memberField.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 353, 434, 35);
		contentPanel.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		

		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					transactionDialog.SetMemberPoint(Integer.parseInt(memberPoints_lbl.getText())); //capture MemberPoints
					transactionDialog.SetMemberID(memberID);//capture MemebrID
					
					if(redeemCheckBox.isSelected()){
						transactionDialog.OptCashBack(true);//opted in for cashback
					}
					else
						transactionDialog.OptCashBack(false);//opted out for cashback
					
					transactionDialog.refresh();//redraw the Transaction main Screen
					
					redeemCheckBox.setSelected(false);;
					memberID = "PUBLIC";
					memberPoints = 0;
					btnDone.setEnabled(false); //disable Done button
					memberField.setText("");
					Refresh();
					dispose();
					
					
			}
		});		
		btnDone.setEnabled(false); //default grey out
		
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
		

		lblMemberId.setBounds(16, 85, 117, 16);
		contentPanel.add(lblMemberId);
		

		memberPoints_lbl.setBounds(220, 157, 56, 16);
		contentPanel.add(memberPoints_lbl);
		

		cashback_Lbl.setBounds(220, 184, 56, 16);
		contentPanel.add(cashback_Lbl);
		
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
	
	public void Refresh(){
		memberPoints_lbl.setText(String.valueOf(memberPoints));
		cashback_Lbl.setText("$" + memberPoints/100);
		//cashback_Lbl.setText();
	}
}
