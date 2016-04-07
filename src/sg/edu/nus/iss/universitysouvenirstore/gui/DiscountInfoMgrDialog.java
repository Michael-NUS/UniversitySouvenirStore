package sg.edu.nus.iss.universitysouvenirstore.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.universitysouvenirstore.Discount;
import sg.edu.nus.iss.universitysouvenirstore.DiscountManger;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class DiscountInfoMgrDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtDiscountCode;
	private JTextField txtDiscountPeriod;
	private JTextField txtDiscountStartDate;
	private JTextField txtDiscountDescription;
	private JLabel lblPeriodDesciption = new JLabel("");
	private JLabel lblStartDateDescription = new JLabel("");
	private JComboBox<String> cboDiscountType = new JComboBox<String>();
	private JLabel lblDPercantage = new JLabel("0 %");
	private JSlider slider = new JSlider();
	public DiscountDialog discountDialog;
	public DiscountInfoMgrDialog() {
		setTitle("Discount Information Manger");
		setBounds(100, 100, 662, 552);
		contentPanel.setBackground(new Color(244, 164, 96));
		DiscountManger.readExistingDiscountsFromDB();
		getContentPane().setLayout(null);
		
		JLabel discountCode = new JLabel("Discount Code :");
		JLabel discountDescription = new JLabel("Discount Description :");
		JLabel discountPeriod = new JLabel("Discount Period :");
		JLabel discountStartDate = new JLabel("Discount Start Date :");
		JLabel discountType = new JLabel("Discount Type :");
		discountCode.setBounds(15, 66, 163, 44);
		discountDescription.setBounds(15, 126, 163, 44);
		discountPeriod.setBounds(15, 186, 163, 44);
		discountStartDate.setBounds(15, 246, 163, 44);
		discountType.setBounds(26, 16, 282, 162);
		discountType.setBounds(0, 80, 119, -80);
		getContentPane().add(discountCode);
		getContentPane().add(discountDescription);
		getContentPane().add(discountPeriod);
		getContentPane().add(discountStartDate);
		getContentPane().add(discountType);
		
		JButton btnOK = new JButton("Save");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dType=cboDiscountType.getSelectedItem().toString().equals("Members")?"M":"A";
				String dCode=txtDiscountCode.getText();
				String dDescription=txtDiscountDescription.getText();
				int dPercentage=slider.getValue();
				String dStartDate=txtDiscountStartDate.getText();
				String dPeriod=txtDiscountPeriod.getText();
				if(getTitle().equals("Edit Discount")){
					DiscountManger.updateDiscountDescription(dCode, dDescription);
					DiscountManger.updateDiscountPercentage(dCode, dPercentage);
					DiscountManger.updateDiscountPeriod(dCode, dPeriod);
					DiscountManger.updateDiscountStartDate(dCode, dStartDate);
					DiscountManger.updateDiscountType(dCode, dType);
					discountDialog.editDiscount(new Discount(dCode, dDescription, dStartDate, dPeriod, dPercentage, dType));
					setVisible(false);
	
				}else{
					if(!DiscountManger.checkExistOfDiscount(txtDiscountCode.getText())){
						discountDialog.addDiscountToTable(new Discount(dCode, dDescription, dStartDate, dPeriod, dPercentage, dType));
						clearData();
						setVisible(false);
					}
				}
			}
		});
		btnOK.setBounds(75, 369, 192, 44);
		getContentPane().add(btnOK);
		
		JButton btnRemoveDiscount = new JButton("Cancel");
		btnRemoveDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearData();
				setVisible(false);
			}
		});
		btnRemoveDiscount.setBounds(282, 369, 192, 44);
		getContentPane().add(btnRemoveDiscount);
		
		JLabel lblNewLabel = new JLabel("Discount Type :");
		lblNewLabel.setBounds(15, 16, 163, 44);
		getContentPane().add(lblNewLabel);
		
		txtDiscountCode = new JTextField();
		txtDiscountCode.setBounds(195, 75, 397, 26);
		getContentPane().add(txtDiscountCode);
		txtDiscountCode.setColumns(10);
		
	
		cboDiscountType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				enableFields(arg0.getItem().toString());
			}
		});
		cboDiscountType.setModel(new DefaultComboBoxModel<String>(new String[] {"Members", "Public"}));
		cboDiscountType.setBounds(193, 25, 148, 26);
		getContentPane().add(cboDiscountType);
		
		txtDiscountPeriod = new JTextField();
		txtDiscountPeriod.setBounds(193, 195, 281, 26);
		getContentPane().add(txtDiscountPeriod);
		txtDiscountPeriod.setColumns(10);
		
		txtDiscountStartDate = new JTextField();
		txtDiscountStartDate.setBounds(195, 255, 279, 26);
		getContentPane().add(txtDiscountStartDate);
		txtDiscountStartDate.setColumns(10);
		
		txtDiscountDescription = new JTextField();
		txtDiscountDescription.setBounds(193, 135, 399, 26);
		getContentPane().add(txtDiscountDescription);
		txtDiscountDescription.setColumns(10);
		
	
		lblPeriodDesciption.setBounds(505, 198, 106, 20);
		getContentPane().add(lblPeriodDesciption);
		
		
		lblStartDateDescription.setBounds(505, 258, 106, 20);
		getContentPane().add(lblStartDateDescription);
		
		JLabel lblDiscountPercentage = new JLabel("Discount Percentage :");
		lblDiscountPercentage.setBounds(15, 306, 163, 20);
		getContentPane().add(lblDiscountPercentage);
		
		
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblDPercantage.setText(slider.getValue()+" %");
			}
		});

		slider.setValue(0);
		slider.setBounds(191, 300, 288, 26);
		getContentPane().add(slider);
		

		lblDPercantage.setBounds(485, 306, 69, 20);
		getContentPane().add(lblDPercantage);
		contentPanel.setBackground(new Color(244, 164, 96));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	
	}
	public void setDiscountDialog(DiscountDialog discountDialog){
		this.discountDialog=discountDialog;
	}
	public void enableFields(String discountType){
		if(discountType.equals("Members")){
			lblStartDateDescription.setText("");
			txtDiscountStartDate.setText("ALWAYS");
			txtDiscountStartDate.setEditable(false);
			txtDiscountPeriod.setText("ALWAYS");
			txtDiscountPeriod.setEditable(false);
		}else{
			lblStartDateDescription.setText("(yyyy-mm-dd)");
			txtDiscountStartDate.setText("");
			txtDiscountStartDate.setEditable(true);
			txtDiscountPeriod.setText("");
			txtDiscountPeriod.setEditable(true);
		}
	}
	public void setData(Discount d){
		txtDiscountCode.setText(d.getCode());
		txtDiscountDescription.setText(d.getDescription());
		slider.setValue(d.getPercentage());
		if(d.discountType.equals("M")){
			cboDiscountType.setSelectedIndex(0);
			enableFields("Members");
		}else if(d.discountType.equals("A")){
			cboDiscountType.setSelectedIndex(1);
			txtDiscountStartDate.setEditable(true);
			txtDiscountPeriod.setEditable(true);
			txtDiscountStartDate.setText(d.getStartDate());
			txtDiscountPeriod.setText(d.getPeriod());
		}
	}
	public void clearData(){
		txtDiscountCode.setText("");
		txtDiscountDescription.setText("");
	}
}
