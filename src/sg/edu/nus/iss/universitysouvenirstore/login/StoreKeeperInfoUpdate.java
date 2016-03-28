/**
 * File name : StoreKeeperInfoUpdate.java
 * 
 * Description : JFrame GUI class for handling Storekeeper information
 * 
 * @author : NayLA 
 * 
 * Date :10/03/2016
 * 
 */

package sg.edu.nus.iss.universitysouvenirstore.login;

import sg.edu.nus.iss.universitysouvenirstore.Storekeeper;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class StoreKeeperInfoUpdate extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldRetype;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	public AlertDialog alertdialog = new AlertDialog();
	public UserStatusMessage userstatusmessagedialog = new UserStatusMessage();
	public SelectionAlert selectionalertdialog = new SelectionAlert();
	public RegistrationStatus registrationstatusdialog = new RegistrationStatus("");
	
	/**
	 * Create the frame.
	 */
	public StoreKeeperInfoUpdate() {
		
		setTitle("Storekeeper Management");
		//
		setBounds(100, 100, 458, 289);		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(244, 164, 96));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setBounds(131, 70, 183, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(131, 105, 183, 20);
		contentPane.add(passwordField);
			
		JLabel lblUsername = new JLabel("Type user name");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsername.setForeground(new Color(0, 0, 128));
		lblUsername.setBounds(10, 72, 111, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Type password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setForeground(new Color(0, 0, 139));
		lblPassword.setBounds(10, 97, 111, 35);
		contentPane.add(lblPassword);
		
		JLabel lblRegistration = new JLabel("         User Management");
		lblRegistration.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRegistration.setForeground(new Color(0, 0, 128));
		lblRegistration.setBounds(131, 11, 183, 22);
		contentPane.add(lblRegistration);
		
		passwordFieldRetype = new JPasswordField();
		passwordFieldRetype.setBounds(131, 142, 183, 23);
		contentPane.add(passwordFieldRetype);
		
		JLabel lblRetypePassword = new JLabel("Re-type password");
		lblRetypePassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRetypePassword.setForeground(new Color(0, 0, 128));
		lblRetypePassword.setBounds(10, 138, 123, 28);
		contentPane.add(lblRetypePassword);
			
		JRadioButton rdbtnAdd = new JRadioButton("Add");
		rdbtnAdd.setBackground(new Color(205, 92, 92));
		buttonGroup.add(rdbtnAdd);
		
		rdbtnAdd.setBounds(131, 40, 91, 23);
		contentPane.add(rdbtnAdd);
		
		JRadioButton rdbtnRemove = new JRadioButton("Remove");
		rdbtnRemove.setBackground(new Color(205, 92, 92));
		buttonGroup.add(rdbtnRemove);
		
		rdbtnRemove.setBounds(224, 40, 90, 23);
		contentPane.add(rdbtnRemove);
		
		rdbtnAdd.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
			
				usernameField.setText("");/* Clear */
				
				lblPassword.setVisible(true);
				passwordField.setVisible(true);
				passwordField.setText("");/* Clear */
				
				lblRetypePassword.setVisible(true);
				passwordFieldRetype.setVisible(true);	
				passwordFieldRetype.setText("");/* Clear */
				
			}		
		});
		
		rdbtnRemove.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg1) {
				
				usernameField.setText("");/* Clear */
			
				lblPassword.setVisible(false);
				passwordField.setVisible(false);
				passwordField.setText("");/* Clear */
				
				lblRetypePassword.setVisible(false);
				passwordFieldRetype.setVisible(false);
				passwordFieldRetype.setText("");/* Clear */
			}			
		});
						
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(162, 180, 129, 35);		
		contentPane.add(btnConfirm);
		
		btnConfirm.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg2) {
				
				Storekeeper storekeeper = new Storekeeper();										
				storekeeper.setUsernameAndPassword(usernameField.getText() ,String.valueOf(passwordField.getPassword()));
				
				if(rdbtnAdd.isSelected()){
					
					Storekeeper existingStorekeepers = new Storekeeper();
					Boolean NameExistNotExist = false;
					String newName = usernameField.getText();
					ArrayList<String> listDataRead = new ArrayList<String>();
										
					try{
						
						listDataRead = existingStorekeepers.readStorekeepersFile();
					
					}catch (IOException e) {
				    	
				        e.printStackTrace();
				    }
					
					for (String name : listDataRead) {
	          	    	
				        if(name.contains(newName)){
				        	  
				        	/* This user already exists! */
				        	NameExistNotExist = true;
				        	
				        	break;
				        	
				        }else{
				        	/* This user doesn't exist! */
				        	NameExistNotExist = false;
				        }
					}
					
				    if(!NameExistNotExist){			
					    /* Update with new user only if the name doesn't exist. */
						if(storekeeper.updateStoreKeeperInfo()){
						
							usernameField.setText("");/* Clear */
							passwordField.setText("");/* Clear */
							passwordFieldRetype.setText("");/* Clear */
							dispose();
														
							try {
								
								registrationstatusdialog = new RegistrationStatus("Registration successful!");
								registrationstatusdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);								
								registrationstatusdialog.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}							
						
						}else{
														
							try {
								
								registrationstatusdialog = new RegistrationStatus("Registration unsuccessful!");
								registrationstatusdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);								
								registrationstatusdialog.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
														
						}
						
				    }else{
				    	
				    	/* Alert if the name already exists! */
				    	try {
							
							alertdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							alertdialog.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}				    	
				    }				
					
				}else if(rdbtnRemove.isSelected()){
					
					/*  Perform remove action here. */					
					if(storekeeper.removeStoreKeeper(usernameField.getText())){	
					
						usernameField.setText("");/* Clear */
						dispose();
						
						try {
							
							registrationstatusdialog = new RegistrationStatus("User removed successfully!");
							registrationstatusdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);								
							registrationstatusdialog.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}else{
						
						/*   Alert if the name doesn't exists! */
						try {

							userstatusmessagedialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							userstatusmessagedialog.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}							
					}
												
				}else{
					
					/* Alert if nothing is selected in radioButtons! */
					try {

						selectionalertdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						selectionalertdialog.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}					
				}
			}
		});
		
		btnConfirm.addKeyListener(new MyKeyListener(){
			
			/* Override keyPressed() method of MyKeyListener class .*/
			public void keyPressed(KeyEvent evt)
			{
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					
					Storekeeper storekeeper = new Storekeeper();										
					storekeeper.setUsernameAndPassword(usernameField.getText() ,String.valueOf(passwordField.getPassword()));
					
					if(rdbtnAdd.isSelected()){
						
						Storekeeper existingStorekeepers = new Storekeeper();
						Boolean NameExistNotExist = false;
						String newName = usernameField.getText();
						ArrayList<String> listDataRead = new ArrayList<String>();
											
						try{
							
							listDataRead = existingStorekeepers.readStorekeepersFile();
						
						}catch (IOException e) {
					    	
					        e.printStackTrace();
					    }
						
						for (String name : listDataRead) {
		          	    	
					        if(name.contains(newName)){
					        	  
					        	/* This user already exists! */
					        	NameExistNotExist = true;
					        	
					        	break;/* This is important !*/
					        	
					        }else{
					        	/* This user doesn't exist! */
					        	NameExistNotExist = false;
					        }
						}
						
					    if(!NameExistNotExist){			
						
							if(storekeeper.updateStoreKeeperInfo()){
							
								usernameField.setText("");/* Clear */
								passwordField.setText("");/* Clear */
								passwordFieldRetype.setText("");/* Clear */
								dispose();
								
								try {
									
									registrationstatusdialog = new RegistrationStatus("Registration successful!");
									registrationstatusdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);								
									registrationstatusdialog.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							
							}else{
								
								try {
									
									registrationstatusdialog = new RegistrationStatus("Registration unsuccessful!");
									registrationstatusdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);								
									registrationstatusdialog.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
								
							}
							
					    }else{
					    	
					    	/* Alert if nothing is selected in radioButtons! */
					    	try {
								
								alertdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								alertdialog.setEnabled(true);
								alertdialog.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}				    	
					    }				
						
					}else if(rdbtnRemove.isSelected()){
						
						/*  Perform remove action here. */					
						if(storekeeper.removeStoreKeeper(usernameField.getText())){	
						
							usernameField.setText("");/* Clear */
							dispose();
							
							try {
								
								registrationstatusdialog = new RegistrationStatus("User removed successfully!");
								registrationstatusdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);								
								registrationstatusdialog.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
							
						}else{
							
							/*   Alert if the name doesn't exists! */
							try {
								
								userstatusmessagedialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								userstatusmessagedialog.setEnabled(true);
								userstatusmessagedialog.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}							
						}
													
					}else{
						
						/* Alert if nothing is selected! */
						try {
							
							selectionalertdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							selectionalertdialog.setEnabled(true);
							selectionalertdialog.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}					
					}					
				}
			}
		});
				
	}
}
