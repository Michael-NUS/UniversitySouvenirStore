package sg.edu.nus.iss.universitysouvenirstore.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.universitysouvenirstore.CustomException;

public class CategoryInfoDialog extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCategoryId;
	private JTextField txtDescription;
	public CategoryManagerDialog categoryManager;
	public int position=-1;
	public CategoryInfoDialog(){
		setTitle("Category manager");
		setBounds(100, 100, 450, 300);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(358, 243, 86, 29);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeDialog();
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(btnCancel);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getTitle().equals("Add New Category")){
					saveDialog("AddCategory");
				}else if(getTitle().equals("Edit Category")){
					saveDialog("EditCategory");
				}
				
			}
		});
		btnOk.setBounds(246, 243, 117, 29);
		getContentPane().add(btnOk);
		
		JLabel lblCategoryCode = new JLabel("Category Code");
		lblCategoryCode.setBounds(6, 6, 122, 16);
		getContentPane().add(lblCategoryCode);
		
		txtCategoryId = new JTextField();
		txtCategoryId.setBounds(131, 6, 130, 26);
		getContentPane().add(txtCategoryId);
		txtCategoryId.setColumns(3);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(6, 50, 122, 16);
		getContentPane().add(lblDescription);
		
		txtDescription = new JTextField();
		txtDescription.setBounds(131, 45, 130, 26);
		getContentPane().add(txtDescription);
		txtDescription.setColumns(45);
		contentPanel.setBackground(new Color(244, 164, 96));
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	}
	public void setCategoryMgr(CategoryManagerDialog categoryMgr){
		this.categoryManager=categoryMgr;
	}
	public void saveDialog(String dialogType){
		try{
			this.categoryManager.updateManager(this.txtCategoryId.getText(), this.txtDescription.getText(),position);
			closeDialog();
		}catch(CustomException e){
			if(e.getMessage().equals("Category_Code_Error")){
				JOptionPane.showMessageDialog(null, "Please enter 3 uppercase letters for Category Code","Error",JOptionPane.ERROR_MESSAGE);	
			}else if(e.getMessage().equals("Already_Exist_Error")){
				JOptionPane.showMessageDialog(null, "Please choose other category code as current code already used!","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	
	}
	public void closeDialog(){
		clearEditData();
		this.setVisible(false);
		this.dispose();
	}
	public void showDialog(){
		this.setVisible(true);
	}
	public void clearEditData(){
		this.txtCategoryId.setText("");
		this.txtDescription.setText("");
	}
	public void setEditData(String categoryId,String description){
		this.txtCategoryId.setText(categoryId);
		this.txtDescription.setText(description);
	}
}
