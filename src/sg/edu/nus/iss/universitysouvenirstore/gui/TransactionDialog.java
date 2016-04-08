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

/**
 * 
 * @author Lim Hean Soon
 * Transaction/Checkout screen
 */
public class TransactionDialog extends JFrame{
	//private static final long serialVersionUID = 1L;

	private Transaction transaction = new Transaction();
	private boolean cashback = false;
	private ArrayList<TransactionedItem> items = new ArrayList<TransactionedItem>();
	private float grandTotal;
    private boolean isMember = false;
    private int memberCashback;
	private String memberID = "PUBLIC";
	private int memberPoint = 0;	
	private int discount = 0;
	private int numItems = 0;
	
	private final JPanel contentPanel = new JPanel();

	private java.awt.List jlist;
	
	private JCheckBox memberCheckBox = new JCheckBox("Member");	
	private JButton button = new JButton("Add Item");
	private JButton checkOutButton = new JButton("Check Out");	
	private JButton editButton = new JButton("Edit Item");
	private JButton removeButton = new JButton("Remove Item");
	private JButton cancelButton = new JButton("Cancel");
	
	private JLabel lblNewLabel = new JLabel("Sub Total:");
	private JLabel lblDeductPoints = new JLabel("Cash Rebate:");
	private JLabel lblDiscount = new JLabel("Discount");
	private JLabel lblGrandTotal = new JLabel("Grand Total:");
	private JLabel lblItems = new JLabel("Items:");
	private JLabel lblMemberId = new JLabel("Member ID:");
	private JLabel subTotalLbl = new JLabel("");
	private JLabel deductPointLbl = new JLabel("");
	private JLabel discountLbl = new JLabel("");		
	private JLabel grandTotalLbl = new JLabel("");	
	private JLabel lblPublic = new JLabel("PUBLIC");
	private JLabel pointsLBL = new JLabel("");
	
	private TransactionMemberDialog memberInfo;
	private TransactionItemDialog transactionItemDialog;	
	
	/**
	 * Constructor
	 * initiate the screen layout as well as initiate the AddTransactionItem screen and GetMembers screen
	 */
	public TransactionDialog() {		
		transactionItemDialog = new TransactionItemDialog(this); //initiate the AddItem to this transaction screen
		memberInfo = new TransactionMemberDialog(this); //initiate the GetMember screen
		
		setTitle("Transaction");		
		setBounds(100, 100, 450, 427);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(244, 164, 96));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		discount = transaction.GetHighestDiscount(); //get the highest discount
		
		CreateButtonPanel();
		CreateLabelPanel();
	}
	
	/**
	 * Define the panel's items and the action for buttons
	 */
	public void CreateButtonPanel(){		
		jlist = new java.awt.List (10); //initiate the jlist with showing the 10 items
		jlist.setMultipleMode (false);
		jlist.setBounds(5, 101, 280, 153);
		contentPanel.add(jlist);//add jlist into the panel	

		button.addActionListener(new ActionListener() { //Add Item 	
			public void actionPerformed(ActionEvent arg0) {
				transactionItemDialog.setTitle("New Item");
				transactionItemDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				transactionItemDialog.setEnabled(true);
				transactionItemDialog.setVisible(true);
				transactionItemDialog.setCurProduct(null);
				//condition to tell the Add item screen on whether it's Add/Edit or Remove item caller
				transactionItemDialog.setEditCase(false); 
				transactionItemDialog.setDeleteCase(false);
				transactionItemDialog.Refresh(); //refresh this screen
			}			
		});
		button.setBounds(297, 101, 127, 37);
		contentPanel.add(button);		

		editButton.addActionListener(new ActionListener() {//Edit selected item (trigger the Edit Item Quantity Screen)
			public void actionPerformed(ActionEvent e) {
				transactionItemDialog.setTitle("New Item");
				transactionItemDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				transactionItemDialog.setEnabled(true);
				transactionItemDialog.setVisible(true);
				transactionItemDialog.setCurProduct(null);				
				//condition to tell the Add item screen on whether it's Add/Edit or Remove item caller
				transactionItemDialog.setEditCase(true);
				transactionItemDialog.setDeleteCase(false);				
				transactionItemDialog.Refresh();				
				if(jlist.getSelectedItem() != null) //no action will be triggered if no item is being selected
				{
					transactionItemDialog.EditItem(jlist.getSelectedItem());
				}				
			}
		});
		editButton.setBounds(297, 143, 127, 37);
		contentPanel.add(editButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 352, 434, 35);
		contentPanel.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));		
		
		removeButton.addActionListener(new ActionListener() {//Remove selected item (trigger the Remove Item Screen)
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
				if(jlist.getSelectedItem() != null) //no action will be triggered if no item is being selected
				{
					transactionItemDialog.EditItem(jlist.getSelectedItem());
				}
				else
					checkOutButton.setEnabled(false);
					
			}
		});		
		removeButton.setBounds(297, 184, 127, 37);
		contentPanel.add(removeButton);		
		
		removeButton.setEnabled(false);  //when no item in the list, Remove button is disabled
		editButton.setEnabled(false); //when no item in the list, Edit button is disabled
		
		checkOutButton.addActionListener(new ActionListener() {//Check out button, only able to check out if there's at least 1 item
			public void actionPerformed(ActionEvent e){
				transaction.CheckOut();	//initiate the checkout method
				transaction = null; //destroy the transction
				
				transactionItemDialog.setVisible(false); //destroy the add transaction screen (if it's still visible)
				transactionItemDialog.dispose();
				memberInfo.setVisible(false);//destroy the member screen (if it's still visible)
				memberInfo.dispose();
				
		        String title = "~Transaction Completed~";
		        String msg = "Thank you, please Come Again";
		        
		        int res = JOptionPane.showOptionDialog(null, msg, title, JOptionPane.DEFAULT_OPTION,
		                JOptionPane.INFORMATION_MESSAGE, null, null, null);				//prompt the Transaction complete screen
				
		        setVisible (false); //destroy this screen
		        dispose();
			}
		});
		checkOutButton.setEnabled(false); //when no item in the list, Check Out button is disabled		
		checkOutButton.setActionCommand("Check Out");
		panel.add(checkOutButton);		

		cancelButton.addActionListener(new ActionListener() { //destroy this class
			public void actionPerformed(ActionEvent e) {
				transaction = null;
		        setVisible (false);
		        dispose();
			}
		});
		
		cancelButton.setActionCommand("Cancel");
		panel.add(cancelButton);
		
		jlist.addMouseListener(new MouseAdapter() { //when the user selected any item, enabled the Remove and Edit button
			@Override
			public void mouseClicked(MouseEvent e) {
				removeButton.setEnabled(true);
				editButton.setEnabled(true);
			}
		});

		memberCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 12)); //checkbox to indicate it's a member
		memberCheckBox.setBounds(297, 229, 75, 25);
		contentPanel.add(memberCheckBox);
		
		//prompt 2nd screen that allow the store keeper to add the MemberID
		//in the 2nd screen once the Member ID is added, show the member's information
		//the member can select if they wanted to use the Points to deduct
		memberCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//on check, need to prompt Transaction Member screen
				if(memberCheckBox.isSelected()){					
					//TransactionMemberDialog memberInfo = new TransactionMemberDialog(member);
					memberInfo.setTitle("Member");
					memberInfo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					memberInfo.setEnabled(true);
					memberInfo.setVisible(true);				
				}
				else
				{
					lblPublic.setText("PUBLIC");
					memberID = "PUBLIC";
					memberPoint = 0;
					isMember = false;
					//System.out.println("Uncheck"); //debug
					refresh();
				}
			}
		});
	}
	
	/**
	 * Adding all the labels/text into the panel
	 */
	public void CreateLabelPanel(){
		//label for static text display		

		lblNewLabel.setBounds(5, 267, 66, 16);
		contentPanel.add(lblNewLabel);
		

		lblDeductPoints.setBounds(5, 281, 97, 16);
		contentPanel.add(lblDeductPoints);
		

		lblDiscount.setBounds(5, 296, 66, 16);
		contentPanel.add(lblDiscount);
		

		lblGrandTotal.setBounds(5, 310, 84, 16);
		contentPanel.add(lblGrandTotal);

		lblItems.setBounds(5, 84, 66, 16);
		contentPanel.add(lblItems);
		

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

	/**
	 * Destroy the screen when the user press on 'x'
	 */
    public void shutdown () {
        System.exit(0);
    }
    
	/**
	 * Refresh this screen
	 */
    public void refresh(){
    	items = GetTransactionedItems(); //get the list of items for this transaction
    	jlist.removeAll(); //destroy the jlist
    	
		discount = transaction.GetHighestDiscount(); //get the highest discount for calculation to show in the summary    	
    	
        Iterator<TransactionedItem> i = items.iterator(); //iterate thru the list of item
        while (i.hasNext()) {
        	jlist.add (i.next().toString()); //add each of the item into the jlist
        }    	
        
        if(!items.isEmpty()) //if there's at least 1 item, Check Out button is enabled to allow the user to complete the transaction
        	checkOutButton.setEnabled(true);
        else
        	checkOutButton.setEnabled(false);
        
        String tempSubTotal = String.format("%.2f", transaction.GetTotalPrice()); //show the SubTotal
        subTotalLbl.setText("$" + tempSubTotal);

        
        if(cashback && isMember) //indicate if there's cash back
        {
        	memberCashback = memberPoint/100;
        	deductPointLbl.setText("$" + memberCashback);
        }
        else
        {
        	memberCheckBox.setSelected(false); //uncheck the Member box if there's no memberID provided in the member screen
        	memberCashback = 0;
        	deductPointLbl.setText("N/A");      	
        }
        
        lblPublic.setText(memberID);
        
        if(isMember)
        	pointsLBL.setText(String.valueOf("Points available: " + memberPoint)); //show the available points
        else
        	pointsLBL.setText(String.valueOf(""));
        
    	discountLbl.setText(String.valueOf(discount + "%")); //show the discount %
    	
        grandTotal = (float) (((transaction.GetTotalPrice() - memberCashback) * (100 - discount)) / 100);  //show the grand total the user need to pay
        
        //System.out.println("GrandTotal: " + grandTotal + " MemberCashBack: " + memberCashback + " transaction.GetTotalPrice: "+ transaction.GetTotalPrice()); //debug        
        String tempGrandtotal = String.format("%.2f", grandTotal);
        grandTotalLbl.setText("$" + tempGrandtotal);
    }    
    
    /**
     * get the list of the items for this transaction
     * returned an ArrayList of TransactionedItem
     * @return
     */
	public ArrayList<TransactionedItem> GetTransactionedItems(){
		return transaction.GetTransactionItems();
	}
	
	/**
	 * Add new ProductID into the list
	 * @param productID
	 * @param quantity
	 * @param price
	 * @throws CustomException
	 * 
	 * call the Transaction's method which will in turn add TransactionedItem into the list
	 */
    public void AddTransactionedItem(String productID, int quantity, double price) throws CustomException
    {
    	boolean conflict = false;
    	//System.out.println("Quantity @TransactionDialog: " + quantity);
    	conflict = transaction.ConflictItem(productID);
    	if (!conflict){//new productID, hence adding this as new entry
	    	transaction.AddTransactionItem(productID, quantity, price);
	    	this.refresh();    	
    	}
    	else//productID already existed, instead of adding a new entry, increase the quantity instead
    	{
    		transaction.IncreaseTransactionItem(productID, quantity);
    		this.refresh();  
    		//System.out.println("DUPLICATE");
    	}
    }
    
    /**
     * Edit the Product's quantity
     * @param productID
     * @param quantity
     * @throws CustomException
     */
    public void EditTransactionedItem(String productID, int quantity) throws CustomException
    {
    	transaction.EditTransactionItem(productID, quantity);
		removeButton.setEnabled(false);
		editButton.setEnabled(false);

    	//System.out.println(productID + " <-TranscationDialog.EditTransactionedItem-> " + quantity); //debug
    	this.refresh();    	
    }
    
    /**
     * Remove the Product
     * @param productID
     * @throws CustomException
     */
    public void RemoveTransactionedItem(String productID) throws CustomException
    {
    	//System.out.println(productID + " <-TranscationDialog.RemoveTransactionedItem"); //debug
        String title = "Remove Product";
        String msg = "Do you want to Delete " + productID + "?";
        
        int res = JOptionPane.showOptionDialog(null, title, msg, JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, null, null);
        	if (res == 0)
        	{
				transaction.RemoveTransactionItem(productID);
				
				removeButton.setEnabled(false);
				editButton.setEnabled(false);
		    	refresh();
        	}        
    }
    
    /**
     * Set the Member's point which will be used to compute the Cashback as well as adding new points on successful purchase
     * @param points
     */
    public void SetMemberPoint(int points){
    	memberPoint = points;
    	transaction.SetMemberPoints(points);
    }
    
    /**
     * Set the Member ID - after that call a refresh
     * @param memberID
     */
    public void SetMemberID(String memberID){
    	this.memberID=memberID;
    	isMember = true;
    	transaction.SetMember(memberID);
    	refresh();
    }
    
    /**
     * Set the Opt in to Cashback's option
     * @param opt
     */
	public void OptCashBack(boolean opt)
	{
		cashback = opt;
		transaction.SetCashBack(opt);
		refresh();
	}
	
	/**
	 * Keep track of whether this is a member or a public customer
	 */
	public void isMemberToogle ()
	{
		if(memberCheckBox.isSelected())
			isMember = true;
		else
			isMember = false;
	}	
}
