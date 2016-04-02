package sg.edu.nus.iss.universitysouvenirstore.gui;

import java.awt.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;

import javax.swing.*;

import sg.edu.nus.iss.universitysouvenirstore.*;

import java.awt.Font;

import javax.swing.border.EmptyBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TransactionDialog extends JFrame{
	
	private TransactionItemDialog transactionItemDialog;	
	private Transaction transaction = new Transaction();
	
	private ArrayList<TransactionedItem> items = new ArrayList<TransactionedItem>();

	private String memberID = null;
	private int discount = 0;
	private float total = 0;
	//private ArrayList<TransactionedItem> itemList = new ArrayList<TransactionedItem>();
	//private Member member = new Member();
	private final JPanel contentPanel = new JPanel();
	int numItems = 0;
	DefaultListModel model;
	//private TransactionPanel transactionPanel = new TransactionPanel (this);
	
	//JList<String> jlist = new JList<String>();
	
	private java.awt.List jlist;
	
	public TransactionDialog() {	
		
		transactionItemDialog = new TransactionItemDialog(this);
		setTitle("Transaction");		
		setBounds(100, 100, 450, 427);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(244, 164, 96));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		
		CreateButtonPanel();
		CreateLabelPanel();
	}
	
	public void CreateButtonPanel(){
		
		jlist = new java.awt.List (10); //show items
		jlist.setMultipleMode (false);
		jlist.setBounds(5, 101, 280, 153);
		contentPanel.add(jlist);
		
		JButton button = new JButton("Add Item");
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				transactionItemDialog.setTitle("New Item");
				transactionItemDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				transactionItemDialog.setEnabled(true);
				transactionItemDialog.setVisible(true);
				transactionItemDialog.setCurProduct(null);

				//Edit
				transactionItemDialog.setEditCase(false);
				transactionItemDialog.setDeleteCase(false);
				transactionItemDialog.Refresh();
				//transactionItemDialog.dataInit();
			}
			
		});
		button.setBounds(297, 101, 127, 37);
		contentPanel.add(button);
		
		JButton editBtn = new JButton("Edit Item");
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transactionItemDialog.setTitle("New Item");
				transactionItemDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				transactionItemDialog.setEnabled(true);
				transactionItemDialog.setVisible(true);
				transactionItemDialog.setCurProduct(null);

				//Edit
				transactionItemDialog.setEditCase(true);
				transactionItemDialog.setDeleteCase(false);				
				transactionItemDialog.Refresh();
				
				if(jlist.getSelectedItem() != null)
				{
					transactionItemDialog.EditItem(jlist.getSelectedItem());
					//transactionItemDialog.dataInit();
				}
				
			}
		});
		editBtn.setBounds(297, 143, 127, 37);
		contentPanel.add(editBtn);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 353, 434, 35);
		contentPanel.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JButton checkOutButton = new JButton("Check Out");	
		
		JButton removeButton = new JButton("Remove Item");		
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				transactionItemDialog.setTitle("New Item");
				transactionItemDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				transactionItemDialog.setEnabled(true);
				transactionItemDialog.setVisible(true);
				transactionItemDialog.setCurProduct(null);

				//Edit
				transactionItemDialog.setEditCase(false);
				transactionItemDialog.setDeleteCase(true);
				transactionItemDialog.Refresh();
				
				if(jlist.getSelectedItem() != null)
				{
					transactionItemDialog.EditItem(jlist.getSelectedItem());
					//transactionItemDialog.dataInit();
				}
			}
		});
		removeButton.setEnabled(false);
		
		removeButton.setBounds(297, 184, 127, 37);
		contentPanel.add(removeButton);
		
		editBtn.setEnabled(false); //when no item in the list, Remove button is disabled
		checkOutButton.setEnabled(false); //when no item in the list, Check Out button is disabled		
		
		checkOutButton.setActionCommand("Check Out");
		panel.add(checkOutButton);
		
		JButton button_3 = new JButton("Cancel");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        setVisible (false);
		        dispose();
			}
		});
		button_3.setActionCommand("Cancel");
		panel.add(button_3);
		
		jlist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeButton.setEnabled(true);
				editBtn.setEnabled(true);
			}
		});
		//Member Checkbox
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
					//lblPublic.setText(memberInfo.GetMemberID());					
				}
				else;
					//lblPublic.setText("PUBLIC");
			}
		});
	}
	
	public void CreateLabelPanel(){
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
	}
	
    public void shutdown () {
        System.exit(0);
    }
    
    public TransactionDialog getMainWindow() {
        return this;
    }
    

    public void refresh(){
    	items = GetTransactionedItems();
    	jlist.removeAll();
    	
        Iterator<TransactionedItem> i = items.iterator();
        while (i.hasNext()) {
        	jlist.add (i.next().toString());
        }    	
    }    
    
	public ArrayList<TransactionedItem> GetTransactionedItems(){
		return transaction.GetTransactionItems();
	}
	

    public void AddTransactionedItem(String productID, int quantity)
    {
    	transaction.AddTransactionItem(productID, quantity);
    	this.refresh();    	
    }
    
    public void EditTransactionedItem(String productID, int quantity)
    {
    	transaction.EditTransactionItem(productID, quantity);
    	//System.out.println(productID + " <-TranscationDialog.EditTransactionedItem-> " + quantity); //debug
    	this.refresh();    	
    }
    
    public void RemoveTransactionedItem(String productID)
    {
    	System.out.println(productID + " <-TranscationDialog.RemoveTransactionedItem"); //debug
    	transaction.RemoveTransactionItem(productID);
    	this.refresh();
    }
}
