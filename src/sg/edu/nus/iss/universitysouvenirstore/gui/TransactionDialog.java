package sg.edu.nus.iss.universitysouvenirstore.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.universitysouvenirstore.CustomException;
import sg.edu.nus.iss.universitysouvenirstore.Transaction;
import sg.edu.nus.iss.universitysouvenirstore.TransactionedItem;

public class TransactionDialog extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TransactionItemDialog transactionItemDialog;	
	private Transaction transaction = new Transaction();
	private boolean cashback = false;
	private ArrayList<TransactionedItem> items = new ArrayList<TransactionedItem>();
    float grandTotal;
    private boolean isMember = false;
	int memberCashback;
	
	TransactionMemberDialog memberInfo;
	
	private String memberID = "PUBLIC";
	private int memberPoint = 0;
	
	private int discount = 0;
	
	private final JPanel contentPanel = new JPanel();
	int numItems = 0;

	
	JButton checkOutButton = new JButton("Check Out");	
	JButton editBtn = new JButton("Edit Item");
	JButton removeButton = new JButton("Remove Item");
	
	JLabel subTotalLbl = new JLabel("");
	JLabel deductPointLbl = new JLabel("");
	JLabel discountLbl = new JLabel("");		
	JLabel grandTotalLbl = new JLabel("");	
	JLabel lblPublic = new JLabel("PUBLIC");
	JLabel pointsLBL = new JLabel("");
	//private TransactionPanel transactionPanel = new TransactionPanel (this);
	//Member Checkbox
	JCheckBox memberCheckBox = new JCheckBox("Member");
	//JList<String> jlist = new JList<String>();
	
	private java.awt.List jlist;
	
	public TransactionDialog() {	
		
		transactionItemDialog = new TransactionItemDialog(this);
		memberInfo = new TransactionMemberDialog(this);
		
		setTitle("Transaction");		
		setBounds(100, 100, 450, 427);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(244, 164, 96));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		discount = transaction.GetHighestDiscount();
		
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
		panel.setBounds(0, 345, 434, 35);
		contentPanel.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		
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
				else
					checkOutButton.setEnabled(false);
					
			}
		});
		removeButton.setEnabled(false);
		
		removeButton.setBounds(297, 184, 127, 37);
		contentPanel.add(removeButton);
		
		editBtn.setEnabled(false); //when no item in the list, Edit button is disabled
		checkOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				transaction.CheckOut();				
				transaction = null;
				
				transactionItemDialog.setVisible(false);
				transactionItemDialog.dispose();
				memberInfo.setVisible(false);
				memberInfo.dispose();
				
		        setVisible (false);
		        dispose();
			}
		});
		checkOutButton.setEnabled(false); //when no item in the list, Check Out button is disabled		
		
		checkOutButton.setActionCommand("Check Out");
		panel.add(checkOutButton);
		
		JButton button_3 = new JButton("Cancel");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transaction = null;
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


		memberCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		memberCheckBox.setBounds(297, 229, 75, 25);
		contentPanel.add(memberCheckBox);
		
		memberCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//on check, need to prompt Transaction Member screen
				if(memberCheckBox.isSelected()){					
					//TransactionMemberDialog memberInfo = new TransactionMemberDialog(member);
					memberInfo.setTitle("Member");
					memberInfo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					memberInfo.setEnabled(true);
					memberInfo.setVisible(true);
					//prompt 2nd screen that allow the store keeper to add the MemberID
					//in the 2nd screen once the Member ID is added, show the member's information
					//the member can select if they wanted to use the Points to deduct
					//lblPublic.setText(memberInfo.GetMemberID());					
				}
				else
				{
					lblPublic.setText("PUBLIC");
					memberID = "PUBLIC";
					memberPoint = 0;
					isMember = false;
					System.out.println("Uncheck");
					refresh();
				}
				

			}
		});
	}
	
	public void CreateLabelPanel(){
		//label for static text display		
		JLabel lblNewLabel = new JLabel("Sub Total:");
		lblNewLabel.setBounds(5, 267, 66, 16);
		contentPanel.add(lblNewLabel);
		
		JLabel lblDeductPoints = new JLabel("Cash Rebate:");
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
		lblMemberId.setFont(new Font("Cambria", Font.PLAIN, 13));
		lblMemberId.setBounds(5, 13, 75, 16);
		contentPanel.add(lblMemberId);
		lblPublic.setForeground(new Color(255, 255, 204));
		

		lblPublic.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 14));
		lblPublic.setBounds(80, 13, 75, 16);
		contentPanel.add(lblPublic);
	
		subTotalLbl.setBounds(201, 267, 84, 16);
		contentPanel.add(subTotalLbl);
		

		deductPointLbl.setBounds(201, 281, 56, 16);
		contentPanel.add(deductPointLbl);
		

		discountLbl.setBounds(201, 296, 56, 16);
		contentPanel.add(discountLbl);
		

		grandTotalLbl.setBounds(201, 310, 56, 16);
		contentPanel.add(grandTotalLbl);
		pointsLBL.setFont(new Font("Cambria", Font.PLAIN, 13));
		

		pointsLBL.setBounds(80, 33, 148, 16);
		contentPanel.add(pointsLBL);
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
    	
		discount = transaction.GetHighestDiscount();

    	
    	
        Iterator<TransactionedItem> i = items.iterator();
        while (i.hasNext()) {
        	jlist.add (i.next().toString());
        }    	
        
        if(!items.isEmpty())
        	checkOutButton.setEnabled(true);
        else
        	checkOutButton.setEnabled(false);
        
        String tempSubTotal = String.format("%.2f", transaction.GetTotalPrice());
        subTotalLbl.setText("$" + tempSubTotal);
        lblPublic.setText(memberID);
        
        if(cashback && isMember)
        {
        	memberCashback = memberPoint/100;
        	deductPointLbl.setText("$" + memberCashback);
        }
        else
        {
        	memberCashback = 0;
        	deductPointLbl.setText("N/A");      	
        }
        
        if(isMember)
        	pointsLBL.setText(String.valueOf("Points available: " + memberPoint));
        else
        	pointsLBL.setText(String.valueOf(""));
        
    	discountLbl.setText(String.valueOf(discount));
    	
        grandTotal = (float) (((transaction.GetTotalPrice() - memberCashback) * (100 - discount)) / 100);  
        
        System.out.println("GrandTotal: " + grandTotal + " MemberCashBack: " + memberCashback + " transaction.GetTotalPrice: "+ transaction.GetTotalPrice());
        
        String tempGrandtotal = String.format("%.2f", grandTotal);
        grandTotalLbl.setText("$" + tempGrandtotal);
    }    
    
	public ArrayList<TransactionedItem> GetTransactionedItems(){
		return transaction.GetTransactionItems();
	}
	

    public void AddTransactionedItem(String productID, int quantity, double price) throws CustomException
    {
    	boolean conflict = false;
    	//System.out.println("Quantity @TransactionDialog: " + quantity);
    	conflict = transaction.ConflictItem(productID);
    	if (!conflict)
    	{
    	
	    	transaction.AddTransactionItem(productID, quantity, price);
	    	this.refresh();    	
    	}
    	else
    	{
    		transaction.IncreaseTransactionItem(productID, quantity);
    		this.refresh();  
    		System.out.println("DUPLICATE");
    	}
    }
    
    public void EditTransactionedItem(String productID, int quantity) throws CustomException
    {
    	transaction.EditTransactionItem(productID, quantity);
		removeButton.setEnabled(false);
		editBtn.setEnabled(false);

    	//System.out.println(productID + " <-TranscationDialog.EditTransactionedItem-> " + quantity); //debug
    	this.refresh();    	
    }
    
    public void RemoveTransactionedItem(String productID) throws CustomException
    {
    	System.out.println(productID + " <-TranscationDialog.RemoveTransactionedItem"); //debug


        String title = "Remove Product";
        String msg = "Do you want to Delete " + productID + "?";
        
        int res = JOptionPane.showOptionDialog(null, title, msg, JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, null, null);
        	if (res == 0)
        	{
				transaction.RemoveTransactionItem(productID);
				
				removeButton.setEnabled(false);
				editBtn.setEnabled(false);
		    	refresh();
        	}
        /*
        ConfirmDialog d = new ConfirmDialog (null, title, msg) {

			protected boolean performOkAction () {

			}  
        }; 
        
        d.pack();
        d.setVisible(true);
        */
        
        
    }
    
    public void SetMemberPoint(int points){
    	memberPoint = points;
    	transaction.SetMemberPoints(points);
    }
    
    public void SetMemberID(String memberID){
    	this.memberID=memberID;
    	isMember = true;
    	transaction.SetMember(memberID);
    	refresh();
    }
    

	public void OptCashBack(boolean opt)
	{
		cashback = opt;
		transaction.SetCashBack(opt);
		refresh();
	}
	
	public void isMemberToogle ()
	{
		if(memberCheckBox.isSelected())
			isMember = true;
		else
			isMember = false;
	}
	
}
