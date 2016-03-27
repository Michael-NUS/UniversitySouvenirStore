/**
 * File name : ApplicationLogin.java
 * 
 * Description : JFrame GUI class for Login
 *               This is also start of the application and related to architectural design of the system.          
 * 
 * @author : NayLA 
 * 
 * Date : 08/03/2016
 * 
 */
package sg.edu.nus.iss.universitysouvenirstore.login;

import sg.edu.nus.iss.universitysouvenirstore.Storekeeper;

/**
 * @author NayLA
 *
 */
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JMenuBar;
import java.util.Date;

public class ApplicationLogin extends JFrame {

	private JTextField textFieldUserName;
	private JPasswordField passwordField;
	
	MainMenu frameMainMenu = new MainMenu();
	Storekeeper currentstorekeeper = new Storekeeper();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
			
		EventQueue.invokeLater(new Runnable() {
				
			public void run() {
				try {
							
					ApplicationLogin frameLogin = new ApplicationLogin();
					frameLogin.setTitle("Univeristy Souvenir Store");
					frameLogin.setVisible(true);									
																					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 */
	public ApplicationLogin() {
		
		setResizable(false);						
		LoginScreen();					
	}
		
	public  void LoginScreen() {
		
		ContentPanel contentPanel = new ContentPanel();
		contentPanel.setBackground(new Color(244, 164, 96));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
			
		Date now = new Date();
				
		JButton btnSystemAdmin = new JButton("System Administration");
		btnSystemAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/* This is dummy for now. */
			}
		});
		menuBar.add(btnSystemAdmin);
		
		JLabel lblCalender = new JLabel();
		menuBar.add(lblCalender);
		lblCalender.setText(now.toString());		
		
		textFieldUserName = new JTextField();
		textFieldUserName.setBounds(83, 208, 173, 23);
		contentPanel.add(textFieldUserName);
		textFieldUserName.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(83, 254, 173, 23);
		contentPanel.add(passwordField);
		
		JButton btnOK = new JButton("OK");
					
		btnOK.setBounds(124, 298, 89, 23);
		contentPanel.add(btnOK);
		
		JLabel lblUserName = new JLabel("User name");
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUserName.setForeground(new Color(0, 0, 139));
		lblUserName.setBounds(10, 211, 69, 14);
		contentPanel.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setForeground(new Color(0, 0, 128));
		lblPassword.setBounds(10, 257, 69, 14);
		contentPanel.add(lblPassword);
	    setSize(380, 324);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 120, 340, 400);
					
		
		btnOK.addKeyListener(new MyKeyListener(){
			
			/* Override keyPressed() method of MyKeyListener class .*/
			public void keyPressed(KeyEvent evt)
			{
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					
					String textUserName = textFieldUserName.getText();/* Get input from textField.*/
					String textPassword = String.valueOf(passwordField.getPassword());/* Get input from passwordField.*/	
															
					try{
											
						if(currentstorekeeper.checkUserInfo(textUserName,textPassword))
						{				
							/* Open Dialog Box if Username and Password is correct.*/							
							try {
								frameMainMenu.setTitle("MainMenu Screen : " + "Logged in as "+ textUserName);
								frameMainMenu.setVisible(true);
															
								dispose();/* Destroy current frame where the button exits.*/
								
							} catch (Exception homee) {
								homee.printStackTrace();
							}						
							
						}
						else{
							
							try {
								UnauthorizedLoginAlert frameAlert = new UnauthorizedLoginAlert();
								frameAlert.setTitle("Login check");
								frameAlert.setVisible(true);
							} catch (Exception logine) {
								logine.printStackTrace();
							}
							
						}
					
					}catch(IOException fioe){
						
						fioe.printStackTrace();
					}
					
				}
			}
		});
		
		btnOK.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				
				String textUserName = textFieldUserName.getText();/* Get input from textField.*/
				String textPassword = String.valueOf(passwordField.getPassword());/* Get input from passwordField.*/
								
				try{
				
					if(currentstorekeeper.checkUserInfo(textUserName,textPassword))
					{				
						/* Open Dialog Box if Username and Password is correct.*/						
						try {
							MainMenu frameMain = new MainMenu();
							frameMain.setTitle("MainMenu");
							frameMain.setVisible(true);
							
							dispose();/* Destroy current frame where the button exits.*/
							
						} catch (Exception homee) {
							homee.printStackTrace();
						}																
					}
					else{
											
						try {
							UnauthorizedLoginAlert frameAlert = new UnauthorizedLoginAlert();
							frameAlert.setTitle("Login check");
							frameAlert.setVisible(true);
						} catch (Exception logine) {
							logine.printStackTrace();
						}
						
					}
				
				}catch(IOException fioe){
					
					fioe.printStackTrace();
				}					
							
			}
		});
		
	}
}

/* Outer class */
class ContentPanel extends JPanel {
	  Image bgimage = null;  
	  Image logoimage = null;

	  ContentPanel() {
	    MediaTracker mt = new MediaTracker(this);	   
	    logoimage = Toolkit.getDefaultToolkit().getImage("./src/sg/edu/nus/iss/universitysouvenirstore/images/NUS_ISS.png");
	    bgimage = Toolkit.getDefaultToolkit().getImage("./src/sg/edu/nus/iss/universitysouvenirstore/images/StoreLogo.png");
	    
	    mt.addImage(bgimage, 0);	    
	    mt.addImage(logoimage, 1);
	    
	    try {
	      mt.waitForAll();
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	  }

	  protected void paintComponent(Graphics g) {
		  
	    super.paintComponent(g);    	    
	    g.drawImage(logoimage, 10, 1, null);
	    g.drawImage(bgimage, 15, 110, null);
	    	    
	  }
	}