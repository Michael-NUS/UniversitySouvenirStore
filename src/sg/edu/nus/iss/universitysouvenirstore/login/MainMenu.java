/**
 * File name : MainMenu.java
 * 
 * Description : JFrame GUI class for handling MainMenu after login
 * 
 * @author : NayLA 
 * 
 * Date :10/03/2016
 * 
 */

package sg.edu.nus.iss.universitysouvenirstore.login;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.universitysouvenirstore.gui.CategoryManagerDialog;
import sg.edu.nus.iss.universitysouvenirstore.gui.DiscountDialog;
import sg.edu.nus.iss.universitysouvenirstore.gui.MemberManagerDialog;
import sg.edu.nus.iss.universitysouvenirstore.gui.ProductManagerDialog;
import sg.edu.nus.iss.universitysouvenirstore.gui.ReportDialog;
import sg.edu.nus.iss.universitysouvenirstore.gui.TransactionDialog;
import sg.edu.nus.iss.universitysouvenirstore.gui.UtilityManagerDialog;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
//import java.awt.Window;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	
	StoreKeeperInfoUpdate storekeeperframe = new StoreKeeperInfoUpdate();//Added for test only
	ProductManagerDialog productdialog = new ProductManagerDialog();
	CategoryManagerDialog categorydialog = new CategoryManagerDialog();
	TransactionDialog transactiondialog = new TransactionDialog();
	ReportDialog reportdialog = new ReportDialog();
	DiscountDialog discountdialog = new DiscountDialog();
	MemberManagerDialog memberdialog = new MemberManagerDialog();
	UtilityManagerDialog utilitydialog = new UtilityManagerDialog();
	
	
	public void AllSubWindowsDisabled(){
		
		storekeeperframe.setEnabled(false);
		storekeeperframe.setVisible(false);
		
		productdialog.setEnabled(false);
		productdialog.setVisible(false);
		
		categorydialog.setEnabled(false);
		categorydialog.setVisible(false);
		
		transactiondialog.setEnabled(false);
		transactiondialog.setVisible(false);
		
		reportdialog.setEnabled(false);
		reportdialog.setVisible(false);
		
		discountdialog.setEnabled(false);
		discountdialog.setVisible(false);
		
		memberdialog.setEnabled(false);
		memberdialog.setVisible(false);
		
		utilitydialog.setEnabled(false);
		utilitydialog.setVisible(false);
		
	}
	
	public void AllSubWindowsDestroyed(){
		
		storekeeperframe.dispose();
		productdialog.dispose();
		categorydialog.dispose();
		transactiondialog.dispose();
		reportdialog.dispose();
		discountdialog.dispose();
		memberdialog.dispose();
		utilitydialog.dispose();
		
		storekeeperframe.alertdialog.dispose();
		storekeeperframe.userstatusmessagedialog.dispose();
		storekeeperframe.selectionalertdialog.dispose();	
		storekeeperframe.registrationstatusdialog.dispose();
	}
	
	
	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setTitle("Main menu");										      					
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 495, 467);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(244, 164, 96));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		/****************************/
		AllSubWindowsDisabled();
		/****************************/
		
		JButton btnStorekeeperRegistration = new JButton("Add/Remove storekeeper");
		btnStorekeeperRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
							
				try {
					
					setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					storekeeperframe.setEnabled(true);
					storekeeperframe.setVisible(true);
				} catch (Exception rege) {
					rege.printStackTrace();
				}
			}
		});
												
		btnStorekeeperRegistration.addKeyListener(new MyKeyListener(){
			
			/* Override keyPressed() method of MyKeyListener class .*/
			public void keyPressed(KeyEvent evt)
			{
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					
					try {
						
						setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						storekeeperframe.setEnabled(true);
						storekeeperframe.setVisible(true);
					} catch (Exception rege) {
						rege.printStackTrace();
					}
					
				}
			}
		});
		btnStorekeeperRegistration.setBounds(265, 280, 188, 51);
		contentPane.add(btnStorekeeperRegistration);
		
		JLabel lblRegistration = new JLabel("             Registrations");
		lblRegistration.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRegistration.setForeground(new Color(0, 0, 139));
		lblRegistration.setBounds(265, 11, 156, 23);
		contentPane.add(lblRegistration);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					ApplicationLogin frameLogin = new ApplicationLogin();
					frameLogin.setTitle("Univeristy Souvenir Store");
					frameLogin.setVisible(true);	
					
					dispose();/* Destroy current frame where the button exits.*/
									
					AllSubWindowsDestroyed();/* Destory all sub-windows after logging out. */
					
				} catch (Exception logine) {
					logine.printStackTrace();
				}
								
			}
		});
		
		btnLogout.addKeyListener(new MyKeyListener(){
			
			/* Override keyPressed() method of MyKeyListener class .*/
			public void keyPressed(KeyEvent evt)
			{
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					
					try {
						
						ApplicationLogin frameLogin = new ApplicationLogin();
						frameLogin.setTitle("Univeristy Souvenir Store");
						frameLogin.setVisible(true);	
						
						dispose();/* Destroy current frame where the button exits.*/
						
						AllSubWindowsDestroyed();/* Destroy all sub-windows after logging out. */
																					
					} catch (Exception logine) {
						logine.printStackTrace();
					}
					
				}
			}
		});
		btnLogout.setToolTipText("Press here to log out!");
		btnLogout.setBounds(151, 354, 195, 51);
		contentPane.add(btnLogout);
		
		JButton btnAddRemoveProduct = new JButton("Add/Remove product");
		btnAddRemoveProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					productdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					productdialog.setEnabled(true);
					productdialog.setVisible(true);
				} catch (Exception Pde) {
					Pde.printStackTrace();
				}		
			}
		});
		
		btnAddRemoveProduct.addKeyListener(new MyKeyListener(){
			
			/* Override keyPressed() method of MyKeyListener class .*/
			public void keyPressed(KeyEvent evt)
			{
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					
					try {
						
						productdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						productdialog.setEnabled(true);
						productdialog.setVisible(true);
					} catch (Exception Pde) {
						Pde.printStackTrace();
					}
					
				}
			}
		});
		btnAddRemoveProduct.setBounds(265, 114, 188, 51);
		contentPane.add(btnAddRemoveProduct);
		
		JButton btnAddRemoveCategory = new JButton("Add/Remove category");
		btnAddRemoveCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					categorydialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					categorydialog.setEnabled(true);
					categorydialog.setVisible(true);
				} catch (Exception Cate) {
					Cate.printStackTrace();
				}
				
			}
		});
		
		btnAddRemoveCategory.addKeyListener(new MyKeyListener(){
			
			/* Override keyPressed() method of MyKeyListener class .*/
			public void keyPressed(KeyEvent evt)
			{
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					
					try {
						
						categorydialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						categorydialog.setEnabled(true);
						categorydialog.setVisible(true);
					} catch (Exception Cate) {
						Cate.printStackTrace();
					}
					
				}
			}
		});
		btnAddRemoveCategory.setBounds(265, 197, 188, 54);
		contentPane.add(btnAddRemoveCategory);
		
		JButton btnTransaction = new JButton("New transaction");
		btnTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					transactiondialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					transactiondialog.setEnabled(true);
					transactiondialog.setVisible(true);
				} catch (Exception Transe) {
					Transe.printStackTrace();
				}
			}
		});
		
		btnTransaction.addKeyListener(new MyKeyListener(){
			
			/* Override keyPressed() method of MyKeyListener class .*/
			public void keyPressed(KeyEvent evt)
			{
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					
					try {
						
						transactiondialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						transactiondialog.setEnabled(true);
						transactiondialog.setVisible(true);
					} catch (Exception Transe) {
						Transe.printStackTrace();
					}
					
				}
			}
		});
		btnTransaction.setBounds(36, 36, 195, 51);
		contentPane.add(btnTransaction);
		
		JButton btnReport = new JButton("Generate report");
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					reportdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					reportdialog.setEnabled(true);
					reportdialog.setVisible(true);
				} catch (Exception Repe) {
					Repe.printStackTrace();
				}
				
			}
		});
		
		btnReport.addKeyListener(new MyKeyListener(){
			
			/* Override keyPressed() method of MyKeyListener class .*/
			public void keyPressed(KeyEvent evt)
			{
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					
					try {
						
						reportdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						reportdialog.setEnabled(true);
						reportdialog.setVisible(true);
					} catch (Exception Repe) {
						Repe.printStackTrace();
					}
					
				}
			}
		});
		btnReport.setBounds(36, 197, 195, 54);
		contentPane.add(btnReport);
		
		JButton btnDiscount = new JButton("Manage discounts");
		btnDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					discountdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					discountdialog.setEnabled(true);
					discountdialog.setVisible(true);
				} catch (Exception Disce) {
					Disce.printStackTrace();
				}
			}
		});
		
		btnDiscount.addKeyListener(new MyKeyListener(){
			
			/* Override keyPressed() method of MyKeyListener class .*/
			public void keyPressed(KeyEvent evt)
			{
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					
					try {
						
						discountdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						discountdialog.setEnabled(true);
						discountdialog.setVisible(true);
					} catch (Exception Disce) {
						Disce.printStackTrace();
					}
					
				}
			}
		});
		
		btnDiscount.setBounds(36, 114, 195, 51);
		contentPane.add(btnDiscount);
		
		JButton btnAddRemoveMember = new JButton("Add/Remove member");
		btnAddRemoveMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					memberdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					memberdialog.setEnabled(true);
					memberdialog.setVisible(true);
				} catch (Exception Membe) {
					Membe.printStackTrace();
				}
				
			}
		});
		
		btnAddRemoveMember.addKeyListener(new MyKeyListener(){
			
			/* Override keyPressed() method of MyKeyListener class .*/
			public void keyPressed(KeyEvent evt)
			{
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					
					try {
						
						memberdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						memberdialog.setEnabled(true);
						memberdialog.setVisible(true);
					} catch (Exception Membe) {
						Membe.printStackTrace();
					}
					
				}
			}
		});
		btnAddRemoveMember.setBounds(265, 36, 188, 51);
		contentPane.add(btnAddRemoveMember);
		
		JButton btnUtility = new JButton("Utility");
		btnUtility.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					utilitydialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					utilitydialog.setEnabled(true);
					utilitydialog.setVisible(true);
				} catch (Exception Utile) {
					Utile.printStackTrace();
				}
			}
		});
		
		btnUtility.addKeyListener(new MyKeyListener(){
			
			/* Override keyPressed() method of MyKeyListener class .*/
			public void keyPressed(KeyEvent evt)
			{
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					
					try {
						
						utilitydialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						utilitydialog.setEnabled(true);
						utilitydialog.setVisible(true);
					} catch (Exception Utile) {
						Utile.printStackTrace();
					}
					
				}
			}
		});
		btnUtility.setBounds(36, 280, 195, 51);
		contentPane.add(btnUtility);
		
		JLabel lblNewLabel = new JLabel("Functions");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setBounds(93, 11, 70, 19);
		contentPane.add(lblNewLabel);
	}
}
