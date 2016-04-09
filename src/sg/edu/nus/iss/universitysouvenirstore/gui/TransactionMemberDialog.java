package sg.edu.nus.iss.universitysouvenirstore.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.universitysouvenirstore.MemberManager;

/**
 * @author Lim Hean Soon
 * Transaction Member's info retrieving screen
 */
public class TransactionMemberDialog extends JDialog {	
	private static final long serialVersionUID = 1L;
	private String memberID = null;
	private int memberPoints = 0;
	private final JPanel contentPanel = new JPanel();
	private JTextField memberField;
	private TransactionDialog transactionDialog;
	private JButton btnRetrieveMember = new JButton("Retrieve Member");
	private JLabel memberPoints_lbl = new JLabel("");
	private JLabel cashback_Lbl = new JLabel("");
	private JButton btnDone = new JButton("Done");
	private JLabel lblMemberId = new JLabel("Member ID");
	private JLabel lblNewLabel = new JLabel("Current Loyalty Points");
	private JLabel lblDeductPoints = new JLabel("Eligible Cash Rebate");
	private JCheckBox redeemCheckBox = new JCheckBox("Redeem");
	/**
	 * Constructor that take in TransactionDialog's object
	 * the TransactionDialog will allow this screen to call the method and make changes to the internal parameters
	 * @param transactionDialog
	 */
	public TransactionMemberDialog(TransactionDialog transactionDialog) {	
		this.transactionDialog = transactionDialog;		
		setTitle("Member Info");
		setBounds(100, 100, 450, 427);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(244, 164, 96));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);		

		btnRetrieveMember.addActionListener(new ActionListener() {//Retrieve member's information thru Member ID
			public void actionPerformed(ActionEvent arg0) {
				if(!memberField.getText().equals("")){
					if(MemberManager.getMemberLoyaltyPoint(memberField.getText()) != -99){	//call MemberUtils to get the loyalty point
						memberPoints = MemberManager.getMemberLoyaltyPoint(memberField.getText());
						memberID = memberField.getText();
						redeemCheckBox.setEnabled(true);//enable the Redeem checkbox
						//System.out.println("member points: " + memberPoints);
						btnDone.setEnabled(true); //turn on Done button						
						Refresh(); //redraw this screen				
					}					
					else //Member ID not found
					{
						String error ="";
						error = "Member ID not found";
						JOptionPane.showMessageDialog(null,error, "Error", JOptionPane.INFORMATION_MESSAGE);	
						redeemCheckBox.setEnabled(false);//grey out the Redeem checkbox
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

		lblNewLabel.setBounds(16, 157, 167, 16);
		contentPanel.add(lblNewLabel);		

		lblDeductPoints.setBounds(16, 184, 167, 16);
		contentPanel.add(lblDeductPoints);		

		redeemCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		redeemCheckBox.setBounds(16, 209, 78, 18);
		redeemCheckBox.setEnabled(false);
		contentPanel.add(redeemCheckBox);		
		memberField = new JTextField();
		memberField.setBounds(16, 101, 167, 20);
		contentPanel.add(memberField);
		memberField.setColumns(10);		
		JPanel panel = new JPanel();
		panel.setBounds(1, 344, 434, 35);
		contentPanel.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnDone.addActionListener(new ActionListener() {//Complete the member information gathering
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
				transactionDialog.refresh();
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
	}
	
	/**
	 * Return this Member's ID
	 * @return memberID
	 */
	public String GetMemberID ()
	{
		return memberID;
	}
	
	/**
	 * Redraw of the screen
	 */
	public void Refresh(){
		memberPoints_lbl.setText(String.valueOf(memberPoints));
		cashback_Lbl.setText("$" + memberPoints/100);
		//cashback_Lbl.setText();
	}
}
