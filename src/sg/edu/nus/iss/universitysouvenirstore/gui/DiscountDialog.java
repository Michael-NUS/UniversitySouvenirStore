/**
 * @author nyinyizin
 * @version 1.0
 */
package sg.edu.nus.iss.universitysouvenirstore.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import sg.edu.nus.iss.universitysouvenirstore.Discount;
import sg.edu.nus.iss.universitysouvenirstore.DiscountManger;

public class DiscountDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	JList<?> list = new JList<Object>();
	DefaultListModel<String> defList=new DefaultListModel<String>();
	private JTable table;
	private DiscountInfoMgrDialog discountInfoMgrDialog=new DiscountInfoMgrDialog();
	private Discount selectedDiscount;
	private DefaultTableModel defaultTable=new DefaultTableModel(0,0);
	private 		JButton btnAddNewDiscount = new JButton("Add New Discount");
	JLabel discountCode = new JLabel("Discount Code :");
	JLabel discountDescription = new JLabel("Discount Description :");
	JLabel discountPeriod = new JLabel("Discount Period :");
	JLabel discountStartDate = new JLabel("Discount Start Date :");
	JLabel discountType = new JLabel("Discount Type :");
	JButton btnEditDiscount = new JButton("Edit Discount");
	JButton btnRemoveDiscount = new JButton("Remove Discount");
	JLabel lblDiscountType = new JLabel("");
	JLabel lblDescription = new JLabel("");
	JLabel lblCode = new JLabel("");
	JLabel lblPeriod = new JLabel("");
	JLabel lblStartDate = new JLabel("");
	JLabel lblDiscountPercentageLabel = new JLabel("Discount Percentage :");
	JLabel lblDiscountPercentage = new JLabel("");
	JScrollPane jscrollPane=new JScrollPane();
	/**
	 * Create the dialog.
	 */
	public DiscountDialog() {
		setTitle("Discount manager");
		setBounds(100, 100, 632, 634);
		contentPanel.setBackground(new Color(244, 164, 96));
		list.setBounds(39, 192, 362, -172);
		table = new JTable();
		
		defaultTable.addColumn("Discount Code");
		defaultTable.addColumn("Description");
		defaultTable.addColumn("Period");
		defaultTable.addColumn("Start Date");
		defaultTable.addColumn("Discount Percentage");
		defaultTable.addColumn("Discount Type");	
		
		getContentPane().setLayout(null);

		jscrollPane.setBounds(15, 82, 582, 162);
		getContentPane().add(jscrollPane);
		
	
		discountCode.setBounds(15, 260, 163, 44);
		discountDescription.setBounds(15, 305, 163, 44);
		discountPeriod.setBounds(15, 351, 163, 44);
		discountStartDate.setBounds(15, 393, 163, 44);
		discountType.setBounds(26, 16, 282, 162);
		discountType.setBounds(0, 80, 119, -80);
		getContentPane().add(discountCode);
		getContentPane().add(discountDescription);
		getContentPane().add(discountPeriod);
		getContentPane().add(discountStartDate);
		getContentPane().add(discountType);
		
		discountInfoMgrDialog.setDiscountDialog(this);
		btnAddNewDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				discountInfoMgrDialog.setTitle("Add New Discount");
				discountInfoMgrDialog.enableFields("Members");
				discountInfoMgrDialog.setVisible(true);
			}
		});
		btnAddNewDiscount.setBounds(15, 518, 192, 44);
		getContentPane().add(btnAddNewDiscount);
		
	
		btnEditDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				discountInfoMgrDialog.setTitle("Edit Discount");
				discountInfoMgrDialog.setData(selectedDiscount);
				discountInfoMgrDialog.setVisible(true);
			}
		});
		btnEditDiscount.setBounds(210, 518, 192, 44);
		getContentPane().add(btnEditDiscount);
		

		btnRemoveDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(DiscountManger.removeDiscount(selectedDiscount.getCode())){
					defaultTable.removeRow(table.getSelectedRow());
					defaultTable.fireTableDataChanged();
					table.repaint();
					clearTableData();
				}
			
			}
		});
		btnRemoveDiscount.setBounds(405, 518, 192, 44);
		getContentPane().add(btnRemoveDiscount);
		

		lblDescription.setBounds(220, 317, 387, 20);
		getContentPane().add(lblDescription);
		

		lblCode.setBounds(220, 270, 385, 20);
		getContentPane().add(lblCode);
		

		lblPeriod.setBounds(220, 363, 387, 20);
		getContentPane().add(lblPeriod);
		

		lblStartDate.setBounds(220, 399, 387, 20);
		

		lblDiscountPercentageLabel.setBounds(15, 441, 163, 20);
		getContentPane().add(lblDiscountPercentageLabel);
		

		lblDiscountPercentage.setBounds(220, 441, 383, 20);
		getContentPane().add(lblDiscountPercentage);
		
		JLabel lblDiscountTypeLabel = new JLabel("Discount Type :");
		lblDiscountTypeLabel.setBounds(15, 477, 163, 20);
		getContentPane().add(lblDiscountTypeLabel);
		
	
		lblDiscountType.setBounds(220, 482, 375, 20);
		getContentPane().add(lblDiscountType);
		getContentPane().add(lblStartDate);
		//scrollPane.setColumnHeaderView(table);
		contentPanel.setBackground(new Color(244, 164, 96));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		updateDiscountTableData();
		
		btnEditDiscount.setEnabled(false);
		btnRemoveDiscount.setEnabled(false);
	
	}
	
	/**
	 * Add new Discount Row into Table
	 * @param d Discount Object
	 */
	public void addDiscountToTable(Discount d){
		if(DiscountManger.addDiscount(d.getCode(), d.getDescription(), d.getStartDate(), d.getPeriod(), d.getPercentage(), d.getType())){
			List<String> slist = new ArrayList<String>();
			slist.add(d.getCode());
			slist.add(d.getDescription());
			slist.add(d.getPeriod());
			slist.add(d.getStartDate());
			slist.add(Integer.toString(d.getPercentage()));
			slist.add(d.discountType);
			defaultTable.addRow(slist.toArray());
		}
	}
	
	/**
	 * Update Discount Table
	 */
	public void updateDiscountTableData(){
		DiscountManger.readExistingDiscountsFromDB();
		ArrayList<Discount> d=DiscountManger.convertToDiscountArraylist();
		for(Discount discount:d){
			List<String> slist = new ArrayList<String>();
			slist.add(discount.getCode());
			slist.add(discount.getDescription());
			slist.add(discount.getPeriod());
			slist.add(discount.getStartDate());
			slist.add(Integer.toString(discount.getPercentage()));
			slist.add(discount.discountType);
			defaultTable.addRow(slist.toArray());
		}
		table=new JTable(defaultTable);
		jscrollPane.setViewportView(table);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				  btnEditDiscount.setEnabled(true);
				  btnRemoveDiscount.setEnabled(true);
				  if(table.getSelectedRow()!=-1){
				  selectedDiscount=new Discount(table.getValueAt(table.getSelectedRow(),0).toString(),table.getValueAt(table.getSelectedRow(),1).toString(),table.getValueAt(table.getSelectedRow(),3).toString(),table.getValueAt(table.getSelectedRow(),2).toString(),Integer.parseInt(table.getValueAt(table.getSelectedRow(),4).toString()),table.getValueAt(table.getSelectedRow(),5).toString());
				  lblCode.setText(selectedDiscount.getCode());
				  lblDescription.setText(selectedDiscount.getDescription());
				  lblPeriod.setText(selectedDiscount.getPeriod());
				  lblStartDate.setText(selectedDiscount.getStartDate());
				  lblDiscountPercentage.setText(selectedDiscount.getPercentage()+" %");
				  lblDiscountType.setText(selectedDiscount.getType());
				  }
			}
	    });
	}
	
	/**
	 * Update Table after Discount object Edited
	 * @param d Discount Object
	 */
	public void editDiscount(Discount d){
		
		defaultTable.fireTableDataChanged();
		table.repaint();
		defaultTable.setRowCount(0);
		updateDiscountTableData();
		clearTableData();
	}
	
	/**
	 * Clear Table 
	 */
	public void clearTableData(){
		btnEditDiscount.setEnabled(false);
		btnRemoveDiscount.setEnabled(false);
		selectedDiscount=null;
		lblCode.setText("");
		  lblDescription.setText("");
		  lblPeriod.setText("");
		  lblStartDate.setText("");
		  lblDiscountPercentage.setText("");
		  lblDiscountType.setText("");
	}
}
