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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	JList<?> list = new JList<Object>();
	DefaultListModel<String> defList=new DefaultListModel<String>();
	private JTable table;

	/**
	 * Create the dialog.
	 */
	public DiscountDialog() {
		setTitle("Discount manager");
		setBounds(100, 100, 632, 634);
		contentPanel.setBackground(new Color(244, 164, 96));
		list.setBounds(39, 192, 362, -172);
		DiscountManger.readExistingDiscountsFromDB();
		ArrayList<Discount> d=DiscountManger.convertToDiscountArraylist();
		table = new JTable();
		
		System.out.println(d.size());
		System.out.println(table.getRowCount());
		DefaultTableModel defaultTable=new DefaultTableModel(0,0);
	
		defaultTable.addColumn("Discount Code");
		defaultTable.addColumn("Description");
		defaultTable.addColumn("Period");
		defaultTable.addColumn("Start Date");
		defaultTable.addColumn("Discount Type");	
		
		for(Discount discount:d){
			List<String> slist = new ArrayList<String>();
			slist.add(discount.getCode());
			slist.add(discount.getDescription());
			slist.add(discount.getPeriod());
			slist.add(discount.getStartDate());
			slist.add(discount.discountType);
			defaultTable.addRow(slist.toArray());
			
		}
		getContentPane().setLayout(null);
		
		table.setModel(defaultTable);
		System.out.print(table.getRowCount());
		JScrollPane jscrollPane=new JScrollPane(table);
		jscrollPane.setBounds(15, 82, 582, 162);
		getContentPane().add(jscrollPane);
		
		JLabel discountCode = new JLabel("Discount Code :");
		JLabel discountDescription = new JLabel("Discount Description :");
		JLabel discountPeriod = new JLabel("Discount Period :");
		JLabel discountStartDate = new JLabel("Discount Start Date :");
		JLabel discountType = new JLabel("Discount Type :");
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
		
		JButton btnAddNewDiscount = new JButton("Add New Discount");
		btnAddNewDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddNewDiscount.setBounds(15, 472, 192, 44);
		getContentPane().add(btnAddNewDiscount);
		
		JButton btnEditDiscount = new JButton("Edit Discount");
		btnEditDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditDiscount.setBounds(210, 472, 192, 44);
		getContentPane().add(btnEditDiscount);
		
		JButton btnRemoveDiscount = new JButton("Remove Discount");
		btnRemoveDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRemoveDiscount.setBounds(405, 472, 192, 44);
		getContentPane().add(btnRemoveDiscount);
		
		JLabel lblDescription = new JLabel("");
		lblDescription.setBounds(210, 317, 387, 20);
		getContentPane().add(lblDescription);
		
		JLabel lblCode = new JLabel("");
		lblCode.setBounds(212, 270, 385, 20);
		getContentPane().add(lblCode);
		
		JLabel lblPeriod = new JLabel("");
		lblPeriod.setBounds(210, 363, 387, 20);
		getContentPane().add(lblPeriod);
		
		JLabel lblStartDate = new JLabel("");
		lblStartDate.setBounds(210, 405, 387, 20);
		getContentPane().add(lblStartDate);
		//scrollPane.setColumnHeaderView(table);
		contentPanel.setBackground(new Color(244, 164, 96));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				  System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
				  lblCode.setText(table.getValueAt(table.getSelectedRow(),0).toString());
				  lblDescription.setText(table.getValueAt(table.getSelectedRow(),1).toString());
				  lblPeriod.setText(table.getValueAt(table.getSelectedRow(),2).toString());
				  lblStartDate.setText(table.getValueAt(table.getSelectedRow(),3).toString());
				  
			}
	    });
	}
}
