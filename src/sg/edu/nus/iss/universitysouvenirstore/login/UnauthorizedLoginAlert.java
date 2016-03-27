/**
 * File name : UnauthorizedLoginAlert.java
 * 
 * Description : JFrame GUI class for displaying alert message if login fails.
 * 
 * @author : NayLA 
 * 
 * Date :09/03/2016
 * 
 */

package sg.edu.nus.iss.universitysouvenirstore.login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

public class UnauthorizedLoginAlert extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnauthorizedLoginAlert frame = new UnauthorizedLoginAlert();
					frame.setTitle("Login check");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UnauthorizedLoginAlert() {
		getContentPane().setBackground(new Color(244, 164, 96));
		setBounds(200, 200, 269, 110);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Unauthorized access!");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setBounds(57, 29, 161, 14);
		getContentPane().add(lblNewLabel);

	}
}
