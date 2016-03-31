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
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.universitysouvenirstore.gui.TransactionPanel;
import sg.edu.nus.iss.universitysouvenirstore.*;

import java.awt.Font;
import javax.swing.JCheckBox;

public class TransactionDialog2 extends JDialog {
	//private TransactionPanel transactionPanel = new TransactionPanel (manager);
	
	private String memberID = null;
	private int discount = 0;
	private float total = 0;
	//private ArrayList<TransactionedItem> itemList = new ArrayList<TransactionedItem>();
	private Transaction items = new Transaction();
	//private Member member = new Member();
	private final JPanel contentPanel = new JPanel();
	int numItems = 0;
	/**
	 * Create the dialog.
	 */
	public TransactionDialog2() {

		
		setTitle("Transaction");
		setBounds(100, 100, 450, 427);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(244, 164, 96));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);


		JList<String> jlist = new JList<String>();
		jlist.setBounds(5, 101, 280, 153);
		contentPanel.add(jlist);
		
		JButton button = new JButton("Add Item");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setBounds(297, 101, 127, 37);
		contentPanel.add(button);
		
		JButton editBtn = new JButton("Edit Item");
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		editBtn.setBounds(297, 143, 127, 37);
		contentPanel.add(editBtn);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 353, 434, 35);
		contentPanel.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JButton checkOutButton = new JButton("Check Out");
		checkOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		
		JButton removeButton = new JButton("Remove Item");
		removeButton.setEnabled(false);
		removeButton.setBounds(297, 184, 127, 37);
		contentPanel.add(removeButton);
		
		editBtn.setEnabled(false); //when no item in the list, Remove button is disabled
		checkOutButton.setEnabled(false); //when no item in the list, Check Out button is disabled
		
		
		checkOutButton.setActionCommand("Check Out");
		panel.add(checkOutButton);
		
		JButton button_3 = new JButton("Cancel");
		button_3.setActionCommand("Cancel");
		panel.add(button_3);
		
		//label for static text display		
		JLabel lblNewLabel = new JLabel("Sub Total:");
		lblNewLabel.setBounds(5, 267, 66, 16);
		contentPanel.add(lblNewLabel);
		
		JLabel lblDeductPoints = new JLabel("Deduct Points:");
		lblDeductPoints.setBounds(5, 281, 97, 16);
		contentPanel.add(lblDeductPoints);
		
		JLabel lblDiscount = new JLabel("Discount");
		lblDiscount.setBounds(5, 296, 66, 16);
		contentPanel.add(lblDiscount);
		
		JLabel lblGrandTotal = new JLabel("Grand Total:");
		lblGrandTotal.setBounds(5, 310, 84, 16);
		contentPanel.add(lblGrandTotal);
		
		JLabel lblItems = new JLabel("Items:");
		lblItems.setBounds(5, 84, 66, 16);
		contentPanel.add(lblItems);
		
		JLabel lblMemberId = new JLabel("Member ID:");
		lblMemberId.setBounds(5, 13, 75, 16);
		contentPanel.add(lblMemberId);
		
		JLabel lblPublic = new JLabel("PUBLIC");
		lblPublic.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblPublic.setBounds(80, 13, 75, 16);
		contentPanel.add(lblPublic);
		
		JCheckBox memberCheckBox = new JCheckBox("Member");
		memberCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		memberCheckBox.setBounds(297, 229, 75, 25);
		contentPanel.add(memberCheckBox);
		
		memberCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//on check, need to prompt Transaction Member screen
				
				if(memberCheckBox.isSelected())
				{
					
					//TransactionMemberDialog memberInfo = new TransactionMemberDialog(member);
					TransactionMemberDialog memberInfo = new TransactionMemberDialog();
					memberInfo.setTitle("New Product");
					memberInfo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					memberInfo.setEnabled(true);
					memberInfo.setVisible(true);
					//prompt 2nd screen that allow the store keeper to add the MemberID
					//in the 2nd screen once the Member ID is added, show the member's information
					//the member can select if they wanted to use the Points to deduct
					lblPublic.setText(memberInfo.GetMemberID());					
				}
				else
					lblPublic.setText("PUBLIC");
			}
		});
	}
	
	public ArrayList<TransactionedItem> GetTransactionedItems(){
		return items.GetTransactionItems();
	}

	public JFrame getMainWindow() {
		// TODO Auto-generated method stub
		return null;
	}

    
    public void AddTransactionedItem(String productID, int quantity)
    {
    	items.AddTransactionItem(productID, quantity);
    }
}
