/**
 * @author nyinyizin
 * @version 1.0
 */
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
		setBackground(new Color(244, 164, 96));
		getContentPane().setBackground(new Color(244,164,96));

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(281, 165, 117, 29);
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
		btnOk.setBounds(172, 165, 117, 29);
		getContentPane().add(btnOk);

		JLabel lblCategoryCode = new JLabel("Category Code");
		lblCategoryCode.setBounds(38, 50, 122, 16);
		getContentPane().add(lblCategoryCode);

		txtCategoryId = new JTextField();
		txtCategoryId.setBounds(172, 45, 226, 26);
		getContentPane().add(txtCategoryId);
		txtCategoryId.setColumns(3);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(38, 106, 122, 16);
		getContentPane().add(lblDescription);

		txtDescription = new JTextField();
		txtDescription.setBounds(172, 101, 226, 26);
		getContentPane().add(txtDescription);
		txtDescription.setColumns(45);

		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

	}
	public void setCategoryMgr(CategoryManagerDialog categoryMgr){
		this.categoryManager=categoryMgr;
	}
	public void saveDialog(String dialogType){
		try{
			if(txtCategoryId.getText().isEmpty()||txtDescription.getText().isEmpty()){
				throw new CustomException("Empty_Data");
			}else if(txtDescription.getText().contains(",")){
				throw new CustomException("Comma_in_descritpion");
			}
			if(dialogType.equals("AddCategory")){
				this.categoryManager.updateManager(this.txtCategoryId.getText(), this.txtDescription.getText(),-1);
			}else{
				this.categoryManager.updateManager(this.txtCategoryId.getText(), this.txtDescription.getText(),position);
			}

			closeDialog();
		}catch(CustomException e){
			if(e.getMessage().equals("Category_Code_Error")){
				JOptionPane.showMessageDialog(null, "Please enter 3 uppercase letters for Category Code","Error",JOptionPane.ERROR_MESSAGE);
			}else if(e.getMessage().equals("Already_Exist_Error")){
				JOptionPane.showMessageDialog(null, "Please choose other category code as current code already used!","Error",JOptionPane.ERROR_MESSAGE);
			}else if(e.getMessage().equals("Empty_Data")){
				JOptionPane.showMessageDialog(null, "Category code or category description cannot be empty!","Error",JOptionPane.ERROR_MESSAGE);
			}else if(e.getMessage().equals("Comma_in_descritpion")){
				JOptionPane.showMessageDialog(null, "Category Description cannot contain comma (,) !","Error",JOptionPane.ERROR_MESSAGE);
			}
		}

	}
	public void closeDialog(){
		clearEditData();
		this.setVisible(false);
		this.dispose();
	}
	public void showDialog(){
		if(this.getTitle().equals("Edit Category")){
			this.txtCategoryId.setEditable(false);
		}else if(this.getTitle().equals("Add New Category")){
			this.txtCategoryId.setEditable(true);
		}
		this.setVisible(true);
	}
	public void clearEditData(){
		this.txtCategoryId.setText("");
		this.txtDescription.setText("");
		this.txtCategoryId.setEditable(true);
	}
	public void setEditData(String categoryId,String description){
		this.txtCategoryId.setText(categoryId);
		this.txtDescription.setText(description);
	}
}
