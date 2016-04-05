package sg.edu.nus.iss.universitysouvenirstore.gui;

import sg.edu.nus.iss.universitysouvenirstore.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.BoxLayout;

public class DiscountDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JList list = new JList();
	DefaultListModel<String> defList=new DefaultListModel<String>();
	private JTable table;

	/**
	 * Create the dialog.
	 */
	public DiscountDialog() {
		setTitle("Discount manager");
		setBounds(100, 100, 450, 300);
		contentPanel.setBackground(new Color(244, 164, 96));
		list.setBounds(39, 192, 362, -172);
		DiscountManger.readExistingDiscountsFromDB();
		ArrayList<Discount> d=DiscountManger.convertToDiscountArraylist();
		table = new JTable();
		
		System.out.println(d.size());
		System.out.println(table.getRowCount());
		DefaultTableModel defaultTable=new DefaultTableModel(0,0);
	
		defaultTable.addColumn("Discount Type");
		defaultTable.addColumn("Discount Code");
		defaultTable.addColumn("Description");
		defaultTable.addColumn("Period");
		defaultTable.addColumn("Start Date");
		
		
		for(Discount discount:d){
			List<String> slist = new ArrayList<String>();
			slist.add(discount.discountType);
			slist.add(discount.getCode());
			slist.add(discount.getDescription());
			slist.add(discount.getPeriod());
			slist.add(discount.getStartDate());
			defaultTable.addRow(slist.toArray());
		}
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		table.setModel(defaultTable);
		System.out.print(table.getRowCount());
		JScrollPane jscrollPane=new JScrollPane(table);
		getContentPane().add(jscrollPane);
		//scrollPane.setColumnHeaderView(table);
		contentPanel.setBackground(new Color(244, 164, 96));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	}
}
